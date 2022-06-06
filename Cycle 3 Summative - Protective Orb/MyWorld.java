import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        super(600, 400, 1); 
        spawnObjects();
        // Makes objects appear to be infront of one another.
        setPaintOrder(Woodcutter.class, Enemy.class, ProtectiveOrb.class, Ground.class, Platform.class, Cactus.class);
    }
    
    public void act()
    {
        
    }
    
    public void spawnObjects()
    {
        Woodcutter woodcutter = new Woodcutter();
        addObject (woodcutter, 50, 330);
        Enemy enemy = new Enemy();
        addObject (enemy, 590, 325);
        
        Ground ground = new Ground();
        addObject (ground, 100, 390);
        Ground ground2 = new Ground();
        addObject (ground2, 500, 387);
        Platform platform = new Platform();
        addObject (platform, 225, 275);
        Platform2 platform2 = new Platform2();
        addObject (platform2, 375, 215);
        Platform platform3 = new Platform();
        addObject (platform3, 200, 160);
        
        Cactus cactus = new Cactus();
        addObject (cactus, 330, 330);
        Cactus2 cactus2 = new Cactus2();
        addObject (cactus2, 450, 300);
    }
    
    
    
}
