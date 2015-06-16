import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Knife here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Knife extends Weapons
{
    private long start;
    
    public Knife(){
        this.offsetX = 10 + this.getImage().getWidth()/2;
        this.start = System.currentTimeMillis();
    }
    
    /**
     * Act - do whatever the Knife wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        super.act();
        this.removeAfterSeconds(0.05);
        // Add your action code here.
    }
   
    public void removeAfterSeconds(double seconds){
        if(System.currentTimeMillis() - this.start > seconds*1000){
            this.getWorld().removeObject(this);
        }
    }
}
