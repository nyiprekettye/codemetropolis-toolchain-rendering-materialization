package codemetropolis.toolchain.rendering.materialization.controller.fileGenerate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import codemetropolis.toolchain.rendering.materialization.building.Building;
import codemetropolis.toolchain.rendering.materialization.controller.thread.FileGenerateThread;

public class GenerateScadFile {
	FileGenerateThread fileGenerateThread;
	String filepath;
	PrintWriter scadWriter; 
	
	public GenerateScadFile(FileGenerateThread fileGenerateThread) {
		this.fileGenerateThread = fileGenerateThread;
		filepath = "scadoutput.scad";	
		
		File file = new File(filepath);		
		if (file.exists()) {			
			file.delete();
			System.out.println(file.getName() + " is deleted!");
		} else {
			System.out.println("A fálj nem létezik!");
		}
		
		
		 initFileWriter();
		 closeFileWriter();
	}

	public void closeFileWriter() {
		scadWriter.write("}");
		try {
			scadWriter.close();
			System.out.println("Sikeresen bezártam a writert!");
		} catch (Exception ex) {
			System.out.println("Hiba a write lezárásában" +ex);
		}
		
	}

	public void initFileWriter() {		
		try {
			scadWriter = new PrintWriter(filepath, "UTF-8");
			 System.out.println("Sikeresen létrehoztam és írtam a file-ba");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			 System.out.println("Error nem tudtam létrehozni és írni a file-ba!" + e1);
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			 System.out.println("Error nem tudtam létrehozni és írni a file-ba!" + e1);
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		scadWriter.write("rotate([0,0,0]){");
	}

	public void printBuilding(Building building) {
		
		scadWriter.write("translate(["+building.getLocation().getX()
			+","+building.getLocation().getZ()
			+","+building.getLocation().getY()+"]){"
			+ "cube(["
			+building.getSize().getX()
			+","+building.getSize().getZ()
			+","+building.getSize().getY()
			+"]);}\n");
		
	}

	public void printBuilding(Building building, Building buildingContainer) {
		
	}

	public void printBuilding(Building building, Building buildingContainer, boolean resetPosition,boolean resetUnderGround) {
		int translateBackX = 0;
		int translateBackY = 0;
		int translateBackZ = 0;
		int underGroung= 0;
		
		if (resetPosition == true){			 
			translateBackX = buildingContainer.getLocation().getX();
			translateBackY = buildingContainer.getLocation().getY();
			translateBackZ = buildingContainer.getLocation().getZ();			
			System.out.println("Reset with: ["+translateBackX+","+translateBackY+","+translateBackZ+"]");
		}
		if (resetUnderGround == true){
			underGroung = buildingContainer.getLocation().getY();
		}
		int locationX = (building.getLocation().getX()- translateBackX);
		int locationY =(building.getLocation().getY()- translateBackY) ;
		int locationZ=(building.getLocation().getZ()- translateBackZ);
		
		int sizeX =building.getSize().getX();
		int sizeY =building.getSize().getZ();
		int sizeZ =building.getSize().getY();
		
		if (building.getLocation().getY() >= underGroung){
			scadWriter.write("translate(["
				+locationX
				+","+locationZ
				+","+locationY+"]){"
				+ "cube(["
				+sizeX
				+","+sizeY
				+","+ sizeZ 
				+"]);}\n");
		}	
	}

}
