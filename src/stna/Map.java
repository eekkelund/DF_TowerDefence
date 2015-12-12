/*
 * eetz1s License such cool so wow
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;

/**
 *
 * @author eetz1
 */
//HERE MAP IS CREATED!!
public class Map {

    private Arena arena;
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
