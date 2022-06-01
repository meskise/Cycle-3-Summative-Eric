import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    int enemyFrameCounter = 0;
    double deltaX = -2;
    double deltaY = 0;
    int health = 200;
    
    int animationInterval = 5;
    
    GreenfootImage imageDying1;
    GreenfootImage imageDying2;
    GreenfootImage imageDying3;
    public Enemy()
    {
        GreenfootImage image = getImage();
        image.scale(50, 50);
        setImage(image);
        
        imageDying1 = new GreenfootImage("EnemyDying01.png");
        imageDying2 = new GreenfootImage("EnemyDying02.png");
        imageDying3 = new GreenfootImage("EnemyDying03.png");
        
        imageDying1.scale(50, 32);
        imageDying2.scale(50, 32);
        imageDying3.scale(50, 32);
    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        isTouching();
        health();
    }
    
    public void health()
    {
        if (isTouching(ProtectiveOrb.class))
        {
            health = health - 25;
        }
        else if (health < 1)
        {
            Die();
        }
    }
    
    public void isTouching()
    {
        
        if (isAtEdge())
        {
            movement();
        }
        setLocation(getX() + (int)deltaX, getY() + (int)deltaY);
    }    
    
    public void movement()
    {
        deltaX = deltaX * -1;
    }
    
    public void Die()
    {
         deltaX = 0.5;
         if (enemyFrameCounter >= 8 * animationInterval)
         {
             // Reset to 0.
             enemyFrameCounter = 0;
             getWorld().removeObject(this);
         }
                
         if (enemyFrameCounter == 0)
         {
             setImage(imageDying1);
         }
         else if (enemyFrameCounter == 1 * animationInterval)
         {
             setImage(imageDying1);
         }
         else if (enemyFrameCounter == 2 * animationInterval)
         {
             setImage(imageDying2);
         }
         else if (enemyFrameCounter == 3 * animationInterval)
         {
             setImage(imageDying2);
         }
         else if (enemyFrameCounter == 4 * animationInterval)
         {
             setImage(imageDying3);
         }
         else if (enemyFrameCounter == 5 * animationInterval)
         {
             setImage(imageDying3);
         }
         else if (enemyFrameCounter == 6 * animationInterval)
         {
             setImage(imageDying3);
         }
         else if (enemyFrameCounter == 7 * animationInterval)
         {
             setImage(imageDying3);
         }
                
         // Increment frame counter.
         enemyFrameCounter++;
    }
