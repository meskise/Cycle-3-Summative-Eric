import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ProtectiveOrb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ProtectiveOrb extends Actor
{
    int frameCounter = 0;
    int cooldown = 0;

    double deltaX;
    double deltaY;
    boolean isFacingRight;
    
    public ProtectiveOrb()
    {
        // Load image and resize it.
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
       // Run cooldown.
       cooldown++;
       
       if (isTouching(Enemy.class))
       {
            // Run frame counter.
            frameCounter++;
            
            if (frameCounter == 3)
            {
                // Remove protective orb after hitting enemy.
                getWorld().removeObject(this);
            }
       }
       
       // Protective orbs last 100 frames.
       if (cooldown == 100)
       {
           getWorld().removeObject(this);
       }
    
       // If Shift and C are pressed, remove all protective orbs.
       if (Greenfoot.isKeyDown("shift") && Greenfoot.isKeyDown("c"))
       {
           getWorld().removeObject(this);
       }
    }
}
