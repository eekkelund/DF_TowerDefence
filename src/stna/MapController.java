/*
 * eetz1s License such cool so wow
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eetz1
 */
public class MapController {

    static private ModelBlock[][] objGrid;//this is grid where modelblocks are stored aka visible map
    private List<ModelGround> blocks = new ArrayList();//all the groundblocks
    private List<ModelEnemy> enemies = new ArrayList();//all the enemies
    private List<ModelTower> towers = new ArrayList();//all the towers
    private List<ModelTower> ImaginaryTowers = new ArrayList();//all different towers that are made just for that one can buy them from shop
    private ModelGround ground;//modelground d44
    private ModelTower tower;//tower..
    private ModelEnemy enemy;//enemyyyy
    private ModelPlayer player;//and player
    private Map map;//also map
    private static int[][] grid; //map(numbers) are stored here
    private int bsize = 32;//blocksize EDIT HERE IF WANT TO EDIT
    private int price;//tower price stored here
    private int spawn_wave;//depends on players level
    private int ecounter;//counter to check how many enemies spawned so far on that level
    private String[] newTower = new String[2];//just to store and return to view "no money"&"wrong place"

    public MapController() {
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
                if (towerid.equals(tower.getid())) {
                    price = tower.getPrice();
                    newTower[0] = "";
                    newTower[1] = "";
                }
            }
            if (player.getMoney() >= price) {
                setTower(y, x, towerid);
                player.reduceMoney(price);
                newTower[0] = "";
                newTower[1] = "";
            } else {
                newTower[0] = "Not enough";
                newTower[1] = "money";
            }
        } else {
            newTower[0] = "Wrong";
            newTower[1] = "place";
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
            case 6:
                spawn_wave = 20;
                break;
            case 7:
                spawn_wave = 30;
                break;
            case 8:
                spawn_wave = 23;
                break;
            case 9:
                spawn_wave = 17;
                break;
            case 10:
                spawn_wave = 7;
                break;
            default:
                spawn_wave = player.getLevel() * 5;
                break;

        }
        return spawn_wave;
    }

    public void spawnEnemy() {//SPAWNS ONE ENEMY DEPENDING ON LEVEL

        for (int y = 0; y < objGrid.length; y++) {
            for (int x = 0; x < objGrid[0].length; x++) {
                if (objGrid[y][x].getid().equals("start")) {
                    switch (player.getLevel()) {
                        case 1://if level is 1 spawn only normal yducks
                            enemy = new YDuck(y, x, y * bsize, x * bsize, "enemy");
                            enemies.add(enemy);
                            break;
                        case 2://if level is 2 spawn only normal yducks
                            enemy = new YDuck(y, x, y * bsize, x * bsize, "enemy");
                            enemies.add(enemy);
                            break;
                        case 3://if level is 3 spawn five fast pink ducks and rest normal yducks
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
                        case 4://level 4, spawn first pink ducks then rest black ducks
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

                        case 5://level 5, spawn one big duck
                            enemy = new BigDuck(y, x, y * bsize, x * bsize, "enemy4");
                            enemies.add(enemy);
                            break;
                        case 6://level 6, spawn first black ducks then yellow ducks then couple pink
                            if (ecounter < 5) {
                                enemy = new BDuck(y, x, y * bsize, x * bsize, "enemy3");
                                enemies.add(enemy);
                                ecounter++;
                                break;
                            } else if (ecounter < 17) {
                                enemy = new YDuck(y, x, y * bsize, x * bsize, "enemy");
                                enemies.add(enemy);
                                ecounter++;
                                break;
                            } else {
                                enemy = new PDuck(y, x, y * bsize, x * bsize, "enemy2");
                                enemies.add(enemy);
                                break;
                            }
                        case 7://if level is 7 spawn only normal yducks
                            enemy = new YDuck(y, x, y * bsize, x * bsize, "enemy");
                            enemies.add(enemy);
                            break;
                        case 8://level 8, spawn first yellow ducks then black ducks then pink
                            if (ecounter < 5) {
                                enemy = new YDuck(y, x, y * bsize, x * bsize, "enemy");
                                enemies.add(enemy);
                                ecounter++;
                                break;
                            } else if (ecounter < 15) {
                                enemy = new BDuck(y, x, y * bsize, x * bsize, "enemy3");
                                enemies.add(enemy);
                                ecounter++;
                                break;
                            } else {
                                enemy = new PDuck(y, x, y * bsize, x * bsize, "enemy2");
                                enemies.add(enemy);
                                break;
                            }
                        case 9://if level is 9 spawn pink ducks
                            enemy = new PDuck(y, x, y * bsize, x * bsize, "enemy2");
                            enemies.add(enemy);
                            break;
                        case 10://level 10, spawn big ducks
                            enemy = new BigDuck(y, x, y * bsize, x * bsize, "enemy4");
                            enemies.add(enemy);
                            break;
                        default://this math spawns rest of the levels just randomly ducks
                            double rand = Math.random();
                            if (rand <= 0.10) {
                                enemy = new BigDuck(y, x, y * bsize, x * bsize, "enemy4");
                                enemies.add(enemy);
                                break;
                            } else if (rand <= 0.40) {
                                enemy = new BDuck(y, x, y * bsize, x * bsize, "enemy3");
                                enemies.add(enemy);
                                break;
                            } else if(rand<=0.60){
                                enemy = new PDuck(y, x, y * bsize, x * bsize, "enemy2");
                                enemies.add(enemy);
                                break;
                            }
                                else{
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

    public String[] getNewTower() {
        return newTower;
    }

}
