import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Woodcutter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Woodcutter extends Actor
{
    double deltaX = 0;
    double deltaY = 0;
    
    double g = 0.8;
    
    boolean isInAir;
    boolean isFacingRight;
    
    int cooldown = 0;
    
    public Woodcutter()
    {
        GreenfootImage image = getImage();
        image.scale(40, 45);
        setImage(image);
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
            cooldown = 50;
        }
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
        //System.out.println("You Pressed C " + cooldown);
        for(double i = 0; i < 9; i++)
        {
            getWorld().addObject(new ProtectiveOrb(), aX, aY - 50);
            
            getWorld().addObject(new ProtectiveOrb(), aX + 50, aY);
            
            getWorld().addObject(new ProtectiveOrb(), aX - 50 , aY);
            
            getWorld().addObject(new ProtectiveOrb(), aX, aY + 30);
        }
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
