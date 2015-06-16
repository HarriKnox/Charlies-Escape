import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PrisonStaffDoor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PrisonStaffDoor extends ActionPoint
{
    protected void behavior() {
        ((PrisonTemplate) this.getWorld()).nextLevel();
    }    
}
