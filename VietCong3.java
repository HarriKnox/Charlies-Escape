import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class VietCong2 here.
 * 
 * @author (Ben Griffith) 
 * @version (a version number or a date)
 */
public class VietCong3 extends VietCongTemplate
{
    protected void fire()
    {
        getWorld().addObject(new A4(), getX(),getY());
    }
}

