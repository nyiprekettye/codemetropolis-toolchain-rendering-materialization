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

public class XmlProcessing {

	private List<Building> buildings;
	private String xmlFile;
	
	public XmlProcessing(String xmlFile) {		
		this.xmlFile = xmlFile;
	}
	public void setXmlFile(String xmlFile) {
		this.xmlFile = xmlFile;
	}
	
	public boolean xmlProcessingAndBuildingsGeneration() {
	    try {
	    	buildings = new ArrayList<Building>();;
			File fXmlFile = new File(xmlFile);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();			
			NodeList nList = doc.getElementsByTagName("buildable");			

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				
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
					
					NodeList nodeList = nNode.getChildNodes();
					
					 switch (nodeList.item(1).getNodeType()) {
				        case Node.ELEMENT_NODE:

				            Element element = (Element) nodeList.item(1);
				            if (element.getNodeName().equalsIgnoreCase("position"))
				            {				               
				            	building.getLocation().setX(Integer.parseInt((element.getAttribute("x")))); 
								building.getLocation().setY(Integer.parseInt((element.getAttribute("y")))); 
								building.getLocation().setZ(Integer.parseInt((element.getAttribute("z")))); 
				            }
				    break;
					 }
					 
					 switch (nodeList.item(3).getNodeType()) {
				        case Node.ELEMENT_NODE:

				            Element element = (Element) nodeList.item(3);				           
				            if (element.getNodeName().equalsIgnoreCase("size"))
				            {				               
				            	building.getSize().setX(Integer.parseInt((element.getAttribute("x")))); 
								building.getSize().setY(Integer.parseInt((element.getAttribute("y")))); 
								building.getSize().setZ(Integer.parseInt((element.getAttribute("z"))));  
				            }
				    break;
					 }
									
					//System.out.println(building.toString());
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
	
	public List<Building> getBuildings() {
		return buildings;
	}


}
