package shipment.main.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import shipment.main.ShipmentFileProcessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ShipmentFileProcessorTest {
    private static String csvFile1 = "shipments.csv";
    private static String xmlFile1 = "shipments.xml";
    private static String xmlFile2 = "emptyShipments.xml";
    private static String txtFile1 = "shipments.txt";

    @BeforeClass
    public static void createFilesToTest() throws IOException {
        File csvShipments1 = new File(csvFile1);
        csvShipments1.createNewFile();

        FileWriter fw = new FileWriter(csvFile1);
        fw.write("coffee;FOOD;1000;5;500;Brazil;");
        fw.write("cellphone;ELETRONICS;70;800;1500;China;");
        fw.write("board game;TOY;2000;100;150;Italy;");
        fw.close();

        File xmlShipments1 = new File(xmlFile1);
        xmlShipments1.createNewFile();
        fw = new FileWriter(xmlFile1);
        fw.write("<shipments>");
        fw.write("<shipment product=\"coffee\" type=\"FOOD\" amount=\"1000\" individualprice=\"5\" shipmentPrice=\"500\" country=\"Brazil\" />");
        fw.write("</shipments>");
        fw.close();

        File xmlShipments2 = new File(xmlFile2);
        xmlShipments2.createNewFile();
        fw = new FileWriter(xmlFile2);
        fw.write("<shipments>");
        fw.write("</shipments>");
        fw.close();

        File txtShipments1 = new File(txtFile1);
        txtShipments1.createNewFile();
        fw = new FileWriter(txtFile1);
        fw.write("coffee;FOOD;1000;5;500;Brazil;");
        fw.write("cellphone;ELETRONICS;70;800;1500;China;");
        fw.write("board game;TOY;3000;100;2000;China;");
        fw.close();
    }

    @AfterClass
    public static void deleteFilesToTest() {
        File csvShipments1 = new File(csvFile1);
        csvShipments1.delete();

        File xmlShipments1 = new File(xmlFile1);
        xmlShipments1.delete();

        File xmlShipments2 = new File(xmlFile2);
        xmlShipments2.delete();

        File txtShipments1 = new File(txtFile1);
        txtShipments1.delete();
    }

    @Test
    public void calcCsvExample() {
        double expectedTaxAmountOwed = 1.0;
        double taxAmountOwed = ShipmentFileProcessor.calculate(csvFile1);
        assertEquals(expectedTaxAmountOwed,taxAmountOwed,0.001);
    }

    @Test
    public void calcXmlExample() {
        double expectedTaxAmountOwed = 2200.0;
        double taxAmountOwed = ShipmentFileProcessor.calculate(xmlFile1);
        assertEquals(expectedTaxAmountOwed,taxAmountOwed,0.001);
    }

    @Test
    public void calcEmptyXmlExample() {
        double expectedTaxAmountOwed = 0.0;
        double taxAmountOwed = ShipmentFileProcessor.calculate(xmlFile2);
        assertEquals(expectedTaxAmountOwed,taxAmountOwed,0.001);
    }

    @Test
    public void calcTxtExample() {
        assertThrows(RuntimeException.class,() -> ShipmentFileProcessor.calculate(txtFile1));
    }
}
