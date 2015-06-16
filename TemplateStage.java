
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
    This is the abstract level superclass. Sub classes will contain the
    level designs and this will take the designs and build the levels on
    a grid. This uses arrays of strings to map out where all the features
    of the level are.
    
    @author Knox and Heller
    @version 3.14159
*/
public abstract class TemplateStage extends World
{
    private final static char AIR = ' ';
    private final static char PLATFORM = 'p';
    private final static char WALL = 'w';
    private final static char SGT_CHARLIE = 's';
    private final static char VIETCONG = 'v';
    private final static char VIETCONG2 = 'u';
    private final static char VIETCONG3 = 'i';
    private final static char VIETCONG4 = 'o';
    private final static char TRAP = 't';
    private final static char CHECKPOINT = 'c';
    private final static char LEVEL_END = 'e';
    private final static char ANIMAL = 'a';
    private final static char GUN = 'q';
    private final static char VINE = 'g'; //V is for a viet cong, W is for wall, so I randomlly choose G :)
    
    private int checkpointX = -1;
    private int checkpointY = -1;
    private int spawnpointX = -1;
    private int spawnpointY = -1;
    private boolean hitcheckpoint = false;
        
    protected VictorCharlie victor = null;
    protected EndPoint endPoint = null;
    protected static DeathBoard deathcounter = new DeathBoard();
    protected static TimeBoard timer = new TimeBoard();
    protected static WeaponBoard weaponBoard = new WeaponBoard();
    protected static ScoreBoard scoreboard= new ScoreBoard();
    
    private static TemplateStage[] STAGES;
    private static int stageNumber;
    
    private GreenfootSound bkgMusic = new GreenfootSound("sounds/imminentthreatloop.mp3");
    // Music c/o newgrounds.com - "Imminent Threat - Loop" by vartan
    
    public TemplateStage()
    {
        super(600, 400, 1, false);
        this.setPaintOrder(Text.class, Board.class, Physics.class, KillVC.class, Checkpoint.class, Actor.class);
    }
    public void act()
    {
        
    }
    public void build()
    {
        String[] design = this.getDesign();
        int height = design.length; // Height of the level design.
        if (height != 20)
            err("Level design has too " + ((height > 20) ? "many" : "few") + " rows: got " + Integer.toString(height) + ", should be 20");
        int vcX = -1;
        int vcY = -1;
        int epX = -1;
        int epY = -1;
        for (int row = 0; row < 20; row++)
        {
            String line = design[row];
            int width = line.length(); // Width of the level design
            if (width != 30)
                err("Row " + Integer.toString(row) + " of level design has too " + ((width > 30) ? "many" : "few") + " columns: got " + Integer.toString(width) + ", should be 30");
            for (int col = 0; col < 30; col++)
            {
                char cell = line.charAt(col);
                int xPos = 10 + (col * 20);
                int yPos = 10 + (row * 20);
                Actor block = null;
                switch (cell)
                {
                    case PLATFORM:
                    case WALL: // Harrison Knox
                        boolean onTop = (row > 0) && (design[row - 1].charAt(col) == PLATFORM || design[row - 1].charAt(col) == WALL);
                        boolean onBottom = (row < 19) && (design[row + 1].charAt(col) == PLATFORM || design[row + 1].charAt(col) == WALL);
                        boolean onLeft = (col > 0) && (line.charAt(col - 1) == PLATFORM || line.charAt(col - 1) == WALL); // Check to see if there's a platform to the left
                        boolean onRight = (col < 29) && (line.charAt(col + 1) == PLATFORM || line.charAt(col + 1) == WALL); // Ditto to the right
                        block = new Platform(((onTop) ? 8 : 0) + ((onBottom) ? 4 : 0) + ((onLeft) ? 2 : 0) + ((onRight) ? 1 : 0));
                    case AIR:
                        break;
                    case SGT_CHARLIE:
                        if (this.victor != null)
                            err("Duplicate Victor Charlies: at (" + Integer.toString(vcX) + ", " + Integer.toString(vcY) + ") and (" + Integer.toString(col) + ", " + Integer.toString(row) + ")");
                        block = new VictorCharlie();
                        vcX = col;
                        vcY = row;
                        spawnpointX = xPos;
                        spawnpointY = yPos - 10;
                        this.victor = (VictorCharlie)block;
                        break;
                    case VIETCONG:
                        block = new VietCong();
                        break;
                    case VIETCONG2:// Ben Griffith
                        block = new VietCong2();
                        break;
                    case VIETCONG3://Ben Griffith
                        block = new VietCong3();
                        break;
                    case VIETCONG4://Ben Griffith
                        block = new VietCong4();
                        break;
                    case GUN:
                        block = new GunPoint();
                        break;
                    case TRAP: // Harrison Knox
                        boolean tLeft = (col > 0) && (line.charAt(col - 1) == PLATFORM || line.charAt(col - 1) == WALL);
                        boolean tRight = (col < 29) && (line.charAt(col + 1) == PLATFORM || line.charAt(col + 1) == WALL);
                        if (tLeft)
                            block = new LeftThorn();
                        else if (tRight)
                            block = new RightThorn();
                        else
                            block = new FloorThorn();
                        break;
                    case CHECKPOINT:
                        if (this.checkpointX != -1)
                            err("Duplicate checkpoints: at (" + Integer.toString(this.checkpointX) + ", " + Integer.toString(this.checkpointY) + ") and (" + Integer.toString(col) + ", " + Integer.toString(row) + ")");
                        block = new Checkpoint();
                        this.setCheckpoint(xPos, yPos - 10);
                        break;
                    case LEVEL_END:
                        if (this.endPoint != null)
                            err("Duplicate end points: at (" + Integer.toString(epX) + ", " + Integer.toString(epY) + ") and (" + Integer.toString(col) + ", " + Integer.toString(row) + ")");
                        epX = col;
                        epY = row;
                        block = new EndPoint();
                        this.endPoint = (EndPoint)block;
                        break;
                    case ANIMAL:
                        block = new Animal();
                        break;
                    case VINE: //Matheus de Sousa Faria added this
                        block = new Vine();
                        break;
                }
                if (block != null)
                {
                    switch (cell)
                    {
                        case CHECKPOINT:
                        case SGT_CHARLIE:
                        case VIETCONG:
                        case VIETCONG2:
                        case VIETCONG3:
                        case VIETCONG4:
                        case GUN:
                            yPos -= 10;
                    }
                    this.addObject(block, xPos, yPos);
                }
            }
        }
        if (this.victor == null)
            err("No Victor Charlie defined");
            
        int boardsY = this.getHeight() - timer.getImage().getHeight()/2;
        int timerBoardX = timer.getImage().getWidth()/2 + 10;
        int deathCounterX = timer.getImage().getWidth() + deathcounter.getImage().getWidth()/2 + 20;
        int scoreBoardX = timer.getImage().getWidth() + deathcounter.getImage().getWidth() + scoreboard.getImage().getWidth()/2 + 30;
        int weaponBoardX = this.getWidth() - weaponBoard.getImage().getWidth()/2 - 10;
        
        this.addObject(timer, timerBoardX, boardsY);
        this.addObject(deathcounter, deathCounterX, boardsY);
        this.addObject(weaponBoard, weaponBoardX, boardsY);
        this.addObject(scoreboard, scoreBoardX, boardsY);
        this.addObject(new StagePlate(TemplateStage.stageNumber), this.getWidth()/2, this.getHeight()/2 - 100);
    }
    
    protected abstract String[] getDesign();
    
    public void addDeath()
    {
       deathcounter.addDeath();
    }
    
    public static void nextStage()
    {
        stageNumber++;
        if (stageNumber < STAGES.length)
        {
            TemplateStage stage = STAGES[stageNumber];
            Greenfoot.setWorld(stage);
            stage.build();
        }
        else
        {
            endGame();
        }
        //Matheus de Sousa Faria
    }
    
    public static void firstStage()
    {
        stageNumber = -1;
        nextStage();
    }
    
    public static void endGame()
    {
        //bkgMusic.stop();
        End go = new End(deathcounter.getDeaths(), timer.getTime());
        Greenfoot.setWorld(go);
       //Matheus de Sousa Faria with a hint of Harrison Knox
    }
    
    public void setCheckpoint(int x, int y)
    {
        this.checkpointX = x;
        this.checkpointY = y;
       //Matheus de Sousa Faria
    }
    
    public int getCheckpointX()
    {
        return this.checkpointX;
       //Matheus de Sousa Faria
    }
    
    public int getCheckpointY()
    {
        return this.checkpointY;
       //Matheus de Sousa Faria
    }
    
    public void activateCheckpoint()
    {
        hitcheckpoint = true;
    }
    
    public int getRespawnX()
    {
        if (hitcheckpoint == true)
        {
            return checkpointX;
        }
        else
        {
            return spawnpointX;
        }
    } // Ben Griffith
    public int getRespawnY()
    {
        if (hitcheckpoint == true)
        {
            return checkpointY;
        }
        else
        {
            return spawnpointY;
        }
    } // Ben Griffith
    
    
    private void err(String message)
    {
        System.out.println(message);
        Greenfoot.stop();
    } // Harrison Knox
    
    // Add new levels to this in the order they appear.
    static
    {
        stageNumber = -1;
        STAGES = new TemplateStage[]{
            new Lev1(),
            new Lev2(),
            new Lev3(),
            new Lev4(),
            new Lev5(),
            new Lev6(),
            new Lev7(),
            new Lev8(),
            new Lev9(),
            new Lev10(),
        };
    }
}
