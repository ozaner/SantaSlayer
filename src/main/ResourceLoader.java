package main;

import interactables.FlagPole;
import interactables.hostiles.Enemy;
import interactables.hostiles.GBNormal;
import interactables.hostiles.GBShield;
import interactables.hostiles.GBSword;
import interactables.player.Present;
import interactables.player.Santa;

import java.awt.Image;

import javax.swing.ImageIcon;

import levels.Level;
import levels.Level1;
import levels.Level2;

/**
 * This Class handles the game's resources
 *
 * @author Ozaner Hansha
 */
public class ResourceLoader
{
	/**
	 * This is the non-static object that actually
	 * calls the {@link loadResource} Method.
	 */
	public static ResourceLoader loaderObj = new ResourceLoader();
	
	/**
	 * Image For Main Class <br>
	 * Shown when application starts.
	 */
	public static Image loadScreen, windowIcon;
	
	/**
	 * Loads menu screens. <br>
	 * @see MainMenu
	 */
	public void menuResources()
	{
		ClassLoader cl = this.getClass().getClassLoader();
		MainMenu.mainBG = new ImageIcon(cl.getResource("background/mainMenu/mainBG.png")).getImage();
		MainMenu.levelBG = new ImageIcon(cl.getResource("background/mainMenu/levelBG.png")).getImage();
		MainMenu.instructionBG = new ImageIcon(cl.getResource("background/mainMenu/instructionBG.png")).getImage();
		MainMenu.gameWonBG = new ImageIcon(cl.getResource("background/mainMenu/gameWonBG.png")).getImage();
		MainMenu.gameOverBG = new ImageIcon(cl.getResource("background/mainMenu/gameOverBG.png")).getImage();
	}

	/**
	 * Loads level backgrounds. <br>
	 * @see Level
	 * @see Level1
	 * @see Level2
	 */
	public void levelResources()
	{
		ClassLoader cl = this.getClass().getClassLoader();
		
		Level.pauseBG = new ImageIcon(cl.getResource("background/mainMenu/pauseBG.png")).getImage();
		
		for(int x = 0; x != 7; x++)
		{
			Level1.levelBackgrounds.add(new ImageIcon(cl.getResource("background/level1/background"+ x +".png")).getImage());
		}
		
		for(int x = 0; x != 4; x++)
		{
			Level2.levelBackgrounds.add(new ImageIcon(cl.getResource("background/level2/background"+ x +".png")).getImage());
		}
	}
	
	/**
	 * Loads animation for GBNormal enemy. <br>
	 * @see GBNormal
	 */
	public void normalResources()
	{
		ClassLoader cl = this.getClass().getClassLoader();
		for(int y = 0;  y <= 3; y++)
		{
			for(int x = 0; x <= 3; x++)
			{
				GBNormal.animation.put(Enemy.animationTypes[y] + x, new ImageIcon(cl.getResource("characters/gbNormal/" + Enemy.animationTypes[y] + "/" + Enemy.animationTypes[y] + x + ".png")).getImage());
			}
		}
	}
	
	/**
	 * Loads animation for GBShield enemy. <br>
	 * @see GBShield
	 */
	public void shieldResources()
	{
		ClassLoader cl = this.getClass().getClassLoader();
		for(int y = 0;  y <= 3; y++)
		{
			for(int x = 0; x <= 3; x++)
			{
				GBShield.animation.put(Enemy.animationTypes[y] + x, new ImageIcon(cl.getResource("characters/gbShield/" + Enemy.animationTypes[y] + "/" + Enemy.animationTypes[y] + x + ".png")).getImage());
			}
		}
	}

	/**
	 * Loads animation for GBSword enemy. <br>
	 * @see GBSword
	 */
	public void swordResources()
	{
		ClassLoader cl = this.getClass().getClassLoader();
		for(int y = 0;  y <= 3; y++)
		{
			for(int x = 0; x <= 3; x++)
			{
				GBSword.animation.put(Enemy.animationTypes[y] + x, new ImageIcon(cl.getResource("characters/gbSword/" + Enemy.animationTypes[y] + "/" + Enemy.animationTypes[y] + x + ".png")).getImage());
			}
		}
	}
	
	/**
	 * Loads images related to player.
	 * @see Santa
	 * @see Present
	 * @see FlagPole
	 */
	public void santaResources()
	{
		ClassLoader cl = this.getClass().getClassLoader();
		
		FlagPole.flagPolePNG = new ImageIcon(cl.getResource("background/mainMenu/flagPole.png")).getImage();
		Present.sprite = new ImageIcon(cl.getResource("characters/santa/present.png")).getImage();
		
		for(int x = 0; x <= 16; x++)
		{
			Santa.animation.put("walk" + x, new ImageIcon(cl.getResource("characters/santa/santaPNG" + x + ".png")).getImage());
		}	
	}
	
	/**
	 * This method loads all the images for 
	 * all the levels & characters, Only needs to run once
	 * @see MainClass#main(String[])
	 */
	public void loadResources()
	{
		ClassLoader cl = this.getClass().getClassLoader();
		loadScreen = new ImageIcon(cl.getResource("background/mainMenu/loadingScreen.png")).getImage();
		windowIcon = new ImageIcon(cl.getResource("windowIcon.png")).getImage();
		menuResources();
		santaResources();
		levelResources();
		normalResources();
		shieldResources();
		swordResources();
	}
}