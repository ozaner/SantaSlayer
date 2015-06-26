package interactables.player;

import interactables.FlagPole;

import java.awt.Graphics;
import java.awt.Image;
import java.util.HashMap;

import main.UserInput;

public class Santa extends PlayerControls
{
	public static HashMap<String, Image> animation = new HashMap<>();
	
	//Constructor sets health & coords at center and ground level,
	public Santa(double x, double y)
	{
		coords.x = x;
		coords.y = y;
		health = 1;
	}
	
	//refreshes the santa object
	public void refreshSanta(Graphics art)
	{
		userInputMove(art);
		playerCollision();
		idleCheck();
		Present.presentObj.refreshPresent(art);
		FlagPole.flagPoleObj.refreshFlagPole(art);
		
		//paints santa
		if(!lastDirectionL)
			art.drawImage(animation.get("walk" + currentFrame), (int)coords.x, (int)coords.y, null);
		else
			art.drawImage(animation.get("walk" + currentFrame), (int)coords.x + 140, (int)coords.y, -150, 150, null);
		
		previousCoords = coords; //for idle check
	}
	
	//This checks to see if santa hasn't moved and if so makes him stand still.
	public void idleCheck()
	{
		if(coords.x == previousCoords.x && !(UserInput.rightPressed || UserInput.leftPressed))
			idleCount++;
		
		if(idleCount >= 2)
		{
			currentFrame = 0;
			idleCount = 0;
		}
	}
}
