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
public class Stna extends JFrame implements ActionListener{
    private Arena arena = new Arena();
    private TowerEngineController contr = new TowerEngineController(arena);
    private JLabel selite;
    private JButton kasvatusp, nollausp;
    private ActionListener actionL;
    private JButton button;
    private JPanel panel;
    public boolean first = false;
    private int bsize = 32;
    private Timer timer;
    public Stna () {
       
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Look and feel -asetus epäonnistui.");
        }
 
        alusta();
        arena.spawnEnemy();
        arena.setTower(5, 5, "tower");
        start();
       
       
    }
 
    public void alusta() {
        setTitle("Karta");
        setSize(800, 600);
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
    }
   
      //  private class ButtonListener implements ActionListener {
       
    public void actionPerformed(ActionEvent e) {
            //if (e.getSource()==button) {
                move();
            }
        
    
    public void move(){
        contr.move();
        repaint();
        
    }
    public void start(){
        timer = new Timer(10, this);
        timer.start();
    }
    
    
     public void paint(Graphics g) {
               
        ModelBlock[][] grid = arena.getArena();
        super.paint(g);
 
        if (!first){
            //first=true;
        for (int y=0;y<grid.length; y++){//DRAWS MAP
            int h =y;
            h =h*bsize;
            for(int x=0; x<grid[0].length;x++){
                int w =x;
            w =w*bsize;
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
        if (contr.shoot()){//shooting??
            for(ModelEnemy enemy : arena.getEnemies()){
                for(ModelTower tower : arena.getTowers()){
            g.setColor(new Color(255, 255, 0));
            g.drawLine(tower.getX()*bsize+(bsize/2), tower.getY()*bsize+(bsize/2), enemy.getX()*bsize+(bsize/2), enemy.getY()*bsize+(bsize/2));
        }
            }
        }
        }
        drawEnemy(g);
        drawTower(g);
    }
    public void drawEnemy(Graphics g) {
   
        BufferedImage img;
                    try {//DRAWS ENEMYS
                        img = ImageIO.read(new File("images/img.png"));
                        for(ModelEnemy enemy : arena.getEnemies()){
                            g.drawImage(img, enemy.getMoveX(), enemy.getMoveY(), this);
                        }
                    } catch (IOException ex) {
                        System.out.print(ex);
                    }
           
    }
    public void drawTower(Graphics g) {
        BufferedImage img;
                    try {img = ImageIO.read(new File("images/img2.png"));
                        for(ModelTower tower : arena.getTowers()){
                            g.drawImage(img, tower.getX()*32, tower.getY()*32, this);
                        }
                    }catch (IOException ex) {
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
   
    
    public static void main(String args[]){
        new Stna();
        
    }
}