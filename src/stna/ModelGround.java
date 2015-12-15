/* 
 * Copyright (C) 2015 eetz1
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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

