/*
 * eetz1s License such cool so wow
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Stna extends JFrame implements ActionListener {

    private Arena arena = new Arena();
    private TowerEngineController contr = new TowerEngineController(arena);
    private JLabel selite;
    private JButton kasvatusp, nollausp;
    private ActionListener actionL;
    private JButton button;
    private JPanel panel;
    public boolean first = false;
    private int bsize;
    private int width = 700;
    private int height = 400;
    private Timer timer;
    private Graphics buffer;
    private Image dbImage;
    public static double fps = 60.0;

    public Stna() {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Look and feel -asetus epäonnistui.");
        }

        alusta();

        
        arena.setTower(5, 5, "tower");
        arena.setTower(4, 3, "tower");
        arena.setTower(2, 10, "tower");
        //arena.spawnEnemy();
        //start();
        game.run();

    }

    public void alusta() {
        setTitle("Karta");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = new JPanel();
        //selite = new JLabel();
        //actionL = new ButtonListener();
        button = new JButton("move");

        //setContentPane(panel);
        panel.add(button);
        //button.addActionListener(actionL);

        add(panel, BorderLayout.SOUTH);

        setVisible(true);
        bsize = arena.getBsize();
    }

    //  private class ButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        //if (e.getSource()==button) {
        move();

    }

    public void move() {
        for (int i = 0; i < arena.getEnemies().size(); i++) {
            ModelEnemy enemy = arena.getEnemies().get(i);
            contr.move(enemy);
            //repaint();
        }
    }
    public double spawnTime = 1 * (double) (fps), spawnFrame = spawnTime - fps;

    public void enemySpawner() {
        if (spawnFrame >= spawnTime) {
            arena.spawnEnemy();
            spawnFrame = 1;//-= spawnTime;
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
//                    if()
                    updates++;

                    enemySpawner();
                    
                    move();
                    delta--;

                }
                repaint();
                if (System.currentTimeMillis() - timer >= 1000) {
                    timer += 1000;
                    setTitle(" | ups: " + updates + " | fps: " + frames);
                    updates = 0;
                    frames = 0;
                }
            }
        }
    });

    /*public void start() {
        timer = new Timer(10, this);
        timer.start();
    }*/

    public void paint(Graphics g) {
        dbImage = createImage(width, height);
        buffer = dbImage.getGraphics();
        paintComponent(buffer);
        g.drawImage(dbImage, 0, 0, this);

    }

    public void paintComponent(Graphics g) {

        ModelBlock[][] grid = arena.getArena();
        super.paint(g);

        //if (!first){
        //first=true;
        for (int y = 0; y < grid.length; y++) {//DRAWS MAP
            int h = y;
            h = h * bsize;
            for (int x = 0; x < grid[0].length; x++) {
                int w = x;
                w = w * bsize;
                switch (grid[y][x].getid()) {
                    case "grass":
                        g.setColor(Color.GREEN);
                        g.fillRect(w, h, bsize, bsize);
                        break;
                    case "road":
                        g.setColor(Color.GRAY);
                        g.fillRect(w, h, bsize, bsize);
                        break;
                    case "start":
                        g.setColor(Color.BLACK);
                        g.fillRect(w, h, bsize, bsize);
                        break;
                    case "finish":
                        g.setColor(Color.PINK);
                        g.fillRect(w, h, bsize, bsize);
                        break;
                }
            }

        }
        //if (contr.shoot()) {//shooting??
            //for (ModelEnemy enemy : arena.getEnemies()) {
        for (ModelTower tower : arena.getTowers()) {
        try{
             //ModelEnemy enemy = (ModelEnemy) contr.shoot()[0];
             //ModelTower tower = (ModelTower) contr.shoot()[1];
            //ModelTower tower;
            ModelEnemy enemy;
             enemy=contr.shoot(tower);
             //ModelTower tower = en_to[0];
                //for (ModelTower tower : arena.getTowers()) {
                    g.setColor(new Color(255, 255, 0));
                    g.drawLine(tower.getX() * bsize + (bsize / 2), tower.getY() * bsize + (bsize / 2), enemy.getMoveX() + (bsize / 2), enemy.getMoveY() + (bsize / 2));
                //}
            //}
                //}
        }catch(Exception e){
            System.out.print(e);
        }}
            
        //}
        drawEnemy(g);
        drawTower(g);
    }

    public void drawEnemy(Graphics g) {

        BufferedImage img;
        try {//DRAWS ENEMYS

           for (int i = 0; i < arena.getEnemies().size(); i++) {
            ModelEnemy enemy = arena.getEnemies().get(i);
                img = ImageIO.read(new File(enemy.getImg()));
                g.drawImage(img, enemy.getMoveX(), enemy.getMoveY(), this);
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
                g.drawImage(img, tower.getX() * bsize, tower.getY() * bsize, this);
            }
        } catch (IOException ex) {
            System.out.print(ex);
        }
    }

    /*for (int x=0;x<grid.length; x++){
     int w =x;
     w =w*32;
     for(int y=0; y<grid[0].length;y++){
     int h =y;
     h =h*32;
     if(grid[x][y].getid().equals("start")){
     arena.setEnemy(x,y,"enemy");
     //g.setColor(Color.blue);
     //g.fillRect(h, w, 20, 20);
     BufferedImage img; 
     try {
     img = ImageIO.read(new File("images/img.png"));
     g.drawImage(img, h, w, this);
     } catch (IOException ex) {
     System.out.print(ex);
     }
                    
                            
     }
     }
     }*/
    public static void main(String args[]) {
        new Stna();

    }

}
