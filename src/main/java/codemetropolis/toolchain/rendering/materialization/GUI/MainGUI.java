package codemetropolis.toolchain.rendering.materialization.GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import codemetropolis.toolchain.rendering.materialization.GUI.view.Labels;
import codemetropolis.toolchain.rendering.materialization.controller.Controller;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	Controller con;
	Button startGenerateBtn = new Button(Labels.MainGUI_button_startGenerateBtn);
	static JTextArea console;
	
	public void startGUI(Controller con) {
		this.con = con;
		setTitle(Labels.MainGUI_Ttitle);
		setSize(800,500);
		setLayout(new BorderLayout());
		InputPanel inputPanel = new InputPanel(this);
		add(inputPanel, BorderLayout.NORTH);
		startGenerateBtn.setSize(100,100);
		add(startGenerateBtn, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		//ImageIcon icon = createImageIcon("images/middle.gif");

		tabbedPane.addTab("Console", makeConole());		
		tabbedPane.addTab("Tree", makePanel("This is tab 2"));
		tabbedPane.addTab("Kész tree", makePanel("This is tab 2"));
		
		add(tabbedPane, BorderLayout.SOUTH);
		setVisible(true);
		
	}
	private static JPanel makeConole(){
			JPanel p = new JPanel();
			console = new JTextArea(10, 50);
			console.setEditable(false);
			p.setLayout(new GridLayout(1, 1));
			p.add(console);			
        	return p;
	}
    private static JPanel makePanel(String text) {

	        JPanel p = new JPanel();
	        p.add(new Label(text));
	        p.setLayout(new GridLayout(1, 1));
	        return p;

	    }

}
