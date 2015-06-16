import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TimeBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TimeBoard extends Board
{
    private Timer timer;
    
    public TimeBoard(){
        this.timer = new Timer();
    }
    
    public void addedToWorld(World world) 
    {
        world.addObject(this.timer, this.getX() + 8, this.getY());
    }
    
    public int getTime()
    {
        return timer.millisElapsed;
    } // Ryan Blair
}
