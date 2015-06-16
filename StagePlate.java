import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StagePlate here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StagePlate extends Board
{
    private int transparency = 255;
    
    public StagePlate(int stage){
        this.setImage(new GreenfootImage("stage" + (stage + 1) + ".png"));
    }
    
    public void act() 
    {
        this.fadeOut();
        this.removeItSelf();
    }
    
    public void fadeOut(){
        this.transparency -= 3;
        if(this.transparency < 0){
            this.transparency = 0;
        }
        this.getImage().setTransparency((int) this.transparency);
    }
    
    private void removeItSelf(){
        if(this.getImage().getTransparency() == 0)
            this.getWorld().removeObject(this);
    }   
}
