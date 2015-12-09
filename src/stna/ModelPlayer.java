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


//player aka YOU is created here
public class ModelPlayer {
    private int healt;
    private int money;
    private boolean alive =true;
    private int level;
    
    public ModelPlayer(){
        
        init();
    }
    public void init(){
    healt = 10000;
    money = 150;
    level =5;
    }
     public int getHealt() {
        return healt;
    }
     public int getLevel() {
        return level;
        
    } 
     public void setLevel() {
        level = level+1;
    }
    
     
    
    public boolean isAlive(){
        return alive;
    }
    
    public void setHealt(int damage) {
        healt = healt - damage;
        if (healt <= 0){
            alive=false;
        }
    }
    
    public int getMoney() {
        return money;
    }
    
    public void reduceMoney(int cost) {
        if (money>=cost){
        money = money-cost;
        }
    }
    public void addMoney(int prize) {
        money = money+prize;
    }
}
