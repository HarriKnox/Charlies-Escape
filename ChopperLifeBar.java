import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color.*;

/**
 * Write a description of class ChopperEnergyBar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ChopperLifeBar extends Positional
{
    public void act() 
    {
        ChopperBoss boss = (ChopperBoss) (this.getWorld().getObjects(ChopperBoss.class)).get(0);
        
        float energy = boss.getLife();
        float chopperInitialLife = boss.getInitialLife();
        
        float w = this.getWorld().getWidth() - 50, h = 20;
        
        GreenfootImage img = new GreenfootImage((int) w, (int) h);
        
        img.setColor(java.awt.Color.GREEN);
        img.fillRect(0, 0, (int) (w*(energy/chopperInitialLife)), (int) h);
        
        img.setColor(java.awt.Color.RED);
        img.fillRect((int) (w*(energy/chopperInitialLife)), 0,(int) (w - w*(energy/chopperInitialLife)), (int) h);
        
        this.setImage(img);
    }
}
