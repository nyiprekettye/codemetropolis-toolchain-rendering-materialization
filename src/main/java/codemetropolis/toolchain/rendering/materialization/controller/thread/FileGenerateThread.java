package codemetropolis.toolchain.rendering.materialization.controller.thread;

import javax.swing.JTextArea;

import codemetropolis.toolchain.rendering.materialization.building.Building;
import codemetropolis.toolchain.rendering.materialization.controller.fileGenerate.GenerateScadFile;

public class FileGenerateThread extends Thread {

	MainThread mainThread;
	Building building;
	Building buildingContainer;
	JTextArea console ;
	String whereToPrint="ownConsole";
	boolean debug = true;
	boolean resetPosition = true;
	boolean resetUnderGround = true;
	GenerateScadFile generateScadFile;
	
	
	public FileGenerateThread(MainThread mainThread) {
		this.mainThread = mainThread;
		console = mainThread.getConntroller().getMainGUI().getConsoleFileGenerate();
		generateScadFile= new GenerateScadFile(this);
	}

	public void run () {
		generateScadFile.initFileWriter();
		while(mainThread.isEndFileGenerateThread() == false){
				
				printDebug("A file generator: Megpróbálom lekérdezni a következő épületet");
				
			if( mainThread.getFileGeneratorBuilding() == true){
				building = mainThread.getCurrentBuilding();
				mainThread.filegenerateThreadGotCurrentBuilding();
				
				if (building.getParent_id() == null ){					
					buildingContainer = building;					
				}		
				generateScadFile.printBuilding(building,buildingContainer,resetPosition,resetUnderGround);
				
				
				printDebug("A file generator: Feldolgozom a kapott épületet");
					
				printDebug("A file generator: A kapott épület: "+ building.toString());
				printDebug("A file generator: Feldolgoztam az épületet \n\n");
					
			}
				
			
		}
		printDebug("A file generator: I finised my job, i exited!");
		
		generateScadFile.closeFileWriter();
	}

	private void printDebug(String string) {
		if (debug){
			if (whereToPrint == "ownConsole"){
				console.append(string +"\n");
				console.setCaretPosition(console.getDocument().getLength());
			}else{
				System.out.println(string);
			}
		}
	}
	
}
