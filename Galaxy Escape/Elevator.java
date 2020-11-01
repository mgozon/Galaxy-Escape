import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Elevator here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Elevator extends Platform
{
    double rx, ry;
    double b;
    double cx, cy;
    
    public Elevator(double a, double b, double w, double h, GreenfootImage i, double rxx, double ryy, double s) {
        super(a, b, w, h, i, "r");
        cx = a; cy = b;
        rx = rxx; ry = ryy;
        this.b = 2*Math.PI / s;
    }
    
    public void act() 
    {
        super.act();
        SetXY(cx + rx*Math.cos(b*Time.gTime), cy + ry*Math.sin(b*Time.gTime));
        //System.out.println(b);
        //System.out.println(Math.cos(2*Math.PI/5*Time.gTime));
        //System.out.println((cx + rx*Math.cos(b*Time.gTime)) + " " + (cy + ry*Math.sin(b*Time.gTime)));
        //System.out.println(Time.gTime);
        //System.out.println(GetX() + " " + GetY());
    }   
}
