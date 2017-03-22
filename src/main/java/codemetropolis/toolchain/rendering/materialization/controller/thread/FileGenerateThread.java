package codemetropolis.toolchain.rendering.materialization.controller.thread;

import codemetropolis.toolchain.rendering.materialization.building.Building;
import codemetropolis.toolchain.rendering.materialization.controller.Controller;

public class FileGenerateThread extends Thread {

	MainThread mainThread;
	Building building;
	boolean debug = false;
	public FileGenerateThread(MainThread mainThread) {
		this.mainThread = mainThread;
	}

	public void run () {
		while(mainThread.isEndFileGenerateThread() == false){
			if (debug)
			System.out.println("A file generator: Megpróbálom lekérdezni a következő épületet");
			
			building = mainThread.getFileGeneratorBuilding();
			
			if (debug)
			System.out.println("A file generator: Feldolgozom a kapott épületet");
			//if (debug)
			System.out.println("A file generator: A kapott épület: "+ building.toString());
			
			if (debug)
			System.out.println("A file generator: Feldolgoztam az épületet \n\n");
			
		}
		System.out.println("A file generator: I finised my job, i exited!");
	}
}
