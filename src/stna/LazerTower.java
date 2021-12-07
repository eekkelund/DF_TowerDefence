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

/**
 *
 * @author eetz1
 */
//LAZERRRTOWERRR
public class LazerTower extends ModelTower {

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
    max_level = 2;
    info = "<html>Shoots one<br />enemy in<br />its range.</html>";// PLZ
  }

  @Override
  public void setLevel() {
    level = level + 1;
    switch (level) {
      case 2:
        img = "images/tower2.png";
        fireRate = 2.5;
        damage = 2;
        range = 3;
        clr = BLUE;
        update_price = 0;
        break;
      default:
        break;
    }

  }
}
