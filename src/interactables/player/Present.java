package interactables.player;

import interactables.Collision;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Point2D;

import levels.Level;

public class Present extends Collision
{
	public Point2D.Double coords = new Point2D.Double(-100, Level.groundLevel + 50); //coords of present(default out of screen)
	public static boolean presentThrown = false; //if present has started throwing animation
	public static Image sprite;
	public int presentTimer = 0;
	public boolean presentDirectionL = false; //this is the direction the present will travel in
	
	public static Present presentObj = new Present();
	
	public void refreshPresent(Graphics art)
	{
		if(presentThrown)
		{
			if(presentTimer == 0)
			{
				if(presentDirectionL)
					coords.x = Level.player.coords.x - 128;
				else
					coords.x = Level.player.coords.x + 128;
				coords.y = Level.groundLevel + 30;
				presentDirectionL = Level.player.lastDirectionL;
			}
			
			art.drawImage(sprite, (int)coords.x, (int)coords.y, null);
			
			if(presentTimer <= 15)
			{
				if(presentDirectionL)
					coords.x -= 10;
				else
					coords.x += 10;
				
				presentTimer++;
			}
			else
			{
				presentTimer = 0;
				coords.x = -1000;
				presentThrown = false;
			}
		}
		presentCollision(this);
	}
	
}
