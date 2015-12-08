/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;

import static java.awt.Color.*;


/**
 *
 * @author eetz1
 */
public class MoneyTower extends ModelTower {

    public MoneyTower(int y, int x, String id) {
        super(y, x, id);
        init();
    }

    public void init() {
        
        img = "images/tower8.png";
        fireRate = 100000000;
        damage = 10;
        range = 0;
        clr = YELLOW;
        update_price = 100;
        max_level =2;
        price = 50;
    }

    @Override
    public void setLevel() {
        level = level + 1;
        switch (level) {
            case 2:
                img = "images/tower4.png";
                fireRate = 100000000;
                damage = 20;
                range = 0;
                clr = ORANGE;
                update_price = 10;
                break;
        }

    }
}
