import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PreviousLevel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PreviousLevel extends ActionPoint
{
    protected void action() {
        ((PrisonTemplate) this.getWorld()).previousLevel();
    }   
}
