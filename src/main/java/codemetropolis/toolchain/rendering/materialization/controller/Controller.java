package codemetropolis.toolchain.rendering.materialization.controller;

import codemetropolis.toolchain.rendering.materialization.GUI.MainGUI;

public class Controller {
	Controller con;
	public void start(Controller con) {
		 this.con =con;
		 MainGUI mainGUI = new MainGUI();
		mainGUI.startGUI(con);
	}

}
