import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StaffFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StaffFiles extends ActionPoint
{
    private boolean first_time = true;
    private Credits st = new Credits();
    private boolean onScreen = false;
    private long appearTime = 0, cooldown = 1;
    
    public void act(){
        super.act();
        if(this.cooldown > 0)
            this.cooldown--;
    }
    
    protected void action()
    {
    }
    
    protected void behavior()
    {
        if(!this.onScreen){
            if(this.cooldown == 0){
                this.getWorld().addObject(this.st, this.getWorld().getWidth()/2, this.getWorld().getHeight()/2);
                this.onScreen = true;
                this.appearTime = System.currentTimeMillis();
            }
        }
        else {
            if(System.currentTimeMillis() - this.appearTime > 500)
            {
                this.getWorld().removeObject(this.st);
                this.onScreen = false;
                this.cooldown = 10;
            }
        }
    }
    
    protected void gotOut(){
        if(this.onScreen) {
            this.getWorld().removeObject(this.st);
            this.onScreen = false;
        }
    }    
}
