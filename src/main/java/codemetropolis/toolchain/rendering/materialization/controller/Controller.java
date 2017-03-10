package codemetropolis.toolchain.rendering.materialization.controller;

import codemetropolis.toolchain.rendering.materialization.GUI.MainGUI;
import codemetropolis.toolchain.rendering.materialization.GUI.XmlToJGraph;

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
        XmlToJGraph b = new XmlToJGraph();
        b.read(inputXML);
        
        /*
         * ha sikeres volt a beolvasás akkor a grafikus felületet aktiválni kell
         */
        mainGUI.setVisibleTabletGraphicsPanel();
	}

}
