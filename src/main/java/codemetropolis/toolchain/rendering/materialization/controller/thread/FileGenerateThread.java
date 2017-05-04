package codemetropolis.toolchain.rendering.materialization.controller.thread;

import java.util.List;

import javax.swing.JTextArea;

import codemetropolis.toolchain.rendering.materialization.building.Building;
import codemetropolis.toolchain.rendering.materialization.controller.fileGenerate.GenerateScadFile;

public class FileGenerateThread extends Thread {

	MainThread mainThread;	
	Building buildingContainer;
	JTextArea console ;
	int whereToPrint= 1;
	
	List<Building> buildings;
	
	boolean debug = true;
	boolean resetPosition = true;
	boolean resetUnderGround = true;
	GenerateScadFile generateScadFile;
	
	
	public FileGenerateThread(MainThread mainThread,List<Building> buildings) {
		this.mainThread = mainThread;
		this.buildings = buildings;
		console = mainThread.getConntroller().getMainGUI().getConsoleFileGenerate();
		generateScadFile= new GenerateScadFile(this);
	}

	public void run () {
		generateScadFile.initFileWriter();
	
		
		for (Building building : buildings) {
			printDebug("\nA file generator: Megpróbálom lekérdezni a következő épületet");
			if (building.getParent_id() == null ){					
				buildingContainer = building;					
			}		
			generateScadFile.printBuilding(building,buildingContainer,resetPosition,resetUnderGround);
			printDebug("A file generator: Feldolgozom a kapott épületet");
		}
		
		
		
		generateScadFile.closeFileWriter();
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
