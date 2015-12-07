/*
 * eetz1s License such cool so wow
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;

import java.awt.Color;
import static java.awt.Color.*;

/**
 *
 * @author eetz1
 */
public class ModelTower extends ModelBlock {

    protected int x;
    protected int y;
    protected int damage;
    protected int range;
    protected String img;
    protected String id;
    protected double fireRate;
    protected Color clr;
    protected int level=1;
    protected int update_price;
    protected double freezeDamage;
    protected int fire_time;

    public ModelTower(int y, int x, String id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    public Color getClr() {
        return clr;
    }

    public void setLevel() {
        level = level+1;
    }
     public int getLevel() {
        return level;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public String getImg() {
        return img;
    }
    public double getfRate() {
        return fireRate;
    }
    public int getPrice() {
        return update_price;
    }
    public double getFrz(){
    return freezeDamage;
}
public int getFireTime() {
        return fire_time;
    }

    public void setFireTime(int fire_time) {
        this.fire_time = fire_time;
    }

  
    
    

}
