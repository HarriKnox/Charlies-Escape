import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NextLevel here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NextLevel extends ActionPoint
{
    protected void action() {
        ((PrisonTemplate) this.getWorld()).nextLevel();
    }        

}
