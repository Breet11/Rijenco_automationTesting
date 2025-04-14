package lab_3_Ex2;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class StaAX {
    private static final String FILE_PATH = "cars.xml";
    public static void main(String[] args){
        query("002");
    }

    private static void query(String requestedModelId) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(FILE_PATH));

            boolean insideModel = false;
            String currentBrand = "";

            boolean bName = false;
            boolean bEngine = false;
            boolean bYear = false;
            boolean bColor = false;
            boolean bPrice = false;

            System.out.println("Searching for model: " + requestedModelId);
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String qualifiedName = startElement.getName().getLocalPart();

                        if (qualifiedName.equalsIgnoreCase("brand")) {
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attr = attributes.next();
                                if (attr.getName().getLocalPart().equalsIgnoreCase("name")) {
                                    currentBrand = attr.getValue();
                                }
                            }
                        }
                        if (qualifiedName.equalsIgnoreCase("model")) {
                            Iterator<Attribute> attributes = startElement.getAttributes();
                            while (attributes.hasNext()) {
                                Attribute attr = attributes.next();
                                if (attr.getName().getLocalPart().equalsIgnoreCase("modelId")) {
                                    String modelId = attr.getValue();
                                    if (modelId.equalsIgnoreCase(requestedModelId)) {
                                        System.out.println("Inside brand: " + currentBrand);
                                        System.out.println("Model id: " + modelId);
                                        System.out.println("Start element: model");
                                        insideModel = true;
                                    }
                                }
                            }
                        } else if (insideModel) {
                            switch (qualifiedName) {
                                case "name" :
                                    bName = true;
                                    break;
                                case "engine" :
                                    bEngine = true;
                                    break;
                                case "year" :
                                    bYear = true;
                                    break;
                                case "color" :
                                    bColor = true;
                                    break;
                                case "features" :
                                    System.out.println("\tfeatures: ");
                                    break;
                                case "feature" :
                                    event = eventReader.nextEvent();
                                    if (event.isCharacters()) {
                                        System.out.println("\t\t" + event.asCharacters().getData());
                                    }
                                    break;
                                case "price" :
                                    bPrice = true;
                                    break;
                            }
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        Characters characters = event.asCharacters();
                        String text = characters.getData().trim();
                        if (!text.isEmpty() && insideModel) {
                            if (bName) {
                                System.out.println("\tname: " + text);
                                bName = false;
                            }
                            if (bEngine) {
                                System.out.println("\tengine: " + text);
                                bEngine = false;
                            }
                            if (bYear) {
                                System.out.println("\tyear: " + text);
                                bYear = false;
                            }
                            if (bColor) {
                                System.out.println("\tcolor: " + text);
                                bColor = false;
                            }
                            if (bPrice) {
                                System.out.println("\tprice: " + text);
                                bPrice = false;
                            }
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        EndElement endElement = event.asEndElement();
                        if (endElement.getName().getLocalPart().equalsIgnoreCase("model") && insideModel) {
                            System.out.println("End element: model\n");
                            insideModel = false;
                        }
                        break;
                }
            }
        } catch (IOException | XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }
}
