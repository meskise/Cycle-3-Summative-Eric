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
       //movementKeys();
       
       if (Greenfoot.isKeyDown("c"))
       {
           
       }
    
       if (counter >= 300)
       {
           getWorld().removeObject(this);
           counter = 0;
       }
       
       counter++;
    }
    
    public void movementKeys()
    {
        if (Greenfoot.isKeyDown("a"))
        {
            deltaX = - 3.5;
        }
        else if (Greenfoot.isKeyDown("d"))
        {
            deltaX = + 3.5;
        }
        else
        {
            deltaX = 0;
        }

        // Allows movement
        setLocation(getX() + (int)deltaX, getY() + (int)deltaY);
        
        // If deltaX is greater than 0 then facing right is true.
        if (deltaX > 0)
        {
            isFacingRight = true;
        }
        // If deltaX is less than 0 then facing right is false.
        else if (deltaX < 0)
        {
            isFacingRight = false;
        }
    
    }
}
