import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class WeaponBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WeaponBoard extends Board
{
    private String lastWeapon = "";
    private Counter bullets;
    
    public void act(){
        VictorCharlie vc = (VictorCharlie) (this.getWorld().getObjects(VictorCharlie.class)).get(0);
        String weapon = vc.getWeapon();
        if(weapon != this.lastWeapon){
            if(weapon == VictorCharlie.GUN){
                this.setImage(new GreenfootImage("gun_board.png"));
            }
            else if(weapon == VictorCharlie.KNIFE){
                this.setImage(new GreenfootImage("knife_board.png"));
            }
            else{
                this.setImage(new GreenfootImage("empty_board.png"));
            }
            this.lastWeapon = weapon;
        }
        
        if(weapon == VictorCharlie.GUN){
            if (this.bullets == null)
            {
                this.bullets = new Counter();
                this.getWorld().addObject(this.bullets, this.getX() + 10, this.getY());
            }
            this.bullets.showScore(vc.getBullets());
        }
    } // Someone else with a hint of Harrison Knox
    
}
