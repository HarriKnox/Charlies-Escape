import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Write a description of class Text here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Text extends Positional
{
    String text;
    
    public Text(String text){
        this.setText(text);
    }
    
    public void setText(String text){
        this.text = text;
        setImage(new GreenfootImage(this.text, 14, Color.BLACK, new Color(0, 0, 0 , 0)));
    }
}
