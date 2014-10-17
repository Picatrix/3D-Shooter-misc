import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.util.glu.GLU.gluPerspective;

import org.lwjgl.opengl.Display;

public class View
{
	public static void refresh3D()
	{
	    glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();
	    gluPerspective(70, (float)Display.getWidth() / (float)Display.getHeight(), 0.03f, 400);
	    glMatrixMode(GL_MODELVIEW);
	    glLoadIdentity();		
	}
	
	public static void refresh2D()
	{
	    glMatrixMode(GL_PROJECTION);
	    glLoadIdentity();
	    glOrtho(0.0f, Display.getWidth(), 0.0f, Display.getHeight(), 0.0f, 1.0f);
	    glMatrixMode(GL_MODELVIEW);
	    glLoadIdentity();
	}
}
