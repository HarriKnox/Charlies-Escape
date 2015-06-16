import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Score here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score extends Text
{
    /**
     * Act - do whatever the Score wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public int score1;
    public int dscore;
    public int tscore;
    public int fscore;
    public Score() {
        super("00");
    }
    public void act() 
    {
        getDeaths();
        getTime();
        checkScore();
        this.setText(String.format("%03d", this.score1));
        
    }    
    public int getDeaths()
    {
        dscore=DeathCounter.deaths;
        return dscore;
    }
    public int getTime()
    {
        tscore=Timer.millisElapsed/1000;
        return tscore;
    }
    public void checkScore()
    {
        score1 = 13*dscore+tscore;
    } // Ryan Heller
    public int getScore()
    {
        return score1;
    } // Ryan Blair and Harrison Knox
    
}
