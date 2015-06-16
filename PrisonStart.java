import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PrisonStart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PrisonStart extends PrisonTemplate
{
    private int vcx = 10;
    private boolean otherSide = false;
    
    public PrisonStart()
    {
        prepare();
    }
    
    public PrisonStart(boolean otherSide)
    {
        this.otherSide = otherSide;
        if(otherSide){
            this.vcx = this.getWidth() - 30;
        }
        prepare();
    }
    
    public void prepare()
    {
        //Greenfoot.setSpeed(VictorCharlie.speed);
        Greenfoot.setSpeed(50);
        
        this.addObject(new PrisonGround(), this.getWidth()/2, this.getHeight() - 5);
        
        PrisonBench pb = new PrisonBench(); 
        this.addObject(pb, pb.getWidth()/2, 370);
        
        VictorCharlie vc = new VictorCharlie();
        int vcy = 340;
        if(this.otherSide)
        {
            //vc.turnLeft();
            vcy = this.getHeight() - 30;
        }
        this.addObject(vc, this.vcx, 340);
        
        this.addObject(new PrisonStartLights(), this.getWidth()/2, 220);
        
        NextLevel nl = new NextLevel();
        this.addObject(nl, this.getWidth() - nl.getImage().getWidth()/2 , this.getHeight() - nl.getImage().getHeight()/2 - 10);
        Greenfoot.start();
    }
    
    public void nextLevel(){
        Greenfoot.setWorld(new PrisonStaffRoom());
    }
    
    public void previousLevel(){
    }
}
