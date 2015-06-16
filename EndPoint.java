import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndPoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndPoint extends Positional
{
    /**
     * Act - do whatever the EndPoint wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        this.actorReachsEndPoint();
        //Matheus de Sousa Faria
    } 
    
    private void actorReachsEndPoint()
    {
        if(this.getOneIntersectingObject(VictorCharlie.class) != null){
            TemplateStage.nextStage();
        }
        //Matheus de Sousa Faria with edits by Harrison Knox
    }
}
