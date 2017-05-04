package codemetropolis.toolchain.rendering.materialization.lwjgl;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import codemetropolis.toolchain.rendering.materialization.building.Building;

import static org.lwjgl.opengl.GL11.*;

/**
*
* @author Benny
*/
public class LwjglMain3d{
	
	private static List<Building> buildings;
	
	public static void main(String[] args)
	{
		//new LwjglMain3d();
	
	}
	public LwjglMain3d(List<Building> buildings){
		this.buildings=buildings;
		initDisplay();
		
		gameLoop();
		cleanUp();
	}
	
	public static void gameLoop()
	{
		Camera cam = new Camera(70,(float)Display.getWidth()/(float)Display.getHeight(),0.3f,1000);
		float x = 0;
		
		
		
		while(!Display.isCloseRequested())
		{
			
			keyProccess(cam);		
			
		    GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			glLoadIdentity();
			
			cam.useView();
			
			render();
			
			
			x += 0.1f;
			Display.update();
			Display.sync(60);
			 /*if(Mouse.isButtonDown(0))
             {
             	 System.out.println("\nKleikkeltem: ");
                     select(Mouse.getX(),Mouse.getY());
                    
             }*/
			
		}
	}
	
	private static void keyProccess(Camera cam) {
		boolean key_w = Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8) ;
		boolean key_s = Keyboard.isKeyDown(Keyboard.KEY_NUMPAD2) ;
		boolean up = Keyboard.isKeyDown(Keyboard.KEY_NUMPAD9);
		boolean down = Keyboard.isKeyDown(Keyboard.KEY_NUMPAD3);
		boolean left = Keyboard.isKeyDown(Keyboard.KEY_NUMPAD4);
		boolean right = Keyboard.isKeyDown(Keyboard.KEY_NUMPAD6);
		
		if(key_w)
			cam.move(0.1f,1);
		
		if(key_s)
			cam.move(-0.1f,1);
		
		if(left)
			cam.move(0.1f,0);//cam.rotateY(-0.1f);
		
		if(right)
			cam.move(-0.1f,0);//cam.rotateY(0.1f);
		
		if(up)
			cam.updown(-0.1f);//cam.rotateY(0.1f);
			
		if(down)
			cam.updown(0.1f);//cam.rotateY(0.1f);
			
		
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
		cam.rotateY(-1f);
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
		cam.rotateY(1f);
		
	}
	
	private static void render() {
		Cube cube = new Cube();
		glPushMatrix();
		{
			glColor3f(1.0f,0.5f,0f);
			glTranslatef(0,0,-10);
			//glRotatef(x,1,1,0);
			
			//cube.getColorCube(10f, 10f, 10f, 0f, 0f, 0f);
			
			/*cube.getColorCube(0f, 0f, 0f,1f);
			cube.getColorCube(3f, 0f, 0f,1f);
			cube.getColorCube(0f, 3f, 0f,1f);
			cube.getColorCube(-3f, 0f, 0f,1f);*/
			
			/*
			for (int i=-5;i<6; i++){
				for (int j=-5;j<6;j++){
					int tavolsag  =2;
					float Z = j * tavolsag;
					float X = i * tavolsag;
					cube.getColorCube(X, -4f, Z,1f);
				}
			
			}	*/
			
			int i = 0;
			for (Building building : buildings) {
				
					cube.getColorCube(
						building.getSize().getX()
						,building.getSize().getY()
						,building.getSize().getZ()
						,building.getLocation().getX()-60
						,building.getLocation().getY()-90
						,building.getLocation().getZ()-180
						);  
				i++;
			}
			
		}
		glPopMatrix();
		
	}
	
	public static void cubeWithParameters(float sizeX,float sizeY,float sizeZ,float locX,float locY,float locZ ) {
		//glTranslatef(0f,0.01f,0f);    

	    GL11.glTranslatef(locX,locY,locZ);             
	    //GL11.glRotatef(66.0f,0.0f,0.0f,146.0f);  
	    GL11.glScalef(sizeX, sizeY, sizeZ);
	    
		glBegin(GL_QUADS);
		glColor3f(1.0f, 1.0f, 0.0f);     // Yellow
		glVertex3f( 0.1f, 0.1f, -0.1f);
	      glVertex3f(-0.1f, 0.1f, -0.1f);
	      glVertex3f(-0.1f, 0.1f,  0.1f);
	      glVertex3f( 0.1f, 0.1f,  0.1f);
	 
	      // Bottom face (y = -1.0f)
	      glColor3f(1.0f, 1.0f, 0.0f);     // Yellow
	      glVertex3f( 0.1f, -0.1f,  0.1f);
	      glVertex3f(-0.1f, -0.1f,  0.1f);
	      glVertex3f(-0.1f, -0.1f, -0.1f);
	      glVertex3f( 0.1f, -0.1f, -0.1f);
	 
	      // Front face  (z = 1.0f)
	      glColor3f(1.0f, 0.0f, 0.0f);     // Red
	      glVertex3f( 0.1f,  0.1f, 0.1f);
	      glVertex3f(-0.1f,  0.1f, 0.1f);
	      glVertex3f(-0.1f, -0.1f, 0.1f);
	      glVertex3f( 0.1f, -0.1f, 0.1f);
	 
	      // Back face (z = -1.0f)
	      glColor3f(1.0f, 1.0f, 0.0f);     // Yellow
	      glVertex3f( 0.1f, -0.1f, -0.1f);
	      glVertex3f(-0.1f, -0.1f, -0.1f);
	      glVertex3f(-0.1f,  0.1f, -0.1f);
	      glVertex3f( 0.1f,  0.1f, -0.1f);
	 
	      // Left face (x = -1.0f)
	      glColor3f(0.0f, 0.0f, 1.0f);     // Blue
	      glVertex3f(-0.1f,  0.1f,  0.1f);
	      glVertex3f(-0.1f,  0.1f, -0.1f);
	      glVertex3f(-0.1f, -0.1f, -0.1f);
	      glVertex3f(-0.1f, -0.1f,  0.1f);
	 
	      // Right face (x = 1.0f)
	      glColor3f(1.0f, 0.0f, 1.0f);     // Magenta
	      glVertex3f(0.1f,  0.1f, -0.1f);
	      glVertex3f(0.1f,  0.1f,  0.1f);
	      glVertex3f(0.1f, -0.1f,  0.1f);
	      glVertex3f(0.1f, -0.1f, -0.1f);

		glEnd();
	}
	
	public static void cleanUp()
	{
		Display.destroy();
	}
	
	public static void initDisplay()
	{
		try
		{
			Display.setDisplayMode(new DisplayMode(800,600));
			Display.create();
		}
		catch (LWJGLException ex)
		{
			Logger.getLogger(LwjglMain3d.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	private static void select(int x, int y ){
	            // The selection buffer
	            IntBuffer selBuffer = ByteBuffer.allocateDirect(1024).order(ByteOrder.nativeOrder()).asIntBuffer();
	            int buffer[] = new int[256];           

	            IntBuffer vpBuffer = ByteBuffer.allocateDirect(64).order(ByteOrder.nativeOrder()).asIntBuffer();
	            // The size of the viewport. [0] Is <x>, [1] Is <y>, [2] Is <width>, [3] Is <height>
	                int[] viewport = new int[4];           

	            // The number of "hits" (objects within the pick area).
	            int hits;
	            // Get the viewport info
	                GL11.glGetInteger(GL11.GL_VIEWPORT, vpBuffer);
	                vpBuffer.get(viewport);           

	            // Set the buffer that OpenGL uses for selection to our buffer
	            GL11.glSelectBuffer(selBuffer);          

	            // Change to selection mode
	            GL11.glRenderMode(GL11.GL_SELECT);          

	            // Initialize the name stack (used for identifying which object was selected)
	            GL11.glInitNames();
	            GL11.glPushName(0);
	           

	            GL11.glMatrixMode(GL11.GL_PROJECTION);
	            GL11.glPushMatrix();
	            GL11.glLoadIdentity();           

	            /*  create 5x5 pixel picking region near cursor location */
	            GLU.gluPickMatrix( (float) x, (float) y, 5.0f, 5.0f,IntBuffer.wrap(viewport));           

	            GLU.gluPerspective(70,(float)Display.getWidth()/(float)Display.getHeight(),0.3f,1000);
	            render();
	            GL11.glPopMatrix();
	            // Exit selection mode and return to render mode, returns number selected

	            hits = GL11.glRenderMode(GL11.GL_RENDER);
	            System.out.println("hits: " + hits);           

	            selBuffer.get(buffer);
	                // Objects Were Drawn Where The Mouse Was
	                if (hits > 0) {
	                      // If There Were More Than 0 Hits
	                      int choose = buffer[3]; // Make Our Selection The First Object
	                      int depth = buffer[1]; // Store How Far Away It Is
	                      //System.out.println("mélység: " + buffer[1]);
	                      for (int i = 1; i < hits; i++) {
	                            // Loop Through All The Detected Hits
	                            // If This Object Is Closer To Us Than The One We Have Selected
	                            if (buffer[i * 4 + 1] <  depth) {
	                                  choose = buffer[i * 4 + 3]; // Select The Closer Object
	                                  depth = buffer[i * 4 + 1]; // Store How Far Away It Is
	                            }
	                      }
	                      if (depth <-177724416){
	                    	  System.out.println("Chosen: " + choose + " mélysége: "+depth);
	                      }else{
	                    	  System.out.println("öcsém ezt bebuktad, túl messze van");
	                      }
	                }
	    } 
}