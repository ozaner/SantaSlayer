package interactables.hostiles;

import interactables.player.PlayerControls;

import java.awt.Graphics;
import java.util.ArrayList;

import main.MainClass;

public class Enemy extends AI
{	
	public int frameTimer = 0, deathTimer = 0;
	public static String[] animationTypes = {"walk", "idle", "attack", "death"}; //used for loading and displaying enemy
	public boolean insertedToList = false; //a boolean to see whether this enemy was added to the enemy list before
	
	/**
	 * This array list can refer to all enemy objects created
	 */
	public static ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
	
	/**
	 * This is the constructor for creating enemies.
	 * 
	 * @param x - This is the x coordinate of the enemy's spawn
	 * @param y - This is the y coordinate of the enemy's spawn
	 */
	public Enemy(Double x, Double y)
	{
		coords.x = x;
		coords.y = y;
		if(!insertedToList)
		{
			enemyList.add(this);
			insertedToList = true;
		}
	}
	
	/**
	 * If the screen is scrolling this method will scroll the enemy too.
	 * It is used in the refresh enemy method of all enemy classes.
	 * @see #refreshEnemy(Graphics)
	 */
	public void scrollXShift()
	{
		if(PlayerControls.xShiftIs)
		{
			coords.x += PlayerControls.xShiftAmount;
		}
	}
	
	/**
	 * This increases the frame of the current {@link #animationTypes} of the enemy.
	 */
	public void increaseAnimation()
	{
		if(frameTimer == 3)
		{
			if(currentFrame >= 3)
				currentFrame = 0;
			else
				currentFrame++;
			
			frameTimer = 0;
		}
		else
			frameTimer++;
	}
	
	//This checks to see if enemy hasn't moved and if so makes him stand still.
	public void idleCheck()
	{
		if(coords.x == previousCoords.x)
			idleCount++;
		else
			idleCount = 0;
		
		if(idleCount >= 3)
		{
			swapAnimation("idle");
		}
		else
		{
			swapAnimation("walk");
		}
	}
	
	//This checks to see if enemy is dead and if so plays death animation and removes enemy
	public void deathCheck()
	{
		if(isDead && deathTimer < 15)
		{
			coords = previousCoords;
			swapAnimation("death");
			deathTimer++;
		}
		if(isDead && deathTimer > 5)
		{
			coords.y = MainClass.windowSize.height + 500;
		}
	}

	public void refreshEnemy(Graphics art){}//for inheritance
}
