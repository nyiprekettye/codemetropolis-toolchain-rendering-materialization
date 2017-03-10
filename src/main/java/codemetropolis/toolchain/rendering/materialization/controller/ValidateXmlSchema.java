package codemetropolis.toolchain.rendering.materialization.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidateXmlSchema {
	
	private String xml;
	private String xsd;
	private boolean error;
	
	public ValidateXmlSchema() {
		xml=null;
		xsd=null;
		error=false;
	}
	
	public ValidateXmlSchema(String xml, String xsd ) {
		this.xml=xml;
		this.xsd=xsd;
		this.error=false;
	}
	
	public ValidateXmlSchema(String xml, String xsd, boolean getExceptionToConsole ) {
		this.xml=xml;
		this.xsd=xsd;
		this.error=getExceptionToConsole;
	}
	
	public boolean validate() {
	     try {
	         SchemaFactory factory =SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	         Schema schema = factory.newSchema(new File(this.xsd));
	         Validator validator = schema.newValidator();
	         validator.validate(new StreamSource(new File(this.xml)));
	      } catch (IOException e) {
	        System.out.println("Exception: "+e.getMessage());	         
			if(error){
				e.printStackTrace();
	         }
	         return false;
	      } catch (SAXException e1) {
	         System.out.println("SAX Exception: "+e1.getMessage());
	         if(error) {
	        	 e1.printStackTrace();
	         }	       
	         return false;
	      }			
	      return true;		
	   }

}
