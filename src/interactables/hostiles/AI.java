package interactables.hostiles;

import interactables.Collision;
import levels.Level;


public class AI extends Collision
{	
	public boolean playerSideL = false; //For AI to track santa
	public boolean facingL = true; //is enemy facing left
	public boolean collidingWPlayer = false; //is the collision box touching player
	public int actionStatus = 0; //Used to tell what sprite set to draw
	public int swordTimer = 0; //timer for sword animation when player is spotted.
	
	//Follows santa until hitboxes touch
	public void normalAI()
	{
		if(!isDead)
		{
			collisionBox.makeSolidObject((int)coords.x + 55, (int)coords.y + 70, 85, 121); //hitbox for normal GB
			checkPlayer();
			
			if(!collidingWPlayer)
			{
				if(playerSideL)
					moveLeft();
				else
					moveRight();
			}
			staticCollision();
		}
	}
	
	/**
	 * This is the AI method for the {@link GBShield} enemy.
	 * <br><br>
	 * The AI follows santa and guards when within 200 pixels of player.
	 */
	public void shieldAI()
	{
		if(!isDead)
		{
			collisionBox.makeSolidObject((int)coords.x + 45, (int)coords.y + 77, 78, 120); //hitbox for shield GB
			checkPlayer();
			
			isInvincible = true; //makes Shield enemy invincible when within 200 pixels of santa (guarding)
			
			if(Math.abs(Level.player.coords.x - coords.x) > 200) //checks if he is within 200 pixels of santa
			{
				isInvincible = false; //makes shield enemy not invincible when more than 200 pixels away form santa (walking)
				
				if(playerSideL)
					moveLeft();
				else
					moveRight();
			}	
			staticCollision();
		}
	}
	
	/**
	 * This is the AI Method for the {@link GBSword} enemy.
	 * <br><br>
	 * The AI follows the player until he gets within 300 pixels.
	 * When he does he stays in place for a moment then runs towards the player
	 */
	public void swordAI()
	{
		if(!isDead)
		{
			collisionBox.makeSolidObject((int)coords.x + 40, (int)coords.y + 73, 85, 120); //hitbox for shield GB
			checkPlayer();
			
			if(Math.abs(Level.player.coords.x - coords.x) > 300) //checks if he is within 200 pixels of santa
			{
				if(playerSideL)
					moveLeft();
				else
					moveRight();
			}
			else
			{
				if(swordTimer >= 10)
				{
					if(playerSideL)
					{
						moveLeft();
						moveLeft();
					}
					else
					{
						moveRight();
						moveRight();
					}
				}
				swordTimer++;
			}
			staticCollision();
		}
	}
	
	//Checks what side santa is relative to the enemy
	public void checkPlayer()
	{
		if(Level.player.coords.x > coords.x)
			playerSideL = false;
		else
			playerSideL = true;
		
		if(collisionBox.isCollidingWith(Level.player.collisionBox))
		{
			collidingWPlayer = true;
			Level.player.isDead = true;
		}
		else
			collidingWPlayer = false;
	}
	
	//Starts walking Left
	public void moveLeft()
	{
		facingL = true;
		coords.x -= 6;
		swapAnimation("walk");
	}
	
	//Starts walking right
	public void moveRight()
	{
		facingL = false;
		coords.x += 6;
		swapAnimation("walk");
	}
	
	/**
	 * This method switches the {@link #actionStatus} the enemy is in.<br>
	 * The action statuses corresponding to the {@link Enemy#animationTypes}.
	 * 
	 * @param swapTo - Valid parameters are idle, walk, death, and attack.
	 */
	public void swapAnimation(String swapTo)
	{
		for(int x = 0; x <= 3; x++)
		{
			if(swapTo.equals(Enemy.animationTypes[x]))
			{
				actionStatus = x;
			}
		}
	}
}
