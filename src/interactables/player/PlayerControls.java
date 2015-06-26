package interactables.player;

import interactables.Collision;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

import levels.Level;
import main.UserInput;

public class PlayerControls extends Collision
{
	public static int cooldown = 0;
	
	public static double velocity = 0.0; //Speed built up
	
	public static boolean xShiftIs = false; //when screen is scrolling
	public static double xShiftStatic = 0; //How far the screen has moved along
	public static double xShiftAmount = 0; //Checks xshift amount to be added to interactables
	
	public int dustCount = 0;
	public static Image dust32PNG = new ImageIcon(new File("characters/santa/dustPNG32.png").toString()).getImage(),
			dust64PNG = new ImageIcon(new File("characters\\santa\\dustPNG64.png").toString()).getImage();
	
	//Handles all controls
	public void userInputMove(Graphics art)
	{
		velocityInput(art);
		checkXShiftAmount();
		basicInput();
		turnFix();
	} 

	//Handles Basic Movement input and scrolling
	public void basicInput()
	{
		if(UserInput.rightPressed && coords.x <= Level.pointOfScroll.x) //adds to coords when not past scroll point
		{
			xShiftIs = false;
			coords.x = coords.x + 12 + velocity;
			increaseAnimation();
		}
		
		if(UserInput.rightPressed && coords.x > Level.pointOfScroll.x) //adds to x shift when after point of scroll
		{
			xShiftIs = true;
			xShiftStatic += xShiftAmount;
			increaseAnimation();
		}
		
		if((!UserInput.rightPressed || UserInput.leftPressed) && coords.x > Level.pointOfScroll.x)
			xShiftIs = false;
		
		if(UserInput.leftPressed) //if left is pressed santa moves left
		{
			coords.x = coords.x - 12 - velocity;
			increaseAnimation();
		}
		
		if(UserInput.spacePressed && !Present.presentThrown) //if space bar is pressed santa jumps
		{
			Present.presentThrown = true;
			UserInput.spacePressed = false;
		}
	}
	
	//Handles Velocity related controls
	public void velocityInput(Graphics art)
	{
		if(UserInput.rightPressed && UserInput.leftPressed) //If a a and d are held Santa is stationary but velocity is built up
		{
			if(velocity < 6.0)
				velocity = velocity + 0.07;
			else
				velocity = 6.0;
			
			drawDust(art);
		}
		
		if(UserInput.shiftPressed && (UserInput.rightPressed || UserInput.leftPressed)) //if shift is held while a or d is also held, velocity increases
		{
			if(velocity < 7.0)
				velocity = velocity + 0.07;
			else
				velocity = 7.0;
		}
		
		if(!(UserInput.rightPressed && UserInput.leftPressed) && !UserInput.shiftPressed) //if "idle", velocity is 0
		{
			if(velocity > 0)
				velocity = velocity - 0.14;
			if(velocity < 0)
				velocity = 0;
		}
	}
	
	//Corrects direction when turning
	public void turnFix()
	{
		if(UserInput.rightPressed && !UserInput.leftPressed)
			lastDirectionL = false;
		if(!UserInput.rightPressed && UserInput.leftPressed)
			lastDirectionL = true;
	}
	
	//draws dust under santa's feet
	public void drawDust(Graphics art)
	{
		if(dustCount <= 5)
			art.drawImage(dust32PNG, (int)coords.x + 15, (int)coords.y + 100, null);
		if(dustCount >= 14)
			art.drawImage(dust64PNG, (int)coords.x - 35, (int)coords.y + 95, null);
		if(dustCount >= 15)
			dustCount = 0;
		
		dustCount = dustCount + 1;
	}
	
	//Checks amount that xShiftAmount should be
	public void checkXShiftAmount()
	{
		if(xShiftIs)
		{
			xShiftAmount = -12 - velocity;
		}
		else
			xShiftAmount = 0;
	}
	
	//This increases the frame of santa's movement
	public void increaseAnimation()
	{
		if(currentFrame >= 16)
			currentFrame = 1;
		else
			currentFrame = currentFrame + 1;
	}
}
