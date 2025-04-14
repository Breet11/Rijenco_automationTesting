package lab_3_Ex2;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

public class XPathParser {
    private static final String FILE_PATH = "cars.xml";
    public static void main(String[] args){
        String tagToCheck = "model";
        String tagWithChildren = "features";

        System.out.println("Is tag '" + tagToCheck + "' Present ? " + isPresent(tagToCheck));
        System.out.println("Does tag '" + tagWithChildren+ "' has children? " + hasChildren(tagWithChildren));
    }

    private static boolean isPresent(String tagName){
        try{
            File inputFile = new File(FILE_PATH);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document document = dBuilder.parse(inputFile);
            document.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "//" + tagName;
            NodeList nodeList = (NodeList) xPath.evaluate(expression, document, XPathConstants.NODESET);
            return nodeList.getLength() > 0;
        } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean hasChildren(String tagName){
        try{
            File inputFile = new File(FILE_PATH);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document document = dBuilder.parse(inputFile);
            document.getDocumentElement().normalize();

            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "//" + tagName;
            NodeList nodeList = (NodeList) xPath.evaluate(expression, document, XPathConstants.NODESET);

            for(int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if(node.hasChildNodes()){
                    NodeList children = node.getChildNodes();
                    for(int j=0; j<children.getLength(); j++){
                        if(children.item(j).getNodeType() == Node.ELEMENT_NODE){
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }
}
