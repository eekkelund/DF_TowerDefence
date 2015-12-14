/*
 * eetz1s License such cool so wow
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;

//import java.awt.Color;
//import java.awt.Graphics;

/**
 *
 * @author eetz1
 */
public class ModelGround extends ModelBlock{
    private int x;
    private int y;
    private String id;
    private String img; 
    
    public ModelGround (int y, int x, String id) {
        this.x=x;
        this.y=y;
        this.id = id;
        init();
        
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public String getid() {
        return id;
    }
    public String getImg(){
        return img;
    }
    
    public void setid(String id) {
        this.id = id;
    }
    
    public void init(){
        switch(id){
            case "grass":
                img = "images/grass2.png";
                break;
            case "road":
                img = "images/water2.png";
                break;
            case "finish":
                img = "images/finish2.png";
                break;
            case "start":
                img = "images/start2.png";
                break;
        
    }
    
    /*public void paintComponent(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x, y, 32, 32);
    }*/
}
}

