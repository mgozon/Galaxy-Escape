import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
/**
 * Write a description of class GridLines here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GridLines extends GameObject
{
    private int width, distance;
    private ArrayList<GameObject> HLines = new ArrayList<GameObject>();
    private ArrayList<GameObject> VLines = new ArrayList<GameObject>();
    private GreenfootImage image;
    private boolean initiate = false;
    
    public GridLines(int w, int d, GreenfootImage i) {
        width = w;
        distance = d;
        image = i;
        /*for (int j = 0; j < getWorld().getWidth(); j += d) {
            GreenfootImage im = new GreenfootImage(image);
            im.scale(w, getWorld().getHeight());
            GameObject g = new GameObject
            HLines.add(im);
            getWorld().addObject
        }*/
        
    }
    
    public void act() 
    {
        // bad but each frame recalculates grids (low operations and only in dev
        // horizontal then vertical///////////////////////////////////////////////////////////
        for (int i = 0; i < HLines.size(); i++) getWorld().removeObject(HLines.get(i));
        for (int i = 0; i < VLines.size(); i++) getWorld().removeObject(VLines.get(i));
        HLines.clear(); VLines.clear();
        /*for (int i = 0; i < getWorld().getWidth() ; i += distance/getZoom()) {
            GreenfootImage im = new GreenfootImage(image);
            im.scale(width, getWorld().getHeight());
            GameObject g = new GameObject();
            g.setImage(im);
            int[] cams = getCam();
            getWorld().addObject(g, i - (int)(cams[0] % (distance/getZoom())), getWorld().getHeight()/2);
            HLines.add(g);
        }
        for (int i = 0; i < getWorld().getHeight() ; i += distance/getZoom()) {
            GreenfootImage im = new GreenfootImage(image);
            im.scale(getWorld().getWidth(), width);
            GameObject g = new GameObject();
            g.setImage(im);
            int[] cams = getCam();
            getWorld().addObject(g, getWorld().getWidth()/2, i - (int)(cams[1] % (distance/getZoom())));
            VLines.add(g);
        }*/
        double[] cam = getCam();
        for (double i = ((int)(cam[0] - getWorld().getWidth()*getZoom())/50)*50; i < ((int)(cam[0] + getWorld().getWidth()*getZoom())/50)*50; i += distance) {
            GreenfootImage im = new GreenfootImage(image);
            im.scale(width, getWorld().getHeight());
            GameObject g = new GameObject();
            g.setImage(im);
            getWorld().addObject(g, (int)(1/getZoom()*(i-cam[0]) + getWorld().getWidth()/2), getWorld().getHeight()/2);
            HLines.add(g);
        }
        for (double i = ((int)(cam[1] - getWorld().getHeight()*getZoom())/50)*50; i < ((int)(cam[1] + getWorld().getHeight()*getZoom())/50)*50; i += distance) {
            GreenfootImage im = new GreenfootImage(image);
            im.scale(getWorld().getWidth(), width);
            GameObject g = new GameObject();
            g.setImage(im);
            getWorld().addObject(g, getWorld().getWidth()/2, (int)(1/getZoom()*(i-cam[1])*-1 + getWorld().getHeight()/2));
            HLines.add(g);
        }
        ///////////////////////////////////////////////////////////////////////////////////////////
        // make a ton of lines instead
        /*if (initiate == false) {
            initiate = true;
            for (int j = 0; j < 2000; j += distance) {
                GameObject g = new GameObject(j, 5000, width, 20000, image);
                getWorld().addObject(g, 1, 1);
            }
            for (int j = 0; j < 2000; j += distance) {
                GameObject g = new GameObject(5000, j, 20000, width, image);
                getWorld().addObject(g, 0, 0);
            }
        }*/
        
    }    
}
