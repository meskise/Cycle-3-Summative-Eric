import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Door here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Door extends Actor
{
    GreenfootImage DoorOpen;
    
    public Door()
    {
        GreenfootImage image = getImage();
        image.scale(75, 100);
        setImage(image);
        
        DoorOpen = new GreenfootImage("DoorOpen.png");
        DoorOpen.scale(75, 100); 
    }
    
    /**
     * Act - do whatever the Door wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // If temp object is touching door, open the door.
        if(isTouching(Temp.class))
        {
            setImage(DoorOpen);
        }
    }    
}
