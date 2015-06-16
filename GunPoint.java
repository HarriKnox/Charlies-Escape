import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GunPoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GunPoint extends ActionPoint
{
    protected void action()
    {
        VictorCharlie vc = (VictorCharlie) this.getOneIntersectingObject(VictorCharlie.class);
        vc.setWeapon(VictorCharlie.GUN);
        this.getWorld().removeObject(this);
    }    
}
