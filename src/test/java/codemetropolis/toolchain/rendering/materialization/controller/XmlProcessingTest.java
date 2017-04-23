package codemetropolis.toolchain.rendering.materialization.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import codemetropolis.toolchain.rendering.materialization.building.Building;

public class XmlProcessingTest {
	private static XmlProcessing xmlprocessing;
	private static String xmlPath;
	
	@BeforeClass
    public static void setUpBeforeClass() throws Exception {
		xmlPath ="placingToRendering.xml";
	}
	
	@Test
	public final void testXmlProcessing() {
		xmlprocessing= new XmlProcessing(xmlPath);
	}
	
	@Test
	public final void testXmlProcessingAndBuildingsGeneration() {
		assertEquals(true, xmlprocessing.xmlProcessingAndBuildingsGeneration());		
	}

	@Test
	public final void testGetBuildings() {
		List<Building> buildings = null;
		List<Building> buildings_result = xmlprocessing.getBuildings();
		assertEquals(buildings ,buildings_result);
	}

}
