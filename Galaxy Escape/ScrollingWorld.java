import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScrollingWorld extends World
{
    Player player;
    GameRunner gr;
    public ArrayList<GroupP> groups = new ArrayList<GroupP>();
    
    Label title;
    Button play;
    Button[] bs;
    Label[] ls;
    public ScrollingWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, false); // this can be modified at any time
        //makePlayer();
        //makeTerrain();
        //addObject(new GridLines(1, GameObject.BD, new GreenfootImage("lgrey.jpg")), 0, 0);
        addObject(new Time(), 0, 0);
        makeUI();
    }
    
    public void makeUI() {
        GreenfootImage image = new GreenfootImage("whitesquare.png");
        image.scale(1, 1);
        Label title = new Label("Galaxy Escape", 50, Color.BLUE);
        Label instr = new Label("Arrow keys = move\nA/S = zoom in/out\nSpace = stop", 20, Color.GREEN);
        
        
        Button b1 = new Button(new Color(255, 200, 200), 100, 50, 1);
        Button b2 = new Button(new Color(200, 255, 200), 100, 50, 2);
        Button b3 = new Button(new Color(200, 200, 255), 100, 50, 3);
        Button b4 = new Button(new Color(255, 255, 200), 100, 50, 4);
        
        Label l1 = new Label("Main Menu", 20, Color.DARK_GRAY, 1);
        Label l2 = new Label("Game", 20, Color.DARK_GRAY, 2);
        Label l3 = new Label("Lose", 20, Color.DARK_GRAY, 3);
        Label l4 = new Label("Win", 20, Color.DARK_GRAY, 4);
        
        Button[] bs = {b1, b2, b3, b4};
        Label[] lbs = {l1, l2, l3, l4};
        
        Button start = new Button(Color.BLACK, 75, 75, 5);
        
        gr = new GameRunner(image/*, title, bs, lbs, start*/);
        addObject(gr, getWidth()/2, getHeight()/2);
        addObject(title, getWidth()/2, getHeight()/6);
        
        addObject(b1, 100, 200);
        addObject(b2, 500, 200);
        addObject(b3, 100, 300);
        addObject(b4, 500, 300);
        
        addObject(l1, 100, 200);
        addObject(l2, 500, 200);
        addObject(l3, 100, 300);
        addObject(l4, 500, 300);
        
        addObject(start, 300, 250);
        this.title = title;
        play = start;
        this.bs = bs;
        this.ls = lbs;
        
        addObject(instr, 300, 150);
        
        Label instr2 = new Label("Collect the 3 coins to escape through the gate before the time runs out!", 20, Color.MAGENTA);
        addObject(instr2, 300, 100);
        
        Label me = new Label("Marcus Gozon", 20, Color.BLACK);
        addObject(me, 525, 375);
        
        Button bbb = new Button(new Color(0, 0, 0, 0), 20, 50, 100);
        bbb.setImage(new GreenfootImage("music.png"));
        bbb.getImage().scale(20, 40);
        addObject(bbb, 100, 250);
        bbb = new Button(new Color(0, 0, 0, 0), 20, 50, 100);
        bbb.setImage(new GreenfootImage("music.png"));
        bbb.getImage().scale(20, 40);
        addObject(bbb, 500, 250);
    }
    
    public void deleteUI() {
        /*removeObject(title);
        removeObject(play);
        for (int i = 0; i < 4; i++) removeObject(bs[i]);
        for (int i = 0; i < 4; i++) removeObject(ls[i]);
        */
        List<Button> ac = getObjects(Button.class);
        for (Button a : ac) removeObject(a);
        List<Label> ac2 = getObjects(Label.class);
        for (Label l : ac2) removeObject(l);
    }
    
    public void build1() {
        GreenfootImage background = new GreenfootImage("galaxy.jpg");
        background.scale(600, 400);
        setBackground(background);
        GreenfootImage im = new GreenfootImage("beige.jpg");
        GameObject gs = new GameObject(-500, 1550, 100, 40, im);
        addObject(gs, 0, 0);
        im = new GreenfootImage("GATE", 20, Color.RED, new Color(0, 0, 0, 0));
        gs = new GameObject(-500, 1550, im.getWidth(), im.getHeight(), im);
        addObject(gs, 0, 0);
        im = new GreenfootImage("Coin.png");
        Coin c = new Coin(-4835, 25, 50, 50, im);
        addObject(c, 0, 0);
        c = new Coin(-150, -6107, 50, 50, im);
        addObject(c, 0, 0);
        c = new Coin(4784, 50, 50, 50, im);
        addObject(c, 0, 0);
        Gate ggg = new Gate(1152, 6184, 100, 100, new GreenfootImage("Gate.png"));
        addObject(ggg, 0, 0);
        makePlayer();
        // main space
        /*makeBlock(-25, 0, 51, 1);
        makeBlock(0, -25, 1, 51);
        makeBlock(-25, 30, 51, 1);
        makeBlock(-25, -30, 51, 1);
        makeBlock(-30, -25, 1, 51);
        makeBlock(30, -25, 1, 51);*/
        
        makeBlock2(0, 0, 51, 1);
        makeBlock2(0, 0, 1, 51);
        makeBlock2(0, 30, 51, 1);
        makeBlock2(0, -30, 51, 1);
        makeBlock2(30, 0, 1, 51);
        makeBlock2(-30, 0, 1, 51);
        
        //boundaries
        /*makeObstacle(-25, 32, 1, 101);
        makeObstacle(25, 32, 1, 101);
        makeObstacle(32, 25, 101, 1);
        makeObstacle(32, -25, 101, 1);
        makeObstacle(25, -32-100, 1, 101);
        makeObstacle(-25, -32-100, 1, 101);
        makeObstacle(-32-100, 25, 101, 1);
        makeObstacle(-32-100, -25, 101, 1);*/
        
        makeBrick(-25, 32+50, 1, 101);
        makeBrick(25, 32+50, 1, 101);
        makeBrick(-25, -32-50, 1, 101);
        makeBrick(25, -32-50, 1, 101);
        makeBrick(32+50, 25, 101, 1);
        makeBrick(32+50, -25, 101, 1);
        makeBrick(-32-50, 25, 101, 1);
        makeBrick(-32-50, -25, 101, 1);
        
        //bottom
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 10; j++) {
                makeCircle(-25 + i*50/6, -30 - j*50/6, 2);
            }
        }
        double px = -25, py = -30, d = 50/6;
        
        makeBrick(px+d*1/2, py-d, 1, d);
        makeBrick(px+d*1/2, py-2*d, 1, d);
        makeBrick(px+d*1/2, py-3*d, 1, d);
        makeBrick(px+d*1/2, py-4*d, 1, d);
        makeBrick(px+d*1/2, py-5*d, 1, d);
        makeBrick(px+d*3/2, py -2*d, 1, d);
        makeBrick(px+d*3/2, py -3*d, 1, d);
        makeBrick(px+d*3/2, py -4*d, 1, d);
        makeBrick(px+d*3/2, py -2*d, 1, d);
        makeBrick(px+d*5/2, py-d, 1, d);
        makeBrick(px+d*5/2, py-3*d, 1, d);
        makeBrick(px+d*5/2, py-4*d, 1, d);
        makeBrick(px+d*5/2, py-5*d, 1, d);
        makeBrick(px+d*7/2, py-2*d, 1, d);
        makeBrick(px+d*9/2, py-3*d, 1, d);
        makeBrick(px+d*11/2, py-d, 1, d);
        makeBrick(px+d*11/2, py-2*d, 1, d);
        makeBrick(px+d*11/2, py-3*d, 1, d);
        makeBrick(px+d*11/2, py-4*d, 1, d);
        makeBrick(px+d*11/2, py-5*d, 1, d);
        
        makeBrick(px+1*d, py-0.5*d, d, 1);
        makeBrick(px+2*d, py-0.5*d, d, 1);
        makeBrick(px+4*d, py-0.5*d, d, 1);
        makeBrick(px+5*d, py-0.5*d, d, 1);
        makeBrick(px+2*d, py-1.5*d, d, 1);
        makeBrick(px+4*d, py-1.5*d, d, 1);
        makeBrick(px+3*d, py-2.5*d, d, 1);
        makeBrick(px+4*d, py-3.5*d, d, 1);
        makeBrick(px+5*d, py-3.5*d, d, 1);
        makeBrick(px+3*d, py-4.5*d, d, 1);
        makeBrick(px+4*d, py-4.5*d, d, 1);
        makeBrick(px+1*d, py-5.5*d, d, 1);
        makeBrick(px+2*d, py-5.5*d, d, 1);
        makeBrick(px+4*d, py-5.5*d, d, 1);
        makeBrick(px+5*d, py-5.5*d, d, 1);
        
        double r = 5;
        // left
        //makeElevator2(-32-r, -7, 1, 5, r, 0, 6);
        //makeElevator2(-32-2*r, 7, 1, 5, r, 0, 6);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                makeElevator2(-37-2*r*j, -25 + 10*i, 1, 3, r, 0, 6);
                makeElevator2(-37-2*r*(j+1), -20 + 10*i, 1, 3, -r, 0, 6);
            }
        }
        
        //right
        //makeElevator2(32+r, -7, 1, 5, r, r, 6);
        //makeElevator2(32+1.5*r, 7, 1, 5, r, r, 6);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                makeElevator2(37+2*r*j, -25 + 10*i, 1, 3, r, r, 10);
                makeElevator2(37+2*r*(j+1), -20+10*i, 1, 3, -r, -r, 10);
            }
        }
        
        //top
        
        makeElevator2(-10, 32+r, 5, 1, 0, r, 6);
        makeElevator2(-7, 32+2*r, 5, 1, r, r, 6);
        makeElevator2(-22, 32+3*r, 5, 1, 0, r, 6);
        makeElevator2(-22+r, 32+4*r, 5, 1, r, 0, 6);
        makeElevator2(-22+3*r, 32+4.5*r, 5, 1, -r, 0, 6);
        makeElevator2(-22+5*r, 32+5*r, 5, 1, r, 0, 6);
        makeElevator2(-22+7*r, 32+5.5*r, 5, 1, -r, 0, 6);
        makeElevator2(-22+5*r, 32+6*r, 5, 1, r, 0, 6);
        makeElevator2(-22+3*r, 32+6.5*r, 5, 1, -r, 0, 6);
        makeElevator2(-22+2*r, 32+7.5*r, 5, 1, -r, r, 6);
        makeElevator2(-22+2*r, 32+9.5*r, 5, 1, r, -r, 6);
        makeElevator2(-22+2*r, 32+11.5*r, 5, 1, -r, r, 6);
        makeElevator2(-22+2*r, 32+13.5*r, 5, 1, r, -r, 6);
        makeElevator2(-22+2*r, 32+15.5*r, 5, 1, -r, r, 6);
        makeElevator2(-22+2*r, 32+17.5*r, 5, 1, r, -r, 6);
        makeElevator2(-22+3*r, 32+18.5*r, 5, 1, -r, -r, 6);
        makeBrick(-22+5*r, 32+18.5*r, 1, 1);
        makeBrick(-22+6*r, 32 + 18.5*r, 1, 1);
        makeBrick(-22+7*r, 32+18.5*r, 1, 1);
        makeBrick(-22+9*r, 32+18*r, 5, 1);
    }
    
    private void makeElevator(double x, double y, double w, double h, double rx, double ry, double s) {
        GreenfootImage im = new GreenfootImage("Rock.jpg");
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Elevator e = new Elevator((x+i)*GameObject.BD, (y+j)*GameObject.BD, GameObject.BD, GameObject.BD, im, rx*GameObject.BD, ry*GameObject.BD, s);
                addObject(e, 0, 0);
            }
        }
    }
    private void makeElevator2(double x, double y, double w, double h, double rx, double ry, double s) {
        GreenfootImage im = new GreenfootImage("Rock.jpg");
        Elevator e = new Elevator(x*GameObject.BD, y*GameObject.BD, GameObject.BD*w, GameObject.BD*h, im, rx*GameObject.BD, ry*GameObject.BD, s);
        addObject(e, 0, 0);
    }
    public Player getPlayer() { return player; }
    private void makePlayer() {
        GreenfootImage image = new GreenfootImage("white wizard.png");
        player = new Player(100, 100, 50, 50, image);
        addObject(player, (int)player.GetX(), (int)player.GetY());
    }
    private void makeTerrain() {
        makeBlock(0, 0, 20, 2);
        makeBlock(100, 250, 5, 1);
        makeBlock(500, 500, 10, 2);
        makeCircle(300, -500, 4);
    }
    private void makeBlock2(double x, double y, double w, double h) {
        GreenfootImage image = new GreenfootImage("Rock.jpg");
        Platform p = new Platform(x*GameObject.BD, y*GameObject.BD, GameObject.BD*w, GameObject.BD*h, image, "r");
        addObject(p, (int)p.GetX(), (int)p.GetY());
        ArrayList<Platform> temp = new ArrayList<Platform>();
        temp.add(p);
        groups.add(new GroupP(temp, x*GameObject.BD, y*GameObject.BD, w*GameObject.BD/2, h*GameObject.BD/2, "r"));
    }
    private void makeBlock(double x, double y, double w, double h) {
        GreenfootImage image = new GreenfootImage("Rock.jpg");
        ArrayList<Platform> temp = new ArrayList<Platform>();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Platform p = new Platform((x+i)*GameObject.BD, (y+j)*GameObject.BD, GameObject.BD, GameObject.BD, image, "r");
                addObject(p, (int)p.GetX(), (int)p.GetY());
                temp.add(p);
            }
        }
        groups.add(new GroupP(temp, x*GameObject.BD+GameObject.BD/2*(w-1), y*GameObject.BD+GameObject.BD/2*(h-1), w*GameObject.BD/2, h*GameObject.BD/2, "r"));
    }
    private void makeBrick(double x, double y, double w, double h) {
        GreenfootImage image = new GreenfootImage("Rock.jpg");
        Obstacle p = new Obstacle(x*GameObject.BD, y*GameObject.BD, GameObject.BD*w, GameObject.BD*h, image);
        addObject(p, 0, 0);
    }
    private void makeObstacle(double x, double y, double w, double h) {
        GreenfootImage image = new GreenfootImage("Rock.jpg");
        //ArrayList<Platform> temp = new ArrayList<Platform>();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Obstacle p = new Obstacle((x+i)*GameObject.BD, (y+j)*GameObject.BD, GameObject.BD, GameObject.BD, image);
                addObject(p, (int)p.GetX(), (int)p.GetY());
                //temp.add(p);
            }
        }
        //groups.add(new GroupP(temp, x*GameObject.BD+GameObject.BD/2*(w-1), y*GameObject.BD+GameObject.BD/2*(h-1), w*GameObject.BD/2, h*GameObject.BD/2, "r"));
    }
    private void makeCircle(double x, double y, double r) { // r is based off of BD
        GreenfootImage image = new GreenfootImage("Green Circle.png");
        Platform p = new Platform(x*GameObject.BD, y*GameObject.BD, GameObject.BD*r*2, GameObject.BD*r*2, image, "c");
        addObject(p, (int)p.GetX(), (int)p.GetY());
        ArrayList<Platform> temp = new ArrayList<Platform>(); temp.add(p);
        groups.add(new GroupP(temp, x*GameObject.BD, y*GameObject.BD, r*GameObject.BD, r*GameObject.BD, "c"));
    }
}

class GroupP {
    public ArrayList<Platform> plats;
    public double cx, cy, cw, ch;
    public String type;
    public GroupP(ArrayList<Platform> in, double x, double y, double w, double h, String t)
        { plats = in; cx = x; cy = y; cw = w; ch = h; type = t; }
    public String toString() { return String.format("(%.2f, %.2f, %.2f, %.2f, %s)", cx, cy, cw, ch, type); }
}