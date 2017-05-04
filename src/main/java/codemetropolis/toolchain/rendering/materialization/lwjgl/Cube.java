package codemetropolis.toolchain.rendering.materialization.lwjgl;


import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Cube {
	public int name;
	public Cube() {
		name=0;
	}
	public void getColorCube(float sizeX,float sizeY,float sizeZ,float locX,float locY,float locZ){
		
		GL11.glLoadName(name);
		glBegin(GL_QUADS);
		{
			//FrontFace
			glColor3f(1f,0f,0f);
			glVertex3f(locX,locY,locZ);
			glVertex3f(locX+sizeX,locY,locZ);
			glVertex3f(locX+sizeX,locY+sizeY,locZ);	
			glVertex3f(locX,locY +sizeY,locZ);
				
			
			//BackFace
			glVertex3f(locX,locY,locZ +sizeZ);
			glVertex3f(locX+sizeX,locY,locZ +sizeZ);
			glVertex3f(locX+sizeX,locY+sizeY,locZ + sizeZ);		
			glVertex3f(locX,locY +sizeY,locZ +sizeZ);
			
			//BottomFace
			glColor3f(0f,0f,1f);
			glVertex3f(locX,locY,locZ);
			glVertex3f(locX,locY,locZ+sizeZ);
			glVertex3f(locX+sizeX,locY,locZ+sizeZ);
			glVertex3f(locX+sizeX,locY,locZ);
			
			//TopFace
			glColor3f(1f,1f,0f);
			glVertex3f(locX,locY+sizeY,locZ);
			glVertex3f(locX,locY+sizeY,locZ+sizeZ);
			glVertex3f(locX+sizeX,locY+sizeY,locZ+sizeZ);
			glVertex3f(locX+sizeX,locY+sizeY,locZ);
			
			//LeftFace
			glColor3f(0f,1f,1f);
			glVertex3f(locX,locY,locZ);
			glVertex3f(locX,locY,locZ+sizeZ);
			glVertex3f(locX,locY+sizeY,locZ+sizeZ);
			glVertex3f(locX,locY+sizeY,locZ);
			//Right Face
			glColor3f(1f,0f,1f);
			glVertex3f(locX+sizeX,locY,locZ);
			glVertex3f(locX+sizeX,locY,locZ+sizeZ);
			glVertex3f(locX+sizeX,locY+sizeY,locZ+sizeZ);
			glVertex3f(locX+sizeX,locY+sizeY,locZ);
			
		}
		glEnd();


	}

}
