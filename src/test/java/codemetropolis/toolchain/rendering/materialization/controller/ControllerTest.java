package codemetropolis.toolchain.rendering.materialization.controller;


import org.junit.BeforeClass;
import org.junit.Test;



public class ControllerTest {
	private static  Controller con;
	
	 @BeforeClass
    public static void setUpBeforeClass() throws Exception {
		 con = new Controller();
    }
	@Test
	public final void testStart() {
		con.start(con);		
	}

	@Test
	public final void testGenerateFromFile() {
		con.generateFromFile("placingToRendering.xml");
		//fail("Not yet implemented"); // TODO
	}


}
