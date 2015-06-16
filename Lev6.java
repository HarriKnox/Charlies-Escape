import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Lev6 extends TemplateStage
{

    
    public Lev6()
    {
        super();
    }

    protected String[] getDesign()
    {
        return new String[]{
            "                              ",
            "                             e",
            "                            pp",
            "                         twt  ",
            "                              ",
            "       p                      ",
            "                      p       ",
            "                              ",
            "  p                           ",
            "             p                ",
            "                         p    ",
            "                              ",
            "     p                        ",
            "                      twt     ",
            "                  twt         ",
            " s              c twt         ",
            "ppp             p twt         ",
            "                              ",
            "tttttttttttttttttttttttttttttt",
            "pppppppppppppppppppppppppppppp" 
        };
    }
    private int counter = 0;
    public void act()
    {
        counter = counter + 1;
        if (counter == 50)
        {
            counter= 0;
            addObject(new A3(), 300, 0);
        }

    }
}