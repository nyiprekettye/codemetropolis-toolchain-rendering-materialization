package codemetropolis.toolchain.rendering.materialization.GUI;



import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Document;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;

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
		
		//startGenerate.setEnabled(false);
		
		menuPanelInside.add(startGenerate, c);
		add(menuPanelInside);
		setVisible(true);
		
	}

	public void actionPerformed(ActionEvent event) {
		String actionCommand = event.getActionCommand();
		// TODO Auto-generated method stub
		System.out.println("A következőt mezőt választota a felhasználó:" + actionCommand);
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
	    	  
	    	  boolean isValid = validateXMLSchema("CodeMetropolis.xsd",inputFileLabel.getText());
	          
	          if(isValid){
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


	   public static boolean validateXMLSchema(String xsdPath, String xmlPath){
	      try {
	         SchemaFactory factory =
	            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	            Schema schema = factory.newSchema(new File(xsdPath));
	            Validator validator = schema.newValidator();
	            validator.validate(new StreamSource(new File(xmlPath)));
	      } catch (IOException e){
	         System.out.println("Exception: "+e.getMessage());
	         //e.printStackTrace();
	         return false;
	      }catch(SAXException e1){
	         System.out.println("SAX Exception: "+e1.getMessage());
	         //e1.printStackTrace();
	         return false;
	      }
			
	      return true;
		
	   }
	   
	   
}
