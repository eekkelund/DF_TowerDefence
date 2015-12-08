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
public class ModelEnemy extends ModelBlock {

    protected int x, MOVEx;
    protected int y, MOVEy;
    protected int damage;
    protected String id;
    protected String img;
    protected int healt;
    protected boolean alive = true;
    protected int prize;
    protected double speed;
    protected int direction;
    protected int movecounter;
    protected boolean frozen = false;

    public ModelEnemy(int y, int x, int MOVEy, int MOVEx, String id) {
        this.x = x;
        this.y = y;
        this.MOVEx = MOVEx;
        this.MOVEy = MOVEy;
        this.id = id;
        direction = 3;
        
    }

    public int getX() {
        return x;
    }

    public int getMoveX() {
        return MOVEx;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setMoveX(int x) {
        this.MOVEx = x;
    }

    public int getY() {
        return y;
    }

    public int getMoveY() {
        return MOVEy;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMoveY(int y) {
        this.MOVEy = y;
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

    public double getSpeed() {
        return speed;
    }

    public int getPrize() {
        return prize;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getImg() {
        return img;
    }

    public int getHealt() {
        return healt;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setHealt(int damage) {
        healt = healt - damage;
        if (healt <= 0) {
            alive = false;
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getMCounter() {
        return movecounter;
    }

    public void setMCounter(int movecounter) {
        this.movecounter = movecounter;
    }
    
    public boolean isFrzn() {
        return frozen;
    }
    public void setFrzn(boolean frozen) {
        this.frozen = frozen;
    }
}
