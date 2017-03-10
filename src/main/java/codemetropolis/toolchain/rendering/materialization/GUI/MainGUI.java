package codemetropolis.toolchain.rendering.materialization.GUI;

import java.awt.BorderLayout;
import java.awt.Button;

import java.awt.GridLayout;

import java.awt.Label;

import javax.swing.BorderFactory;
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
	JTextArea console;
	JTabbedPane tabbedPane;
	
	public MainGUI(Controller con) {
		this.con = con;
	}

	public void startGUI() {		
		setTitle(Labels.MainGUI_Ttitle);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900,700);
		setLayout(new BorderLayout());		
				
		makeMenuTabbetPane();		
		add(tabbedPane, BorderLayout.CENTER);
		
		JPanel consolePanel = makeConole();
		add(consolePanel, BorderLayout.SOUTH);

		
		setVisible(true);
		
	}
	
	private JTabbedPane makeMenuTabbetPane() {
		tabbedPane = new JTabbedPane();
		//ImageIcon icon = createImageIcon("images/middle.gif");
		tabbedPane.addTab(Labels.MainGUI_tabbetPane_fileTReading, new InputPanel(this));
		tabbedPane.addTab(Labels.MainGUI_tabbetPane_graphicsPanel, new GraphicsPanel(this));		
		tabbedPane.addTab("Tree", makePanel("This is tab 1"));
		tabbedPane.addTab("Tree", makePanel("This is tab 2"));
		tabbedPane.addTab("Teljes tree", makePanel("This is tab 2"));
		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setEnabledAt(2, false);		
		return tabbedPane;
	}	

	
	private JPanel makeConole(){
		JPanel p = new JPanel();			
		p.setBorder(BorderFactory.createTitledBorder(Labels.MainGUI_JPanel_consolePanel));
		p.setToolTipText(Labels.MainGUI_JPanel_consolePanel_Tips);			
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

	public Controller getController() {
		return this.con;		
	}

	public void setVisibleTabletGraphicsPanel() {		
		tabbedPane.setEnabledAt(1, true);
		tabbedPane.setSelectedIndex(1);
	}

}
