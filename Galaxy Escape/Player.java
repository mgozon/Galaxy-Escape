import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Mover
{
    //debug
    private int bufferC = 0;
    
    public Player(double x, double y, double w, double h, GreenfootImage i) {
        super(x, y, w, h, i);
    }
    
    public void act() 
    {
        //System.out.println(getXSpeed() + " " + getYSpeed());
        checkMovement();
        smoothCam();
        super.act();
    }
    
    public void checkMovement() {
        Vector velocity = new Vector(getXSpeed(), getYSpeed());
        Vector forward = velocity.proj(new Vector(-getRotation() * Math.PI / 180));
        Vector vertical = velocity.proj(new Vector((-getRotation()+90) * Math.PI / 180));
        //double angle = forward.angle(new Vector(-getRotation() * Math.PI / 180)) * 180 / Math.PI;
        double angle = velocity.angle(new Vector(-getRotation()*Math.PI/180)) * 180 / Math.PI;
        boolean goingForward = angle < 80 || angle > 280;
        boolean goingBackward = angle < 260 && angle > 100;
        //System.out.println(angle + " " + velocity + " " + forward);
        //System.out.println(forward + " " + vertical + " " + (new Vector(-getRotation());
        //System.out.print("forward: " + goingForward + " ");
        
        /*if (canMoveUp() == false) {
            SetXSpeed(forward.x);
            SetYSpeed(forward.y);
            System.out.println("hit top");
        }*/
        
        if (Greenfoot.isKeyDown("right")) {
            if (canMoveRight()) {
                if (goingForward == false || (goingForward && forward.abs() < terminalSpeed)) {
                    double theta = -getRotation() * Math.PI / 180;
                    SetXSpeed(getXSpeed() + 10*Time.deltaTime*Math.cos(theta));
                    SetYSpeed(getYSpeed() + 10*Time.deltaTime*Math.sin(theta));
                }
            } else {
                SetXSpeed(vertical.x);
                SetYSpeed(vertical.y);
            }
        } else if (Greenfoot.isKeyDown("left")) {
            if (canMoveLeft()) {
                if (goingBackward == false || (goingBackward == true && forward.abs() < terminalSpeed)) {
                    double theta = -getRotation() * Math.PI / 180 + 180;
                    SetXSpeed(getXSpeed() + 12*Time.deltaTime*Math.cos(theta));
                    SetYSpeed(getYSpeed() + 12*Time.deltaTime*Math.sin(theta));
                }
            } else {
                SetXSpeed(vertical.x);
                SetYSpeed(vertical.y);
            }
        } //else SetXSpeed(0);
        if (Greenfoot.isKeyDown("space")) { // debug
            SetYSpeed(0);
            SetXSpeed(0);
        }
        
        if ((canMoveRight() == false && (goingForward==true || forward.abs() >= terminalSpeed)) || (canMoveLeft() == false && (goingBackward==true || forward.abs() >= terminalSpeed))) {
            SetXSpeed(vertical.x);
            SetYSpeed(vertical.y);
        }
        
        List<Coin> lc = getIntersectingObjects(Coin.class);
        for (Coin cc : lc) {
            getWorld().removeObject(cc);
            ((ScrollingWorld)getWorld()).gr.coins++;
        }
        
        List<Gate> lg = getIntersectingObjects(Gate.class);
        if (lg.size() != 0) ((ScrollingWorld)getWorld()).gr.ping();
        
        if (Greenfoot.isKeyDown("up")) {
            //Actor p = getOneObjectAtOffset(0, getImage().getHeight() / 2, Platform.class);
            //if (p != null) { SetYSpeed(10); jump(); }
            //else SetYSpeed(5); // debug
            if (canJump()) {
                double theta = (-getRotation()+90) * Math.PI / 180;
                Vector un = new Vector(theta); // unit normal
                SetXY(GetX() + un.x*GameObject.BD/25, GetY() + un.y*GameObject.BD/25);
                SetXSpeed(getXSpeed() + (-gravity/2)*Math.cos(theta));
                SetYSpeed(getYSpeed() + (-gravity/2)*Math.sin(theta));
                jump();
            }
            
        }
        bufferC++;
        if (bufferC == 10) {
            bufferC = 0;
            //System.out.println(GetX() + " " + GetY() + " " + getRotation() + " " + getOrbit());
        }
        if (Greenfoot.isKeyDown("A")) dZoom(-0.01);
        else if (Greenfoot.isKeyDown("S")) dZoom(0.01);
    }
}
