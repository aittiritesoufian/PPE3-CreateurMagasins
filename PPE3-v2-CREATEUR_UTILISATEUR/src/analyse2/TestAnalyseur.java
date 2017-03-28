package analyse2;

import java.io.FileReader;
import java.io.IOException;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class TestAnalyseur {

	public static void main(String[] args) throws SAXException, IOException{
		XMLReader parseur = XMLReaderFactory.createXMLReader();
		parseur.setContentHandler(new AnalyseUtilisateur());
		FileReader file = new FileReader("XML_DTD/utilisateurs.xml");
		parseur.parse(new InputSource(file));
	}
}
