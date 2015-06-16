import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Arrows shot at Charlie
 * 
 * The Arrow moves to the bot of the screen and then expires. If it hits Charlie
 * on the way, it kills Charlie, then disappears.
 */
public class Arrow  extends Arrows
{
    public Arrow(){
        this.setRotation(180);
    }
    
    protected void makeMovement(){
        this.move(10);
    }
}