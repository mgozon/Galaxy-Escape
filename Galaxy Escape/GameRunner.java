import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GameRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameRunner extends Actor
{
    //Label title;
    GreenfootSound gs;
    //Button[] bs;
    //Label[] ls;
    //Button start;
    int state;
    double gt;
    Label gtl;
    int coins = 0;
    Label cl;
    
    public GameRunner(GreenfootImage image/*, Label l1, Button[] b, Label[] l, Button s*/) {
        setImage(image);
        state = 0;
        /*title = l1;
        bs = b;
        ls = l;
        start = s;*/
        playSound(1);
    }
    
    public void act() 
    {
        if (state==1) {
            gt += Time.deltaTime;
            int tt = 600-(int)gt;
            gtl.setText("Time: " + (tt/60) + ":" + String.format("%02d", tt%60));
            cl.setText("Coins: " + coins);
            if (tt==0) { 
                state = 2;
                Label ll = new Label("Game Over", 50, Color.RED);
                getWorld().addObject(ll, getWorld().getWidth()/2, getWorld().getHeight()/2);
                playSound(3);
            }
        }
    }
    
    public void playGame() {
        if (state!=0) return;
        ((ScrollingWorld)getWorld()).deleteUI();
        //prep world
        ((ScrollingWorld)getWorld()).build1();
        state = 1;
        gt = 0;
        gtl = new Label("Time: ", 15, Color.WHITE);
        getWorld().addObject(gtl, 550, 20);
        coins = 0;
        cl = new Label("Coins: ", 15, Color.WHITE);
        getWorld().addObject(cl, 50, 20);
        playSound(2);
    }
    
    public void playSound(int j) {
        if (gs != null) gs.stop();
        String s;
        if (j==1) s = "mainmenu.mp3";
        else if (j==2) s = "game.mp3";
        else if (j==3) s = "lose.mp3";
        else s = "win.mp3";
        gs = new GreenfootSound(s);
        gs.playLoop();
    }
    
    public void ping() {
        if (state != 1) return;
        if (coins == 3) {
            state = 3;
            Label wl = new Label("You Win!", 50, Color.CYAN);
            getWorld().addObject(wl, getWorld().getWidth()/2, getWorld().getHeight()/2);
            playSound(4);
        }
    }
}
