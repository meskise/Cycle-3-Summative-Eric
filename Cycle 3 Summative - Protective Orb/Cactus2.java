import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cactus2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cactus2 extends Cactus
{
    public Cactus2()
    {
        // Loads and scales images.
        GreenfootImage image = getImage();
        image.scale(60, 100);
        setImage(image);
    }
    
    /**
     * Act - do whatever the Cactus2 wants to do. This method is called whenever
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
