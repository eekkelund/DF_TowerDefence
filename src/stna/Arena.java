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
    private List<ModelGround> blocks = new ArrayList<ModelGround>();
    private List<ModelEnemy> enemies = new ArrayList<ModelEnemy>();
    private List<ModelTower> towers = new ArrayList<ModelTower>();
    private ModelGround ground;
    private ModelTower tower;
    private ModelEnemy enemy;
    private Map map;
    static int[][] grid;
    private TowerEngineController controller;

    public Arena() {
        map = new Map();
        grid = map.getMap();
        //System.out.print(grid.length+""+grid[0].length);
        objGrid = new ModelBlock[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                switch (grid[i][j]) {
                    case 0:
                        ground = new ModelGround(i, j, "grass");
                        objGrid[i][j] = ground;
                        blocks.add(ground);
                        break;
                    case 1:
                        ground = new ModelGround(i, j, "road");
                        objGrid[i][j] = ground;
                        blocks.add(ground);
                        break;
                    case 2:
                        ground = new ModelGround(i, j, "start");
                        objGrid[i][j] = ground;
                        blocks.add(ground);
                        break;
                    case 3:
                        ground = new ModelGround(i, j, "finnish");
                        objGrid[i][j] = ground;
                        blocks.add(ground);
                        break;

                }
            }
        }

    }

    public void setTower(int x, int y, String id) {
        tower = new ModelTower(x, y, id);
        objGrid[x][y] = tower;
        towers.add(tower);
    }

    public void setEnemy(int x, int y, String id) {
        enemy = new ModelEnemy(x, y, id);
        objGrid[x][y] = enemy;
        enemies.add(enemy);
    }

    public ModelBlock[][] getArena() {
        return objGrid;
    }

    public void spawnEnemy() {
        
        for (int x = 0; x < objGrid.length; x++) {
            for (int y = 0; y < objGrid[0].length; y++) {
                if (objGrid[x][y].getid().equals("start")) {
                    enemy = new ModelEnemy(x, y, "enemy");
                    //objGrid[x][y] = enemy;
                    enemies.add(enemy);

                }
            }
        }
    }
    
    public List<ModelEnemy> getEnemies(){
        
        return enemies;
    }
    public List<ModelTower> getTowers(){
        return towers;
    }
    
    public List<ModelGround> getBlocks(){
        return blocks;
    }
    //public getEnemy() {}
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
