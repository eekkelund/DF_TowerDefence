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
    protected int level = 1;
    protected int update_price;
    protected int price;
    protected double freezeDamage;
    protected int fire_time = 100;
    protected int max_level;
     protected int boosted=2;
    protected String info;
    protected int boosts=0;

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
        level = level + 1;
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
        return price;
    }

    public int getUpPrice() {
        return update_price;
    }

    public double getFrz() {
        return freezeDamage;
    }

    public int getFireTime() {
        return fire_time;
    }

    public void setFireTime(int fire_time) {
        this.fire_time = fire_time;
    }

    public int getMaxLvl() {
        return max_level;
    }

    public int isBoosted() {
        return boosted;
    }
 
    public void setBoosted(int boosted) {
        this.boosted = boosted;
    }
   
    public void setBoost(int boost) {
        //If its max boosted -- do nothing
        if (boosted == 1) {
           
        }
        //If its not boosted at all -- boost once
        else if (boosted == 2) {
            boosts = boost;
            setDamage(getDamage() + boost);
            setRange(getRange() + boost);
        }
        //If its boosted once already -- remove earlier boosts and add new ones
        else if (boosted == 3) {
            setDamage(getDamage()- boosts + boost);
            setRange(getRange() - boosts + boost);
        }
        //If a boost tower is going to be sold removes boosts
        else if (boosted == 4) {
            setDamage(getDamage() - boost);
            setRange(getRange() - boost);
        }
 
    }
     public String getInfo() {
        return info;
    }
}
