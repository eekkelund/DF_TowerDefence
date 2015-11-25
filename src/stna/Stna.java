/*
 * eetz1s License such cool so wow
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;

import java.awt.*;
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
public class Stna extends JFrame{
    private Arena arena = new Arena();
    private TowerEngineController contr = new TowerEngineController(arena); 
    private JLabel selite;
    private JButton kasvatusp, nollausp;
    public boolean first = false;
    public Stna () {
       
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Look and feel -asetus epäonnistui.");
        }

        alusta();
        arena.spawnEnemy();
       move();
       
    }

    public void alusta() {
        setTitle("Karta");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel sisältöpaneeli = new JPanel();
        selite = new JLabel();
        sisältöpaneeli.add(selite);
        
        setContentPane(sisältöpaneeli);

        
            
       
        
        setVisible(true);
    
        
        
    
    /*public void start() {
        new Thread(this).start();
    }
    
     
    public void run() {*/
        
       
    }
    public void move(){
        contr.movable();
        
    }
    public void paint(Graphics g) {
                
        ModelBlock[][] grid = arena.getArena();
        super.paint(g);

        if (!first){
            //first=true;
        for (int x=0;x<grid.length; x++){
            int w =x;
            w =w*32;
            for(int y=0; y<grid[0].length;y++){
                int h =y;
            h =h*32;
                if(grid[x][y].getid().equals("grass")){
                    g.setColor(Color.GREEN);
                    g.fillRect(h, w, 32, 32);
                }
                else if(grid[x][y].getid().equals("road")){
                    g.setColor(Color.GRAY);
                    g.fillRect(h, w, 32, 32);
            }
             else if(grid[x][y].getid().equals("start")){
                    g.setColor(Color.BLACK);
                    g.fillRect(h, w, 32, 32);
            }   
                else if(grid[x][y].getid().equals("finish")){
                    g.setColor(Color.PINK); 
                    g.fillRect(h, w, 32, 32);
            }
        }
    
    }
        }
        BufferedImage img; 
                    try {
                        img = ImageIO.read(new File("images/img.png"));
                        for(ModelEnemy enemy : arena.getEnemies()){
                            g.drawImage(img, enemy.getY()*32, enemy.getX()*32, this);
                        }
                    } catch (IOException ex) {
                        System.out.print(ex);
                    }
move();
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