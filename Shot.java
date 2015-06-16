import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shot extends Weapons
{
    public Shot(){
        super();
        this.damage = 5;
    }
    
    public void act() 
    {
        super.act();
        move(10);
    }
}
