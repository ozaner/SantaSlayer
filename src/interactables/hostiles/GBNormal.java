package interactables.hostiles;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

public class GBNormal extends Enemy
{
	public static HashMap<String, Image> animation = new HashMap<>();
	
	public GBNormal(Double x, Double y)
	{
		super(x, y - 50);
		health = 0;
	}

	//Refreshes enemy graphic, coords, etc. on screen
	public void refreshEnemy(Graphics art)
	{
		scrollXShift();
		idleCheck(); //switches to idle animation if idle
		normalAI();
		deathCheck(); //checks death
		
		//paints enemy
		if(facingL)
			art.drawImage(animation.get(animationTypes[actionStatus] + currentFrame), (int)coords.x, (int)coords.y, null);
		else
			art.drawImage(animation.get(animationTypes[actionStatus] + currentFrame), (int)coords.x + 200, (int)coords.y, -200, 210, null);
	
		previousCoords = coords; //for idle check
		increaseAnimation(); //increases whatever animation it is on
	}
}
