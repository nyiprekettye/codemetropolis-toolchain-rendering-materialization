package codemetropolis.toolchain.rendering.materialization.GUI;



import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import codemetropolis.toolchain.rendering.materialization.GUI.view.Labels;


public class InputPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1196786869572154575L;
	
	MainGUI mainGUI;
	JButton inputFileChoose =  new JButton(Labels.InputPanel_button_inputFileChoose);
	JButton startGenerate =  new JButton(Labels.InputPanel_button_startGenerate);
	JLabel inputFileLabel = new JLabel(Labels.InputPanle_label_inputFile_Missing);
	
	public InputPanel(MainGUI mainGUI) {
		this.mainGUI = mainGUI;
		inputFileChoose.addActionListener(this);
		startGenerate.addActionListener(this);

		setLayout(new FlowLayout(FlowLayout.LEFT));		
		JPanel menuPanelInside = new JPanel();
	
		menuPanelInside.setLayout(new GridBagLayout());				
		menuPanelInside.add(inputFileChoose);	
		menuPanelInside.add(inputFileLabel);	
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		
		startGenerate.setEnabled(false);
		
		menuPanelInside.add(startGenerate, c);
		add(menuPanelInside);
		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();
		// TODO Auto-generated method stub
		System.out.println("A következő menüt meghívta a felhasználó:" + actionCommand);
		  if (actionCommand == (Labels.InputPanel_button_inputFileChoose)) {	          
				JFileChooser fileChooser = new JFileChooser();
				
	
				FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");

				fileChooser.setDialogTitle(Labels.InputPanel_JFileChooser);
				// set selected filter
				fileChooser.setFileFilter(xmlfilter);
				
				
				
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				int result = fileChooser.showOpenDialog(this);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				    inputFileLabel.setText(selectedFile.getAbsolutePath());
				    startGenerate.setEnabled(true);
				}else{
					startGenerate.setEnabled(false);
				}
	      } else  if (actionCommand.equals(Labels.InputPanel_button_startGenerate)) {	 
	    	  
	    	  try {
	    		  
	    	  }catch (Exception e){
	    		  
	    	  }
	    	  
	    	  mainGUI.getController().generateFromFile(inputFileLabel.getText());
	      }
		
	}

}
