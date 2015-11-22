/*
 * eetz1s License such cool so wow
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;

import java.awt.*;
import java.awt.event.ActionListener;
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
    private Arena arena;
    private JLabel selite;
    private JButton kasvatusp, nollausp;

    public Stna () {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Look and feel -asetus epäonnistui.");
        }

        alusta();
    }

    public void alusta() {
        setTitle("Karta");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel sisältöpaneeli = new JPanel();
        selite = new JLabel();
        sisältöpaneeli.add(selite);
        
        setContentPane(sisältöpaneeli);

        
            
       
        pack();
        setVisible(true);
    
        
        
    
    /*public void start() {
        new Thread(this).start();
    }
    
     
    public void run() {*/
        
       
    }
    public void paint(Graphics g) {
                arena = new Arena();
        ModelBlock[][] grid = arena.getArena();
        super.paint(g);
        //int w =32;
        //int h=32;
        
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
                
                
        }
    
    }
    
}
    public static void main(String args[]){
        new Stna();
    }
}
