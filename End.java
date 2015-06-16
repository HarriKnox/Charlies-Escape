import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class End here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class End extends World
{
    public int deaths;
    public int time;
    public int score;
    private static final GreenfootImage background = new GreenfootImage("endgame.png");
    
    public End(int deaths, int time)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        this.deaths = deaths;
        this.time = time;
        this.score = (this.deaths * 13) + (this.time / 1000);
        prepare();
    }

    public void prepare()
    {        
        GreenfootImage currentImage = new GreenfootImage(background);
        
        GreenfootImage deathAmount = new GreenfootImage(String.format("%03d", this.deaths), 20, Color.BLACK, Color.WHITE);
        String seconds = String.format("%02d", (this.time / 1000) % 60);
        String minutes = Integer.toString(this.time / 60000);
        GreenfootImage timeAmount = new GreenfootImage(minutes + ":" + seconds, 20, Color.BLACK, Color.WHITE);
        GreenfootImage scoreAmount = new GreenfootImage(String.format("%03d", this.score), 32, Color.BLACK, Color.WHITE);
        
        currentImage.drawImage(deathAmount, 190, 215);
        currentImage.drawImage(timeAmount, 140, 265);
        currentImage.drawImage(scoreAmount, 110, 340);
        this.setBackground(currentImage);
    } // Harrison Knox and Ryan Blair

    public void act()
    {
        if( Greenfoot.mouseClicked(this))
        {
            Greenfoot.setWorld(new PrisonStart());
        }
        //Ryan Heller
    }
}
//Artwork Ryan Heller, but replaced by Ryan Blair