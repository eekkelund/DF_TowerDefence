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
public class RoundTower extends ModelTower {

    public RoundTower(int y, int x, String id) {
        super(y, x, id);
        init();
    }

    public void init() {

        img = "images/tower3.png";
        fireRate = 1.2;
        damage = 5;
        range = 2;
        clr = RED;
        update_price = 10;
        max_level =2;
        price = 10;
    }

    @Override
    public void setLevel() {
        level = level + 1;
        switch (level) {
            case 2:
                img = "images/tower4.png";
                fireRate = 2;
                damage = 5;
                range = 3;
                clr = ORANGE;
                update_price = 10;
                break;
        }

    }
}

