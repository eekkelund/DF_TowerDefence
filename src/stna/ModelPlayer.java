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
public class ModelPlayer {
    private int healt;
    private int money;
    private boolean alive =true;
    
    public ModelPlayer(){
        
        init();
    }
    public void init(){
    healt = 100;
    money = 10;
    }
     public int getHealt() {
        return healt;
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
