package main;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import levels.Level;

/**
 * This Class Controls and paints the main menu.
 * <br><br>
 * This includes menu hitboxes, menu music playback, & level painting
 * 
 * @author Ozaner Hansha
 */
public class MainMenu
{	
	/**
	 * This is one of the backgrounds for the menus.
	 */
	public static Image mainBG, levelBG, instructionBG, gameWonBG, gameOverBG;
	
	/**
	 * This keeps track of what menu/level to paint on screen.
	 */
	public static int currentMenu = 0, currentLevel = 0, gameOverTimer = 0;
	
	/**
	 * This boolean keeps track of whether or not the 
	 * main menu/level has been initialized.
	 */
	public static boolean init = false, initMenu = false, gameWon = false;
	
	/**
	 * These are the hitboxes for the menu screen. 1st set is for my desktop, 
	 * 2nd set is for my laptop, 3rd set is for the monitors in the computer science lab.
	 * @see {@link UserInput}
	 */
	public static Rectangle mouseBox,
		box1 = new Rectangle((int)MainClass.windowSize.getWidth() - 420, (int)MainClass.windowSize.height - 482, 403, 56),
		box2 = new Rectangle ((int)MainClass.windowSize.getWidth() - 420, (int)MainClass.windowSize.height - 372, 403, 56),
		box3 = new Rectangle ((int)MainClass.windowSize.getWidth() - 420, (int)MainClass.windowSize.height - 262, 403, 56);
	
	/**
	 * This is the clip object that controls the playback of main menu music.
	 * @see #initMusic()
	 * @see #levelSelect()
	 */
	public static Clip clip;
	
	/**
	 * This method runs the logic for the menu controls.
	 * It should constantly be ran to check for any changes in menus.
	 * 
	 * @param art - This is the Graphics object used
	 * throughout the game to paint to the screen.
	 * @see #showMenu(Graphics)
	 */
	public static void controls(Graphics art)
	{	
		mouseBox = new Rectangle((int)UserInput.mouseCoords.x, (int)UserInput.mouseCoords.y, 1, 1);
		
		if(currentMenu == 0) //Controls for Main Menu
		{
			if(UserInput.leftClick && mouseBox.intersects(box1))
			{
				currentMenu = 1; //Level Select
				UserInput.leftClick = false;
			}
			if(UserInput.leftClick && mouseBox.intersects(box2))
			{
				currentMenu = 2; //Instructions
				UserInput.leftClick = false;
			}
			if(UserInput.leftClick && mouseBox.intersects(box3))
			{
				currentMenu = 3; //Quits Game
				UserInput.leftClick = false;
			}
		}
		
		if(currentMenu == 1 && currentLevel == 0) //Controls for Level Select 
		{
			if(UserInput.leftClick && mouseBox.intersects(box1))
				currentLevel = 1; //Level 1
			if(UserInput.leftClick && mouseBox.intersects(box2))
				currentLevel = 2; //Level 2
			if(UserInput.leftClick && mouseBox.intersects(box3))
			{
				currentMenu = 0; //Level 3
				UserInput.leftClick = false;
			}
		}
		
		if(currentMenu == 2) //Controls for instruction menu
		{
			if(UserInput.leftClick && mouseBox.intersects(box3))
			{
				currentMenu = 0; //Main Menu
				UserInput.leftClick = false;
			}
		}
	}
	
	/**
	 * This method runs and paints the main menu and by extension the whole game.
	 * 
	 * @param art - This is the Graphics object used
	 * throughout the game to paint to the screen.
	 * @throws Exception Because I don't want to deal with exceptions.
	 */
	public static void showMenu(Graphics art) throws Exception
	{
		if(!initMenu)
		{
			new MainMenu().initMusic();
			initMenu = true;
		}
		switch(currentMenu)
		{
		case 0:
			art.drawImage(mainBG, 0, 0, MainClass.windowSize.width, MainClass.windowSize.height, null);
			break;
		case 1:
			levelSelect(art);
			break;
		case 2:
			art.drawImage(instructionBG, 0, 0, MainClass.windowSize.width, MainClass.windowSize.height, null);
			break;
		case 3:
			System.exit(0);
			break;
		case 4:
			gameOver(art);
			break;
		case 5:
			gameWon(art);
			break;
		default:
			System.out.println("Main Menu Switch Error");
		}
		controls(art);
	}
	
	/**
	 * This method draws the game over screen.
	 * <br>
	 * When {@link #currentMenu} is set to 4 this method is run 
	 * by {@link #showMenu(Graphics)}. This method paints the
	 * {@link #gameOverBG} picture for 40 loops then goes back to the main menu.
	 * 
	 * @param art - This is the Graphics object used
	 * throughout the game to paint to the screen.
	 * @see #showMenu(Graphics)
	 */
	public static void gameOver(Graphics art) throws Exception
	{
		art.drawImage(gameOverBG, 0, 0, MainClass.windowSize.width, MainClass.windowSize.height, null);
		gameOverTimer++;
		
		if(gameOverTimer == 40)
		{
			currentMenu = 0;
			gameOverTimer = 0;
		}
	}
	
	/**
	 * This method draws the game over screen.
	 * <br>
	 * When {@link #currentMenu} is set to 4 this method is run 
	 * by {@link #showMenu(Graphics)}. This method paints the
	 * {@link #gameOverBG} picture for 40 loops then goes back to the main menu.
	 * 
	 * @param art - This is the Graphics object used
	 * throughout the game to paint to the screen.
	 * @see #showMenu(Graphics)
	 */
	public static void gameWon(Graphics art) throws Exception
	{
		art.drawImage(gameWonBG, 0, 0, MainClass.windowSize.width, MainClass.windowSize.height, null);
		gameOverTimer++;
		
		if(gameOverTimer == 80)
		{
			currentMenu = 0;
			gameOverTimer = 0;
			gameWon = false;
		}
	}
	
	/**
	 * This methods run when the {@link currentMenu} is at level select (= 1). <br>
	 * This method dpaints different levels depending on the {@link currentLevel}.
	 * <br><br>
	 * Level 0 draws level select menu. <br>
	 * Level 1 draws and runs level 1 <br>
	 * Level 2 draws and runs level 2 <br>
	 * 
	 * @param art - This is the Graphics object used
	 * throughout the game to paint to the screen.
	 * @throws Exception Because I don't want to deal with exceptions.
	 */
	public static void levelSelect(Graphics art) throws Exception
	{	
		switch(currentLevel)
		{
		case 0: 
			art.drawImage(levelBG, 0, 0, MainClass.windowSize.width, MainClass.windowSize.height, null); //draws Background on level select
			break;
		case 1:
			if(Level.isPause)
				Level.pause(art);
			else
			{
				Level.level1Obj.playLevel(art);
				Level.player.refreshSanta(art);
			}
			break;
		case 2:
			if(Level.isPause)
				Level.pause(art);
			else
			{
				Level.level2Obj.playLevel(art);
				Level.player.refreshSanta(art);
			}
			break;
		default:
			System.out.println("Level Select Menu Error");
		}
		gameCheck();
	}

	/**
	 * This method initializes variables that 
	 * pertain to dying if player is dead.
	 */
	public static void gameCheck()
	{
		if(Level.player.isDead)
			currentMenu = 4;
		else if(gameWon)
			currentMenu = 5;
		if(Level.player.isDead || gameWon)
		{
			Level.clip.stop();
			Level.player.isDead = false;
			init = false;
			initMenu = false;
			currentLevel = 0;
		}
	}
	
	/**
	 * This method starts the main menu {@link music} via the menu {@link clip}
	 * 
	 * @see #showMenu(Graphics)
	 * @throws Exception Because I don't want to deal with exceptions.
	 */
	public void initMusic() throws Exception
	{
		ClassLoader cl = this.getClass().getClassLoader();
		AudioInputStream sound = AudioSystem.getAudioInputStream(cl.getResource("music/menuMusic3.wav"));
		DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
	    clip = (Clip) AudioSystem.getLine(info);
	    clip.open(sound);
	    
	    clip.addLineListener(new LineListener()
	    {
	        public void update(LineEvent event)
	        {
	          if (event.getType() == LineEvent.Type.STOP)
	          {
	            event.getLine().close();
	          }
	        }
	    } );
	    
	    clip.loop(100000);
	}
}