package doctrina;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public interface XmlFileReaderTest {

    String getFileName();
    String getNameAttribute();
    void instanceObject(String[] tiles);

    default void readXmlFile() {
        try {
            File file = new File(getFileName());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("layer");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute("name").equals(getNameAttribute())) {
                        Element dataElement = (Element) element.getElementsByTagName("data").item(0);
                        String dataContent = dataElement.getTextContent();
                        String[] tiles = dataContent.split(",");
                        instanceObject(tiles);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
