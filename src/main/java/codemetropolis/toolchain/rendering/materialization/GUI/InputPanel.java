package codemetropolis.toolchain.rendering.materialization.GUI;


import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import codemetropolis.toolchain.rendering.materialization.GUI.view.Labels;


public class InputPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1196786869572154575L;
	
	MainGUI mainGUI;
	Button inputFileChoose =  new Button(Labels.InputPanel_button_inputFileChoose);
	JLabel inputFileLabel = new JLabel(Labels.InputPanle_label_inputFile_Missing);
	
	public InputPanel(MainGUI mainGUI) {
		this.mainGUI = mainGUI;
		inputFileChoose.addActionListener(this);
		setLayout(new GridLayout(1,2));
		add(inputFileChoose);
		add(inputFileLabel);
		
		
		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		// TODO Auto-generated method stub
		System.out.println("A következő menüt meghívta a felhasználó:" + actionCommand);
		  if (actionCommand.equals(Labels.InputPanel_button_inputFileChoose)) {	          
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(this);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    inputFileLabel.setText(selectedFile.getAbsolutePath());
				}
	      } 
		
	}

}
