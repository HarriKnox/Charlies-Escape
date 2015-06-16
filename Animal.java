import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class WildAnimal here.
 * 
 * @author Matheus de Sousa Faria 
 * @version (a version number or a date)
 */
public class Animal extends KillVC
{
    /**
     * Act - do whatever the WildAnimal wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage[] running_sprite = {
        new GreenfootImage("wolf_0.png"),
        new GreenfootImage("wolf_1.png"),
        new GreenfootImage("wolf_2.png"),
        new GreenfootImage("wolf_3.png"),
        new GreenfootImage("wolf_4.png"),
    };
    private int spriteArraySize = 5;
    private long animationDuration = 100;
    private long animationTimer;
    private int spriteNumber = 0;
    
    private String direction = "right";
    
    public void act() 
    {
        this.moveInPlatform();
        this.animation();
        this.getKilled();
    }
    
    private void getKilled(){
        Weapons shot = (Weapons) this.getOneIntersectingObject(Weapons.class);
        if(shot != null){
            if(shot instanceof Shot)
                this.getWorld().removeObject(shot);
            this.getWorld().removeObject(this);
        }
    }
    
    private void moveInPlatform(){
        int x, y;
        
        y = this.getY();
        x = this.getX();
        
        if(this.direction.equals("right"))
            x++;
        else if(this.direction.equals("left"))
            x--;
        
        if(this.thereIsPlatformIn(x, y) && this.thereIsNoObstacle(x, y))
            this.setLocation(x, y);
        else{            
            if(this.direction.equals("right")){
                this.direction = "left";
                this.turnImages();
            }
            else if(this.direction.equals("left")){
                this.direction = "right";
                this.turnImages();
            }
            //this.getImage().mirrorHorizontally();
        }
    }
   
    private boolean thereIsPlatformIn(int x, int y){
        World stage = this.getWorld();
        List all_platforms = stage.getObjects(Solid.class);
        
        int bottom_y = y + this.getImage().getHeight()/2;
        int right = x + this.getImage().getWidth()/2;
        int left = x - this.getImage().getWidth()/2;
        
        for(Object p: all_platforms){
            Solid platform = (Solid) p;
            
            int platform_left = platform.getX() - platform.getImage().getWidth()/2;
            int platform_right = platform.getX() + platform.getImage().getWidth()/2;
            int platform_y = platform.getY();
            int animal_y_analized = bottom_y + platform.getImage().getHeight()/2;

            if(animal_y_analized == platform_y){
                if(x >= platform_left && x <= platform_right)
                    return true;
            }
        }
        return false;
    }
    
    private boolean thereIsNoObstacle(int x, int y){
        World stage = this.getWorld();
        List all_walls = stage.getObjects(Solid.class);
        int right = x + this.getImage().getWidth()/2;
        int left = x - this.getImage().getWidth()/2;
        
        for(Object p: all_walls){
            Solid platform = (Solid) p;
            
            int platform_left = platform.getX() - platform.getImage().getWidth()/2;
            int platform_right = platform.getX() + platform.getImage().getWidth()/2;
            int platform_y = platform.getY();

            if(platform_y == y){
                if(this.direction.equals("left") && left == platform_right){
                    return false;
                }
                else if(this.direction.equals("right") && right == platform_left){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public void animation()
    {
        if(System.currentTimeMillis() - this.animationTimer >= this.animationDuration){
            this.animationTimer = System.currentTimeMillis();
            this.spriteNumber = (this.spriteNumber + 1) % this.spriteArraySize;
            this.setImage(this.running_sprite[this.spriteNumber]);
        }  
    }
    
    private void turnImages(){
        for(int i = 0; i < this.spriteArraySize; i++){
            this.running_sprite[i].mirrorHorizontally();
        }
    }
}
