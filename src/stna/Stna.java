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

    private Arena arena = new Arena();
    private TowerEngineController contr = new TowerEngineController(arena);
    private JLabel selite;
    private JButton tower, tower2;
    private JPanel panel;
    private ActionListener actionL;
    private int bsize;//BLOCK SIZE
    private int width = 700;//SCREEN WIDTH
    private int height = 400;//SCREEN HEIGHT
    private Graphics buffer;//THIS IS NEEDED FOR DOUBLE BUFFERING
    private Image dbImage;//THIS IMAGE STORES DOUBLEBUFFERED IMAGE, THAT SCREEN WONT FLICKER
    private static double fps = 60.0;//FRAMES PER SECOND
    private int pSec = 15;//PAUSETIME IN SEC, HOW LONG PAUSE IS BETWEEN WAVES
    private double spawnTime = 1 * (double) (fps);//
    private double spawnFrame = spawnTime - fps;
    private int spawnCounter = 0;
    private boolean isFirst = true;
    private boolean btnPress;
    private boolean sPause = false;
    private String towerid;
    private double pauseFrame = 1;
    private double pauseTime = pSec * (double) (fps);
    private double shootFrame = fps;
    private double shootTime = 1 * (int) fps / 3;

    public Stna() {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Look and feel -asetus epäonnistui.");
        }

        alusta();

        arena.setTower(4, 3, "tower2");
        arena.setTower(5, 5, "tower");
        arena.setTower(2, 10, "tower");

        game.run();

    }

    public void alusta() {
        setTitle("Karta");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        actionL = new ButtonListener();
        panel = new JPanel();

        tower = new JButton("tower");

        panel.add(tower);
        tower.addActionListener(actionL);
        addMouseListener(new MouseListener());

        add(panel, BorderLayout.SOUTH);

        setVisible(true);
        bsize = arena.getBsize();
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

        }
    }

    private class MouseListener extends MouseAdapter {

        public void mousePressed(MouseEvent e) {
            if (btnPress) {
                contr.newTowerPos(e.getY(), e.getX(), towerid);
            }
        }
    }

    public void move() {
        for (int i = 0; i < arena.getEnemies().size(); i++) {
            ModelEnemy enemy = arena.getEnemies().get(i);
            contr.move(enemy);
            //repaint();
        }
    }

    public void enemySpawner() {

        if (spawnFrame >= spawnTime && spawnCounter < arena.getSpawnWave() && !sPause) {

            arena.spawnEnemy();
            spawnFrame = 1;//-= spawnTime;
            spawnCounter++;
            isFirst = false;

        } else {
            spawnFrame++;
        }

    }

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
                    if (!sPause) {
                        enemySpawner();

                    }
                    if (arena.getEnemies().isEmpty() && !isFirst) {
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

                    move();
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

    public void drawShoot(Graphics g) {
        for (ModelTower tower : arena.getTowers()) {//For each tower dis is gonna check if there is enemy to shoot
            try {
                if (shootFrame >= shootTime) {//tower has to cool down aka load weapons

                    if (shootFrame <= shootTime * tower.getfRate()) {//this is the time how long tower shoots enemy
                        shootFrame++;
                        int[] shootList = contr.shootable(tower);
                        Color c = new Color(shootList[0]);
                        g.setColor(c);
                        g.drawLine((shootList[1] * bsize + (bsize / 2)), (shootList[2] * bsize + (bsize / 2)), (shootList[3] + (bsize / 2)), (shootList[4] + (bsize / 2)));

                    } else {
                        shootFrame = 1;
                    }
                } else {
                    shootFrame++;
                }
            } catch (Exception e) {}
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
