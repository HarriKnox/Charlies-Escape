import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
	Positional streamlines the process of getting important
	positions on inheriting object. Everything is an extention
	of Positional for the sake of ease of use. Positional
	provides methods to get the values for the top, bottom,
	left, right, height, and width of any object that is
	inheriting this.
	
	@author Harrison Knox
	@version 0.0
*/
public abstract class Positional extends Actor
{
	/**
		Returns the Y value of the top of the object.
		@return topY
	*/
	public int getTop()
	{
		return this.getY() - this.getTopHeight();
	}
	
	/**
		Returns the Y value of the bottom of the object.
		@return bottomY
	*/
	public int getBottom()
	{
		return this.getY() + this.getBottomHeight();
	}
	
	/**
		Returns the X value of the left of the object.
		@return leftX
	*/
	public int getLeft()
	{
		return this.getX() - this.getLeftWidth();
	}
	
	/**
		Returns the X value of the right of the object.
		@return rightX
	*/
	public int getRight()
	{
		return this.getX() + this.getRightWidth();
	}
	
	public int getTopHeight()
	{
		int height = this.getHeight();
		if (height % 2 == 0)
			return height / 2;
		return (height - 1) / 2;
	}
	
	public int getBottomHeight()
	{
		int height = this.getHeight();
		if (height % 2 == 0)
			return (height / 2) - 1;
		return (height - 1) / 2;
	}
	
	public int getLeftWidth()
	{
		int width = this.getWidth();
		if (width % 2 == 0)
			return width / 2;
		return (width - 1) / 2;
	}
	
	public int getRightWidth()
	{
		int width = this.getWidth();
		if (width % 2 == 0)
			return (width / 2) - 1;
		return (width - 1) / 2;
	}
	
	/**
		Returns the height of the object. It's the same as
		obj.getImage().getHeight(), but is a method of obj
		itself, instead of it's image.
		@return height
	*/
	public int getHeight()
	{
		GreenfootImage img = this.getImage();
		if (img != null)
			return img.getHeight();
		return 0;
	}
	
	/**
		Returns the width of the object. It's the same as
		obj.getImage().getWidth(), but is a method of obj
		itself, instead of it's image.
		@return width
	*/
	public int getWidth()
	{
		GreenfootImage img = this.getImage();
		if (img != null)
			return img.getWidth();
		return 0;
	}
	//Harrison Knox
}
