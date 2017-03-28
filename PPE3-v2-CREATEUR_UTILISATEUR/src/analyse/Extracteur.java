package analyse;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Extracteur extends DefaultHandler {

	private Boolean sortie;

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Début de l'extraction");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("Fin de l'extraction");
	}	

	@Override
	public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
		sortie = false;
		// System.out.println("Entrée dans l'élément: "+qName);
		if(qName.equals("employee")) {
			System.out.println("=======================================================");
			System.out.println("ID: " + atts.getValue("ID"));
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		sortie = true;
		// System.out.println("Sortie de l'élément: "+qName);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		if(sortie.equals(true) || new String(ch,start,length).trim().isEmpty())
			return;
		System.out.println("valeur PCDATA:");
		System.out.println(new String(ch, start, length));
	}
}
