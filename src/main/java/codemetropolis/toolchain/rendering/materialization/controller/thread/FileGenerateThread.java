package codemetropolis.toolchain.rendering.materialization.controller.thread;

import javax.swing.JTextArea;

import codemetropolis.toolchain.rendering.materialization.building.Building;
import codemetropolis.toolchain.rendering.materialization.controller.Controller;

public class FileGenerateThread extends Thread {

	MainThread mainThread;
	Building building;
	JTextArea console ;
	String whereToPrint="ownConsole";
	boolean debug = true;
	public FileGenerateThread(MainThread mainThread) {
		this.mainThread = mainThread;
		console = mainThread.getConntroller().getMainGUI().getConsoleFileGenerate();
	}

	public void run () {
		while(mainThread.isEndFileGenerateThread() == false){
				
				printDebug("<b>A file generator:</b> Megpróbálom lekérdezni a következő épületet");
				
			if( mainThread.getFileGeneratorBuilding() == true){
				building = mainThread.getCurrentBuilding();
				mainThread.filegenerateThreadGotCurrentBuilding();
				
				printDebug("A file generator: Feldolgozom a kapott épületet");
					
				printDebug("A file generator: A kapott épület: "+ building.toString());
				printDebug("A file generator: Feldolgoztam az épületet \n\n");
					
			}
		
			
		
			
		}
		printDebug("A file generator: I finised my job, i exited!");
		
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
