import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Counter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Counter extends Text
{
    public static int points;
    
    public Counter(){
        super("000");
        this.points = 0;
    }
    
    public void showScore(int pts){
        this.points = pts;
        String text = String.format("%03d", this.points);
        this.setText(text);
    }
}
