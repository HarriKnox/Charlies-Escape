import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class VictorCharlie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VietCong extends VietCongTemplate
{
    protected void fire()
    {
        getWorld().addObject(new Arrow(), getX(),getY());
       //Ryan Heller
    }
}

