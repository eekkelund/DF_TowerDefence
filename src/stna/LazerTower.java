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
public class LazerTower extends ModelTower{

    public LazerTower(int y, int x, String id) {
        super(y, x, id);
        init();
    }
    public void init() {

                img = "images/tower.png";
                fireRate = 2;
                damage = 1;
                range = 3;
                clr = YELLOW;
        }
    }
