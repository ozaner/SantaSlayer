package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Main Class for Santa Slayer Game.
 * <br><br>
 * Santa Slayer is a game in which you play 
 * as Santa Claus and throw presents at Enemies.
 * <br><br>
 * Made for Intro to Computer Science 2 Class
 * @version 1.0
 * @author Ozaner Hansha
 */
@SuppressWarnings("serial")
public class MainClass extends JPanel
{	
	/**
	 * This is the window the game is put in.
	 */
	public static JFrame frame = new JFrame("Santa Slayer"); //new window titled santa slayer
	
	/**
	 * Dimensions of {@link frame}
	 */
	public static Rectangle windowSize = new Rectangle(0, 0, 1240, 720);
	
	/**
	 * A count of how long load screen is shown on screen. <br>
	 * Stops being shown when this = 50.
	 */
	public static int loadCount = 0;
	
	/**
	 * Constructor for Main Class.
	 * <bp><bp>
	 * This adds key and mouse listeners. 
	 * <bp>
	 * See {@link main.UserInput} for the input handling
	 * @throws Exception - Because I don't want to deal with exceptions.
	 */
	public MainClass() throws Exception
	{
		KeyListener keyListener = new UserInput();
		addKeyListener(keyListener);
		
		MouseListener mouseListener = new UserInput();
		addMouseListener(mouseListener);
		
		setFocusable(true);
	}
	
	@Override
	public void paint(Graphics g)
	{	
		frame.setSize(1240,720); //constantly forces this screen size
		
		super.paint(g);
		Graphics2D art = (Graphics2D) g;
		art.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(!(loadCount == 50))
		{
			setBackground(Color.black);
			art.drawImage(ResourceLoader.loadScreen, (int)(windowSize.width/1.2), (int)(windowSize.height/1.2), null);
			loadCount++;
		}
		else
		{
			try
			{
				setBackground(Color.white);
				MainMenu.showMenu(art);
			}
			catch (Exception e){e.printStackTrace();}
		}
	}

	/**
	 * The Main Method for the game
	 * <br><br>
	 * Will set up window then continue to paint game in it until app closes
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		MainClass game = new MainClass();
		frame.add(game); //adds game (Main Class) to window
	
		ResourceLoader.loaderObj.loadResources(); //Loads game
		
		frame.setSize(1240,720);
//		GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(frame); //sets window to exclusive fullscreen
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //allows for closing upon x
		frame.setIconImage(ResourceLoader.windowIcon); //Sets icon for window
		frame.setVisible(true); //shows frame
		
		while(true) //keeps looping the game
		{
			game.repaint();
			Thread.sleep(30);
		}
	}
}