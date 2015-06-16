import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Weapons here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weapons extends Positional
{
    protected int offsetX;
    protected int damage;
    protected boolean remove;
    
    public Weapons(){
        this.offsetX = 0;
        this.damage = 10;
        this.remove = false;
    }
    
    /**
     * Act - do whatever the Weapons wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */  
    public void act() 
    {
        this.hitSomething();
        this.outOfScreen();
        // Add your action code here.
        
        if (this.remove)
            this.getWorld().removeObject(this);
    }
    
    private void hitSomething(){
        if(this.getOneIntersectingObject(Solid.class) != null)
            this.remove = true;
    } // Harrison Knox && someone else who didn't put his name here.
    
    public int getXOffset(){
        return this.offsetX;
    }
    
    public int getDamage(){
        return this.damage;
    }
    
    private void outOfScreen(){
        World w = this.getWorld();
        if(this.getX() < 0 || this.getX() > w.getWidth() ||
           this.getY() < 0 || this.getY() > w.getHeight())
        {
                this.remove = true;
        }
    } // Harrison Knox && someone else who didn't put his name here.
}
