import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameObject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameObject extends Actor
{
    //private boolean stationary = true;
    private double scrollX, scrollY, x, y, width, height;
    private static double camX = 0, camY = 0;
    private static double zoom = 1;
    public static int BD = 50; // treat this as a meter
    
    private GreenfootImage image = new GreenfootImage("transparent.png");
    // let x and y determine the real location
    // scrollX and scrollY are the screen locations
    public GameObject() { x = y = width = height = scrollX = scrollY = 1; }
    
    public GameObject(double a, double b, double w, double h, GreenfootImage i) {
        x = a;
        y = b;
        width = w;
        height = h;
        image = i;
        GreenfootImage image = new GreenfootImage(i);
        image.scale((int)w, (int)h);
        setImage(image);
    }
    
    public void act() 
    {
        SetLocation();
        //smoothCam();
        //System.out.println(x + " " + y + " " + scrollX + " " + scrollY + " " + camX + " " + camY);
    }
    
    private void SetLocation() {
        scrollX = (int)(1/zoom*(x-camX) + getWorld().getWidth()/2);
        scrollY = (int)(1/zoom*(y-camY)*-1 + getWorld().getHeight()/2);
        setLocation((int)scrollX, (int)scrollY);
        GreenfootImage i = new GreenfootImage(image);
        i.scale((int)Math.ceil(width*(1/zoom)), (int)Math.ceil(height*(1/zoom)));
        setImage(i);
    }
    
    protected void smoothCam() {
        //camX = (camX + ((ScrollingWorld)getWorld()).getPlayer().GetX()) / 2;
        //camY = (camY + ((ScrollingWorld)getWorld()).getPlayer().GetY()) / 2;
        camX = ((ScrollingWorld)getWorld()).getPlayer().GetX();
        camY = ((ScrollingWorld)getWorld()).getPlayer().GetY();
    }
    
    //public void setKinematics() { stationary = false; }
    public double GetX() { return x; }
    public double GetY() { return y; }
    public void SetXY(double a, double b) { x = a; y = b; }
    public double GetWidth() { return width; }
    public double GetHeight() { return height; }
    public void SetDimensions(double a,double b) { width = a; height = b; }
    public void dZoom(double x) { zoom += x; }
    public double getZoom() { return zoom; }
    public double[] getCam() { return new double[] { camX, camY}; }
    public String toString() { return String.format("(%.2f, %.2f, %.2f, %.2f)", x, y, width, height); }
}
