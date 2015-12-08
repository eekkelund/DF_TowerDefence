/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;

import static java.awt.Color.*;
;

/**
 *
 * @author eetz1
 */
public class BoostTower extends ModelTower{
    private int improve;

    public BoostTower(int y, int x, String id) {
        super(y, x, id);
        init();
    }
    public void init() {
                
                   
                        img = "images/tower6.png";
                        fireRate = 1000000000;
                        damage = 0;
                        range = 3;
                        clr = YELLOW;
                        update_price = 15;
                        price = 20;
                        max_level =2;
                        improve = 2;
        }
    @Override
    public void setLevel() {
        level = level+1;
        switch(level){
                    case 2:
                        img = "images/tower2.png";
                        fireRate = 2.5;
                        damage = 5;
                        range = 4;
                        clr=BLUE;
                        update_price =15;
                        break;
                }
        
    }
    public int getImprove(){
        return improve;
    }
    }

    

