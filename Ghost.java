import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ghost here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ghost extends Positional
{
    private int transparency = 255;
    
    public void act() 
    {
        this.goUpAndBeyond();
        this.fadeOut();
        this.removeItSelf();
    }
    
    public void goUpAndBeyond(){
        this.setLocation(this.getX(), this.getY() - 1);
    }
    
    public void fadeOut(){
        this.transparency -= 1;
        if(this.transparency < 0){
            this.transparency = 0;
        }
        this.getImage().setTransparency((int) this.transparency);
    }
    
    private void removeItSelf(){
        if(this.getY() < -10 || this.getImage().getTransparency() == 0)
            this.getWorld().removeObject(this);
    }
}
