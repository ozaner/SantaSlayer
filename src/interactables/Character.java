package interactables;

import java.awt.geom.Point2D;

import main.SolidObject;

/**
 * This class is the Super-Class of the {@link Collision} class.
 * <br>
 * It contains all the variables for an interactable.
 * (Enemy, player, present)
 *
 * @author Ozaner Hansha
 */
public class Character
{
	//Health variables
	public int health;
	public boolean isDead = false, isInvincible = false;
	
	//For collision boxing
	public SolidObject collisionBox = new SolidObject();
	
	//Graphics Stuff
	public int currentFrame = 0, idleCount = 0;
	public boolean lastDirectionL = false;
	
	//Coordinate stuff
	public Point2D.Double coords = new Point2D.Double();
	public Point2D.Double previousCoords = new Point2D.Double(coords.x, coords.y);
}
