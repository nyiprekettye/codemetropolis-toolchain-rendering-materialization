package codemetropolis.toolchain.rendering.materialization;

import codemetropolis.toolchain.rendering.materialization.GUI.MainGUI;
import codemetropolis.toolchain.rendering.materialization.controller.Controller;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
       Controller con = new Controller();
       con.start(con);
    }
}
