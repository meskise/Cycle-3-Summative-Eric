import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Woodcutter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Woodcutter extends Actor
{   
    int orbDistance = 50;
    int orbDiagDistance = 30;
    
    double deltaX = 0;
    double deltaY = 0;
    
    double g = 0.8;
    
    boolean isInAir;
    boolean isFacingRight;
    
    int cooldown = 0;
    
    ProtectiveOrb[] orbs = new ProtectiveOrb[8];
    
    public Woodcutter()
    {
        GreenfootImage image = getImage();
        image.scale(40, 45);
        setImage(image);
        
        orbs[0] = new ProtectiveOrb();
        // Loads all of 8 protective orbs
        for (int i = 0; i < orbs.length; i++)
        {
            orbs[i] = new ProtectiveOrb();
        }
        // 
        
    }
    
    /**
     * Act - do whatever the Woodcutter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        isTouching();
        movementKeys();
        collisonCheck();
        applyGravity();
        orbsSetLocation();
        cooldown();
        
    }
    
    /**
     * Only allows orbs to be used every few seconds.
     */
    public void cooldown()
    {
        if (cooldown > 0) cooldown--; // Run cooldown.
        if(Greenfoot.isKeyDown("c") && (cooldown == 0))
        {
            // If "C" is pressed then spawn protective circle.
            protectiveCircle();
            cooldown = 350;
        }
    }
    
    /**
     * Sets the location for all protective orbs, allowing them to follow player.
     */
    public void orbsSetLocation()
    {
        orbs[0].setLocation(getX() + (int)deltaX, getY() + (int)deltaY - orbDistance);
        orbs[1].setLocation(getX() + (int)deltaX - orbDistance, getY() + (int)deltaY);
        orbs[2].setLocation(getX() + (int)deltaX + orbDistance, getY() + (int)deltaY);
        orbs[3].setLocation(getX() + (int)deltaX, getY() + (int)deltaY + orbDistance);
        orbs[4].setLocation(getX() + (int)deltaX - orbDiagDistance, getY() + (int)deltaY - orbDiagDistance);
        orbs[5].setLocation(getX() + (int)deltaX + orbDiagDistance, getY() + (int)deltaY - orbDiagDistance);
        orbs[6].setLocation(getX() + (int)deltaX + orbDiagDistance, getY() + (int)deltaY + orbDiagDistance);
        orbs[7].setLocation(getX() + (int)deltaX - orbDiagDistance, getY() + (int)deltaY + orbDiagDistance);
    }
   
    /**
     * Basic movement keys.
     */
    public void movementKeys()
    {
        // Movement Keys
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
        
        if (Greenfoot.isKeyDown("space"))
        {
            // Only allow jump when touching Ground / Platform classes.
            if (isTouching(Ground.class))
            {
                // Jump. 
                deltaY = -13;
            }
            if (isTouching(Platform.class))
            {
                // Jump. 
                deltaY = -13;
            
            }
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
    
    /**
     * Void for most isTouching commands.
     */
    public void isTouching()
    {
        // Check if falling.
        if (isTouching(Ground.class))
        {
            // Standing on ground, don't apply gravity.
            deltaY = 0;
        }
    }
    
    /**
     * Spawns protective orbs when called.
     */
    public void protectiveCircle()
    {
        // find woodcutter.
        int aX = getX();
        int aY = getY();
        
         // Moves the orbs to their locations
        for (int i = 0; i < orbs.length; i++)
        {
            getWorld().addObject(orbs[i], aX, aY);
            orbs[i].move(50);
            orbs[i].setRotation(i*45);
        }
        
    }
    
    /**
     * Void for applying gravity to player.
     */
    public void applyGravity()
    {
        // Height of the character, which is just height of the current image.
        int height = getImage().getHeight();
        int width = getImage().getWidth();
        
        Actor platform = getOneObjectAtOffset(0, height / 2, Platform.class);
        if (platform != null) // If there is a platform below.
        {
            deltaY = 0; // Don't apply gravity
            isInAir = false;
          
            moveOnTopOfObject(platform); // Adjust position to just touching platform.
        }
        else // No platform below.
        {
            
            isInAir = true;
        }
        
        platform = getOneObjectAtOffset(0, - height / 2, Platform.class);
        if (platform != null) // If there is a platform above.
        {
            isInAir = true;
            deltaY = 0;
          
            moveOnBottomOfObject(platform); // Adjust position to just touching platform.
        }
        
        Actor ground = getOneObjectAtOffset(0, height / 2, Ground.class);
        if (ground != null) // If there is a platform below.
        {
            deltaY = 0; // Don't apply gravity
            isInAir = false;
            
            moveOnTopOfObject(ground); // Adjust position to just touching platform.
        }
        else // If no ground below.
        {
            isInAir = true;
        }
        
        if (isInAir == true)
        {
            deltaY = deltaY + g; // Apply gravity
        }
    }
    
    /**
     * Checks for any collisons to help with phasing through objects.
     */
    public void collisonCheck()
    {
        int height = getImage().getHeight();
        int width = getImage().getWidth();
        
        Actor platform = getOneObjectAtOffset( - width /2, 0 ,Platform.class);
        if (platform != null)
        {
            deltaX = 0;
            
            stopOnRightObject(platform);
        }
        
        platform = getOneObjectAtOffset( width /2, 0, Platform.class);
        if (platform != null)
        {
            deltaX = 0;
            
            stopOnLeftObject(platform);
        }
        
        if (getY() >= 395)
        {
            //Greenfoot.setWorld(new GameOver());
            Greenfoot.stop();
        }
    }
    
    /**
     * Moves actor on top of object when called.
     */
    public void moveOnTopOfObject(Actor object)
    {
        // Allows player to be adjusted to top of the platforms to prevent bugs.
        int height = getImage().getHeight();
        int objectHeight = object.getImage().getHeight();
        
        setLocation(getX(), object.getY() - objectHeight / 2 - height / 2 + 1);
    }
    
    /**
     * Moves actor down from object when called.
     */
    public void moveOnBottomOfObject(Actor object)
    {
        // Allows player to be adjusted to top of the platforms to prevent bugs.
        int height = getImage().getHeight();
        int objectHeight = object.getImage().getHeight();
        
        setLocation(getX(), object.getY() + objectHeight / 2 + height / 2 + 1);
    }
    
    /**
     * If actor hits the right of a platform, stop.
     */
    public void stopOnRightObject(Actor object)
    {
        int width = getImage().getWidth();
        int objectWidth = object.getImage().getWidth();
        
        setLocation(object.getX() + objectWidth /2 + width /2, getY());
    }
    
    /**
     * If actor hits left of a platform, stop.
     */
    public void stopOnLeftObject(Actor object)
    {
        int width = getImage().getWidth();
        int objectWidth = object.getImage().getWidth();
        
        setLocation(object.getX() - objectWidth /2 - width /2, getY());
    }
}
