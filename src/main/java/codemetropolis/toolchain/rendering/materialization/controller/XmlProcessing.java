package codemetropolis.toolchain.rendering.materialization.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import codemetropolis.toolchain.rendering.materialization.building.Building;
import codemetropolis.toolchain.rendering.materialization.building.point.Size;

public class XmlProcessing {
	private List<Building> buildings;
	private String xmlFile;
	
	public XmlProcessing(String xmlFile) {		
		this.xmlFile = xmlFile;
	}
	public void setXmlFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}
	
	public boolean xmlProcessingAndBuildingsGeneration(){
	    try {
	    	buildings=new ArrayList<Building>();;
			File fXmlFile = new File(xmlFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			//NodeList nList = doc.getElementsByTagName("buildable");
			NodeList nList = doc.getElementsByTagName("size");
			//nList = doc.getElementsByTagName("size");

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				
				//System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					Building building = new Building();
					
					if (nNode.getParentNode().getNodeName() != "buildables") {
						Element parent = (Element) nNode.getParentNode().getParentNode();
						building.setParent_id(parent.getAttribute("id"));
						building.setParent_name(parent.getAttribute("name"));			
					}
					
					building.setId(eElement.getAttribute("id"));
					building.setName(eElement.getAttribute("name"));
					building.setType(eElement.getAttribute("type"));
					
					if(nNode.getNodeName() == "size"){
						
						building.getSize().setX(Integer.parseInt((eElement.getAttribute("x")))); 
						building.getSize().setY(Integer.parseInt((eElement.getAttribute("y")))); 
						building.getSize().setZ(Integer.parseInt((eElement.getAttribute("z")))); 
					}
					if(nNode.getNodeName() == "position"){
						
						building.getLocation().setX(Integer.parseInt((eElement.getAttribute("x")))); 
						building.getLocation().setY(Integer.parseInt((eElement.getAttribute("y")))); 
						building.getLocation().setZ(Integer.parseInt((eElement.getAttribute("z")))); 
					
					}
					
					System.out.println(building.toString());
					
					buildings.add(building);
				
					}
				
				}
		    } catch (Exception e) {
		    	System.out.println("SAX Exception: "+e.getMessage());
		    	//e.printStackTrace();
		    	return false;
		    }		
		return true;		
	}

}
