import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;

public class XMLParser {
    public void parseXML(HashMap<String, BigDecimal> currencies) {
        Logger log = LoggerFactory.getLogger("ParserLogger");
        try {
            File xmlFile = new File("eurofxref-daily.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = factory.newDocumentBuilder();
            Document doc = docBuilder.parse(xmlFile);

            NodeList nodeList = doc.getElementsByTagName("Cube");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;
                    if(element.getAttribute("currency") != "")
                        currencies.put(element.getAttribute("currency"), new BigDecimal(element.getAttribute("rate")));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            log.error("An exception occurred.", e);
        }

    }

}
