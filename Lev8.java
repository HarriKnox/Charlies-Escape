import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Lev7 here.
 * 
 * @author (Ben Griffith) 
 * @version (a version number or a date)
 */
public class Lev8 extends TemplateStage
{

    public Lev8()
    {
        super();
    }

    protected String[] getDesign()
    {
        return new String[]{
            /*0*/"                     w        ",
            /*2*/"                     w        ",
            /*4*/"                     w      s ",
            /*6*/"           c         w     wpp",
            /*8*/"     pppppppppw      w     w  ",
           /*10*/"              w  pp  w     w  ",
           /*12*/"              w      w     w  ",
           /*14*/"        w     w      w     w  ",
           /*16*/"        w     w      w     w  ",
           /*18*/"ppppppppppp   w      w     w  ",
           /*20*/"              w    ppw  t  w  ",
           /*22*/"              w      w  pppw  ",
           /*24*/"    ppppppppppw               ",
           /*26*/"              w              v",
           /*28*/"              wpp           pp",
           /*30*/"ppppppppppp   w               ",
           /*32*/"              w               ",
           /*34*/"              w               ",
           /*36*/" e            w      t        ",
           /*38*/"pppppppppppppppppppppppppppppp"   
        };
    }
    private int counter = 0;
    public void act()
    {
        counter = counter + 1;
        if (counter == 50)
        {
            addObject (new ArrowR(), 0, 145);
            addObject (new ArrowD(), 400 , 0);
        }
        if (counter == 100)
        {
            addObject (new ArrowR(), 0, 225);
            addObject (new ArrowD(), 360, 0);
        }
        if (counter == 130)
        {
            counter = 0;
            addObject (new ArrowR(), 0, 280);
        }
        
    }
}