import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Thorn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Thorns extends Traps
{
    public void Thorns()
    {
        GreenfootImage image = getImage();
        image.scale(image.getWidth(), image.getHeight()-20);
        setImage(image);
    }

    public void act() 
    {
        
        }

    }