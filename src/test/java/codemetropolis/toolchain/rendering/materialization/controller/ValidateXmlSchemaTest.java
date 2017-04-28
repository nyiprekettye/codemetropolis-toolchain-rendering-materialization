package codemetropolis.toolchain.rendering.materialization.controller;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ValidateXmlSchemaTest {	
	private static String xmlPath;
	private static String wrongXmlPath;
	@BeforeClass
    public static void setUpBeforeClass() throws Exception {
		xmlPath ="placingToRendering.xml";
		wrongXmlPath="pom.xml";
	}
	
	@Test
	public final void testValidateXmlSchemaStringString() {
		boolean isValid;
		isValid = new ValidateXmlSchema(xmlPath,"CodeMetropolis.xsd").validate();
		assertEquals(true, isValid);
		
		isValid = new ValidateXmlSchema(wrongXmlPath,"CodeMetropolis.xsd").validate();
		assertEquals(false, isValid);
	}

	@Test
	public final void testValidateXmlSchemaStringStringBoolean() {
		boolean isValid;
		isValid = new ValidateXmlSchema(xmlPath,"CodeMetropolis.xsd",true).validate();
		assertEquals(true, isValid);
		
		isValid = new ValidateXmlSchema(wrongXmlPath,"CodeMetropolis.xsd",true).validate();
		assertEquals(false, isValid);
		
		isValid = new ValidateXmlSchema(xmlPath,"CodeMetropolis.xsd",false).validate();
		assertEquals(true, isValid);
		
		isValid = new ValidateXmlSchema(wrongXmlPath,"CodeMetropolis.xsd",false).validate();
		assertEquals(false, isValid);
	}

	@Test
	public final void testValidate() {
		boolean isValid;
		isValid = new ValidateXmlSchema(xmlPath,"CodeMetropolis.xsd").validate();
		assertEquals(true, isValid);
		
	}

}
