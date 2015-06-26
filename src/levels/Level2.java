package levels;

import interactables.hostiles.Enemy;
import interactables.hostiles.GBNormal;
import interactables.hostiles.GBShield;
import interactables.hostiles.GBSword;
import interactables.player.PlayerControls;
import interactables.player.Santa;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import main.MainMenu;

/**
 * This is the Level 2 class.<br>
 * This class contains the music, backgrounds & 
 * {@link #playLevel(Graphics)} method for level 2
 * 
 * @author Ozaner Hansha
 */
public class Level2 extends Level
{
	/**
	 * This is an array of all background images for level 2.
	 * @see #drawEnvironment(Graphics)
	 */
	public static ArrayList<Image> levelBackgrounds = new ArrayList<Image>();
	
	/**
	 * This is the music file that plays in level 2
	 */
	public static File music;
	
	//Refreshes level (Keep repainting)
	public void playLevel(Graphics art) throws Exception
	{
		if(!MainMenu.init)
			initLevel2();
		drawEnvironment(art);
		pauseCheck();
		for(int x = 0; x != Enemy.enemyList.size(); x++)
		{
			Enemy.enemyList.get(x).refreshEnemy(art);
		}
	}
	
	//redraws up environment of level with scrolling
	public void drawEnvironment(Graphics art)
	{
		for(int x = 0; x < 2; x++) //Draws the grounds in the right x coord
		{
			art.drawImage(levelBackgrounds.get(0), x*3064 + (int)PlayerControls.xShiftStatic/2, 0, null); //draws background
			art.drawImage(levelBackgrounds.get(x+1), x*3064 + (int)PlayerControls.xShiftStatic, 0, null); //draws middleground
		}
		for(int x = 1; x < 2; x++)
		{
			art.drawImage(levelBackgrounds.get(x+1), (x+1)*3064 +(int)PlayerControls.xShiftStatic, 0, null); //draws middleground
		}
	}
	
	//Initializes Level
	public void initLevel2() throws Exception
	{	
		//resets enemies and type
		E1 = new GBSword(1500.0, (double)groundLevel);
		E2 = new GBNormal(1900.0, (double)groundLevel);
		E3 = new GBShield(2300.0, (double)groundLevel);
		E4 = new GBSword(2700.0, (double)groundLevel);
		E5 = new GBSword(3400.0, (double)groundLevel);
		E6 = new GBNormal(3500.0, (double)groundLevel);
		E7 = new GBShield(4500.0, (double)groundLevel);
		E8 = new GBSword(4900.0, (double)groundLevel);
		E9 = new GBNormal(5100.0, (double)groundLevel);
		E10 = new GBNormal(5500.0, (double)groundLevel);
		E11 = new GBNormal(5900.0, (double)groundLevel);
		E12 = new GBShield(6200.0, (double)groundLevel);
		E13 = new GBSword(6400.0, (double)groundLevel);
		E14 = new GBSword(6700.0, (double)groundLevel);
		E15 = new GBShield(7100.0, (double)groundLevel);
		player = new Santa(100.0, (double)groundLevel);
		
		initLevel();
		
		//start level2 music
		ClassLoader cl = this.getClass().getClassLoader();
		AudioInputStream sound = AudioSystem.getAudioInputStream(cl.getResource("music/level2Music.wav"));
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
