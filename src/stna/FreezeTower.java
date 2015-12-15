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
                update_price = 0;
                max_level = 1;
                info = "<html>Slows down<br />enemy in<br />its range.</html>";//MAKO KORJAA NÄÄ PLZ
        }
    
    
    }
