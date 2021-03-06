package codemetropolis.toolchain.rendering.materialization.GUI;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;

import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import codemetropolis.toolchain.rendering.materialization.GUI.view.Labels;
import codemetropolis.toolchain.rendering.materialization.controller.Controller;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	Controller con;
	Button startGenerateBtn = new Button(Labels.MainGUI_button_startGenerateBtn);
	JTextArea consoleGraphics;
	JTextArea consoleFileGenerate;
	JTabbedPane tabbedPane;
	GraphicsPanel graphicsPanel;
	
	public MainGUI(Controller con) {
		this.con = con;
		graphicsPanel = new GraphicsPanel(this);
	}
	
	public GraphicsPanel getGraphicsPanel() {
		return graphicsPanel;
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
		tabbedPane.addTab(Labels.MainGUI_tabbetPane_graphicsPanel,graphicsPanel);		
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
		
		consoleGraphics = new JTextArea(10, 25);
		consoleGraphics.setEditable(false);
		consoleGraphics.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		consoleGraphics.setLineWrap(true);
		consoleGraphics.setWrapStyleWord(true);		
		
		consoleFileGenerate = new JTextArea(10, 25);
		consoleFileGenerate.setEditable(false);	
		consoleFileGenerate.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		consoleFileGenerate.setLineWrap(true);
		consoleFileGenerate.setWrapStyleWord(true);
		
		p.setLayout(new GridLayout(1, 2));
		JScrollPane spGraphics = new JScrollPane(consoleGraphics);
		spGraphics.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spGraphics.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JScrollPane spFileGenerate = new JScrollPane(consoleFileGenerate);
		spFileGenerate.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spFileGenerate.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		p.add(spGraphics);			
		p.add(spFileGenerate);
		//p.add(new JLabel("hello1"));
		//p.add(new JLabel("hello2"));		
		
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

	public JTextArea getConsoleGraphics() {
		return consoleGraphics;
	}

	public JTextArea getConsoleFileGenerate() {
		return consoleFileGenerate;
	}

}
