import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BrokenArrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BrokenArrow extends Positional
{
    int transparency = 255;
    
    public void act() 
    {
        this.fadeOut();
        if(this.transparency == 0){
            this.getWorld().removeObject(this);
        }
    }
    
    public void fadeOut(){
        this.transparency -= 5;
        if(this.transparency < 0){
            this.transparency = 0;
        }
        this.getImage().setTransparency((int) this.transparency);
    }
}
