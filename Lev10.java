import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

public class Lev10 extends TemplateStage
{
    private long timeStamp = 0;
    
    public Lev10()
    {
        super();
        this.addObject(new ChopperBoss(), -200, 50);
        this.addObject(new ChopperLifeBar(), this.getWidth()/2, 20);
    }
    
    protected String[] getDesign()
    {
        return new String[]{
        "                              ",
        "                              ",
        "                              ",
        "                              ",
        "                              ",
        "                              ",
        "                              ",
        "                              ",
        "                              ",
        "                              ",
        "                              ",
        "                              ",
        "                              ",
        "                              ",
        "                              ",
        "                              ",
        "pp            pp            pp",
        "                              ",
        "       q      s               ",
        "pppppppppppppppppppppppppppppp"
        };
    }
    
    public void act(){
        super.act();
        this.dropBullets();
    }
    
    public void dropBullets(){
        if(System.currentTimeMillis() - this.timeStamp >= 30000){
            Random randomGenerator = new Random();
            int x = randomGenerator.nextInt(this.getWidth() - 10) + 10;
            this.addObject(new BulletsPoint(), x, -20);
            this.timeStamp = System.currentTimeMillis();
        }
    }
}
