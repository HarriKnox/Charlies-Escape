import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Checkpoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Checkpoint extends Positional
{
    /**
     * Act - do whatever the Checkpoint wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        charlieHits();
        //Matheus de Sousa Faria
    }    
   
    private void charlieHits()
    {
        if(getOneIntersectingObject(VictorCharlie.class) != null)
        {
            setImage("checkpoint_1.png");
            TemplateStage stage = (TemplateStage) getWorld();
            stage.activateCheckpoint();
            
        }
        //Matheus de Sousa Faria
    }
}
