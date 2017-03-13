package codemetropolis.toolchain.rendering.materialization.controller;

import javax.swing.JOptionPane;

import codemetropolis.toolchain.rendering.materialization.GUI.MainGUI;
import codemetropolis.toolchain.rendering.materialization.GUI.XmlToJGraph;
import codemetropolis.toolchain.rendering.materialization.GUI.view.Labels;

public class Controller {
	Controller con;
	MainGUI mainGUI;
	
	public void start(Controller con) {
		 this.con =con;
		 this.mainGUI = new MainGUI(con);
		 mainGUI.startGUI();
	}
	public void generateFromFile(String inputXML) {
		System.out.println("Ezt a fáljt kaptam: " + inputXML);
		   // Feri Graph tesztje. Egyenlore konsolra irja ki az xml adatait. 
       // XmlToJGraph b = new XmlToJGraph();
        //b.read(inputXML);
        
        XmlProcessing xmlprocessing= new XmlProcessing(inputXML);
        if(xmlprocessing.xmlProcessingAndBuildingsGeneration()) { 
        	JOptionPane.showMessageDialog(
                    mainGUI,
                    Labels.CONTROLLER_SUCCCES_XML_PROCESSING_MESSAGE,
                    Labels.CONTROLLER_SUCCCES_XML_PROCESSING_TITLE,
                    JOptionPane.INFORMATION_MESSAGE);
        	//ide kell hívni a grafikus megjelenítést
        	
        	
            mainGUI.setVisibleTabletGraphicsPanel();
        } else {
        	JOptionPane.showMessageDialog(
                    mainGUI,
                    Labels.CONTROLLER_UNSUCCESSFUL_XML_PROCESSING_MESSAGE + "\n" + Labels.CONTROLLER_UNSUCCESSFUL_XML_PROCESSING_MESSAGE_SUGGESTION,
                    Labels.CONTROLLER_UNSUCCESSFUL_XML_PROCESSING_TITLE,
                    JOptionPane.ERROR_MESSAGE);          
        }
        	
       
        
	}

}
