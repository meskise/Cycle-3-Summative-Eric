import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Woodcutter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Woodcutter extends Actor
{
    int deltaX;
    int deltaY;
    
    boolean isFacingRight;
    
    int cooldown = 0;
    
    /**
     * Act - do whatever the Woodcutter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        movementKeys();
        if (cooldown > 0) cooldown--; // Run cooldown.
        if(Greenfoot.isKeyDown("c") && (cooldown == 0))
        {
            // If "C" is pressed then spawn protective circle.
            protectiveCircle();
            cooldown = 50;
        }
    } 
    
    public void movementKeys()
    {
        double deltaX = 0;
        double deltaY = 0;
        // Movement Keys
        if (Greenfoot.isKeyDown("a"))
        {
            deltaX = - 3.5;
        }
        
        if (Greenfoot.isKeyDown("d"))
        {
            deltaX = + 3.5;
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
    
    public void protectiveCircle()
    {
        int aX = getX();
        int aY = getY();
        //System.out.println("You Pressed C " + cooldown);
        for(double i = 0; i < 9; i++)
        {
            getWorld().addObject(new ProtectiveOrb(), aX, aY - 30);
            
            getWorld().addObject(new ProtectiveOrb(), aX + 30, aY);
            
            getWorld().addObject(new ProtectiveOrb(), aX - 30 , aY);
            
            getWorld().addObject(new ProtectiveOrb(), aX, aY + 30);
        }
    }
    
   
}
