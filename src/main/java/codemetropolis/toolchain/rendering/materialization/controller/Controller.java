package codemetropolis.toolchain.rendering.materialization.controller;

import javax.swing.JOptionPane;

import codemetropolis.toolchain.rendering.materialization.GUI.MainGUI;
import codemetropolis.toolchain.rendering.materialization.GUI.view.Labels;
import codemetropolis.toolchain.rendering.materialization.controller.thread.MainThread;

public class Controller {
	Controller con;
	MainGUI mainGUI;
	XmlProcessing xmlprocessing;
	
	public void start(Controller con) {
		 this.con =con;
		 this.mainGUI = new MainGUI(con);
		 mainGUI.startGUI();
	}
	public void generateFromFile(String inputXML) {
		System.out.println("Ezt a fáljt kaptam: " + inputXML);
		   // Feri Graph tesztje. Egyenlore konsolra irja ki az xml adatait. 
        //XmlToJGraph b = new XmlToJGraph();
       // b.read(inputXML);
        
        xmlprocessing= new XmlProcessing(inputXML);
        if(xmlprocessing.xmlProcessingAndBuildingsGeneration()) { 
        	JOptionPane.showMessageDialog(
                    mainGUI,
                    Labels.CONTROLLER_SUCCCES_XML_PROCESSING_MESSAGE,
                    Labels.CONTROLLER_SUCCCES_XML_PROCESSING_TITLE,
                    JOptionPane.INFORMATION_MESSAGE);
        	//ide kell hívni a grafikus megjelenítést
        	startThread();
        	
        	
            mainGUI.setVisibleTabletGraphicsPanel();
        } else {
        	JOptionPane.showMessageDialog(
                    mainGUI,
                    Labels.CONTROLLER_UNSUCCESSFUL_XML_PROCESSING_MESSAGE + "\n" + Labels.CONTROLLER_UNSUCCESSFUL_XML_PROCESSING_MESSAGE_SUGGESTION,
                    Labels.CONTROLLER_UNSUCCESSFUL_XML_PROCESSING_TITLE,
                    JOptionPane.ERROR_MESSAGE);          
        }
        	
       
        
	}
	private void startThread() {
		// TODO Auto-generated method stub
		MainThread mainThread = new MainThread(this, xmlprocessing.getBuildings());
		mainThread.startGenerate();
	}
	public MainGUI getMainGUI() {
		return mainGUI;
	}
	

}
