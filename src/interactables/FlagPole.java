/**
 * 
 */
package interactables;

import interactables.player.PlayerControls;

import java.awt.Graphics;
import java.awt.Image;

import levels.Level;

/**
 * This class controls the flag pole at the end of every level.
 *
 * @author Ozaner Hansha
 */
public class FlagPole extends Collision
{
	public static FlagPole flagPoleObj = new FlagPole();
	public static Image flagPolePNG;
	
	public void refreshFlagPole(Graphics art)
	{
		coords.y = Level.groundLevel;
		coords.x = 5000 + PlayerControls.xShiftStatic;
		art.drawImage(flagPolePNG, (int)coords.x, (int)coords.y - 70, null);
		flagPoleCollision();
	}
}