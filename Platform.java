import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platform extends Solid
{
    private static final GreenfootImage[] images = {
        new GreenfootImage(20, 20),
        new GreenfootImage(20, 20),
        new GreenfootImage(20, 20),
        new GreenfootImage(20, 20),
        new GreenfootImage(20, 20),
        new GreenfootImage(20, 20),
        new GreenfootImage(20, 20),
        new GreenfootImage(20, 20),
        new GreenfootImage(20, 20),
        new GreenfootImage(20, 20),
        new GreenfootImage(20, 20),
        new GreenfootImage(20, 20),
        new GreenfootImage(20, 20),
        new GreenfootImage(20, 20),
        new GreenfootImage(20, 20),
        new GreenfootImage(20, 20)
    }; // Harrison Knox
    
    public Platform(int sides) 
    {
        this.setImage(images[sides]);
    }
    
    static
    {
        GreenfootImage leftSide = new GreenfootImage("grass_platform_left.png");
        GreenfootImage leftEnd = new GreenfootImage(10, 20);
        GreenfootImage middle = new GreenfootImage("grass_platform_middle.png");
        GreenfootImage rightEnd = new GreenfootImage(10, 20);
        GreenfootImage rightSide = new GreenfootImage("grass_platform_right.png");
        GreenfootImage woodHalf = new GreenfootImage(20, 10);
        GreenfootImage woodWhole = new GreenfootImage("wood.png");
        
        leftEnd.drawImage(leftSide, 0, 0);
        rightEnd.drawImage(rightSide, -10, 0);
        woodHalf.drawImage(woodWhole, 0, 0);
        
        images[0].drawImage(leftEnd, 0, 0);
        images[0].drawImage(rightEnd, 10, 0);
        
        images[1].drawImage(leftSide, 0, 0);
        
        images[2].drawImage(rightSide, 0, 0);
        
        images[3].drawImage(middle, 0, 0);
        
        for (int i = 4; i < 8; i++)
        {
            images[i].drawImage(woodHalf, 0, 10);
            images[i].drawImage(images[i - 4], 0, 0);
        }
        
        images[8].drawImage(woodWhole, 0, 0);
        
        for (int i = 9; i < 12; i++)
        {
            images[i].drawImage(woodHalf, 0, 0);
            images[i].drawImage(images[i - 8], 0, 0);
        }
        
        images[12].drawImage(woodWhole, 0, 0);
        
        for (int i = 13; i < 16; i++)
        {
            images[i].drawImage(woodWhole, 0, 0);
            images[i].drawImage(images[i - 12], 0, 0);
        }
    } // Harrison Knox
}
