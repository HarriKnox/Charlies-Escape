import greenfoot.*;

public class ChopperBoss extends Positional
{
    public final static String INTRO = "intro";
    public final static String PHASE_1 = "phase1";
    public final static String DEATH = "death";
    
    public final static String LAND = "land";
    public final static String DEPART = "depart";
    
    public final static String RIGHT = "R";
    public final static String LEFT = "L";
    
    private String state = ChopperBoss.INTRO;
    private String side = ChopperBoss.LEFT;
    private int speed = 4, angle = 0;
    private boolean isDead = false;
    
    private int phase_1_counter = 0;
    private int recoil = 0;
    private int lifePoints;
    private int initialY = -1;
    
    public ChopperBoss(){
        //this.turnRight();
        this.lifePoints = this.getInitialLife();
    }
    
    public int getInitialLife() { return 100;}
    
    public void act() 
    {
        if(!this.isDead){
            if(this.initialY == -1){
                this.initialY = this.getY();
            }
            
            if(this.state == ChopperBoss.INTRO){
                this.intro();
            }
            else if(this.state == ChopperBoss.PHASE_1){
                this.phase1();
            }
            this.checkDamage();
        }
        else {
            if(this.state == ChopperBoss.LAND){
                this.land();
            }
            else if(this.state == ChopperBoss.DEPART){
                this.depart();
            }
        }
        this.setRotation(this.angle);
    }
    
    private void intro(){
        this.angle = 0;
        if(this.isOnLeft()){
            this.fromLeftToRight(false);
        }
        else {
            this.fromRightToLeft(false);
            if(this.offScreen())
                this.state = ChopperBoss.PHASE_1;
        }
    }
    
    private void phase1(){
        this.angle = 30;
        
        if(this.isOnLeft()){
            this.fromLeftToRight(true);
        }
        else {
            this.angle *= -1;
            this.fromRightToLeft(true);
            if(this.offScreen())
                this.phase_1_counter++;
        }
        if(this.phase_1_counter == 1){
            this.phase_1_counter = 0;
            this.state = ChopperBoss.INTRO; 
            if(this.getY() != this.initialY)
                this.setLocation(this.getX(), this.initialY);
            else
                this.setLocation(this.getX(), 200);
        }
    }
    
    private void land(){
        this.angle = 0;
        if(this.getY() + this.getImage().getHeight()/2 < this.getWorld().getHeight() - 20)
            this.setLocation(this.getX(), this.getY() + 1);
        VictorCharlie vc = (VictorCharlie) this.getOneIntersectingObject(VictorCharlie.class);
        if(vc != null){
            vc.turnInvisible();
            this.state = ChopperBoss.DEPART;
        }
    }
    
    private void depart(){
        
        if(this.isOnRight())
            this.setLocation(this.getX() - 2, this.getY() - 1);
        else
            this.setLocation(this.getX() + 2, this.getY() - 1);
            
        if(this.offScreen()){
            TemplateStage w = (TemplateStage) this.getWorld();
            w.nextStage();
            w.removeObject(this);
        }
    }
    
    private void checkDamage() {
        Shot shot = (Shot) this.getOneIntersectingObject(Shot.class);
        if(shot != null){
            this.lifePoints -= shot.getDamage();
            this.getWorld().removeObject(shot);
            if(this.lifePoints <= 0){
                this.isDead = true;
                this.state = ChopperBoss.LAND;
            }
        }
    }
    
    private void fromRightToLeft(boolean shouldShot){
        this.setLocation(this.getX() - this.speed, this.getY());
        if(shouldShot){
            this.shot();
        }
        if(this.offScreen()){
            this.turnLeft();
        }
    }
    
    private void fromLeftToRight(boolean shouldShot){
        this.setLocation(this.getX() + this.speed, this.getY());
        if(shouldShot){
            this.shot();
        }
        if(this.offScreen()){
            this.turnRight();
        }
    }
    
    private void shot(){
        if(this.recoil <= 0){
            ChopperShots cs = new ChopperShots();
            if(this.isOnRight())
                cs.getImage().mirrorHorizontally();
            if(this.angle < 0)
                cs.setRotation(this.angle + 180);
            else
                cs.setRotation(this.angle);
            this.getWorld().addObject(cs, this.getX(), this.getY());
            this.recoil = 7;
        }
        this.recoil--;
    }
    
    private boolean isOnRight(){
        return this.side == ChopperBoss.RIGHT;
    }
    
    private boolean isOnLeft(){
        return this.side == ChopperBoss.LEFT;
    }
    
    private boolean offScreen(){
        World w = this.getWorld();
        int left = this.getX() - this.getImage().getWidth()/2;
        int right = this.getX() + this.getImage().getWidth()/2;
        
        int bottom = this.getY() + this.getImage().getHeight()/2;
        int top = this.getY() - this.getImage().getHeight()/2;
        
        int offsetX = 400, offsetY = 300;
        return right < 0 - offsetX || left > w.getWidth() + offsetX ||
            bottom > w.getHeight() + offsetY || top < 0 - offsetY;
    }
    
    private void turnLeft(){
        if(!this.isOnLeft())
            this.getImage().mirrorHorizontally();
        this.side = ChopperBoss.LEFT;
    }
    
    private void turnRight(){
        if(!this.isOnRight())
            this.getImage().mirrorHorizontally();
        this.side = ChopperBoss.RIGHT;
    }
    
    public int getLife(){
        return this.lifePoints;
    }
}
