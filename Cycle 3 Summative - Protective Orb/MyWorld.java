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
        setPaintOrder(Woodcutter.class, Enemy.class, Key.class, ProtectiveOrb.class, Ground.class, Platform.class, Door.class, Cactus.class, Temp.class);
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
        Platform platform4 = new Platform();
        addObject (platform4, 500, 150);
        Platform2 platform5 = new Platform2();
        addObject (platform5, 75, 125);
        
        Cactus cactus = new Cactus();
        addObject (cactus, 330, 330);
        Cactus2 cactus2 = new Cactus2();
        addObject (cactus2, 450, 300);
        Cactus2 cactus3 = new Cactus2();
        addObject (cactus3, 500, 85);
        
        Key key = new Key();
        addObject (key, 515, 115);
        Door door = new Door();
        addObject (door, 75, 60);
    }
    
    
    
}
