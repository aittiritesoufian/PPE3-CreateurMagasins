package analyse2;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import modele.Utilisateur;

public class AnalyseUtilisateur implements ContentHandler {
	
	private String pcdata;
	private String attribut;
	private String balise;
	private Utilisateur user;
	private List<Utilisateur> lesUsers;
	

	public AnalyseUtilisateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setDocumentLocator(Locator locator) {
		// TODO Auto-generated method stub

	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("DEBUT DOCUMENT");

	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("FIN DOCUMENT");

	}

	@Override
	public void startPrefixMapping(String prefix, String uri) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void endPrefixMapping(String prefix) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		System.out.println(qName);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println(new String(ch,start,length));

	}

	@Override
	public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		// TODO Auto-generated method stub

	}

	@Override
	public void skippedEntity(String name) throws SAXException {
		// TODO Auto-generated method stub

	}

}
