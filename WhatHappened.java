import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WhatHappened here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WhatHappened extends Positional
{
    private int count = 0;
    private int y_moves = 1;
    private double transparency = 0;
    
    public WhatHappened(){
        this.getImage().setTransparency((int) this.transparency);
    }
    
    public void act() 
    {
        super.act();
        this.movement();
        this.fadeIn();
    }
    
    private void movement(){
        this.setLocation(this.getX(), this.getY() + this.y_moves);
        this.count++;
        if(this.count == 30){
            this.count = 0;
            this.y_moves *= -1;
        }
    }
    
    public void fadeIn(){
        this.transparency += 5;
        if(this.transparency > 255)
            this.transparency = 255;
        this.getImage().setTransparency((int) this.transparency);
    }
}
