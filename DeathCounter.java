import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
/**
 * Write a description of class Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeathCounter extends Text
{
    /**
     * Act - do whatever the Counter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public static int deaths;
    
    public DeathCounter()
    {
        super("000");
    }
    
    public static int getDeaths()
    {
        return deaths;
    }
    
    public void addScore(int pts)
    {
        this.deaths += pts;
        String deathText = String.format("%03d", this.deaths);
        this.setText(deathText);
    }
}
