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

/**
 *
 * @author eetz1
 */
//HERE MAP IS CREATED!!
public class Map {

    private MapController arena;
    private ModelPlayer player;
    private int rows = 12, columns = 16;
    int[][] map1
            = {//1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//1
                {2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//2
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//3
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},//4
                {0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0},//5
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0},//6
                {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0},//7
                {0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 3},//8
                {0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0},//9
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0},//10
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0},//11
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}//12

            };
    int[][] map2
            = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {2, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 3},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}

            };

    public Map(ModelPlayer player) {
        this.player = player;
    }

    public void setMap(int[][] map1, int[][] map2) {
        this.map1 = map1;
        this.map2 = map2;
    }

    public int[][] getMap() {

        switch (player.getLevel()) {
            case 1:
                return map1;
            case 2:
            //return map2;
        }
        return map1;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
