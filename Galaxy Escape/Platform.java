import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Platform here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Platform extends GameObject
{
    public String type;
    
    public Platform(double a, double b, double w, double h, GreenfootImage i, String t) {
        super(a, b, w, h, i);
        type = t;
    }
    
    public void act() 
    {
        super.act();
    }    
}
