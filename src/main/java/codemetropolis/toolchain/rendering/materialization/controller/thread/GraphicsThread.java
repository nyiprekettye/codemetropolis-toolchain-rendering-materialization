package codemetropolis.toolchain.rendering.materialization.controller.thread;

import java.util.List;
import javax.swing.JTextArea;

import codemetropolis.toolchain.rendering.materialization.building.Building;


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
		
		for (Building building : buildings) {
			printDebug("\nA file graphics: Megpróbálom lekérdezni a következő épületet");
			if (building.getParent_id() == null ){					
				buildingContainer = building;					
			}		
			//call graphics writer
			printDebug("A file graphics: Feldolgozom a kapott épületet");
		}
		
		
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
