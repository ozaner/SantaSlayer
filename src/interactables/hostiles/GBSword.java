package interactables.hostiles;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

public class GBSword extends Enemy
{
	public static HashMap<String, Image> animation = new HashMap<>();
	
	//Constructor allows for spawning at any coords
	public GBSword(Double x, Double y)
	{
		super(x, y - 50);
		health = 1;
	}

	//Refreshes enemy graphic, coords, etc. on screen
	public void refreshEnemy(Graphics art)
	{
		scrollXShift();
		idleCheck(); //switches to idle animation if idle
		swordAI();
		deathCheck(); //checks death
		
		//paints enemy
		if(facingL)
			art.drawImage(animation.get(animationTypes[actionStatus] + currentFrame), (int)coords.x, (int)coords.y, null);
		else
			art.drawImage(animation.get(animationTypes[actionStatus] + currentFrame), (int)coords.x + animation.get(animationTypes[actionStatus] + currentFrame).getWidth(null), (int)coords.y, -animation.get(animationTypes[actionStatus] + currentFrame).getWidth(null), animation.get(animationTypes[actionStatus] + currentFrame).getHeight(null), null);
	
		previousCoords = coords; //for idle check
		increaseAnimation(); //increases whatever animation it is on
	}
}
