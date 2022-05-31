import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ProtectiveOrb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ProtectiveOrb extends Actor
{
    int counter = 0;
    double deltaX;
    double deltaY;
    boolean isFacingRight;
    
    public ProtectiveOrb()
    {
        GreenfootImage image = getImage();
        image.scale(15, 15);
        setImage(image);
    }
    
    /**
     * Act - do whatever the ProtectiveOrb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {   
       //System.out.println(counter);
       // If Shift and C are pressed, remove all protective orbs.
       if (Greenfoot.isKeyDown("shift") && Greenfoot.isKeyDown("c"))
       {
           getWorld().removeObject(this);
       }
       // If the counter is 300 or over 300, remove all protective orbs and reset counter.
       if (counter >= 300)
       {
           getWorld().removeObject(this);
           counter = 0;
       }
       
       counter++;
    }
    
}
