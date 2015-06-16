import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class BulletsPoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BulletsPoint extends ActionPoint
{
    private long blinkTimer = 0;
    private long blinkCounter = 0;
    private boolean onGround = false, gone = false;
    
    public void act(){
        if(!this.onGround)
            this.fall();
        else{
            this.blink();
        }
        if(!this.gone)
            super.act();
    }
    
    protected void action()
    {
        VictorCharlie vc = (VictorCharlie) this.getOneIntersectingObject(VictorCharlie.class);
        Random randomGenerator = new Random();
        int b = randomGenerator.nextInt(10) + 10;
        vc.addBullets(b);
        this.getWorld().removeObject(this);
    }
    
    private void fall(){
        if(this.getOneIntersectingObject(Solid.class) == null)
            this.setLocation(getX(), getY() + 1);
        else
            this.onGround = true;
    }
    
    private void blink(){
        if(System.currentTimeMillis() - this.blinkTimer >= 500){
            this.blinkTimer = System.currentTimeMillis();
            int alpha = this.getImage().getTransparency();
            if(alpha == 0)
                this.getImage().setTransparency(255);
            else
                this.getImage().setTransparency(0);
            this.blinkCounter++;
        }
        if(this.blinkCounter == 10){
            this.getWorld().removeObject(this);
            this.gone = true;
        }
    }
}
