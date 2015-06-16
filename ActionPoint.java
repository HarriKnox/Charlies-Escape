import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ActionPoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ActionPoint extends Positional
{
    /**
     * Act - do whatever the ActionPoint wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private boolean out = false;
    
    public void act() 
    {
        Actor charlie = this.getOneIntersectingObject(VictorCharlie.class);
        if(charlie != null){
            action();
            if(((VictorCharlie)charlie).keyPressed(VictorCharlie.SPACE)){
                behavior();
            }
            out = true;
        }
        else if(out) {
            gotOut();
        }
    }
    
    protected void action()
    {
    }
    
    protected void gotOut()
    {
    }
    
    protected void behavior()
    {
    }
}
