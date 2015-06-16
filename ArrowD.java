import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ArrowD here.
 * 
 * @author (Ben Griffith) 
 * @version (a version number or a date)
 */
public class ArrowD extends Arrows{   
    public ArrowD(){
        this.setRotation(90);
    }
    
    protected void makeMovement(){
        this.move(6);
    }
}
