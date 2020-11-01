import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Mover here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mover extends GameObject
{
    private double xSpeed, ySpeed;
    public static double gravity = -30, terminalSpeed = -gravity/5; // let negative mean downwards
    private boolean isJumping = false;
    private GroupP orbit;
    
    public Mover(double a, double b, double w, double h, GreenfootImage i) { super(a, b, w, h, i); }
    
    public void act() 
    {
        findOrbit();
        turnToOrbit();
        applyGravity();
        applyPhysics();
        blockAbove();
        super.act();
    }
    
    private void findOrbit() { // fix as we need to measure it case by case for sides
        double d = Double.MAX_VALUE;
        for (GroupP g : ((ScrollingWorld)getWorld()).groups) {
            double min = Double.MAX_VALUE;
            if (g.type.equals("r")) {
                double t = g.cy+g.ch;
                double b = g.cy-g.ch;
                double r = g.cx+g.cw;
                double l = g.cx-g.cw;
                // 8 cases: sides and corners
                if (GetY()-t > 0) { // above
                    if (GetX() - r > 0) { // right
                        min = Math.sqrt(Math.pow(GetX()-r, 2) + Math.pow(GetY() - t, 2));
                    } else if (l - GetX() > 0) { // left
                        min = Math.sqrt(Math.pow(l-GetX(), 2) + Math.pow(GetY() - t, 2));
                    } else { // middle
                        min = GetY() - t;
                    }
                } else if (b - GetY() > 0) { // below
                    if (GetX() - r > 0) { // right
                        min = Math.sqrt(Math.pow(GetX()-r, 2) + Math.pow(b - GetY(), 2));
                    } else if (l - GetX() > 0) { // left
                        min = Math.sqrt(Math.pow(l-GetX(), 2) + Math.pow(b - GetY(), 2));
                    } else { // middle
                        min = b - GetY();
                    }
                } else { // between
                    if (GetX() - r > 0) { // right
                        min = GetX() - r;
                    } else if (l - GetX() > 0) { // left
                        min = l - GetX();
                    } else { // middle
                        //System.out.println("Inside!?!??!?!?!?!?!?!?");
                        min = 0;
                    }
                }
            } else if (g.type.equals("c")) {
                min = Math.sqrt(Math.pow(GetX() - g.cx, 2) + Math.pow(GetY() - g.cy, 2)) - g.cw;
            }
            
            
            if (min < d) { orbit = g; d = min; }
        }
    }
    
    private void turnToOrbit() {
        if (orbit.type.equals("r")) {
            double t = orbit.cy+orbit.ch;
            double b = orbit.cy-orbit.ch;
            double r = orbit.cx+orbit.cw;
            double l = orbit.cx-orbit.cw;
            // 8 cases: sides and corners
            if (GetY()-t > 0) { // above
                if (GetX() - r > 0) { // right
                    double nx = GetX() - r;
                    double ny = GetY() - t;
                    double theta = Math.atan(ny/nx) * 180 / Math.PI;
                    setRotation((int)(-(theta-90)));
                } else if (l - GetX() > 0) { // left
                    double nx = GetX() - l;
                    double ny = GetY() - t;
                    double theta = Math.atan(ny/nx) * 180 / Math.PI + 180;
                    setRotation((int)(-(theta-90)));
                } else { // middle
                    setRotation(0);
                }
            } else if (b - GetY() > 0) { // below
                if (GetX() - r > 0) { // right
                    double nx = GetX() - r;
                    double ny = GetY() - b;
                    double theta = Math.atan(ny/nx) * 180 / Math.PI;
                    setRotation((int)(-(theta-90)));
                } else if (l - GetX() > 0) { // left
                    double nx = GetX() - l;
                    double ny = GetY() - b;
                    double theta = Math.atan(ny/nx) * 180 / Math.PI + 180;
                    setRotation((int)(-(theta-90)));
                } else { // middle
                    setRotation(180);
                }
            } else { // between
                if (GetX() - r >= 0) { // right
                    setRotation(90);
                } else if (l - GetX() >= 0) { // left
                    setRotation(-90);
                } else { // middle
                    //System.out.println("Inside!?!??!?!?!?!?!?!?");
                }
            }
        } else if (orbit.type.equals("c")) {
            double dx = GetX() - orbit.cx;
            double dy = GetY() - orbit.cy;
            double theta = Math.atan(dy / dx) * 180 / Math.PI;
            if (dx < 0) theta += 180;
            setRotation((int)(-(theta-90)));
        }
    }
    
    public void applyGravity() {
        boolean flag = false;
        double t = (-getRotation()+90) * Math.PI / 180; // normal *note that getOneObjectAtOffset is upside down
        Vector v = new Vector(t);
        Vector velocity = new Vector(xSpeed, ySpeed);
        if (orbit.type.equals("r")) {
            Vector normal = v.scale(getImage().getHeight()/2);
            //System.out.println(normal);
            Actor a = getOneObjectAtOffset(-(int)normal.x, (int)normal.y, Platform.class);
            if (a != null /*&& v.angle(velocity) > Math.PI/2*/) {
                flag = true;
                normal = normal.unit().scale(normal.abs()-GameObject.BD/25);
                int i = 0;
                while (getOneObjectAtOffset((int)(-normal.x + v.x*i), -(int)(-normal.y + v.y*i), Platform.class) != null) i++;
                SetXY(GetX() + v.x*i*getZoom(), GetY() + v.y*i*getZoom());
            }
        } else if (orbit.type.equals("c")) {
            double dx = (GetX() - GetHeight()/2*Math.cos(t)) - orbit.cx; // we must work in one world dimension,
            double dy = (GetY() - GetHeight()/2*Math.sin(t)) - orbit.cy; // not camera screen mixture
            Vector dv = new Vector(dx, dy);
            if (dv.abs() <= orbit.cw /*&& v.angle(velocity) > Math.PI/2*/) {
                flag = true;
                dv = dv.unit().scale(dv.abs() + GameObject.BD/25);
                Vector change = dv.unit();
                int i = 0;
                while (Math.pow(dv.x+change.x*i, 2) + Math.pow(dv.y+change.y*i, 2) <= Math.pow(orbit.cw, 2)) i++;
                SetXY(GetX() + change.x*i*getZoom(), GetY() + change.y*i*getZoom());
            }
        }
        
        if (flag) {
            Vector res = velocity.proj(new Vector(t - Math.PI/2));
            xSpeed = res.x; ySpeed = res.y;
            isJumping = false;
        } else if (true) {
            double theta = (-getRotation() + 90) * Math.PI / 180;
            ySpeed += gravity*Time.deltaTime * Math.sin(theta);
            xSpeed += gravity*Time.deltaTime * Math.cos(theta); 
            //ySpeed = Math.max(ySpeed, terminalSpeed);
            //xSpeed = Math.max(xSpeed, terminalSpeed);
            //xSpeed = Math.min(xSpeed, -terminalSpeed);
            
            //if (forward.abs() > terminalSpeed) forward = forward.unit().scale(terminalSpeed);
            
        }
        velocity = new Vector(xSpeed, ySpeed);
        Vector forward = velocity.proj(new Vector(-getRotation() * Math.PI / 180));
        Vector vertical = velocity.proj(new Vector((-getRotation()+90) * Math.PI / 180));
        double angle = velocity.angle(new Vector((-getRotation() + 90) * Math.PI / 180)) * 180 / Math.PI;
        boolean upwards = (angle < 90 || angle > 270) && vertical.abs() > 2;
        if (upwards == false && vertical.abs() > terminalSpeed) {
            vertical = vertical.unit().scale(terminalSpeed);
            //System.out.println("slow..." +angle+ " " + vertical.abs());
        } //else System.out.println("not slow");
        xSpeed = forward.x + vertical.x;
        ySpeed = forward.y + vertical.y;
        //System.out.println("upwards: " + upwards);
    }
    public void applyPhysics() {
        SetXY(GetX() + GameObject.BD*xSpeed*Time.deltaTime, GetY() + GameObject.BD*ySpeed*Time.deltaTime);
        //Actor b = getOneObjectAtOffset(0, getImage().getHeight()/2, Platform.class);
        //if (b != null) System.out.println("testing here\n\nyes");
    }
    public void SetXSpeed(double a) { xSpeed = a; }
    public void SetYSpeed(double a) { ySpeed = a; }
    public double getXSpeed() { return xSpeed; }
    public double getYSpeed() { return ySpeed; }
    public void jump() { isJumping = true; }
    public boolean canJump() { return !isJumping; }
    public GroupP getOrbit() { return orbit; }
    public boolean canMoveRight() {
        double theta = -getRotation() * Math.PI / 180;
        int xloc = (int)(getImage().getWidth()/2 * Math.cos(theta));
        int yloc = (int)(getImage().getWidth()/2 * Math.sin(theta));
        List<Platform> plats = getObjectsAtOffset(xloc, -yloc, Platform.class);
        for (Platform p : plats) {
            if (p.type.equals("r")) return false;
            else if (p.type.equals("c"))
                if (Math.pow(getX()-xloc-p.getX(), 2) + Math.pow(getY()-yloc-p.getY(), 2) <= Math.pow(p.getImage().getHeight()/2, 2))
                    return false;
        }
        return true;
    }
    public boolean canMoveLeft() {
        double theta = -getRotation() * Math.PI / 180;
        int xloc = -(int)(getImage().getWidth()/2 * Math.cos(theta));
        int yloc = -(int)(getImage().getWidth()/2 * Math.sin(theta));
        List<Platform> plats = getObjectsAtOffset(xloc, -yloc, Platform.class);
        for (Platform p : plats) {
            if (p.type.equals("r")) return false;
            else if (p.type.equals("c"))
                if (Math.pow(getX()-xloc-p.getX(), 2) + Math.pow(getY()-yloc-p.getY(), 2) <= Math.pow(p.getImage().getHeight()/2, 2))
                    return false;
        }
        return true;
    }
    public void blockAbove() {
        double theta = (-getRotation()+90) * Math.PI/180;
        int xloc = (int)(getImage().getHeight()/2 * Math.cos(theta));
        int yloc = (int)(getImage().getHeight()/2 * Math.sin(theta));
        Vector v = new Vector(theta);
        int i = 0;
        while (getOneObjectAtOffset((int)(xloc - v.x*i), (int)(-yloc + v.y*i), Platform.class) != null) i++;
        SetXY(GetX() -v.x*i, GetY() -v.y*i);
        if (i>0) {
            Vector velocity = new Vector(getXSpeed(), getYSpeed());
            Vector forward = velocity.proj(new Vector(-getRotation() * Math.PI / 180));
            Vector vertical = velocity.proj(new Vector((-getRotation()+90) * Math.PI / 180));
            vertical.scale(0);
            SetXSpeed(forward.x+vertical.x);
            SetYSpeed(forward.y+vertical.y);
        }
    }
    /*public boolean canMoveRight() {
        ArrayList<Platform> ps = (ArrayList<Platform>) getIntersectingObjects(Platform.class);
        for (Platform p : ps)
            if (this.GetX() < p.GetX()) return false;
        return true;
    }
    public boolean canMoveLeft() {
        ArrayList<Platform> ps = (ArrayList<Platform>) getIntersectingObjects(Platform.class);
        for (Platform p : ps)
            if (this.GetX() > p.GetX()) return false;
        return true;
    }*/
    /*public boolean canMoveRight() {
        double theta = -getRotation() * Math.PI / 180;
        Actor a = getOneObjectAtOffset((int)(getImage().getWidth()/2 * Math.cos(theta)), (int)(getImage().getWidth()/2 * Math.sin(theta)), Platform.class);
        if (a == null) return true;
        return false;
    }
    public boolean canMoveLeft() {
        double theta = -getRotation() * Math.PI / 180;
        Actor a = getOneObjectAtOffset(-(int)(getImage().getWidth()/2 * Math.cos(theta)), -(int)(getImage().getWidth()/2 * Math.sin(theta)), Platform.class);
        if (a == null) return true;
        return false;
    }*/
    /*public boolean canMoveRight() {
        ArrayList<Platform> ps = (ArrayList<Platform>) getIntersectingObjects(Platform.class);
        for (Platform p : ps) {
            int bl = p.GetY() - p.getImage().getHeight()/2;
            int tl = p.GetY() + p.getImage().getHeight()/2;
            int br = this.GetY() - this.getImage().getHeight()/2;
            int tr = this.GetY() + this.getImage().getHeight()/2;
            if (this.GetX() < p.GetX() && (tr > bl || br < tl)) return false;
        }   
        return true;
    }
    public boolean canMoveLeft() {
        ArrayList<Platform> ps = (ArrayList<Platform>) getIntersectingObjects(Platform.class);
        for (Platform p : ps) {
            int bl = p.GetY() - p.getImage().getHeight()/2;
            int tl = p.GetY() + p.getImage().getHeight()/2;
            int br = this.GetY() - this.getImage().getHeight()/2;
            int tr = this.GetY() + this.getImage().getHeight()/2;
            if (this.GetX() < p.GetX() && (tr > bl || br < tl)) return false;
        }   
        return true;
    }*/
}
