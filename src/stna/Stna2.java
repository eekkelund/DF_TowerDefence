/*
 * eetz1s License such cool so wow
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 *
 * @author eetz1
 */
public class Stna2 extends JPanel implements Runnable {

    private Arena arena;
    private TowerEngineController contr;
    private JLabel towerinfo, towerinfo2;
    private JLabel tower, tower2, tower3, tower4, tower5, update;
    private JPanel storePanel, panel2;
    private ActionListener actionL;
    private int bsize;//BLOCK SIZE
    private int width = 720;//SCREEN WIDTH
    private int height = 480;//SCREEN HEIGHT
    private Graphics buffer;//THIS IS NEEDED FOR DOUBLE BUFFERING
    private Image dbImage;//THIS IMAGE STORES DOUBLEBUFFERED IMAGE, THAT SCREEN WONT FLICKER
    private static double fps = 60;//FRAMES PER SECOND
    private int pSec = 5;//PAUSETIME IN SEC, HOW LONG PAUSE IS BETWEEN WAVES
    private double spawnTime = 1 * (double) (fps);//HOW MANYTIMES PERSEC ENEMIES SPAWN
    private double spawnFrame = spawnTime - fps;//COUNTER TO CHECK UPPER ^
    private int spawnCounter = 0;//COUNTER TO CHECK IF ALL ENEMIES OF WAVE HAVE SPAWNED
    private boolean isFirst = true;//IF ITS FIRST TIME THAT THREAD IS RUN
    private boolean btnPress;//CHECKS IF BTN IS PRESSED
    private boolean towerclick;//CHECKS IF TOWER IS CLICKED
    private boolean sPause = true;//BOOLEAN TO CHECK IF THERE IS PAUSE BETWEEN WAVES
    private String towerid;//JUST TO STORE TOWERID
    private double pauseFrame = 1;//COUNTER FOR PAUSE BETWEEN WAVES
    private double pauseTime = pSec * (double) (fps);//TIME IN FPS BETWEEN WAVES
    private double shootTime = 0.5 * (double) fps;//TIME BETWEEN SHOOTING
    private double shootFrame = shootTime;//COUNTER FOR PAUSE BETWEEN SHOOTING
    private boolean running = true;
    private int mouseX, mouseY;
    private boolean shoot_money = false;//boolean to check if show moneys top of moneytower

    private boolean startFrame = true;
    ModelTower utower;

    JButton start;
    JLabel l1;

    Thread game = new Thread(this);

    JFrame frame;

    public Stna2() {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Look and feel -asetus epäonnistui.");
        }
        //if (startFrame) {
        //  alusta2();
        //}
        alusta();
        //arena.setTower(2, 3, "tower2");
        //arena.setTower(5, 3, "tower2");
        //arena.setTower(3, 2, "tower3");
        //arena.setTower(4, 2, "tower");

    }

    public void alusta2() {

        setSize(width, height);
        actionL = new ButtonListener();
        storePanel = new JPanel();

        //setLayout(new BorderLayout());
        BufferedImage img = null;

        try {
            img = ImageIO.read(new File("images/bg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        setContentPane(new JLabel(imageIcon));
        setLayout(new FlowLayout());
        start = new JButton();

        try {
            img = ImageIO.read(new File("images/startb.png"));

        } catch (IOException ex) {
        }

        start.addActionListener(actionL);

        dimg = img.getScaledInstance(64 * 2, 32 * 2, Image.SCALE_SMOOTH);
        start.setIcon(new ImageIcon(dimg));
        start.setPreferredSize(new Dimension(64 * 2, 32 * 2));
        start.setBorder(null);
        add(start);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void alusta() {
        
        BufferedImage img = null;
        Image dimg = null;
        
        this.setLayout(new BorderLayout());
        
        frame = new JFrame();
        arena = new Arena();
        contr = new TowerEngineController(arena);
        
        bsize = arena.getBsize();

        frame.setTitle("Karta");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        actionL = new ButtonListener();

        storePanel = new JPanel();
        storePanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        panel2 = new JPanel(new BorderLayout());

        tower = new JLabel();
        try {
            img = ImageIO.read(new File("images/tower.png"));

        } catch (IOException ex) {
        }
        dimg = img.getScaledInstance(bsize, bsize, Image.SCALE_SMOOTH);
        tower.setIcon(new ImageIcon(dimg));
        tower.setPreferredSize(new Dimension(bsize, bsize));
        tower.setBorder(null);
        tower.setName("tower");
        
        tower2 = new JLabel();
        
        try {
            img = ImageIO.read(new File("images/tower2.png"));

        } catch (IOException ex) {
        }
        dimg = img.getScaledInstance(bsize, bsize, Image.SCALE_SMOOTH);
        tower2.setIcon(new ImageIcon(dimg));
        tower2.setPreferredSize(new Dimension(bsize, bsize));
        tower2.setBorder(null);
        
        tower3 = new JLabel();
        
        try {
            img = ImageIO.read(new File("images/tower3.png"));

        } catch (IOException ex) {
        }
        dimg = img.getScaledInstance(bsize, bsize, Image.SCALE_SMOOTH);
        tower3.setIcon(new ImageIcon(dimg));
        tower3.setPreferredSize(new Dimension(bsize, bsize));
        tower3.setBorder(null);
        
        tower4 = new JLabel();
        
        try {
            img = ImageIO.read(new File("images/tower4.png"));

        } catch (IOException ex) {
        }
        dimg = img.getScaledInstance(bsize, bsize, Image.SCALE_SMOOTH);
        tower4.setIcon(new ImageIcon(dimg));
        tower4.setPreferredSize(new Dimension(bsize, bsize));
        tower4.setBorder(null);
        
        tower5 = new JLabel();
        
        try {
            img = ImageIO.read(new File("images/tower5.png"));

        } catch (IOException ex) {
        }
        dimg = img.getScaledInstance(bsize, bsize, Image.SCALE_SMOOTH);
        tower5.setIcon(new ImageIcon(dimg));
        tower5.setPreferredSize(new Dimension(bsize, bsize));
        tower5.setBorder(null);

        update = new JLabel("Upgrade");


        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridx = 0;
        c.gridy = 0;
        storePanel.add(tower, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridx = 1;
        c.gridy = 0;
        storePanel.add(tower2, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridx = 2;
        c.gridy = 0;
        storePanel.add(tower3, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0 ;
        c.gridx = 3;
        c.gridy = 0;
        storePanel.add(tower4, c);

        c.anchor = GridBagConstraints.PAGE_END;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.0;
        c.gridx = 4;
        c.gridy = 0;
        storePanel.add(tower5, c);

        update.addMouseListener(new MouseListener());

        tower.addMouseListener(new MouseListener());
        tower.addMouseMotionListener(new MouseListener());
        tower2.addMouseListener(new MouseListener());
                tower2.addMouseMotionListener(new MouseListener());

        tower3.addMouseListener(new MouseListener());
                tower3.addMouseMotionListener(new MouseListener());

        tower4.addMouseListener(new MouseListener());
                tower4.addMouseMotionListener(new MouseListener());

        tower5.addMouseListener(new MouseListener());
                tower.addMouseMotionListener(new MouseListener());


        this.addMouseListener(new MouseListener());
        this.addMouseMotionListener(new MouseListener());

        towerinfo = new JLabel();
        towerinfo2 = new JLabel();

        panel2.add(towerinfo, BorderLayout.EAST);
        panel2.add(towerinfo2, BorderLayout.EAST);

        this.add(storePanel, BorderLayout.SOUTH);
        panel2.add(this, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.EAST);
        

        frame.setVisible(true);
        

        game.start();

    }
    
    
    public Dimension getPreferredSize() {
        return new Dimension(bsize * 14, bsize * 10);
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == tower) {
                btnPress = true;
                towerid = "tower";
            }
            if (e.getSource() == tower2) {
                btnPress = true;
                towerid = "tower2";
            }
            if (e.getSource() == tower3) {
                btnPress = true;
                towerid = "tower3";
            }
            if (e.getSource() == tower4) {
                btnPress = true;
                towerid = "tower4";
            }
            if (e.getSource() == tower5) {
                btnPress = true;
                towerid = "tower5";
            }

            if (e.getSource() == update) {
                int[] upgrade = new int[2];//upgrade[0]=DMG, upgrade[1]=RANGE
                upgrade = arena.upgradeTower(utower);
                towerinfo.setText("damage: " + Integer.toString(upgrade[0]));
                towerinfo2.setText("range: " + Integer.toString(upgrade[1]));
            }
            if (e.getSource() == start) {
                alusta();
            }
        }
    }

    private class MouseListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            
            
            
            
            
         JLabel l = (JLabel) e.getSource();

         if(l.getName().equals("tower")){
             btnPress = true;
                towerid = "tower";
         }else if(l.getName().equals("tower2")){
             btnPress = true;
                towerid = "tower2";
         }
            
            if (btnPress && e.getButton() == 1) {
                arena.newTowerPos(e.getY(), e.getX(), towerid);
                btnPress = false;
                towerclick = false;
            } else {
                for (ModelTower tower : arena.getTowers()) {
                    if (e.getX() / bsize == tower.getX() && e.getY() / bsize == tower.getY()) {
                        utower = tower;
                        towerinfo.setText("damage: " + Integer.toString(tower.getDamage()));
                        towerinfo2.setText("range: " + Integer.toString(tower.getRange()));
                        panel2.add(update, BorderLayout.EAST);
                        towerclick = true;
                    }
                }
            }
            //Allows you to stop placing tower with right mouseclick
            if (e.getButton() == 3 && btnPress) {
                btnPress = false;

            }
            //Clears the tower info
            if (towerclick && (e.getButton() == 1 || e.getButton() == 3) && "grass".equals(arena.getArena()[e.getY() / bsize][e.getX() / bsize].getid())) {
                towerclick = false;
                towerinfo.setText("");
                towerinfo2.setText("");
                panel2.remove(update);
            }
        }

        public void mouseMoved(MouseEvent e) {
            if (btnPress) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        }
    }

    //this method spawns enemies
    public void enemySpawner() {
        //spawns enemy every 1sec(after 60frames) and if spawncounter is not too much. also if spawnpause is true spawning is not happening
        if (spawnFrame >= spawnTime && spawnCounter < arena.getSpawnWave() && !sPause) {

            arena.spawnEnemy();
            spawnFrame = 1;//-= spawnTime;
            spawnCounter++;
            isFirst = false;

        } else {
            spawnFrame++;
        }

    }

    public void terminate() {
        running = false;
    }

    //game happens here!
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / fps;
        double delta = 0;
        int updates = 0, frames = 0;

        while (running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            // Update 60 times a second
            while (delta >= 1) {

                //update();
                if (!arena.getPlayer().isAlive()) {//if player is ded game terminates

                    JLabel labell = new JLabel("Game over");
                    panel2.add(labell);
                    terminate();

                } else if (!sPause) {//if game is not paused aka cooldown between waves, this is true
                    enemySpawner();

                } else if (sPause && isFirst) {//in the beginning of the game wait pSec
                    if (pauseFrame >= pauseTime) {
                        pauseFrame = 1;
                        sPause = false;
                    } else {
                        pauseFrame++;
                        sPause = true;

                    }
                }
                //if there is no enemies on the arena and enemyspawner has spawned all the enemies and its not first run, there will be spawnpause and new level
                if (arena.getEnemies().isEmpty() && !isFirst && spawnCounter == arena.getSpawnWave()) {
                    if (pauseFrame == 1) {
                        contr.shootmoney();//when round is over if player has moneytower it makes moneyyy
                        shoot_money = true;

                    }
                    if (pauseFrame >= pauseTime) {
                        arena.setLevel();
                        spawnCounter = 0;
                        pauseFrame = 1;
                        sPause = false;
                        shoot_money = false;
                    } else {
                        pauseFrame++;
                        sPause = true;
                    }

                }

                updates++;
                repaint();
                contr.moving();
                delta--;

            }

            frames++;
            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                frame.setTitle(" | ups: " + updates + " | fps: " + frames + "| Time: " + Math.round(Math.abs((pauseFrame / fps) - pSec)));
                updates = 0;
                frames = 0;
            }
        }
    }

    //this is needed for doublebuffering it makes image of the game while paintComponent draws new. it happens so quickly that u cant see it.
    public void paint(Graphics g) {
        dbImage = createImage(width, height);
        buffer = dbImage.getGraphics();
        paintComponent(buffer);
        g.drawImage(dbImage, 0, 0, this);

    }

    public void paintComponent(Graphics g) {

        ModelBlock[][] grid = arena.getArena();
//        super.paint(g);

        BufferedImage img;
        for (int y = 0; y < grid.length; y++) {//DRAWS MAP
            int h = y;
            h = h * bsize;
            for (int x = 0; x < grid[0].length; x++) {
                try {
                    int w = x;
                    w = w * bsize;
                    img = ImageIO.read(new File(grid[y][x].getImg()));
                    g.drawImage(img, w, h, bsize, bsize, this);

                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }

        }

        drawShoot(g);
        drawTower(g);
        drawEnemy(g);
        drawMoneyShoot(g);
        drawHover(g);

    }

    public void drawMoneyShoot(Graphics g) {
        for (ModelTower tower : arena.getTowers()) {//For each tower dis is gonna check if there is enemy to shoot

            if ("tower5".equals(tower.getid()) && shoot_money) {
                Color c = (tower.getClr());
                int fontSize = bsize / 2;
                g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
                g.setColor(c);
                g.drawString(Integer.toString(tower.damage), tower.getX() * bsize + bsize / 4, tower.getY() * bsize + bsize / 4);
            }
        }
    }

    public void drawShoot(Graphics g) {
        for (ModelTower tower : arena.getTowers()) {//For each tower dis is gonna check if there is enemy to shoot
            try {
                if (tower.getFireTime() >= shootTime) {//tower has to cool down aka load weapons

                    if (tower.getFireTime() <= shootTime * tower.getfRate()) {//this is the time how long tower shoots enemy
                        tower.setFireTime(tower.getFireTime() + 1);//each tower has it own firetime

                        if ("tower3".equals(tower.getid())) {//for roundtoweer there  is different kind of shooting..
                            //for (ModelTower tower : arena.getTowers()) {
                            for (Iterator<ModelEnemy> iterator = arena.getEnemies().iterator(); iterator.hasNext();) {//list
                                ModelEnemy enemy = iterator.next();
                                if (contr.shootround(tower, enemy)) {//drawing shootlines
                                    Color c = (tower.getClr());
                                    g.setColor(c);
                                    g.drawOval((tower.getX() * bsize - tower.getRange() * bsize + bsize / 2), (tower.getY() * bsize - tower.getRange() * bsize + bsize / 2), tower.getRange() * bsize * 2, tower.getRange() * bsize * 2);
                                }
                            }
                        } else if ("tower2".equals(tower.getid())) {//for freezetower there is different kind of shooting..
                            int[] shootList = contr.shootable(tower);//drawing shootlines
                            Color c = new Color(shootList[0]);
                            g.setColor(c);
                            g.drawLine((shootList[1] * bsize + (bsize / 2)), (shootList[2] * bsize + (bsize / 2)), (shootList[3] + (bsize / 3)), (shootList[4] + (bsize / 3)));
                            g.drawLine((shootList[1] * bsize + (bsize / 2)), (shootList[2] * bsize + (bsize / 2)), (shootList[3] + (bsize / 2)), (shootList[4] + (bsize / 2)));
                        } else if ("tower4".equals(tower.getid())) {//boosttower "shoots" other towers = boosts them
                            for (ModelTower tower2 : arena.getTowers()) {
                                contr.shootImprove((BoostTower) tower, tower2);
                            }

                        } else if ("tower".equals(tower.getid())) {
                            int[] shootList = contr.shootable(tower);//drawing shootlines for lazertowers
                            Color c = new Color(shootList[0]);
                            g.setColor(c);
                            g.drawLine((shootList[1] * bsize + (bsize / 2)), (shootList[2] * bsize + (bsize / 2)), (shootList[3] + (bsize / 2)), (shootList[4] + (bsize / 2)));
                        }
                    } else {
                        tower.setFireTime(1);
                    }

                } else {
                    tower.setFireTime(tower.getFireTime() + 1);
                }
            } catch (Exception e) {
            }
        }

    }

    //Draws the rectangle where you are going to place the tower
    public void drawHover(Graphics g) {
        if (btnPress) {
            int[] coords = arena.hover(mouseX, mouseY, towerid);
            //coords[0]=X, coords[1]=Y, coords[2]=COLOR, coords[3]=RANGE
            g.setColor(Color.BLACK);
            //Sets the color as red if you are hovering on wrong square
            if (coords[2] == 1) {
                g.setColor(Color.RED);
            }
            g.drawRect(coords[0] - bsize, coords[1] - bsize, bsize, bsize);
            //Draws the range of the tower
            g.setColor(Color.white);
            g.drawOval(coords[0] - coords[3] * bsize - bsize / 2, coords[1] - coords[3] * bsize - bsize / 2, coords[3] * bsize * 2, coords[3] * bsize * 2);

        }
        //Draws a rectangle around a selected tower
        if (towerclick) {
            g.setColor(Color.BLACK);
            g.drawRect(utower.getX() * bsize, utower.getY() * bsize, bsize, bsize);
            g.setColor(Color.WHITE);
            g.drawOval(utower.getX() * bsize - utower.getRange() * bsize + bsize / 2, utower.getY() * bsize - utower.getRange() * bsize + bsize / 2, utower.getRange() * bsize * 2, utower.getRange() * bsize * 2);
        }
    }

    public void drawEnemy(Graphics g) {

        BufferedImage img;
        try {//DRAWS ENEMIES

            for (int i = 0; i < arena.getEnemies().size(); i++) {
                ModelEnemy enemy = arena.getEnemies().get(i);
                img = ImageIO.read(new File(enemy.getImg()));
                g.drawImage(img, enemy.getMoveX(), enemy.getMoveY(), bsize + (int) (enemy.getSize() * bsize), bsize + (int) (enemy.getSize() * bsize), this);
            }
        } catch (IOException ex) {
            System.out.print(ex);
        }

    }

    public void drawTower(Graphics g) {
        BufferedImage img;
        try {
            for (ModelTower tower : arena.getTowers()) {
                img = ImageIO.read(new File(tower.getImg()));
                g.drawImage(img, tower.getX() * bsize, tower.getY() * bsize, bsize, bsize, this);
            }
        } catch (IOException ex) {
            System.out.print(ex);
        }
    }

    public static void main(String args[]) {
        new Stna();

    }

}
