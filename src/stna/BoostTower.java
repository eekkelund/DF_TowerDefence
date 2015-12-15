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
                        range = 2;
                        clr = YELLOW;
                        update_price = 30;
                        price = 20;
                        max_level =2;
                        improve = 1;
                        info = "<html>Boosts own<br />towers in<br />its range.</html>";//MAKO KORJAA NÄÄ PLZ
        }
    @Override
    public void setLevel() {
        level = level+1;
        switch(level){
                    case 2:
                        img = "images/tower7.png";
                        fireRate = 1000000000;
                        damage = 0;
                        range = 2;
                        clr=BLUE;
                        improve = 2;
                        update_price = 0;
                        break;
                }
        
    }
    public int getImprove(){
        return improve;
    }
    }

    

