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
                        fireRate = 10;
                        damage = 1;
                        range = 2;
                        clr = YELLOW;
                        price = 5;
        }
    @Override
    public void setLevel() {
        level = level+1;
        switch(level){
                    case 1:
                        img = "images/tower.png";
                        fireRate = 10;
                        damage = 1;
                        range = 2;
                        clr = YELLOW;
                        price = 5;
                        break;
                    case 2:
                        img = "images/tower2.png";
                        fireRate = 20;
                        damage = 5;
                        range = 4;
                        clr=BLUE;
                        price =15;
                        break;
                }
        
    }
    }
