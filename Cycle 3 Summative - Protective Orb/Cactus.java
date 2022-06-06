import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cactus here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cactus extends Actor
{
    public Cactus()
    {
        // Loads and scales images.
        GreenfootImage image = getImage();
        image.scale(45, 60);
        setImage(image);
    }
    
    /**
     * Act - do whatever the Cactus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // If touches protective orb, remove cactus.
        if (isTouching(ProtectiveOrb.class))
        {
            getWorld().removeObject(this);
        }
    }    
}
