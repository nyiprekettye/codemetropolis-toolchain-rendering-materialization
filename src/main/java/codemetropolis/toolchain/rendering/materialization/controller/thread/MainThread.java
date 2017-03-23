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
		
		graphicsThreadCanReadCurrentIttem=false;
		fileGenerateThreadCanReadCurrentIttem=false;
		//graphicsThread = true;
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
		stopBuildingsThreads();
	
		 
	}
	
	private synchronized void stopBuildingsThreads() {
		while (getCanPutBuilding() == false){		
			try {
				if (debug)
				System.out.println("Main: várakoznom kell a leállítással! graphicsThreadCanReadCurrentIttem: "+graphicsThreadCanReadCurrentIttem+" ;fileGenerateThreadCanReadCurrentIttem: "+fileGenerateThreadCanReadCurrentIttem); 
		
				notify();
				wait();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}			
		}		
		notify();
		 endFileGenerateThread = true;
		 endGraphicsTread = true;
	}

	private synchronized void setCurrentbuilding(Building building) {
		while (getCanPutBuilding() == false){		
			try {
				if (debug)
				System.out.println("Main: várakoznom kell! graphicsThreadCanReadCurrentIttem: "+graphicsThreadCanReadCurrentIttem+" ;fileGenerateThreadCanReadCurrentIttem: "+fileGenerateThreadCanReadCurrentIttem); 
				
				wait();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}			
		}
		if (debug)
		System.out.println("Main: nem kell várakoznom! graphicsThreadCanReadCurrentIttem: "+graphicsThreadCanReadCurrentIttem+" ;fileGenerateThreadCanReadCurrentIttem: "+fileGenerateThreadCanReadCurrentIttem); 
		currentBuilding = building;
		fileGenerateThreadCanReadCurrentIttem= true;
		graphicsThreadCanReadCurrentIttem=true;
		
		
		
		notify();
	}	
	public boolean canGetBuilding() {
		// TODO Auto-generated method stub
		return false;
	}

	public synchronized boolean getGraphicsBuilding() {
		if (graphicsThreadCanReadCurrentIttem == true){
			if (debug)
				System.out.println("grafikus: Nem kell várakoznom! " );
			notify();
			return true;
		}else{			
			try {
				if (debug)
				System.out.println("grafikus: várakoznom kell!"); 
				notify();
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			notify();
			return false;
		}
		
	}

	public synchronized boolean getFileGeneratorBuilding() {
		if (fileGenerateThreadCanReadCurrentIttem == true){
			if (debug)
				System.out.println("File generator: Nem kell várakoznom! " );
			notify();
			return true;
		}else{			
			try {
				if (debug)
				System.out.println("File generator: várakoznom kell!"); 
				notify();
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			notify();
			return false;
		}
			
	}

	private boolean getCanPutBuilding() {
		if (this.graphicsThreadCanReadCurrentIttem == false && this.fileGenerateThreadCanReadCurrentIttem == false){	
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

	public Building getCurrentBuilding() {
		return currentBuilding;
	}

	public void graphicsThreadGotCurrentBuilding() {
		this.graphicsThreadCanReadCurrentIttem = false;
		
	}
	public void filegenerateThreadGotCurrentBuilding() {
		this.fileGenerateThreadCanReadCurrentIttem = false;
		
	}





}
