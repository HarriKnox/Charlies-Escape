import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Charlie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VictorCharlie extends Physics
{
	
	public static int controlCounter = 0;
	public static int accumulative = 0;
	public static int signature = 0;
	public static int lastEvent = 0;
	public static int timeSinceLastEvent = 0;
	
	// Direction enums
	public final static int NEITHER = 0;
	public final static int LEFT = 1;
	public final static int RIGHT = 2;
	public final static int UP = 4;
	public final static int SPACE = 8;
	public final static int LEFTUP = LEFT + UP;
	public final static int LEFTSPACE = LEFT + SPACE;
	public final static int LEFTUPSPACE = LEFT + UP + SPACE;
	public final static int RIGHTUP = RIGHT + UP;
	public final static int RIGHTSPACE = RIGHT + SPACE;
	public final static int RIGHTUPSPACE = RIGHT + UP + SPACE;
	
	public final static String KNIFE = "knife";
	public final static String GUN = "shot";
	public final static String UNARMED = "";
	
	private final static String RUN_STATE = "running";
	private final static String IDLE_STATE = "idle";
	private final static String JUMP_STATE = "jump";
	private final static String CLIMB_STATE = "running";
	
	private int shotTimer;
	private boolean climbing;
	private String weapon;
	private int side; // Changed side to int so it can use LEFT and RIGHT instead of the string values.
	private int xSpeed;
	private long respawnTime;
	private int bullets;
	private boolean invisible;
	private boolean respawn;
	
	private HashMap<String, GreenfootImage[]> animations = new HashMap<String, GreenfootImage[]>();
	private String vcState = IDLE_STATE;
	private GreenfootImage[] runSprites = {
		new GreenfootImage("vc_runnig_0.png"),
		new GreenfootImage("vc_runnig_1.png"),
		new GreenfootImage("vc_runnig_2.png"),
		new GreenfootImage("vc_runnig_3.png"),
		new GreenfootImage("vc_runnig_4.png"),
		new GreenfootImage("vc_runnig_5.png"),
		new GreenfootImage("vc_runnig_6.png"),
		new GreenfootImage("vc_runnig_7.png"),
	};
	private GreenfootImage[] idleSprites = {
		new GreenfootImage("vc_idle_0.png"),
	};
	private GreenfootImage[] jumpSprites = {
		new GreenfootImage("vc_jumping.png"),
	};
	private int spriteArraySize = 1;
	
	private long animationDuration = 100;
	private long animationTimer;
	private int spriteNumber = 0;
	private static boolean capnPressed = false;
	
	public VictorCharlie()
	{
	   this.shotTimer = 0;
	   this.climbing = false;
	   this.weapon = UNARMED; //can be KNIFE or GUN
	   this.side = RIGHT;
	   this.xSpeed = 3;
	   this.vcState = IDLE_STATE;
	   this.bullets = 0;
	   this.invisible = false;
	   this.respawn = true;
	   
	   this.animations.put(IDLE_STATE, idleSprites);
	   this.animations.put(RUN_STATE, runSprites);
	   this.animations.put(JUMP_STATE, jumpSprites);
	   this.animationTimer = System.currentTimeMillis();
	   //Matheus de Sousa Faria
	}

	public void act() 
	{
		if(this.invisible){
			this.getImage().setTransparency(0);
			
			if(this.respawn){
				if(System.currentTimeMillis() - this.respawnTime >= 1000){
					TemplateStage world = (TemplateStage)getWorld();
					this.invisible = false;
					this.setLocation(world.getRespawnX(), world.getRespawnY());
					this.setYVelocity(0);
				}
			}
		}
		else
		{
			this.getImage().setTransparency(255);
			
			this.controls();
			super.act();
			this.die();
			this.animation();
			haungsMode();
		}
		if (controlCounter == slowTime)
		{
			Greenfoot.setSpeed(VictorCharlie.slowSpeed);
		}
		if (controlCounter == stopTime)
		{
			Greenfoot.stop();
		}
		controlCounter++;
	}

	private void controls()
	{
		if(this.getOneIntersectingObject(Vine.class) != null){
			this.climbing = true;
			this.vcState = CLIMB_STATE;
		} else {
			this.climbing = false;
		}
		//Matheus de Sousa Faria
		
		this.xSpeed = (climbing) ? 1 : 3; // If you're climbing, your movement is 1, otherwise it's 3
		if (this.keyPressed(LEFT))
		{
			this.setXVelocity(-xSpeed);
			if (this.side != LEFT){     
				this.turnImages();
				//this.getImage().mirrorHorizontally();
			}
			this.side = LEFT;
			if(this.onGround())
				this.vcState = RUN_STATE;
		}
		else if (this.keyPressed(RIGHT))
		{
			this.setXVelocity(xSpeed);
			if (this.side != RIGHT){
				this.turnImages();
				//this.getImage().mirrorHorizontally();
			}
			this.side = RIGHT;
			if(this.onGround())
				this.vcState = RUN_STATE;
		}
		else
		{
			this.setXVelocity(0);
			if(this.onGround())
				this.vcState = IDLE_STATE;
		}
		// Harrison Knox
		
		if (this.keyPressed(UP) && this.onGround()){
			this.vcState = JUMP_STATE;
			this.setYVelocity(-15);
		}
		// Harrison Knox
		
		
		if (shotTimer>0) 
		{
			shotTimer=shotTimer-1;
		}
		else if(this.keyPressed(SPACE))
		{
			fire();
			shotTimer=15;
		}
		//Ryan Heller and Harrison Knox
	}
	
	private void haungsMode()
	{
		
		if (Greenfoot.mouseClicked(this) || (Greenfoot.isKeyDown("h") && !capnPressed))
		{
			TemplateStage.nextStage();
		}
		capnPressed = Greenfoot.isKeyDown("h");
		//Ryan Heller && Harrison Knox
	}

	private void fire()
	{
		if(this.weapon.equals(GUN) && this.bullets > 0){
			Shot shot = new Shot();
			this.getWorld().addObject(shot, this.getX(), this.getY());
			if(this.side == LEFT){
				shot.setRotation(180);
			}
			else if(this.side == RIGHT) {
				shot.setRotation(0);
			}
			this.bullets--;
			//Ryan Heller
		} else if (this.weapon.equals(KNIFE)) {
			Knife   knife = new Knife();
			
			if(this.side == LEFT){
				this.getWorld().addObject(knife, this.getX() - knife.getXOffset(), this.getY());
				knife.getImage().mirrorHorizontally();
			}
			else if(this.side == RIGHT) {
				this.getWorld().addObject(knife, this.getX() + knife.getXOffset(), this.getY());
			}
		}
	}
	
	public void setWeapon(String weapon)
	{
		this.weapon = weapon;
	}
	
	public String getWeapon(){
		return this.weapon;
	}

	private void die()
	{
		Positional killer = (Positional)(this.getOneIntersectingObject(KillVC.class));
		if ((getX() > getWorld().getWidth() || getX() < 0 || getY() > getWorld().getHeight()) || //Out of the world
			((killer != null))) // Killed by some KillVC.class
		{
			this.bullets -= 5;
			if(this.bullets < 0)
				this.bullets = 0;
			TemplateStage world = (TemplateStage)getWorld();
			world.addObject(new Ghost(), this.getX(), this.getY());
			this.respawnTime = System.currentTimeMillis();
			this.invisible = true;
			world.addDeath();
		}
		//Ryan Heller and Ben Griffith and Matheus de Sousa Faria
	}
	
	public Actor getOneIntersectingObject(Class cls)
	{
		Actor thing = super.getOneIntersectingObject(cls);
		if (thing != null && thing instanceof Positional)
		{
			Positional cosa = (Positional)thing;
			if ((this.getRight() > cosa.getLeft()) && (this.getLeft() < cosa.getRight()) && (this.getTop() < cosa.getBottom()) && (this.getBottom() > cosa.getTop()))
				return thing;
			return null;
		}
		return thing;
	}
	
	public boolean keyPressed(int sig)
	{
		signature = sig;
		accumulative = 0;
		/*if (
			until(184, RIGHT)
			
			|| until(151, RIGHT)
			|| until(1, RIGHTSPACE)
			
			|| until(1, RIGHTUP)
			|| until(35, RIGHT)
			|| until(1, RIGHTUP)
			|| until(35, RIGHT)
			|| until(1, RIGHTUP)
			|| until(115, RIGHT)
			
			|| until(73, RIGHTUP)
			|| until(23, RIGHT)
			|| until(1, RIGHTUP)
			|| until(17, RIGHT)
			|| until(4, NEITHER)
			|| until(1, LEFTUP)
			|| until(8, LEFT)
			|| until(13, NEITHER)
			|| until(1, RIGHTUP)
			|| until(47, RIGHT)
			|| until(1, LEFT)
			|| until(12, NEITHER)
			|| until(16, RIGHT)
			|| until(8, NEITHER)
			|| until(17, LEFT)
			|| until(9, NEITHER)
			|| until(9, RIGHT)
			
			|| until(4, RIGHT)
			|| until(1, RIGHTUP)
			|| until(22, RIGHT)
			|| until(1, UP)
			|| until(10, NEITHER)
			|| until(1, LEFT)
			|| until(6, NEITHER)
			|| until(1, UP)
			|| until(27, RIGHT)
			|| until(1, UP)
			|| until(20, RIGHT)
			|| until(16, NEITHER)
			|| until(22, RIGHT)
			|| until(1, RIGHTUP)
			|| until(37, RIGHT)
			|| until(1, RIGHTUP)
			|| until(13, RIGHT)
			|| until(4, NEITHER)
			|| until(1, UP)
			|| until(32, LEFT)
			|| until(1, LEFTUP)
			|| until(1, LEFT)
			|| until(5, NEITHER)
			|| until(15, RIGHT)
			|| until(1, RIGHTUP)
			|| until(28, RIGHT)
			|| until(1, RIGHTUP)
			|| until(25, RIGHT)
			
			|| until(3, RIGHT)
			|| until(1, RIGHTUP)
			|| until(87, RIGHT)
			|| until(1, RIGHTUP)
			|| until(18, RIGHT)
			|| until(1, UP)
			|| until(10, NEITHER)
			|| until(1, LEFT)
			|| until(6, NEITHER)
			|| until(1, RIGHTUP)
			|| until(17, RIGHT)
			|| until(1, RIGHTUP)
			|| until(47, RIGHT)
			|| until(10, NEITHER)
			|| until(14, LEFT)
			
			|| until(26, RIGHT)
			|| until(1, RIGHTUP)
			|| until(20, RIGHT)
			|| until(1, UP)
			|| until(7, NEITHER)
			|| until(16, LEFT)
			|| until(1, LEFTUP)
			|| until(17, LEFT)
			|| until(6, NEITHER)
			|| until(1, RIGHTUP)
			|| until(32, RIGHT)
			|| until(1, RIGHTUP)
			|| until(23, RIGHT)
			|| until(1, RIGHTUP)
			|| until(47, RIGHT)
			|| until(1, RIGHTUP)
			|| until(25, RIGHT)
			|| until(2, NEITHER)
			|| until(4, LEFT)
			|| until(5, NEITHER)
			|| until(40, LEFT)
			|| until(8, NEITHER)
			|| until(75, RIGHT)
			
			|| until(2, RIGHT)
			|| until(1, RIGHTUP)
			|| until(18, RIGHT)
			|| until(3, NEITHER)
			|| until(1, LEFTUP)
			|| until(7, LEFT)
			|| until(14, NEITHER)
			|| until(1, RIGHTUP)
			|| until(26, RIGHT)
			|| until(1, RIGHTUP)
			|| until(38, RIGHT)
			|| until(1, RIGHTUP)
			|| until(43, RIGHT)
			|| until(1, RIGHTUP)
			|| until(25, RIGHT)
			|| until(1, RIGHTUP)
			|| until(5, RIGHT)
			|| until(18, NEITHER)
			|| until(1, LEFTUP)
			|| until(7, LEFT)
			|| until(14, NEITHER)
			|| until(1, RIGHTUP)
			|| until(23, RIGHT)
			|| until(1, RIGHTUP)
			|| until(7, RIGHT)
			|| until(18, NEITHER)
			
			|| until(3, LEFT)
			|| until(1, LEFTUP)
			|| until(32, LEFT)
			|| until(1, LEFTUP)
			|| until(24, LEFT)
			|| until(1, NEITHER)
			|| until(1, LEFTUP)
			|| until(40, LEFT)
			|| until(1, LEFTUP)
			|| until(38, LEFT)
			|| until(1, LEFTUP)
			|| until(29, LEFT)
			
			|| until(1, LEFTUP)
			|| until(39, LEFT)
			|| until(4, NEITHER)
			|| until(1, UP)
			|| until(26, LEFT)
			|| until(1, LEFTUP)
			|| until(6, LEFT)
			|| until(10, NEITHER)
			|| until(1, LEFT)
			|| until(1, RIGHTUP)
			|| until(14, RIGHT)
			|| until(7, NEITHER)
			|| until(1, UP)
			|| until(10, NEITHER)
			|| until(10, LEFT)
			|| until(1, LEFTUP)
			|| until(100, LEFT)
		)
		{
			return true;
		}/* */
		
		return ((Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right") && sig == LEFT) || (Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown("left") && sig == RIGHT) || (Greenfoot.isKeyDown("up") && sig == UP) || (Greenfoot.isKeyDown("space")) && sig == SPACE);
		//Harrison Knox
	}
	
	public static final int speed = 60;
	private static final int slowSpeed = 40;
	
	private static final int stopTime = -1790;
	private static final int slowTime = 2150;
	
	private boolean until(int ticks, int keys)
	{
		boolean bigger = controlCounter >= accumulative;
		if (bigger)
		{
			lastEvent = accumulative;
			timeSinceLastEvent = controlCounter - lastEvent;
			accumulative += ticks;
			return (controlCounter < accumulative) && ((signature & keys) > 0);
		}
		return false;
	}
	
	public void animation()
	{
		if(this.vcState == IDLE_STATE)
			this.spriteArraySize = 1;
		else if(this.vcState == RUN_STATE)
			this.spriteArraySize = 8;
		else if(this.vcState == JUMP_STATE)
			this.spriteArraySize = 1;
			
		if(System.currentTimeMillis() - this.animationTimer >= this.animationDuration){
			this.animationTimer = System.currentTimeMillis();
			this.spriteNumber = (this.spriteNumber + 1) % this.spriteArraySize;
			this.setImage(this.animations.get(this.vcState)[this.spriteNumber]);
		}  
	}
	
	private void turnImages(){
		this.animations.get(JUMP_STATE)[0].mirrorHorizontally();
		this.animations.get(IDLE_STATE)[0].mirrorHorizontally();
		for(int i = 0; i < 8; i++){
			this.animations.get(RUN_STATE)[i].mirrorHorizontally();
		}
	}
	
	public int getWidth(){
		return 20;
	}
	
	public int getBullets(){
		return this.bullets;
	}
	
	public void addBullets(int b){
		this.bullets += b;
	}
	
	public void turnInvisible(){
		this.invisible = true;
		this.respawn = false;
	}
}