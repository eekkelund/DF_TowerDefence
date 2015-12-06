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
public class Stna extends JFrame {

    private Arena arena;
    private TowerEngineController contr;
    private JLabel towerinfo;
    private JButton tower, tower2, tower3, update;
    private JPanel panel, panel2;
    private ActionListener actionL;
    private int bsize;//BLOCK SIZE
    private int width = 720;//SCREEN WIDTH
    private int height = 480;//SCREEN HEIGHT
    private Graphics buffer;//THIS IS NEEDED FOR DOUBLE BUFFERING
    private Image dbImage;//THIS IMAGE STORES DOUBLEBUFFERED IMAGE, THAT SCREEN WONT FLICKER
    private static double fps = 60.0;//FRAMES PER SECOND
    private int pSec = 5;//PAUSETIME IN SEC, HOW LONG PAUSE IS BETWEEN WAVES
    private double spawnTime = 1 * (double) (fps);//HOW MANYTIMES PERSEC ENEMIES SPAWN
    private double spawnFrame = spawnTime - fps;//COUNTER TO CHECK UPPER ^
    private int spawnCounter = 0;//COUNTER TO CHECK IF ALL ENEMIES OF WAVE HAVE SPAWNED
    private boolean isFirst = true;//IF ITS FIRST TIME THAT THREAD IS RUN
    private boolean btnPress;
    private boolean sPause = true;//BOOLEAN TO CHECK IF THERE IS PAUSE BETWEEN WAVES
    private String towerid;//JUST TO STORE TOWERID
    private double pauseFrame = 1;//COUNTER FOR PAUSE BETWEEN WAVES
    private double pauseTime = pSec * (double) (fps);//TIME IN FPS BETWEEN WAVES
    private double shootTime = 0.5 * (int) fps;//TIME BETWEEN SHOOTING
    private double shootFrame = shootTime;//COUNTER FOR PAUSE BETWEEN SHOOTING
    private boolean startFrame = true;

    public Stna() {

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

        setTitle("Karta");
        setSize(width, height);
        actionL = new ButtonListener();
        panel = new JPanel();
        JButton b1;
        JLabel l1;
        setLayout(new BorderLayout());
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
        b1 = new JButton();
        try {
            img = ImageIO.read(new File("images/startb.png"));

        } catch (IOException ex) {
        }
        dimg = img.getScaledInstance(64 * 2, 32 * 2, Image.SCALE_SMOOTH);
        b1.setIcon(new ImageIcon(dimg));
        b1.setPreferredSize(new Dimension(64 * 2, 32 * 2));
        b1.setBorder(null);
        add(b1);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void alusta() {
        arena = new Arena();
        contr = new TowerEngineController(arena);
        setTitle("Karta");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        actionL = new ButtonListener();
        panel = new JPanel();
        
        panel2 = new JPanel();
        
        tower = new JButton("tower");
        tower2 = new JButton("tower2");
        tower3 = new JButton("tower3");
        
        update = new JButton("Update?");
        
        panel.add(tower);
        panel.add(tower2);
        panel.add(tower3);
        tower.addActionListener(actionL);
        tower2.addActionListener(actionL);
        tower3.addActionListener(actionL);
        addMouseListener(new MouseListener());
        
        towerinfo = new JLabel();
        
        panel2.add(towerinfo);

        add(panel2, BorderLayout.EAST);
        add(panel, BorderLayout.SOUTH);
        

        setVisible(true);
        bsize = arena.getBsize();

        game.run();
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
        }
    }

    private class MouseListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            if (btnPress) {
                arena.newTowerPos(e.getY(), e.getX(), towerid);
                btnPress=false;
            }
            else {
                 
                for(ModelTower tower : arena.getTowers()){
                    if(e.getX()/bsize==tower.getX() && e.getY()/bsize==tower.getY()){
                        
                        towerinfo.setText("damage: " + Integer.toString(tower.getDamage()));
                        panel2.add(update);
                        
                        tower.setLevel();
                        System.out.print(tower.getLevel());
                    }
                }
                    
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
//game happens here!
    Thread game = new Thread(new Runnable() {
        public void run() {
            long lastTime = System.nanoTime();
            long timer = System.currentTimeMillis();
            final double ns = 1000000000.0 / fps;
            double delta = 0;
            int updates = 0, frames = 0;

            while (true) {

                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;

                // Update 60 times a second
                while (delta >= 1) {

                    //update();
                    if (!arena.getPlayer().isAlive()) {//if player is ded game waits until...
                        try {
                            game.wait();
                            JLabel labell = new JLabel("Game over");
                            add(labell);

                        } catch (InterruptedException ex) {
                        }
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
                        if (pauseFrame >= pauseTime) {
                            arena.setLevel();
                            spawnCounter = 0;
                            pauseFrame = 1;
                            sPause = false;
                        } else {
                            pauseFrame++;
                            sPause = true;
                        }

                    }

                    updates++;

                    contr.moving();
                    delta--;

                }

                repaint();
                frames++;
                if (System.currentTimeMillis() - timer >= 1000) {
                    timer += 1000;
                    setTitle(" | ups: " + updates + " | fps: " + frames + "| Time: " + Math.round(Math.abs((pauseFrame / fps) - pSec)));
                    updates = 0;
                    frames = 0;
                }
            }
        }
    });

    //this is needed for doublebuffering it makes image of the game while paintComponent draws new. it happens so quickly that u cant see it.
    public void paint(Graphics g) {
        dbImage = createImage(width, height);
        buffer = dbImage.getGraphics();
        paintComponent(buffer);
        g.drawImage(dbImage, 0, 0, this);

    }

    public void paintComponent(Graphics g) {

        ModelBlock[][] grid = arena.getArena();
        super.paint(g);

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

        /*for (ModelTower tower : arena.getTowers()) {//For each tower dis is gonna check if there is enemy to shoot
         try {

         ModelEnemy enemy;//enemy what to shoot

         if (shootFrame >= shootTime) {//tower has to cool down aka load weapons

         if (shootFrame <= shootTime * tower.getfRate()) {//this is the time how long tower shoots enemy
         shootFrame++;
         enemy = contr.shoot(tower);

         } else {
         shootFrame = 1;
         enemy = null;
         }
         } else {
         shootFrame++;
         enemy = null;
         }

         g.setColor(tower.getClr());
         System.out.println(tower.getClr().getRGB());
         g.drawLine(tower.getX() * bsize + (bsize / 2), tower.getY() * bsize + (bsize / 2), enemy.getMoveX() + (bsize / 2), enemy.getMoveY() + (bsize / 2));

         } catch (Exception e) {}*/
        drawShoot(g);
        drawEnemy(g);
        drawTower(g);

        //}
    }
    //private double spawnTime = 1 * (double) (fps);//
    // private double spawnFrame = spawnTime - fps;

    public void drawShoot(Graphics g) {
        for (ModelTower tower : arena.getTowers()) {//For each tower dis is gonna check if there is enemy to shoot
            try {
                if (shootFrame >= shootTime) {//tower has to cool down aka load weapons

                    if (shootFrame <= shootTime * tower.getfRate()) {//this is the time how long tower shoots enemy
                        shootFrame++;

                        if ("tower3".equals(tower.getid())) {//for roundtoweer there  is different kind of shooting..
                            //for (ModelTower tower : arena.getTowers()) {
                            for (Iterator<ModelEnemy> iterator = arena.getEnemies().iterator(); iterator.hasNext();) {
                                ModelEnemy enemy = iterator.next();
                                if (contr.shoottest(tower, enemy)) {
                                    Color c = (tower.getClr());
                                    g.setColor(c);
                                    g.drawOval((tower.getX() * bsize - tower.getRange() * bsize + bsize / 2), (tower.getY() * bsize - tower.getRange() * bsize + bsize / 2), tower.getRange() * bsize * 2, tower.getRange() * bsize * 2);
                                }
                            }
                        } else {
                            int[] shootList = contr.shootable(tower);
                            Color c = new Color(shootList[0]);
                            g.setColor(c);
                            g.drawLine((shootList[1] * bsize + (bsize / 2)), (shootList[2] * bsize + (bsize / 2)), (shootList[3] + (bsize / 2)), (shootList[4] + (bsize / 2)));
                        }
                    } else {
                        shootFrame = 1;
                    }

                } else {
                    shootFrame++;
                }
            } catch (Exception e) {
            }
        }

    }

    public void drawEnemy(Graphics g) {

        BufferedImage img;
        try {//DRAWS ENEMYS

            for (int i = 0; i < arena.getEnemies().size(); i++) {
                ModelEnemy enemy = arena.getEnemies().get(i);
                img = ImageIO.read(new File(enemy.getImg()));
                g.drawImage(img, enemy.getMoveX(), enemy.getMoveY(), bsize, bsize, this);
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
