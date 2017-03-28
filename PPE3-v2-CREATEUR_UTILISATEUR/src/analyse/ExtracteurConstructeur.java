package analyse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import modele.Utilisateur;
import outils.Resultat;

public class ExtracteurConstructeur extends DefaultHandler {

	private static List<Utilisateur> employees = new ArrayList<>();
    private static Utilisateur empl = null;
    private static String text = null;
    private Resultat resultat;    
    
    public ExtracteurConstructeur(Resultat resultat) {
		super();
		this.resultat = resultat;
	}

	@Override
    // A start tag is encountered.
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    
    	switch (qName) {
                      //Création d'un utilisateur à chaque ouverture d'un Element nommée "utilisateur" (balise <utilisateur>)
                      case "utilisateur": {
                           empl = new Utilisateur();
                           //empl.setIdutilisateur(Integer.valueOf(attributes.getValue("ID")));
                           break;
                      }
                 }
            }
        
   @Override
   public void endElement(String uri, String localName, String qName) throws SAXException {
   
	   switch (qName) {
                      case "utilisateur": {
                       //ajout de la date de dernière mise à jour, l'utilisateur ayant modifier l'enregistrement et ajout de cet utilisateur à la liste de tous les utilisateurs dès que l'on rencontre la fermeture d'un élément "utilisateur" (balise </utilsateur>)
                    	empl.setDerniere_mise_a_jour(new Date());
                    	empl.setModifie_par("ADMIN");
                        employees.add(empl);
                        break;
                      }
                      case "email": {//ajout de l'email à l'utilisateur actuel dès que l'on rencontre la fermeture d'un élément nommé "email" (balise </email>).
                    	  	//Au moment où l'on rencontre cette balise la variable "text" contient la dernière valeur lue par la méthode "characters", c'est à dire le contenu PCDATA présent entre la balise d'ouverture et de fermeture "email", on obtient ainsi la valeur de l'email de l'utilisateur à enregistrer.
                            empl.setEmail(text);
                            break;
                       }
                      case "nom": {//ajout du nom à l'utilisateur actuel dès que l'on rencontre la fermeture d'un élément nommé "nom" (balise </nom>).
                            empl.setNom(text);
                            break;
                       }
                      case "prenom": {//ajout du prénom à l'utilisateur actuel dès que l'on rencontre la fermeture d'un élément nommé "prenom" (balise </prenom>).
                            empl.setPrenom(text);
                            break;
                       }
                       case "password": {//ajout du mot de passe à l'utilisateur actuel dès que l'on rencontre la fermeture d'un élément nommé "password" (balise </password>).
                            empl.setPassword(text);
                            break;
                       }
                 }
             }
         
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
                  text = String.copyValueOf(ch, start, length).trim();//stockage dans la variable "text" de la valeur PCDATA contenu entre les Elements du fichier XML
    }
    
    public void endDocument() throws SAXException {
		System.out.println("Fin de l'extraction");
		resultat.setEmployees(employees);//fin de la lecture du document, on attribut à l'objet resultat la liste de tous les utilisateurs à faire persister.
	}
	
}
