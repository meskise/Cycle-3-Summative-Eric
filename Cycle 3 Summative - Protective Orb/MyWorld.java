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
    }
    
    public void act()
    {
        
    }
    
    public void spawnObjects()
    {
        Woodcutter woodcutter = new Woodcutter();
        addObject (woodcutter, 250, 330);
        Enemy enemy = new Enemy();
        addObject (enemy, 590, 325);
        
        Ground ground = new Ground();
        addObject (ground, 100, 390);
        Ground ground2 = new Ground();
        addObject (ground2, 500, 387);
    }
    
    
    
}
