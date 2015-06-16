import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrows here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Arrows extends KillVC
{
    public void act() 
    {
        this.makeMovement();
        this.removeArrow();
    }
    
    private void removeArrow(){
        World world = this.getWorld();
        
        if((this.getOneIntersectingObject(Solid.class) != null) ||
            (this.getX() > world.getWidth() && this.getX() < 0 &&
             this.getY() > world.getHeight() && this.getY() < 0))
        {
            BrokenArrow ba = new BrokenArrow();
            world.addObject(ba, this.getX(), this.getY());
            ba.setRotation(this.getRotation());
            world.removeObject(this);
        }
    }
    
    protected void makeMovement(){
    }
}
