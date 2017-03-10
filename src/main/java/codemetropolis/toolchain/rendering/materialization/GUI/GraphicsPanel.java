package codemetropolis.toolchain.rendering.materialization.GUI;

import java.awt.Label;

import javax.swing.JPanel;

public class GraphicsPanel extends JPanel {

	private static final long serialVersionUID = -4771614720014150163L;
	private MainGUI mainGUI;
	
	public GraphicsPanel(MainGUI mainGUI) {
		this.mainGUI = mainGUI;
		add(new Label("Grafikus felület"));
	}

}
