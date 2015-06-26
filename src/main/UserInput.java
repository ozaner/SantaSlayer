package main;

import java.awt.event.*;
import java.awt.geom.Point2D;
import levels.Level;

/**
 * This Class handles all UserInput for the game.
 * <br>
 * @see #MainClass.MainClass()
 * @author Ozaner Hansha
 */
public class UserInput implements KeyListener, MouseListener
{
	/**
	 * This is a boolean that holds the state of a key.<br>
	 * True is pressed down & False is not.
	 * @see #keyPressed(KeyEvent)
	 * @see #keyReleased(KeyEvent)
	 * 
	 * @see #mousePressed(MouseEvent)
	 * @see #mouseReleased(MouseEvent)
	 */
	public static boolean upPressed = false, leftPressed = false, downPressed = false, rightPressed = false, 
			shiftPressed = false, spacePressed = false, pPressed = false, leftClick, rightClick;
	
	/**
	 * These are the current coordinates of the mouse pointer.<br>
	 * The coords are updated whenever the mouse left clicks.
	 * @see #mouseClicked(MouseEvent)
	 */
	public static Point2D.Double mouseCoords = new Point2D.Double(0,0); //Mouse Coordinates
	
	//Key bindings (sets true)
	@SuppressWarnings("static-access")
	@Override
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		if(key == e.VK_UP)
			upPressed = true;
		if(key == e.VK_LEFT)
		{
			leftPressed = true;
			Level.player.lastDirectionL = true;
		}
		if(key == e.VK_DOWN)
			downPressed = true;
		if(key == e.VK_RIGHT)
		{
			rightPressed = true;
			Level.player.lastDirectionL = false;
		}
		if(key == e.VK_SPACE)
			spacePressed = true;
		if(key == e.VK_P)
			pPressed = true;
		if(key == e.VK_SHIFT)
			shiftPressed = true;
	}

	//Key bindings (sets false)
	@SuppressWarnings("static-access")
	@Override
	public void keyReleased(KeyEvent arg0)
	{
		int key = arg0.getKeyCode();
		if(key == arg0.VK_UP)
			upPressed = false;
		if(key == arg0.VK_LEFT)
			leftPressed = false;
		if(key == arg0.VK_DOWN)
			downPressed = false;
		if(key == arg0.VK_RIGHT)
			rightPressed = false;
		if(key == arg0.VK_SPACE)
			spacePressed = false;
		if(key == arg0.VK_P)
			pPressed = false;
		if(key == arg0.VK_SHIFT)
			shiftPressed = false;
	}

	//Not Used (put here to prevent Error)
	@Override
	public void keyTyped(KeyEvent e) {}

	//Mouse Bindings (sets true)
	@SuppressWarnings("static-access")
	@Override
	public void mousePressed(MouseEvent e)
	{	
		int key = e.getButton();
		
		mouseCoords.x = e.getX(); //records x and y coords of mouse
		mouseCoords.y = e.getY(); //whenever mouse clicked
		
		if(key == e.BUTTON1)
			leftClick = true;
		if(key == e.BUTTON2)
			rightClick = true;
	}

	//Mouse Bindings (sets false)
	@SuppressWarnings("static-access")
	@Override
	public void mouseReleased(MouseEvent e)
	{
		int key = e.getButton();
		
		if(key == e.BUTTON1)
			leftClick = false;
		if(key == e.BUTTON2)
			rightClick = false;
	}

	//Not Used (put here to prevent Error)
	@Override
	public void mouseEntered(MouseEvent e){}
	
	//Not Used (put here to prevent Error)
	@Override
	public void mouseExited(MouseEvent e){}
	
	//Not Used (put here to prevent Error)
	@Override
	public void mouseClicked(MouseEvent e){}
}
