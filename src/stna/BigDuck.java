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

/**
 *
 * @author eetz1
 */
public class BigDuck  extends ModelEnemy{

    public BigDuck(int y, int x, int MOVEy, int MOVEx, String id) {
        super(y, x, MOVEy, MOVEx, id);
        init();
    }
    public void init() {

                img = "images/duck4.png";
                speed = 0.5;
                healt = 3000;
                damage = 30;
                prize = 20;
                size =0.5;
                
        
    }
}
