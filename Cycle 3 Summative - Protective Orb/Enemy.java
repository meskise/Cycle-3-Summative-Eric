import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
     SimpleTimer shotTimer = new SimpleTimer();
    
    // Sword Throwing
    int randomShot = Greenfoot.getRandomNumber(6000);
    int minShotWait = 1000;
    
    int enemyFrameCounter = 0;
    int counter = 0;
    
    double deltaX = -2;
    double deltaY = 0;
    int health = 200;
    
    int animationInterval = 5;
    

    
    GreenfootImage imageWalkLeft1;
    GreenfootImage imageWalkLeft2;
    GreenfootImage imageWalkLeft3;
    GreenfootImage imageWalkLeft4;
    GreenfootImage imageWalkLeft5;
    GreenfootImage imageWalkLeft6;
    GreenfootImage imageWalkLeft7;
    GreenfootImage imageWalkLeft8;
      
    GreenfootImage imageWalkRight1;
    GreenfootImage imageWalkRight2;
    GreenfootImage imageWalkRight3;
    GreenfootImage imageWalkRight4;
    GreenfootImage imageWalkRight5;
    GreenfootImage imageWalkRight6;
    GreenfootImage imageWalkRight7;
    GreenfootImage imageWalkRight8;
    
    GreenfootImage imageDying1;
    GreenfootImage imageDying2;
    GreenfootImage imageDying3;
    
    GreenfootImage imageAttack1;
    GreenfootImage imageAttack2;
    GreenfootImage imageAttack3;
    GreenfootImage imageAttack4;
    
    public Enemy()
    {
        // Load images.
        imageWalkLeft1 = new GreenfootImage("EnemyWalking01.png");
        imageWalkLeft2 = new GreenfootImage("EnemyWalking02.png");
        imageWalkLeft3 = new GreenfootImage("EnemyWalking03.png");
        imageWalkLeft4 = new GreenfootImage("EnemyWalking04.png");
        imageWalkLeft5 = new GreenfootImage("EnemyWalking05.png");
        imageWalkLeft6 = new GreenfootImage("EnemyWalking06.png");
        imageWalkLeft7 = new GreenfootImage("EnemyWalking07.png");
        imageWalkLeft8 = new GreenfootImage("EnemyWalking08.png");
        // Images are the same for walking right, just mirrored.
        imageWalkRight1 = new GreenfootImage("EnemyWalking01.png");
        imageWalkRight2 = new GreenfootImage("EnemyWalking02.png");
        imageWalkRight3 = new GreenfootImage("EnemyWalking03.png");
        imageWalkRight4 = new GreenfootImage("EnemyWalking04.png");
        imageWalkRight5 = new GreenfootImage("EnemyWalking05.png");
        imageWalkRight6 = new GreenfootImage("EnemyWalking06.png");
        imageWalkRight7 = new GreenfootImage("EnemyWalking07.png");
        imageWalkRight8 = new GreenfootImage("EnemyWalking08.png");
        
        imageDying1 = new GreenfootImage("EnemyDying01.png");
        imageDying2 = new GreenfootImage("EnemyDying02.png");
        imageDying3 = new GreenfootImage("EnemyDying03.png");
        
        imageAttack1 = new GreenfootImage("EnemyAttack01.png");
        imageAttack2 = new GreenfootImage("EnemyAttack02.png");
        imageAttack3 = new GreenfootImage("EnemyAttack03.png");
        imageAttack4 = new GreenfootImage("EnemyAttack04.png");
        
        imageDying1.scale(50, 32);
        imageDying2.scale(50, 32);
        imageDying3.scale(50, 32);
        
        imageAttack1.scale(45, 50);
        imageAttack2.scale(45,50);
        imageAttack3.scale(50,50);
        imageAttack4.scale(50,50);
        
        // Mirrors images for walking right.
                imageWalkRight1.mirrorHorizontally();
                imageWalkRight2.mirrorHorizontally();
                imageWalkRight3.mirrorHorizontally();
                imageWalkRight4.mirrorHorizontally();
                imageWalkRight5.mirrorHorizontally();
                imageWalkRight6.mirrorHorizontally();
                imageWalkRight7.mirrorHorizontally();
                imageWalkRight8.mirrorHorizontally();
                
                // Changes the size of the images.
                imageWalkLeft1.scale(45, 45);
                imageWalkLeft2.scale(45, 45);
                imageWalkLeft3.scale(45, 45);
                imageWalkLeft4.scale(45, 45);
                imageWalkLeft5.scale(45, 45);
                imageWalkLeft6.scale(45, 45);
                imageWalkLeft7.scale(45, 45);
                imageWalkLeft8.scale(45, 45);
                
                imageWalkRight1.scale(45, 45);
                imageWalkRight2.scale(45, 45);
                imageWalkRight3.scale(45, 45);
                imageWalkRight4.scale(45, 45);
                imageWalkRight5.scale(45, 45);
                imageWalkRight6.scale(45, 45);
                imageWalkRight7.scale(45, 45);
                imageWalkRight8.scale(45, 45);
    }
    
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        isTouching();
        health();
        swordThrow();
        walkLeft();
        walkRight();
        enemyFrameCounter++;
        //System.out.println(enemyFrameCounter);
        // If enemy frame counter is 40, reset to 0.
        if (enemyFrameCounter == 40)
        {
            enemyFrameCounter = 0;
        }
    }
    
    public void swordThrow()
    {
        //System.out.println(counter);
        if (shotTimer.millisElapsed() > minShotWait + randomShot)
        {
            shotTimer.mark();
            randomShot = Greenfoot.getRandomNumber(6000);
            spawnSword();
        }
    }
    
    public void spawnSword()
    {
        Sword sword = new Sword();
        
        getWorld().addObject(sword, getX(), getY());
    }
    
    public void health()
    {
        // if touches protective orb lose 25 health.
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
        //System.out.println(deltaX);
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
         getWorld().removeObject(this);
    }
    
    public void walkLeft()
            {
                if (deltaX < 0)
                {
                    if (enemyFrameCounter >= 8 * animationInterval)
                    {
                        // Reset to 0.
                        //enemyFrameCounter = 0;
                    }
                
                    if (enemyFrameCounter == 0)
                    {
                        setImage(imageWalkLeft1);
                    }
                    else if (enemyFrameCounter == 1 * animationInterval)
                    {
                        setImage(imageWalkLeft2);
                    }
                    else if (enemyFrameCounter == 2 * animationInterval)
                    {
                        setImage(imageWalkLeft3);
                    }
                    else if (enemyFrameCounter == 3 * animationInterval)
                    {
                        setImage(imageWalkLeft4);
                    }
                    else if (enemyFrameCounter == 4 * animationInterval)
                    {
                        setImage(imageWalkLeft5);
                    }
                    else if (enemyFrameCounter == 5 * animationInterval)
                    {
                        setImage(imageWalkLeft6);
                    }
                    else if (enemyFrameCounter == 6 * animationInterval)
                    {
                        setImage(imageWalkLeft7);
                    }
                    else if (enemyFrameCounter == 7 * animationInterval)
                    {
                        setImage(imageWalkLeft8);
                    }
                
                    // Increment frame counter.
                    //enemyFrameCounter++;
                }
            }
            
     public void walkRight()
            {
                if (deltaX > 1)
                {
                    if (enemyFrameCounter >= 8 * animationInterval)
                    {
                    // Reset to 0.
                    //enemyFrameCounter = 0;
                    }
            
                    if (enemyFrameCounter == 0)
                    {
                        setImage(imageWalkRight1);
                    }
                    else if (enemyFrameCounter == 1 * animationInterval)
                    {
                        setImage(imageWalkRight2);
                    }
                    else if (enemyFrameCounter == 2 * animationInterval)
                    {
                        setImage(imageWalkRight3);
                    }
                    else if (enemyFrameCounter == 3 * animationInterval)
                    {
                        setImage(imageWalkRight4);
                    }
                    else if (enemyFrameCounter == 4 * animationInterval)
                    {
                        setImage(imageWalkRight5);
                    }
                    else if (enemyFrameCounter == 5 * animationInterval)
                    {
                        setImage(imageWalkRight6);
                    }
                    else if (enemyFrameCounter == 6 * animationInterval)
                    {
                        setImage(imageWalkRight7);
                    }
                    else if (enemyFrameCounter == 7 * animationInterval)
                    {
                        setImage(imageWalkRight8);
                    }
                } 
        
                // Increment frame counter.
                //enemyFrameCounter++;
            }
}
