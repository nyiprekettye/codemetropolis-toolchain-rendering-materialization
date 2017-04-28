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
	
	public MainThread(Controller con, List<Building> buildings) {
		
		this.conntroller = con;
		graphicsThread = new GraphicsThread(this,buildings);
		fileGenerateThread =  new FileGenerateThread(this, buildings);
		
		graphicsThreadCanReadCurrentIttem=false;
		fileGenerateThreadCanReadCurrentIttem=false;
		fileGenerateThreadWait = true;
	}
	
	public void startGenerate() {
		graphicsThread.start();
		fileGenerateThread.start();		 
	}


	public Controller getConntroller() {
		return conntroller;
	}





}
