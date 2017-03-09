package codemetropolis.toolchain.rendering.materialization.GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import codemetropolis.toolchain.rendering.materialization.GUI.view.Labels;
import codemetropolis.toolchain.rendering.materialization.controller.Controller;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	Controller con;
	Button startGenerateBtn = new Button(Labels.MainGUI_button_startGenerateBtn);
	
	public void startGUI(Controller con) {
		this.con = con;
		setTitle(Labels.MainGUI_Ttitle);
		setSize(800,200);
		setLayout(new BorderLayout());
		InputPanel inputPanel = new InputPanel(this);
		add(inputPanel, BorderLayout.NORTH);

		add(startGenerateBtn, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		//ImageIcon icon = createImageIcon("images/middle.gif");

		tabbedPane.addTab("Console", makePanel("This is tab 1"));		
		tabbedPane.addTab("Tree", makePanel("This is tab 2"));
		tabbedPane.addTab("Kész tree", makePanel("This is tab 2"));
		
		add(tabbedPane, BorderLayout.SOUTH);
		setVisible(true);
		
	}
	
    private static JPanel makePanel(String text) {

	        JPanel p = new JPanel();
	        p.add(new Label(text));
	        p.setLayout(new GridLayout(1, 1));
	        return p;

	    }

}
