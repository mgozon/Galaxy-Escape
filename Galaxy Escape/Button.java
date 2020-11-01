import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Button here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button extends Actor
{
    int j = -1;
    public Button(Color c, int w, int h, int j) {
        GreenfootImage im = new GreenfootImage("beige.jpg");
        if (j==5) im = new GreenfootImage("playbutton.png");
        im.scale(w, h);
        im.setColor(c);
        setImage(im);
        this.j = j;
    }
    
    public void act() 
    {
        if (Greenfoot.mouseClicked(this) && j < 5) ((ScrollingWorld)getWorld()).gr.playSound(j);
        else if (Greenfoot.mouseClicked(this)) ((ScrollingWorld)getWorld()).gr.playGame();
    }    
}
