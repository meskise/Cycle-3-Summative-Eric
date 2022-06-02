import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Sword here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sword extends Actor
{
    public Sword()
    {
        // Gets the sword image to resize it.
        GreenfootImage image = getImage();
        image.scale(30, 13);
        setImage(image);
    }
    
    /**
     * Act - do whatever the Sword wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {   
        // Move.
        move(-4);

        // If touches woodcutter remove woodcutter and sword, set world to game over.
        Woodcutter w = (Woodcutter) getOneIntersectingObject(Woodcutter.class);
        if (w != null)
        {
            getWorld().removeObject(w);
            getWorld().removeObject(this);
            Greenfoot.setWorld(new GameOver());
        }
        else if (getY() > 390)
        {
            getWorld().removeObject(this);
        }
        else if (getY() < 1)
        {
            getWorld().removeObject(this);
        }
        else if (getX() > 595)
        {
            getWorld().removeObject(this);
        }
        else if (getX() < 1)
        {
            getWorld().removeObject(this);
        }
    
        if (isTouching(ProtectiveOrb.class))
        {
            getWorld().removeObject(this);
        }
    }
}
