package codemetropolis.toolchain.rendering.materialization.controller.thread;

import codemetropolis.toolchain.rendering.materialization.building.Building;
import codemetropolis.toolchain.rendering.materialization.controller.Controller;

public class GraphicsThread extends Thread{
	MainThread mainThread;
	Building building;
	boolean debug = false;
	
	public GraphicsThread(MainThread mainThread) {
		this.mainThread = mainThread;
	}
	
	public void run () {
		while(mainThread.isEndGraphicsTread() == false){
			if (debug)
			System.out.println("A grafikus: Megpróbálom lekérdezni a következő épületet");
			
			building = mainThread.getGraphicsBuilding();
			
			if (debug)
			System.out.println("A grafikus: Feldolgozom a kapott épületet");
			//if (debug)
			System.out.println("A grafikus: A kapott épület: "+ building.toString());
	
			if (debug)
			System.out.println("A grafikus: Feldolgoztam az épületet \n\n");
			
		}
		
		
		System.out.println("A graphics: I finised my job, i exited!");
	}

}
