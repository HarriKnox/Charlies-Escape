import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ScoreBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreBoard extends Board
{
    /**
     * Act - do whatever the ScoreBoard wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private Score score;
    public ScoreBoard()
    {
        this.score=new Score();
    }
    public void addedToWorld(World world) 
    {
        world.addObject(this.score,this.getX()+10,this.getY());
    }    
    public void addScore()
    {
        this.score.getScore();
    }
}
