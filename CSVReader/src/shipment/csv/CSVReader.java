package shipment.csv;

import shipment.api.Shipment;
import shipment.api.ShipmentReader;

import java.util.List;

public class CSVReader implements ShipmentReader {

    @Override
    public List<Shipment> readFile(String filepath) {
        return null;
    }

    @Override
    public String getFileType() {
        return null;
    }
}
