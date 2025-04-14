package lab_3_Ex2;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JDom {
    private static final String FILE_PATH = "cars.xml";
    public static void main(String[] args){
        query("002");
        //create();
    }

    private static void query(String modelID){
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(new File(FILE_PATH));
            Element root = document.getRootElement();
            for (Element brand : root.getChildren("brand")) {
                for (Element model : brand.getChildren("model")) {
                    if (modelID.equals(model.getAttributeValue("modelId"))) {
                        System.out.println("Found model: " +
                                brand.getAttributeValue("name") +
                                ". with modelId = " + modelID);
                        for (Element child : model.getChildren()) {
                            if(child.getChildren().isEmpty()){
                                System.out.println(child.getName() + ": " + child.getText());
                            }else if(child.getName().equals("features")){
                                System.out.println(child.getName() + ":");
                                for (Element feature: child.getChildren()) {
                                    System.out.println("\t" + feature.getName() + ": " + feature.getText());
                                }
                            }
                        }
                    }
                }
            }
        } catch (JDOMException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void create(){
        try {
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(new File(FILE_PATH));
            Element root = document.getRootElement();

            Element brand = new Element("brand");
            brand.setAttribute("name", "Subaru");
            brand.setAttribute("brandId", "3");

            Element model = new Element("model");
            model.setAttribute("modelId", "005");

            model.addContent(new Element("name").setText("Levorg"));
            model.addContent(new Element("engine").setText("2.4L Turbo"));
            model.addContent(new Element("year").setText("2023"));
            model.addContent(new Element("color").setText("Blue"));

            Element features = new Element("features");
            features.addContent(new Element("feature").setText("Leather seats"));
            features.addContent(new Element("feature").setText("keyless entry"));
            features.addContent(new Element("feature").setText("Full-Wheel-Drive"));

            model.addContent(features);
            model.addContent(new Element("price").setText("35000"));

            brand.addContent(model);
            root.addContent(brand);

            try(FileWriter writer = new FileWriter(FILE_PATH);){
                XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
                xmlOutputter.output(document, writer);
                System.out.println("New brand tag with model tag added in XML");
            }
        } catch (IOException | JDOMException e) {
            throw new RuntimeException(e);
        }
    }
}
