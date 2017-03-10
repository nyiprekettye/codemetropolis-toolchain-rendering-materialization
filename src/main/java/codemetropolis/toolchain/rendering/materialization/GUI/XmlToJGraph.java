package codemetropolis.toolchain.rendering.materialization.GUI;


	import javax.xml.parsers.DocumentBuilderFactory;
import javax.swing.JFrame;
import javax.xml.parsers.DocumentBuilder;


import org.w3c.dom.Document;
	import org.w3c.dom.NodeList;

import codemetropolis.toolchain.rendering.materialization.building.Building;

import org.w3c.dom.Node;
	import org.w3c.dom.Element;
	import java.io.File;

	public class XmlToJGraph {
		
		private String filename;
		
	  public void read(String filename) {
		  
			JFrame frame = new JFrame("GraphTreeModel");
			//JGraph graph = new JGraph();
			//GraphTreeModel gtModel = new GraphTreeModel(graph.getModel());
			//JTree tree = new JTree(gtModel);
			//graph.getModel().addGraphModelListener(gtModel);
			//tree.setRootVisible(false);
			//JScrollPane sGraph = new JScrollPane(graph);
		//	JScrollPane sTree = new JScrollPane(tree);
			//JSplitPane pane =
			//	new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sTree, sGraph);
			//frame.getContentPane().add(pane);
			frame.pack();
			//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
	    try {

		File fXmlFile = new File(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);

		//optional, but recommended
		//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
		doc.getDocumentElement().normalize();

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		NodeList nList = doc.getElementsByTagName("buildable");

		System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
			
			//System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				Building building = new Building();
				if(nNode.getParentNode().getNodeName() != "buildables"){
					Element parent = (Element) nNode.getParentNode().getParentNode();
					building.setParent_id(parent.getAttribute("id"));
					building.setParent_name(parent.getAttribute("name"));
					/*
					System.out.println("Current Element parents id:" +building.getParent_id());
					System.out.println("Current Element parents name:" + building.getParent_name());
					*/
					/*
					System.out.println("Current Element parents id:" +parent.getAttribute("id"));
					System.out.println("Current Element parents name:" +parent.getAttribute("name"));
					*/
				}
				building.setId(eElement.getAttribute("id"));
				building.setName(eElement.getAttribute("name"));
				building.setType(eElement.getAttribute("type"));
				/*
				System.out.println("Buildable id : " + building.getId());
				System.out.println("Buildable name: " + building.getName());
				System.out.println("Buildable type : " + building.getType());
				*/
				/*
				System.out.println("Buildable id : " + eElement.getAttribute("id"));
				System.out.println("Buildable name: " + eElement.getAttribute("name"));
				System.out.println("Buildable type : " + eElement.getAttribute("type"));*/
				
				System.out.println(building.toString());
				
				/*
				System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
				System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
				System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
				System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
*/
			}
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	  }

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	}
