import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Woodcutter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Woodcutter extends Actor
{
    ProtectiveOrb pO = new ProtectiveOrb();
    ProtectiveOrb pO2 = new ProtectiveOrb();
    ProtectiveOrb pO3 = new ProtectiveOrb();
    ProtectiveOrb pO4 = new ProtectiveOrb();
    ProtectiveOrb pO5 = new ProtectiveOrb();
    ProtectiveOrb pO6 = new ProtectiveOrb();
    ProtectiveOrb pO7 = new ProtectiveOrb();
    ProtectiveOrb pO8 = new ProtectiveOrb();
    
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
        for (int i = 0; i < orbs.length; i++)
        {
            orbs[0 + 1] = new ProtectiveOrb();
        }
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
        
        
        if (cooldown > 0) cooldown--; // Run cooldown.
        if(Greenfoot.isKeyDown("c") && (cooldown == 0))
        {
            // If "C" is pressed then spawn protective circle.
            protectiveCircle();
            cooldown = 350;
        }
        pO.setLocation(getX() + (int)deltaX, getY() + (int)deltaY - 50);
        pO2.setLocation(getX() + (int)deltaX - 50, getY() + (int)deltaY);
        pO3.setLocation(getX() + (int)deltaX + 50, getY() + (int)deltaY);
        pO4.setLocation(getX() + (int)deltaX, getY() + (int)deltaY + 50);
        pO5.setLocation(getX() + (int)deltaX - 30, getY() + (int)deltaY - 30);
        pO6.setLocation(getX() + (int)deltaX + 30, getY() + (int)deltaY - 30);
        pO7.setLocation(getX() + (int)deltaX + 30, getY() + (int)deltaY + 30);
        pO8.setLocation(getX() + (int)deltaX - 30, getY() + (int)deltaY + 30);
        
    } 
   
    
    public void movementKeys()
    {
        //double deltaX = 0;
        //double deltaY = 0;
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
    
    public void isTouching()
    {
        // Check if falling.
        if (isTouching(Ground.class))
        {
            // Standing on ground, don't apply gravity.
            deltaY = 0;
        }
    }
    
    public void protectiveCircle()
    {
        int aX = getX();
        int aY = getY();
        getWorld().addObject(pO, aX, aY);
        getWorld().addObject(pO2, aX, aY);
        getWorld().addObject(pO3, aX, aY);
        getWorld().addObject(pO4, aX, aY);
        getWorld().addObject(pO5, aX, aY);
        getWorld().addObject(pO6, aX, aY);
        getWorld().addObject(pO7, aX, aY);
        getWorld().addObject(pO8, aX, aY);
        
        //System.out.println("You Pressed C " + cooldown);
        // for(double i = 0; i < 9; i++)
        // {
            // getWorld().addObject(pO, aX, aY - 50);
            
            // getWorld().addObject(pO2, aX + 50, aY);
            
            // getWorld().addObject(pO3, aX - 50 , aY);
            
            // getWorld().addObject(pO4, aX, aY + 50);
        // }
    }
    
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
        else
        {
            isInAir = true;
        }
        
        if (isInAir == true)
        {
            deltaY = deltaY + g; // Apply gravity
        }
    }
    
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
    
    public void moveOnTopOfObject(Actor object)
    {
        // Allows player to be adjusted to top of the platforms to prevent bugs.
        int height = getImage().getHeight();
        int objectHeight = object.getImage().getHeight();
        
        setLocation(getX(), object.getY() - objectHeight / 2 - height / 2 + 1);
    }
    
    public void moveOnBottomOfObject(Actor object)
    {
        // Allows player to be adjusted to top of the platforms to prevent bugs.
        int height = getImage().getHeight();
        int objectHeight = object.getImage().getHeight();
        
        setLocation(getX(), object.getY() + objectHeight / 2 + height / 2 + 1);
    }
    
    public void stopOnRightObject(Actor object)
    {
        int width = getImage().getWidth();
        int objectWidth = object.getImage().getWidth();
        
        setLocation(object.getX() + objectWidth /2 + width /2, getY());
    }
    
    public void stopOnLeftObject(Actor object)
    {
        int width = getImage().getWidth();
        int objectWidth = object.getImage().getWidth();
        
        setLocation(object.getX() - objectWidth /2 - width /2, getY());
    }
}
