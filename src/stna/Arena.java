/*
 * eetz1s License such cool so wow
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;

import java.io.BufferedReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author eetz1
 */
public class Arena {

    public static final int width = 480 / 32;
    public static final int height = 320 / 32;
    static private ModelBlock[][] objGrid;
    private ArrayList<ModelGround> blocks = new ArrayList();
    private ArrayList<ModelEnemy> enemies = new ArrayList();
    private ArrayList<ModelTower> towers = new ArrayList();
    ModelGround ground;
    private Map map;
    static int[][] grid;

    public Arena() {
        map = new Map();
        grid = map.getMap();
        //System.out.print(grid.length+""+grid[0].length);
        objGrid = new ModelBlock[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                switch (grid[i][j]) {
                    case 0:
                        /*ground = new ModelGround();
                        ground.setX(i);
                        ground.setY(j);
                        ground.setid("grass");*/
                        objGrid[i][j] = new ModelGround(i,j,"grass");
                        break;
                    case 1:
                        ground = new ModelGround();
                        ground.setX(i);
                        ground.setY(j);
                        ground.setid("road");
                        objGrid[i][j] = ground;
                        break;
                    case 2:
                        ground = new ModelGround(i,j,"finish");
                        /*ground.setX(i);
                        ground.setY(j);
                        ground.setid("finish");*/
                        objGrid[i][j] = ground;
                        System.out.print(objGrid[i][j].getid());
                        break;

                }
            }
        }

    }

    public static void setTower(int x, int y) {
        ModelTower tower = new ModelTower(x,y,"lazerT");
        objGrid[x][y] = tower;
        tower.setX(x);
        tower.setY(y);
        tower.setid("lazerT");
    }
    public static ModelBlock[][] getArena() {
        return objGrid;
    }
   /* public static void main(String args[]) {
        //Map map = new Map();
        Arena arena = new Arena();

        setTower(3,5);
         for (int x = 0; x < objGrid.length; x++) {
         System.out.println("");
         for (int y = 0; y < objGrid[0].length; y++) {
         System.out.print(objGrid[x][y].getid());
         }
         }
    }*/
}
