import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.awt.*;
/**
 * Write a description of class Label here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Label extends Actor
{
    int fs;
    Color cc;
    boolean isButton;
    int j;
    public Label(String text, int size, Color c) {
        fs = size; cc = c;
        GreenfootImage im = new GreenfootImage(text, fs, cc, new Color(0, 0, 0, 0));
        setImage(im);
        isButton = false;
    }
    
    public Label(String text, int size, Color c, int j) {
        isButton = true;
        fs = size; cc = c;
        GreenfootImage im = new GreenfootImage(text, fs, cc, new Color(0, 0, 0, 0));
        setImage(im);
        this.j = j;
    }
    
    public void setText(String text) {
        GreenfootImage im = new GreenfootImage(text, fs, cc, new Color(0, 0, 0, 0));
        setImage(im);
    }
    
    public void act() {
        if (isButton) {
            if (Greenfoot.mouseClicked(this) && j < 5) ((ScrollingWorld)getWorld()).gr.playSound(j);
        }
    }
    /*
    private int x, y;
    public Label(String text, int x, int y, int s) {
        GreenfootImage img = new GreenfootImage(2*s*text.length(), 3*s);
        img.setColor(greenfoot.Color.BLUE);
        img.drawString(text, x, y);
        this.x = x; this.y = y;
        setImage(img);
    }
    
    public void setText(String text) {
        getImage().drawString(text, x, y);
    }
    */
    /*public void act() 
    {
        // Add your action code here.
    }*/    
}
