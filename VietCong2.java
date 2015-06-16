import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class VietCong2 here.
 * 
 * @author (Ben Griffith) 
 * @version (a version number or a date)
 */
public class VietCong2 extends VietCongTemplate
{    
    public VietCong2(){
        this.turnImages();
    }
    
    protected void fire()
    {
       Arrow2 arrow = new Arrow2();
       getWorld().addObject(arrow, getX(),getY());
       arrow.setRotation(45);  
    }
}

