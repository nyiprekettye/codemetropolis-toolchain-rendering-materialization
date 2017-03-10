package codemetropolis.toolchain.rendering.materialization.controller;

import codemetropolis.toolchain.rendering.materialization.GUI.MainGUI;
import codemetropolis.toolchain.rendering.materialization.GUI.XmlToJGraph;

public class Controller {
	Controller con;
	public void start(Controller con) {
		 this.con =con;
		 MainGUI mainGUI = new MainGUI();
		mainGUI.startGUI(con);
	}
	public void generateFromFile(String inputXML) {
		System.out.println("Ezt a fáljt kaptam: " + inputXML);
		   // Feri Graph tesztje. Egyenlore konsolra irja ki az xml adatait. 
        XmlToJGraph b = new XmlToJGraph();
        b.read(inputXML);
	}

}
