package levels;

import interactables.Collision;
import interactables.hostiles.Enemy;
import interactables.player.PlayerControls;
import interactables.player.Santa;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

import javax.sound.sampled.Clip;

import main.MainClass;
import main.MainMenu;
import main.SolidObject;
import main.UserInput;

/**
 * This Class is the super class for all other level classes.<br>
 * It includes pause mechanics and a level reset method.
 * @author Ozaner Hansha
 */
public class Level
{
	/**
	 * This clip controls audio playback for all levels
	 */
	public static Clip clip;
	
	/**
	 * This is an invisible hitbox to denote the left side of the window.<br>
	 * The player cannot pass this object.
	 */
	public static SolidObject leftWall = new SolidObject();
	
	/**
	 * This is the y coordinate of the ground. All interactables start at this y.
	 */
	public static int groundLevel = MainClass.windowSize.height - MainClass.windowSize.height / 3;
	
	/**
	 * This is an invisble point to denote where the screen starts to scroll.<br>
	 * When the play move beyond this point, the screen starts to shift to the left.
	 * @see PlayerControls
	 */
	public static Point2D.Double pointOfScroll = new Point2D.Double(MainClass.windowSize.height / 1.3, groundLevel);

	/**
	 * This is the player object that the user controls.
	 * @see Santa
	 */
	public static Santa player = new Santa(100.0, (double)groundLevel);
	
	/**
	 * This is one of the enemy objects.
	 * It can be configured into any type of enemy in the level classes.
	 */
	public static Enemy E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14, E15;
	
	/**
	 * This boolean is set to true when p is pressed ({@link #pauseCheck()}) during a level.<br>
	 * When it is true the game paints the {@link #pause(Graphics)} menu
	 */
	public static boolean isPause = false;
	
	/**
	 * This is the background painted when the game pauses
	 */
	public static Image pauseBG;
	
	/**
	 * This is a level object. It reforms all the 
	 * level methods require to play the level.
	 */
	public static Level level1Obj = new Level1(), level2Obj = new Level2();
	
	/**
	 * This method creates the left wall and adds it to the 
	 * {@link #staticObjects} array for collision.
	 * This method is run only once in the {@link MainClass#loadGame} method.
	 */
	public static void leftWall()
	{
		leftWall.makeSolidObject(-1, -1, 1, main.MainClass.windowSize.height);
		Collision.staticObjects.add(leftWall);
	}
	
	/**
	 * Checks if p has been pressed while playing a level.<br>
	 * Is constantly ran in the {@link #playLevel} method of all levels.
	 */
	public void pauseCheck()
	{
		if(MainMenu.currentMenu == 1 && !(MainMenu.currentLevel == 0) && UserInput.pPressed == true) //checks for pause
		{
			Level.isPause = true;
			UserInput.pPressed = false;
		}
	}
	
	/**
	 * This method draws the pause menu.
	 * <br><br>
	 * When {@link #isPause} is true the {@link #main.MainMenu.showMenu(Graphics art)}
	 * method runs this method and doesn't refresh the enemy, player & level.
	 * 
	 * @param art - This is the Graphics object used
	 * throughout the game to paint to the screen.
	 */
	public static void pause(Graphics art)
	{
		art.drawImage(pauseBG, 0, 0, MainClass.windowSize.width, MainClass.windowSize.height, null); //draws pause background
		
		MainMenu.mouseBox = new Rectangle((int)UserInput.mouseCoords.x, (int)UserInput.mouseCoords.y, 1, 1);
		if((UserInput.leftClick && MainMenu.mouseBox.intersects(MainMenu.box1)) || UserInput.pPressed)
		{
			UserInput.leftClick = false;
			isPause = false;
			UserInput.pPressed = false;
		}
		if(UserInput.leftClick && MainMenu.mouseBox.intersects(MainMenu.box3))
		{
			player.isDead = true;
			UserInput.leftClick = false;
			
			isPause = false;
		}
	}
	
	/**
	 * This method resets a level when it is first played.
	 * <br><br>
	 * It resets the enemies' death, resets screen scrolling (xShift), 
	 * and stops the main menu's music
	 * 
	 * @see #playLevel(Graphics)
	 */
	public void initLevel()
	{
		//resets enemy death status
		for(int x = 0; x != Enemy.enemyList.size(); x++)
		{
			Enemy.enemyList.get(x).isDead = false;
		}
		
		//reset screen scrolling/player
		PlayerControls.xShiftAmount = 0;
		PlayerControls.xShiftStatic = 0;
		PlayerControls.xShiftIs = false;
		player.coords.x = 100.0;
		
		//Reset menu, stop menu music
		MainMenu.init = true;
		MainMenu.clip.close();
	}
	
	/**
	 * This Method plays the level.<br>
	 * This means it refreshes the enemies, player, and environment
	 * @param art - This is the Graphics object used
	 * throughout the game to paint to the screen.
	 */
	public void playLevel(Graphics art) throws Exception{}
	
}