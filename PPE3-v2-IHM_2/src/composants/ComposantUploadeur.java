package composants;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

@ManagedBean(name="bean")
@RequestScoped
public class ComposantUploadeur {

	private String fileContent;
	private Part file;
	private String resultat = " ";

	public ComposantUploadeur() {
		super();
	}
	
	public String upload() {
		if(file == null)
			return "index.xhtml";
		try {
				//fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
				//System.out.println(fileContent);
				file.write("/tmp/" + file.getSubmittedFileName());//enregistrement du fichier sur le serveur local dans le répertoire /tmp
				byte[] flux = Files.readAllBytes(Paths.get("/tmp/"+file.getSubmittedFileName()));//conversion du fichier xml en bytecode
				boolean statut = transfert(new String(flux, Charset.defaultCharset()));//transfert du fichier convertis en chaîne de caractère vers le serveur CREATEUR UTILISATEUR
				if (statut) resultat = "Le chargement a réussi";//si le transfert c'est correctement effectué le message de résultat sera celui-ci
				else resultat = "échec du transfert du fichier";//sinon ce sera celui-ci
				File lefichier = new File("/tmp/"+file.getSubmittedFileName());//chargement du fichier précédemment stocké localement
				lefichier.delete();//suppression du fichier local
		} catch (IOException e) { e.printStackTrace();
			resultat = "échec du chargement";// message d'erreur en cas d'échec lors du traitement du fichier xml
		}
		return "confirmation.xhtml";// page vers laquelle devra rediriger le serveur après avoir cliquer sur le bouton "Chargement du fichier"
	}
	
	private boolean transfert(String xml) {
		Client client = ClientBuilder.newClient();//création d'un client REST
		WebTarget cible = client.target(UriBuilder.fromPath("http://tomcatcreateurutilisateur:8080/PPE3v2CREATEURUTILISATEUR/"));//connexion au serveur distant
		WebTarget ciblefinale = cible.path("xml");//PATH ciblé dans le serveur
		ciblefinale.request(MediaType.TEXT_PLAIN_TYPE).post(Entity.entity(xml, MediaType.TEXT_PLAIN));//fichier xml envoyer en chaîne de caractère envoyé par méthode HTTP POST
		return true;//renvoi TRUE si aucune erreur n'est sûrvenue dans l'exécution de la méthode
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public String getResultat() {
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}
}