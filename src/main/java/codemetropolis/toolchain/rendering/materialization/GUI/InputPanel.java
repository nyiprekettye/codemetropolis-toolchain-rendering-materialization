package codemetropolis.toolchain.rendering.materialization.GUI;



import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import codemetropolis.toolchain.rendering.materialization.GUI.view.Labels;
import codemetropolis.toolchain.rendering.materialization.controller.ValidateXmlSchema;


public class InputPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1196786869572154575L;
	
	MainGUI mainGUI;
	JButton inputFileChoose =  new JButton(Labels.InputPanel_button_inputFileChoose);
	JButton startGenerate =  new JButton(Labels.InputPanel_button_startGenerate);
	JCheckBox getInputFileExceptionCheckedBox = new JCheckBox();
	JLabel inputFileLabel = new JLabel(Labels.InputPanle_label_inputFile_Missing);
	JLabel getInputFileExceptionLabel = new JLabel(Labels.InputPanle_label_inputFile_Exception);
	
	public InputPanel(MainGUI mainGUI) {
		this.mainGUI = mainGUI;
		inputFileChoose.addActionListener(this);
		startGenerate.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));		
		JPanel menuPanelInside = new JPanel();
	
		menuPanelInside.setLayout(new GridBagLayout());				
		menuPanelInside.add(inputFileChoose);	
		inputFileLabel.setText(new File("placingToRendering.xml").getPath());
		menuPanelInside.add(inputFileLabel);
		
		
		
		GridBagConstraints c = new GridBagConstraints();
		c.ipady = 20;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;	
		JPanel getInputFileExceprion = new JPanel();
		getInputFileExceprion.add(getInputFileExceptionCheckedBox);
		getInputFileExceprion.add(getInputFileExceptionLabel);
		menuPanelInside.add(getInputFileExceprion,c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 40;      //make this component tall
		c.weightx = 0.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		
		//startGenerate.setEnabled(false);
		
		menuPanelInside.add(startGenerate, c);
		add(menuPanelInside);
		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent event) {
		
		String actionCommand = event.getActionCommand();		
		System.out.println("A következőt mezőt választota a felhasználó:" + actionCommand);
		
		 if (actionCommand == (Labels.InputPanel_button_inputFileChoose)) {        			  
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
				fileChooser.setDialogTitle(Labels.InputPanel_JFileChooser);				
				fileChooser.setFileFilter(xmlfilter);				
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
				int result = fileChooser.showOpenDialog(this);
				/**
				 *if select file, than set label text to file path
				 *else disenable startgenerate - button 
				 */
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    System.out.println("Selected file: " + selectedFile.getPath());
				    inputFileLabel.setText(selectedFile.getPath());
				    startGenerate.setEnabled(true);						    
				}else{
					startGenerate.setEnabled(false);
				}
	      } else  if (actionCommand.equals(Labels.InputPanel_button_startGenerate)) {	 
	    	  boolean isValid =false;
	    	  /**
	    	   * if getInputFileExceptionCheckedBox is checked 
	    	   * than: call ValidateXmlSchema.validate() function with "show exception from console"
	    	   * else call ValidateXmlSchema.validate() function with "hide exception from console"
	    	   */
	    	  if(getInputFileExceptionCheckedBox.isSelected()){
	    		  isValid = new ValidateXmlSchema(inputFileLabel.getText(),"CodeMetropolis.xsd",true).validate();          
		      } else {
		    	  isValid = new ValidateXmlSchema(inputFileLabel.getText(),"CodeMetropolis.xsd").validate();
		      }	    	 
	          if (isValid) {
	             System.out.println("is valid xml " );             
	          } else {
	             System.out.println( "ERROR - is not valid xml " );
	             JOptionPane.showMessageDialog(
	                     mainGUI,
	                     Labels.inputPanel_ERROR_NOT_VALID_XML,
	                     Labels.inputPanel_ERROR_MessageDialog_TITLE,
	                     JOptionPane.ERROR_MESSAGE);
	             return;
	          }
	    	  
	    	  mainGUI.getController().generateFromFile(inputFileLabel.getText());
	      }	
		  
	}	   
	   
}
