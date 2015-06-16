import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PrisonTable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PrisonTable extends ActionPoint
{
    private boolean first_time = true;
    private WhatHappened wh = new WhatHappened();
    private StoryText st = new StoryText();
    private boolean onScreen = false;
    private long appearTime = 0, cooldown = 1;
    
    public void act(){
        super.act();
        if(this.cooldown > 0)
            this.cooldown--;
    }
    
    protected void action()
    {
        if(this.first_time)
        {
            this.getWorld().addObject(this.wh, this.getX(), this.getY() - wh.getImage().getHeight() - 35);
            this.wh.fadeIn();
        }
        this.first_time = false;
    }
    
    protected void behavior()
    {
        if(!this.onScreen){
            if(this.cooldown == 0){
                this.getWorld().addObject(this.st, this.getWorld().getWidth()/2, this.getWorld().getHeight()/2);
                this.onScreen = true;
                this.appearTime = System.currentTimeMillis();
            }
        }
        else {
            if(System.currentTimeMillis() - this.appearTime > 500)
            {
                this.getWorld().removeObject(this.st);
                this.onScreen = false;
                this.cooldown = 10;
            }
        }
    }
    
    protected void gotOut(){
        if(this.onScreen) {
            this.getWorld().removeObject(this.st);
            this.onScreen = false;
        }
    }
}
