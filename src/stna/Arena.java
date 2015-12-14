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
    private List<ModelGround> blocks = new ArrayList();
    private List<ModelEnemy> enemies = new ArrayList();
    private List<ModelTower> towers = new ArrayList();
    private List<ModelTower> ImaginaryTowers = new ArrayList();
    private ModelGround ground;
    private ModelTower tower;
    private ModelEnemy enemy;
    private ModelPlayer player;
    private Map map;
    static int[][] grid;
    private TowerEngineController controller;
    private int bsize = 32, price;
    private int spawn_wave;
    private int ecounter;

    public Arena() {
        player = new ModelPlayer();//lets create player

        map = new Map(player);//lets create new map = grid

        //Adding imaginary towers for easier price and range handling for some methods
        ImaginaryTowers.add(new LazerTower(1, 1, "tower"));
        ImaginaryTowers.add(new FreezeTower(1, 1, "tower2"));
        ImaginaryTowers.add(new RoundTower(1, 1, "tower3"));
        ImaginaryTowers.add(new BoostTower(1, 1, "tower4"));
        ImaginaryTowers.add(new MoneyTower(1, 1, "tower5"));

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
            case "tower4"://if tower4 button is pressed it makes boosttower
                tower = new BoostTower(y, x, id);
                break;
            case "tower5"://if tower5 button is pressed it makes moneytower
                tower = new MoneyTower(y, x, id);
                break;
        }
        objGrid[y][x] = tower;
        towers.add(tower);
    }

    public void newTowerPos(int y, int x, String towerid) {
        x /= bsize;
        y /= bsize;
        if ("grass".equals(objGrid[y][x].getid())) {
            for (ModelTower tower : ImaginaryTowers) {
                if (towerid == tower.getid()) {
                    price = tower.getPrice();
                }
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

    

    public int[] hover(int x, int y, String towerid) {
        int xcoord = 0, ycoord = 0;
        int[] coords = new int[4]; //coords[0]=X, coords[1]=Y, coords[2]=COLOR, coords[3]=RANGE
        for (int i = 0; i <= getArena().length; i++) {
            ycoord = bsize * i;
            for (int j = 0; j <= getArena()[0].length; j++) {
                xcoord = bsize * j;

                if (x <= xcoord && x >= xcoord - bsize) {
                    coords[0] = xcoord;
                }
                if (y <= ycoord && y >= ycoord - bsize) {
                    coords[1] = ycoord;
                }
            }
        }
        for (ModelTower tower : getImTowers()) {
            if (tower.getid().equals(towerid)) {
                coords[3] = tower.getRange();
            }
        }
        if (y / bsize < getArena().length && x / bsize < getArena()[0].length) {
            if (!"grass".equals(objGrid[(y / bsize)][(x / bsize)].getid())) {
                coords[2] = 1;
            }
        }
        return coords;
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
                spawn_wave = 10;
                break;
            case 3:
                spawn_wave = 15;
                break;
            case 4:
                spawn_wave = 10;
                break;
            case 5:
                spawn_wave = 1;
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
                            enemy = new YDuck(y, x, y * bsize, x * bsize, "enemy");
                            enemies.add(enemy);
                            break;
                        case 2:
                            enemy = new YDuck(y, x, y * bsize, x * bsize, "enemy");
                            enemies.add(enemy);
                            break;
                        case 3:
                            if (ecounter < 5) {
                                enemy = new PDuck(y, x, y * bsize, x * bsize, "enemy2");
                                enemies.add(enemy);
                                ecounter++;
                                break;
                            } else {
                                enemy = new YDuck(y, x, y * bsize, x * bsize, "enemy");
                                enemies.add(enemy);
                                break;
                            }
                        case 4:
                            if (ecounter < 5) {
                                enemy = new YDuck(y, x, y * bsize, x * bsize, "enemy2");
                                enemies.add(enemy);
                                ecounter++;
                                break;
                            } else {
                                enemy = new BDuck(y, x, y * bsize, x * bsize, "enemy3");
                                enemies.add(enemy);
                                break;
                            }

                        case 5:
                            enemy = new BigDuck(y, x, y * bsize, x * bsize, "enemy4");
                            enemies.add(enemy);
                            break;
                        default:
                            if (Math.random() >= 0.75) {
                                enemy = new PDuck(y, x, y * bsize, x * bsize, "enemy2");
                                enemies.add(enemy);
                                break;
                            } else {
                                enemy = new YDuck(y, x, y * bsize, x * bsize, "enemy");
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

    //returns list of towers
    public List<ModelTower> getTowers() {
        return towers;
    }

    //get imaginary towers bitch = all different towers
    public List<ModelTower> getImTowers() {
        return ImaginaryTowers;
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

    public int getRows() {
        return map.getRows();
    }

    public int getColumns() {
        return map.getColumns();
    }

}
