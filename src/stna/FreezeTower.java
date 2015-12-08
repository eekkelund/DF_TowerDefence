/*
 * eetz1s License such cool so wow
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;

import static java.awt.Color.*;

/**
 *
 * @author eetz1
 */
public class FreezeTower extends ModelTower{
    
    

    public FreezeTower(int y, int x, String id) {
        super(y, x, id);
        init();
    }
    public void init() {

                img = "images/tower5.png";
                fireRate = 1000;
                damage = 0;
                price = 5;
                freezeDamage=0.5;
                range = 1;
                clr=CYAN;
                update_price = 15;
                max_level = 1;
        }
    
    
    }
