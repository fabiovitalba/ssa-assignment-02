package shipment.xml;

import shipment.api.FileType;
import shipment.api.Shipment;
import shipment.api.ShipmentReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@FileType(fileExtension = "xml")
public class XMLReader implements ShipmentReader {
    @Override
    public List<Shipment> readFile(String filepath) {
        List<Shipment> shipments = new ArrayList<>();

        try {
            File xmlFile = new File(filepath);
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            shipments.addAll(createShipmentListFromXmlDocument(document));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return shipments;
    }

    private List<Shipment> createShipmentListFromXmlDocument(Document document) {
        List<Shipment> shipments = new ArrayList<>();

        NodeList nodeList = document.getElementsByTagName("shipment");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Shipment shipment = newShipmentFromXmlNode(node);
            if (shipment != null)
                shipments.add(shipment);
        }

        return shipments;
    }

    private Shipment newShipmentFromXmlNode(Node xmlNode) {
        if (xmlNode.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) xmlNode;
            String productName = element.getAttribute("product");
            String productType = element.getAttribute("type");
            int amount = Integer.parseInt(element.getAttribute("amount"));
            double individualPrice = Double.parseDouble(element.getAttribute("individualprice"));
            double shipmentPrice = Double.parseDouble(element.getAttribute("shipmentPrice"));
            String country = element.getAttribute("country");
            return new Shipment(productName, productType, amount, individualPrice, shipmentPrice, country);
        }
        return null;
    }
}
