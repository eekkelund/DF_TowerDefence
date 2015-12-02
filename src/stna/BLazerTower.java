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
public class BLazerTower extends ModelTower{

    public BLazerTower(int y, int x, String id) {
        super(y, x, id);
        init();
    }
    public void init() {

                img = "images/tower2.png";
                fireRate = 1000;
                damage = 2;
                range = 4;
                clr=BLUE;
        }
    }
