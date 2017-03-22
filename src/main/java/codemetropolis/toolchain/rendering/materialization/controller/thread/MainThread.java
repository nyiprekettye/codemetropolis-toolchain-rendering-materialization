package codemetropolis.toolchain.rendering.materialization.controller.thread;

import java.util.List;

import codemetropolis.toolchain.rendering.materialization.building.Building;
import codemetropolis.toolchain.rendering.materialization.controller.Controller;

public class MainThread {
	Controller conntroller;
	GraphicsThread graphicsThread;
	FileGenerateThread fileGenerateThread;
	Building currentBuilding;
	
	boolean graphicsThreadWait;
	boolean fileGenerateThreadWait;
	
	boolean graphicsThreadCanReadCurrentIttem;
	boolean fileGenerateThreadCanReadCurrentIttem;
	boolean debug = false;
	
	boolean endGraphicsTread = false;
	boolean endFileGenerateThread = false;
	
	public MainThread(Controller con) {
		this.conntroller = con;
		graphicsThread = new GraphicsThread(this);
		fileGenerateThread =  new FileGenerateThread(this);
		
		graphicsThreadWait = true;
		fileGenerateThreadWait = true;
	}
	
	public void startGenerate(List<Building> buildings) {
		graphicsThread.start();
		fileGenerateThread.start();
		for (Building building : buildings) {
			if (debug)
			System.out.println("A main: Megpróbálom belerakni a következő épületet");
			setCurrentbuilding(building);	
			if (debug)
			System.out.println("A main:Sikeresen beleraktam az épületet");
		}
		 endFileGenerateThread = true;
		 endGraphicsTread = true;
		 
	}
	
	private synchronized void setCurrentbuilding(Building building) {
		while (getCanPutBuilding() == false){		
			try {
				if (debug)
				System.out.println("Main: várakoznom kell!"); 
				wait();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}			
		}
		if (debug)
		System.out.println("graphicsThreadWait: "+graphicsThreadWait+" ;fileGenerateThreadWait: "+fileGenerateThreadWait); 
		currentBuilding = building;
		graphicsThreadWait = false;
		fileGenerateThreadWait = false;
		
		notify();
	}	

	public synchronized Building getGraphicsBuilding() {
		while (graphicsThreadWait == true){
			try {
				if (debug)
				System.out.println("grafikus: várakoznom kell!"); 
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (debug)
		System.out.println("grafikus: Nem kell várakoznom mert: "+graphicsThreadWait ); 
		graphicsThreadWait = true;
		if (debug)
		System.out.println("grafikus: A grafikus száll megkapta az épületet");
		notify();
		return currentBuilding;		
	}

	public synchronized Building getFileGeneratorBuilding() {
		while (fileGenerateThreadWait == true){
			try {
				if (debug)
				System.out.println("file generator: várakoznom kell!"); 
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		if (debug)
		System.out.println("file generator: Nem kell várakoznom mert: "+fileGenerateThreadWait ); 		
		fileGenerateThreadWait = true;
		if (debug)
		System.out.println("file generator: A file generator száll megkapta az épületet");
		notify();
		return currentBuilding;	
	}

	private boolean getCanPutBuilding() {
		if (this.graphicsThreadWait == true && this.fileGenerateThreadWait == true){	
			return true;
		}
		return false;
	}

	public boolean isEndGraphicsTread() {
		return endGraphicsTread;
	}

	public boolean isEndFileGenerateThread() {
		return endFileGenerateThread;
	}




}
