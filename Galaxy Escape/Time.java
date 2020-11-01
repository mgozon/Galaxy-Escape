import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Time here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Time extends Actor
{
    public static double deltaTime, gTime=0;
    private static long lastTime = System.currentTimeMillis();
    public void act() 
    {
        deltaTime = (double)(System.currentTimeMillis()-lastTime) / 1000;
        lastTime = System.currentTimeMillis();
        gTime += deltaTime;
    }    
}
