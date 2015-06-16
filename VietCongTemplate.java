import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class VietCongTemplate here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VietCongTemplate extends Physics{
    private int shotTimer = 10, initialTime = 100;
    
    private GreenfootImage[] shotting_sprite = {
        new GreenfootImage("viet_0.png"),
        new GreenfootImage("viet_1.png"),
        new GreenfootImage("viet_2.png"),
    };
    private int spriteArraySize = 3;
    private long animationDuration = 100;
    private long animationTimer;
    private int spriteNumber = 1;
    
    public VietCongTemplate() {
        this.turnImages();
    }
    
    public void act() {
        super.act();
        
        if(this.shotTimer <= this.initialTime/2){
            this.spriteNumber = 1;
        }
        else if(this.shotTimer <= this.initialTime/4){
            this.spriteNumber = 0;
        }
        
        if(this.shotTimer > 0){
            this.shotTimer--;
        }
        else{
            this.shotTimer = this.initialTime;
            this.spriteNumber = 2;
            fire();
        }
        
        this.setImage(this.shotting_sprite[this.spriteNumber]);
        die();
    }
    
    protected void fire(){       
    }
    
    public void die(){
        Actor weapon;
        weapon = this.getOneIntersectingObject(Weapons.class);
        if (weapon != null){
            World world = this.getWorld();
            world.removeObject(weapon);
            world.removeObject(this);
        }
    }
    
    protected void turnImages(){
        for(int i = 0; i < this.spriteArraySize; i++){
            this.shotting_sprite[i].mirrorHorizontally();
        }
    }
}


