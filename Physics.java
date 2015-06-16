import greenfoot.*;
/**
	Gravity, acceleration and velocity, and collision for all objects in game.
	
	@author Harrison Knox
	
	@version 0.0
*/
public abstract class Physics extends Positional
{
	// There is no need for this since I fixed the vine climbing in Victor Charlie. -Harrison
    //private static int GRAVITY_VALUE = 1;
    //Matheus de Sousa Faria
    
	/**
		The X velocity of the entity.
		Positive is to the right.
	*/
	private int xVelocity;
	
	/**
		The Y velocity of the entity.
		Positive is going down.
	*/
	private int yVelocity;
	
	/**
		On every game tick, the Physics will change the Y velocity by the value of gravity
		(same as how gravity works in the real world). After it changes your y velocity to
		be more down, it checks your trajectory to see if you're going to collide with
		anything. If you are going to collide with anything, the checkTrajectory() method
		will set your X and Y velocities accordingly so you don't go through said objects.
		Then it will change the object's location by adding the X/Y velocity to the
		object's X/Y position.
	*/
	public void act()
	{
		this.changeYVelocity(this.getGravity());
		this.checkTrajectory();
		this.setLocation(this.getX() + this.getXVelocity(), this.getY() + this.getYVelocity());
	}
	
	/**
		The magnitude of acceleration due to gravity.
		Since Y increases as you go down, gravity is positive.
		This should be, and will remain, static.
	*/
	public static int getGravity() { return 1; }
	
	/** Sets X velocity */
	public void setXVelocity(int xvel) { this.xVelocity = xvel; }
	
	/** Sets Y velocity */
	public void setYVelocity(int yvel) { this.yVelocity = yvel; }
	
	/** Changes X velocity by passed value */
	public void changeXVelocity(int deltaX) { this.xVelocity += deltaX; }
	
	/** Changes Y velocity by passed value */
	public void changeYVelocity(int deltaY) { this.yVelocity += deltaY; }
	
	/** Returns X velocity */
	public int getXVelocity() { return this.xVelocity; }
	
	/** Returns Y velocity */
	public int getYVelocity() { return this.yVelocity; }
	
	/** Returns whether it's on the ground */
	public boolean onGround()
	{
		int dX = -this.getLeftWidth();
		Solid floor = null;
		while (floor == null && dX <= this.getRightWidth())
		{
			floor = (Solid)(this.getOneObjectAtOffset(dX, this.getBottomHeight() + 1, Solid.class));
			dX++;
		}
		return floor != null;
	}
	
	/**
		Follows the path that the object will travel and calculates collisions.
		This uses a modified Bresenham Line algorithm to calculate the trajectory every game-tick.
		
		¡¡DO NOT TOUCH!!
		Harrison Knox
	*/
	public void checkTrajectory()
	{
		/// First we grab the X and Y velocities and store them to local variables.
		int deltaX = this.getXVelocity();
		int deltaY = this.getYVelocity();
		
		/// If the object collides with something, these are the offsets used so it can continue to check on the other axis.
		int xOffset = 0;
		int yOffset = 0;
		
		/// If the object collided with something on one axis, the variable here for that axis is set to true, indicating that the offset value is to be used.
		boolean freezeX = false;
		boolean freezeY = false;
		
		/// These are local references to solid objects, with which this object may collide.
		Solid wall = null; // Vertical surface
		Solid platform = null; // Horizontal surface
		
		/// This is true if the triangle is wider than it is high.
		boolean wide = abs(deltaX) > abs(deltaY);
		
		/// This is the maximum number of iterations the collision checker will run.
		/// The trajectory follows the hypoteneuse of a right triangle and this is the length of the longer leg.
		int limit = (wide) ? abs(deltaX) : abs(deltaY);
		
		/// The control variable, step, starts at 0 and continues until either it becomes greater than the limit, or both the X and Y axes collided.
		for (int step = 0; step < limit && (!freezeX || !freezeY); step++)
		{
			/// These are the offsets of the center on the X and Y axes to check potential collisions at that point.
			int centerDX;
			int centerDY;
			
			
			if (wide)
			{
				/// If it is wide, centerDX is iterated through.
				centerDX = copySign(step, deltaX);
				
				/// The other axis, centerDY, is set to the approximate value based on the Bresenham algorithm.
				centerDY = round((double)(deltaY * centerDX) / (double)deltaX);
			}
			else
			{
				/// If it's not wide, do the same thing except switch the center offsets.
				centerDY = copySign(step, deltaY);
				centerDX = round((double)(deltaX * centerDY) / (double)deltaY);
			}
			
			/// If the X or Y axis has collided, then set the center to that offset value.
			if (freezeX)
				centerDX = xOffset;
			if (freezeY)
				centerDY = yOffset;
			
			/// This iterates across the width to detect collisions above or below the object.
			for (int stepX = 0; stepX < this.getWidth() && !freezeY; stepX++)
			{
				/// This is the x offset that "scans" across the top/bottom of the object detecting any collision.
				int dX;
				
				/// If the object is travelling to the left, then it scans from right-to-left; otherwise it scans from left-to-right, incrementing by control variable, stepX.
				if (deltaX < 0)
					dX = this.getRightWidth() - stepX;
				else
					dX = -this.getLeftWidth() + stepX;
				
				if (deltaY < 0)
				{
					/// If the object is travelling upwards, it searches above the object for a platform.
					platform = (Solid)(this.getOneObjectAtOffset(centerDX + dX, centerDY - this.getTopHeight() - 1, Solid.class));
					
					/// If it finds one, then it sets the Y velocity to be the the distance between the object and the platform so
					/// in the next game tick it travels that distance and becomes flushed up against the platform.
					if (platform != null)
						this.setYVelocity(platform.getBottom() + 1 - this.getTop());
				}
				else
				{
					/// If the object is travelling downwards, it searches below the object.
					platform = (Solid)(this.getOneObjectAtOffset(centerDX + dX, centerDY + this.getBottomHeight() + 1, Solid.class));
					
					/// If it finds a platform, it sets Y velocity appropriately.
					if (platform != null)
						this.setYVelocity(platform.getTop() - 1 - this.getBottom());
				}
				
				/// If a platform was found, it sets the yOffset to the current change in the Y center so it doesn't continue appempting
				/// to check through the platform. It turns freezeY on to have it use the yOffset value instead of control variable, step.
				if (platform != null)
				{
					yOffset = centerDY;
					freezeY = true;
				}
			}
			
			/// This does exactly the same thing as the for loop above, except it iterates over the height to get collisions to the left or right.
			for (int stepY = 0; stepY < this.getHeight() && !freezeX; stepY++)
			{
				int dY;
				if (deltaY < 0)
					dY = this.getBottomHeight() - stepY;
				else
					dY = -this.getTopHeight() + stepY;
				if (deltaX < 0)
				{
					wall = (Solid)(this.getOneObjectAtOffset(centerDX - this.getLeftWidth() - 1, centerDY + dY, Solid.class));
					if (wall != null)
						this.setXVelocity(wall.getRight() + 1 - this.getLeft());
				}
				else
				{
					wall = (Solid)(this.getOneObjectAtOffset(centerDX + this.getRightWidth() + 1, centerDY + dY, Solid.class));
					if (wall != null)
						this.setXVelocity(wall.getLeft() - 1 - this.getRight());
				}
				if (wall != null)
				{
					xOffset = centerDX;
					freezeX = true;
				}
			}
		}
	}
	
	/** Returns the number rounded away from zero to the nearest integer. */
	public static int round(double num) { return copySign((int)(abs(num) + 0.5), (num < 0) ? -1 : 1); }
	
	/** Returns the absolute value of a number */
	public static int abs(int num) { return (num > 0) ? num : -num; }
	
	/** Returns the absolute value of a number */
	public static double abs(double num) { return (num > 0) ? num : -num; }
	
	/** Returns the larger of two numbers */
	public static int max(int first, int second) { return (first > second) ? first : second; }
	
	/** Same as Math.copySign, but works with integers without needing to cast to float/double. */
	public static int copySign(int mag, int sign)
	{
		if ((mag >= 0) == (sign >= 0))	// if both mag and sign have the same sign, then the number already has the correct sign
			return mag;						// ie mag is negative and sign is negative, then mag >= 0 is false and sign >= 0 is false, but false == false returns true
		else							// if mag and sign are of different signs, then we return mag with the opposite sign
			return -mag;					// ie mag is negative and sign is positive, then negative-negative is positive
	}
	
	/*protected void turnOffPhysics()
    {
        Physics.GRAVITY_VALUE = 0;
        //Matheus de Sousa Faria
    }
    
    protected void turnOnPhysics()
    {
        Physics.GRAVITY_VALUE = 1;
        //Matheus de Sousa Faria
    }*/
}