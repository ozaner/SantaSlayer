package interactables;

import interactables.hostiles.AI;
import interactables.hostiles.Enemy;
import interactables.player.PlayerControls;
import interactables.player.Present;
import interactables.player.Santa;

import java.awt.Graphics;
import java.util.ArrayList;

import levels.Level;
import main.MainMenu;
import main.SolidObject;
import main.UserInput;

/**
 * This class is the Super-Class of the {@link AI} and {@link PlayerControls} Class.
 * <br>
 * This class implements to ability to collide with other objects.
 *
 * @author Ozaner Hansha
 */
public class Collision extends Character
{
	/**
	 * This is an arraylist that hols the location of all static/enemy hitboxes.
	 */
	public static ArrayList<SolidObject> staticObjects= new ArrayList<SolidObject>(),
			enemyObjects= new ArrayList<SolidObject>();
	
	/**
	 * This method checks if the player has collided with any {@link #staticObjects}.<br>
	 * This method is constantly ran in the {@link Santa#refreshPlayer(Graphics art)} method.
	 */
	public void playerCollision()
	{
		collisionBox.makeSolidObject((int)coords.x + 27, (int)coords.y + 23, 85, 112); //Santa hit box
		staticCollision();
	}
	
	/**
	 * This method will check to see if THIS
	 * is colliding with any {@link #staticObjects}.
	 * 
	 * @see SolidObject#isColliding(ArrayList)
	 */
	public void staticCollision()
	{
		if(collisionBox.isColliding(staticObjects) && UserInput.leftPressed)
			coords.x += 12 + Santa.velocity;
		
		if(collisionBox.isColliding(staticObjects) && UserInput.rightPressed)
			coords.x -= 12 - Santa.velocity;
	}
	
	/**
	 * This method checks to see if THIS collided with any 
	 * enemies and if so it sets {@link Character#isDead} to false.
	 * <br><br>
	 * This method is constantly ran in the 
	 * {@link Present#refreshPresent(java.awt.Graphics)} method
	 * 
	 * @param present - This is the {@link Present#presentObj}
	 * that is being thrown by the {@link Level#player}
	 */
	public void presentCollision(Present present)
	{
		present.collisionBox.makeSolidObject((int)present.coords.x, (int)present.coords.y, 64, 64);

		for(int x = 0; x != Enemy.enemyList.size(); x++)
		{
			if(present.collisionBox.isCollidingWith(Enemy.enemyList.get(x).collisionBox) && (!Enemy.enemyList.get(x).isInvincible))
			{
				Enemy.enemyList.get(x).isDead = true;
				Enemy.enemyList.get(x).collisionBox.makeSolidObject(0, 0, 0, 0);
			}
		}
	}
	
	public void flagPoleCollision()
	{
		collisionBox.makeSolidObject((int)coords.x, (int)coords.y, 40, 40);
		if(collisionBox.isCollidingWith(Level.player.collisionBox))
		{
			MainMenu.gameWon = true;
		}
	}
}
