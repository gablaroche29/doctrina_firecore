import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class XmlFileReader {
    public static void main(String[] args) {
        try {

            File file = new File("resources/xml/file.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            System.out.println("Root element: " + document.getDocumentElement().getNodeName());
            NodeList nodeList = document.getElementsByTagName("staff");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("\nNode Name :" + node.getNodeName());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.println("First Name: "+ eElement.getElementsByTagName("firstname").item(0).getTextContent());
                    System.out.println("Last Name: "+ eElement.getElementsByTagName("lastname").item(0).getTextContent());
                    System.out.println("Subject: "+ eElement.getElementsByTagName("nickname").item(0).getTextContent());
                    System.out.println("Marks: "+ eElement.getElementsByTagName("salary").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
