import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DeathBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeathBoard extends Board
{
    private DeathCounter counter;
    
    public DeathBoard(){
        this.counter = new DeathCounter();
    }
    
    public void addedToWorld(World world) 
    {
        world.addObject(this.counter, this.getX() + 10, this.getY());
    }
    
    public void addDeath(){
        this.counter.addScore(1);
    }
    public int getDeaths()
    {
        return this.counter.getDeaths();
    } // Ryan Blair
}
