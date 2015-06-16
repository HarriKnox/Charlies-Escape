import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PrisonStaffRoom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PrisonStaffRoom extends PrisonTemplate
{

    public PrisonStaffRoom()
    {
        prepare();
    }
    
    public void prepare()
    {
        this.addObject(new PrisonGround(), this.getWidth()/2, this.getHeight() - 5);
        
        this.addObject(new PrisonStaffDoor(), 540, 360);
        this.addObject(new PrisonTable(), 310, 375);
        this.addObject(new StaffFiles(), 420, 332);
        
        BackToCellPoint bp = new BackToCellPoint();
        this.addObject(bp, bp.getImage().getWidth()/2, 360);
        
        this.addObject(new PreviousLevel(), 10, this.getHeight() - 30);
        
        this.addObject(new VictorCharlie(), 50, 360);
    }
    
    public void nextLevel(){
        TemplateStage.firstStage();
    }
    
    public void previousLevel(){
        Greenfoot.setWorld(new PrisonStart(true));
    }
}
