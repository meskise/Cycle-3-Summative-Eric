import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Key here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Key extends Actor
{
    int counter = 0;
    
    public Key()
    {
        GreenfootImage image = getImage();
        image.scale(30, 30);
        setImage(image);
    }
    
    /**
     * Act - do whatever the Key wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
       Temp temp = new Temp();
       if (isTouching(Woodcutter.class))
        {
            counter++;
            // If Woodcutter touches key, add temp object on the door.
            getWorld().addObject(temp, 75, 60);
            if (counter == 3)
            {
                // Remove key after touching player.
                getWorld().removeObject(this);
            }
        }
    }    
}
