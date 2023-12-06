package doctrina;

import org.w3c.dom.NodeList;

public interface XmlReader {
    String getFileName();
    String getNameAttribute();
    void instanceObject(NodeList nodeList);
}
