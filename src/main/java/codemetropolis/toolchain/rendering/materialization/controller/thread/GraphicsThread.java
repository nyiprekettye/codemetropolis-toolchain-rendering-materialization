package codemetropolis.toolchain.rendering.materialization.controller.thread;

import java.util.List;
import javax.swing.JTextArea;


import codemetropolis.toolchain.rendering.materialization.building.Building;
import codemetropolis.toolchain.rendering.materialization.lwjgl.LwjglMain3d;


public class GraphicsThread extends Thread{
	MainThread mainThread;
	Building buildingContainer;
	JTextArea console ;
	boolean debug = true;
	List<Building> buildings;
	int whereToPrint=1 ;
	
	public GraphicsThread(MainThread mainThread, List<Building> buildings) {
		this.mainThread = mainThread;
		this.buildings = buildings;
		console = mainThread.getConntroller().getMainGUI().getConsoleGraphics();
	}
	
	public void run () {
		/*
		for (Building building : buildings) {
			printDebug("\nA file graphics: Megpróbálom lekérdezni a következő épületet");
			if (building.getParent_id() == null ){					
				buildingContainer = building;					
			}	
			System.out.println(building.getSize().getX()+" - "+building.getSize().getY());
			//Cube c = new Cube();
			//c.cubeWithParameters(building.getSize().getX(),building.getSize().getY(),building.getSize().getZ(),building.getLocation().getX(),building.getLocation().getY(),building.getLocation().getZ());
			//call graphics writer
			
			
			
			
			
			
			
			
			
			printDebug("A file graphics: Feldolgozom a kapott épületet");
		}
		//new Cube().run();
		*/
		printDebug("A file graphics:Indul az épületek megjelenitése");
		new LwjglMain3d(buildings);
		
		printDebug("A file graphics:Bezártam az épületek megjelenitését");
		System.out.println("A graphics: I finised my job, i exited!");
		
	}
	
	private void printDebug(String string) {
		if (debug){
			if (whereToPrint == 1){
				console.append(string +"\n");
				console.setCaretPosition(console.getDocument().getLength());
			}else{
				System.out.println(string);
			}
		}
	}

}
