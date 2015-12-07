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

    static private ModelBlock[][] objGrid;
    private List<ModelGround> blocks = new ArrayList<ModelGround>();
    private List<ModelEnemy> enemies = new ArrayList<ModelEnemy>();
    private List<ModelTower> towers = new ArrayList<ModelTower>();
    private ModelGround ground;
    private ModelTower tower;
    private ModelEnemy enemy;
    private ModelPlayer player;
    private Map map;
    static int[][] grid;
    private TowerEngineController controller;
    private int bsize = 32, price = 5;
    private int spawn_wave;
    private int ecounter;

    public Arena() {
        player = new ModelPlayer();//lets create player

        map = new Map(player);//lets create new map = grid
        grid = map.getMap();//map=grid
        objGrid = new ModelBlock[grid.length][grid[0].length];//from that mapgrid lets make objectgrid where objects are groundobjects
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                switch (grid[i][j]) {
                    case 0://if map grid is 0 that means it is grass ground
                        ground = new ModelGround(i, j, "grass");
                        objGrid[i][j] = ground;
                        blocks.add(ground);
                        break;
                    case 1://if map grid is 1 that means it is road ground
                        ground = new ModelGround(i, j, "road");
                        objGrid[i][j] = ground;
                        blocks.add(ground);
                        break;
                    case 2://if map grid is 2 that means it is start ground
                        ground = new ModelGround(i, j, "start");
                        objGrid[i][j] = ground;
                        blocks.add(ground);
                        break;
                    case 3://if map grid is 3 that means it is finish ground
                        ground = new ModelGround(i, j, "finish");
                        objGrid[i][j] = ground;
                        blocks.add(ground);
                        break;

                }
            }
        }

    }

    //this sets towers to objectgrid

    public void setTower(int y, int x, String id) {
        switch (id) {
            case "tower"://if tower button is pressed it makes lazertower
                tower = new LazerTower(y, x, id);
                break;
            case "tower2"://if tower2 button is pressed it makes freezetower
                tower = new FreezeTower(y, x, id);
                break;
            case "tower3"://if tower3 button is pressed it makes roundtower
                tower = new RoundTower(y, x, id);
                break;
        }
        objGrid[y][x] = tower;
        towers.add(tower);
    }

    public void newTowerPos(int y, int x, String towerid) {

        x /= bsize;
        y /= bsize;
        if ("grass".equals(objGrid[y][x].getid())) {
            switch (towerid) {
                case "tower":
                    price = 5;
                    break;
                case "tower2":
                    price = 10;
                    break;
                case "tower3":
                    price = 10;
                    break;
            }
            if (player.getMoney() >= price) {
                setTower(y, x, towerid);
                player.reduceMoney(price);
            } else {
                System.out.print("no mani no hani");
            }
        } else {
            System.out.print("Wrong palace");
        }
    }

    //if u have moneys one can update towers

    public void updateTower(ModelTower tower) {
        if (player.getMoney() >= tower.getPrice()) {
            if (tower.getLevel() < tower.getMaxLvl()) {
                tower.setLevel();
            } else {
                System.out.print("already on maxlvl");
            }
        } else {
            System.out.print("no mani no upgrade");
        }
    }

    //returns objectgrid aka arena

    public ModelBlock[][] getArena() {
        return objGrid;
    }

    //this defenies how many enemies per round/level is spawned

    public int getSpawnWave() {
        switch (player.getLevel()) {
            case 1:
                spawn_wave = 5;

                break;

            case 2:
                spawn_wave = 15;
                break;
            case 3:
                spawn_wave = 15;
                break;
            default:
                spawn_wave = player.getLevel() * 10;
                break;

        }
        return spawn_wave;
    }

    public void spawnEnemy() {//SPAWNS ONE ENEMY DEPENDING ON LEVEL

        for (int y = 0; y < objGrid.length; y++) {
            for (int x = 0; x < objGrid[0].length; x++) {
                if (objGrid[y][x].getid().equals("start")) {
                    switch (player.getLevel()) {
                        case 1:
                            enemy = new ModelEnemy(y, x, y * bsize, x * bsize, "enemy3");
                            enemies.add(enemy);
                            break;
                        case 2:
                            enemy = new ModelEnemy(y, x, y * bsize, x * bsize, "enemy");
                            enemies.add(enemy);
                            break;
                        case 3:
                            if (ecounter < 5) {
                                enemy = new ModelEnemy(y, x, y * bsize, x * bsize, "enemy2");
                                enemies.add(enemy);
                                ecounter++;
                                break;
                            } else {
                                enemy = new ModelEnemy(y, x, y * bsize, x * bsize, "enemy");
                                enemies.add(enemy);
                                break;
                            }
                        default:
                            if (Math.random() >= 0.75) {
                                enemy = new ModelEnemy(y, x, y * bsize, x * bsize, "enemy2");
                                enemies.add(enemy);
                                break;
                            } else {
                                enemy = new ModelEnemy(y, x, y * bsize, x * bsize, "enemy");
                                enemies.add(enemy);
                                break;
                            }
                    }
                }
            }
        }
    }

    //SETTERS AND GETTERS
    public List<ModelEnemy> getEnemies() {

        return enemies;
    }

    public List<ModelTower> getTowers() {
        return towers;
    }

    public List<ModelGround> getBlocks() {
        return blocks;
    }

    public ModelPlayer getPlayer() {
        return player;
    }

    public int getBsize() {
        return bsize;
    }

    public int getLevel() {
        return player.getLevel();
    }

    public void setLevel() {
        player.setLevel();
    }

}
