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

//LAZERRRTOWERRR
public class LazerTower extends ModelTower{

    public LazerTower(int y, int x, String id) {
        super(y, x, id);
        init();
    }
    public void init() {
                
                   
                        img = "images/tower.png";
                        fireRate = 2;
                        damage = 1;
                        range = 2;
                        clr = YELLOW;
                        update_price = 15;
                        price = 5;
                        max_level =2;
                        info = "<html>Shoots one<br />enemy in<br />its range.</html>";//MAKO KORJAA NÄÄ PLZ
        }
    @Override
    public void setLevel() {
        level = level+1;
        switch(level){
                    case 2:
                        img = "images/tower2.png";
                        fireRate = 2.5;
                        damage = 2;
                        range = 3;
                        clr=BLUE;
                        update_price =0;
                        break;
                }
        
    }
    }
