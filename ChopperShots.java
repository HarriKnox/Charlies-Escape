import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ChopperShots here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChopperShots extends KillVC
{
    /**
     * Act - do whatever the ChopperShots wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        this.move(20);
        
        World world = this.getWorld();
        if((this.getOneIntersectingObject(Solid.class) != null) ||
            (this.getX() > world.getWidth() && this.getX() < 0 &&
             this.getY() > world.getHeight() && this.getY() < 0))
        {
            world.removeObject(this);
        }
    }    
}
