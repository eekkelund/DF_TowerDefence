/*
 * eetz1s License such cool so wow
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;

/**
 *
 * @author eetz1
 */
public class ModelTower extends ModelBlock {

    private int x;
    private int y;
    private int damage;
    private int range;
    private String img;
    private String id;
    private int speed;

    public ModelTower(int y, int x, String id) {
        this.x = x;
        this.y = y;
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

    public void init() {
        switch (id) {
            case "tower":
                img = "path/to/file";
                speed = 2;
                damage = 1;
                range = 3;

        }
    }

}
