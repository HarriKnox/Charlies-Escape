import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lev7 here.
 * 
 * @author (Ben Griffith) 
 * @version (a version number or a date)
 */
public class Lev9 extends TemplateStage
{

    public Lev9()
    {
        super();
    }

    protected String[] getDesign()
    {
        return new String[]{
            /*0*/"                              ",
            /*2*/"                              ",
            /*4*/"                              ",
            /*6*/"  e    t                      ",
            /*8*/"pppppppp         t            ",
           /*10*/"         pp   pppp  pppp      ",
           /*12*/"                              ",
           /*14*/"                              ",
           /*16*/"                          w   ",
           /*18*/"    ppppppp               w   ",
           /*20*/"                          w   ",
           /*22*/"          i   c w   a     w  o",
           /*24*/"pp        pppppppppppppppppppp",
           /*26*/"                              ",
           /*28*/"                              ",
           /*30*/"pppp                          ",
           /*32*/"                          w   ",
           /*34*/"                          w   ",
           /*36*/"                t         w s ",
           /*38*/"pppppppppppppppppppppppppppppp"   
        };
    }
    private int counter = 0;
    public void act()
    {
        counter = counter + 1;
        if (counter == 100)
        {
            addObject (new ArrowR(), 0, 355);
            counter = 0;
        }
    }
}