import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Arrow2 here.
 * 
 * @author (Ben Griffith) 
 * @version (a version number or a date)
 */
public class Arrow2 extends Arrows
{
    private int speed = 7;
    
    protected void makeMovement(){
        this.setLocation(getX() + speed, getY() + 10);
    }
}