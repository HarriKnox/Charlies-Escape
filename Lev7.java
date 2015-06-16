import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lev7 here.
 * 
 * @author (Ben Griffith) 
 * @version (a version number or a date)
 */
public class Lev7 extends TemplateStage
{

    public Lev7()
    {
        super();
    }

    protected String[] getDesign()
    {
        return new String[]{
            "                              ",
            "                              ",
            " e                            ",
            "wpp                           ",
            "wt                            ",
            "wt    ppp                     ",
            "wt                            ",
            "wt          ppp               ",
            "wt                            ",
            "wt               ppp          ",
            "wt                            ",
            "wt                            ",
            "wt                            ",
            "wt                            ",
            "wt                    ppp     ",
            "wt                            ",
            "wt                            ",
            "wt                            ",
            "wttttttttttttttttttttttt    s ",
            "ppppppppppppppppppppppppt  ppp"   
        };
    }
    private int counter = 0;
    public void act()
    {
        counter = counter + 1;
        if (counter == 25)
        {
            addObject(new ArrowD(), 460, 0);
        }
        if (counter == 50)
        {
            addObject(new ArrowD(), 360, 0);
        }
        if (counter == 75)
        {
            addObject(new ArrowD(), 260, 0);
        }
        if (counter == 100)
        {
            counter = 0;
            addObject(new ArrowD(), 160, 0);
        }
        
    }
}