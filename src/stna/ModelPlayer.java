/* 
 * Copyright (C) 2015 eetz1 <eetukah@metropolia.fi>
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
    healt = 100;
    money = 15;
    level =1;
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
