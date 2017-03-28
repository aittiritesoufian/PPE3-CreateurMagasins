package service;



import java.io.IOException;
import java.io.StringReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import analyse.ExtracteurConstructeur;
import outils.Resultat;

@Path("xml")
public class ServiceREST {	
	
	@POST
	@Consumes("text/plain")//méthode de réception des donnés HTTP POST
	public void postXML(String xml) throws ParserConfigurationException, SAXException, IOException {
		System.out.println(xml);//affichage du contenu reçu	
		creer(xml);//exécution de la méthode POST avec le contenu reçu
	}
	
	private boolean creer(String xml) throws ParserConfigurationException, SAXException, IOException {
		boolean statut = false;//
		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
		SAXParser parser = parserFactor.newSAXParser();
		Resultat resultat = new Resultat();//instanciation de la variable resultat
		ExtracteurConstructeur ec = new ExtracteurConstructeur(resultat);//insertion de l'objet resultat dans l'extracteur pour pouvoir récupérer ensuite la liste des employés à faire persister
		parser.parse(new InputSource(new StringReader(xml)), ec);//lecture du contenu XML à partir des données reçus en chaînes de caractères grâce à l'objet ec (class ExtracteurConstructeur)
		resultat.enregistrer();//persistence de tous les utilisateurs lus dans le contenu XML
		statut= true;//changement du statut à true permettant de confirmer la bonne exécution de la méthode
		return statut;
	}	
}
