package doctrina;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public abstract class Manager implements XmlReader {

    protected NodeList objects;
    public abstract String getFileName();
    public abstract String getNameAttribute();
    public abstract void instanceObject(NodeList nodeList);
    public abstract void update();
    public abstract void draw(Canvas canvas, Camera camera);

    public Manager() {
        this.readXmlFile();
        instanceObject(objects);
    }

    private void readXmlFile() {
        try {
            File file = new File(getFileName());
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("objectgroup");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (element.getAttribute("name").equals(getNameAttribute())) {
                        objects = element.getElementsByTagName("object");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
