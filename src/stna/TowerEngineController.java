/*
 * eetz1s License such cool so wow
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;

import java.util.*;

/**
 *
 * @author eetz1
 */
public class TowerEngineController {

    //private ModelEnemy enemy2;
    private ModelTower tower;
    private ModelPlayer player;
    private final Arena arena;
    private List enemies;
//    private List towers = arena.getTowers();
    public final int up = 1, down = 2, right = 3, left = 4;
    private int direction;
    private int bsize, price=5;
    boolean move = true;

    public TowerEngineController(Arena a) {
        this.arena = a;
    }

    public ModelEnemy shoot(ModelTower tower) {
        //enemies = arena.getEnemies();

        //for (ModelTower tower : arena.getTowers()) {
            for (ModelEnemy enemy : arena.getEnemies()) {
                int range = tower.getRange();
                if (Math.abs(enemy.getX() - tower.getX()) < range && Math.abs(enemy.getY() - tower.getY()) < range) {
                    enemy.setHealt(tower.getDamage());
                    if (isDead()) {
                        player.addMoney(enemy.getPrize());
                        System.out.print(player.getMoney());
                    }

                    return enemy;
                    
                }
            }

        return null;

    }
    
    public ModelTower shootable(){
        for (ModelTower tower : arena.getTowers()) {
            ModelEnemy enemy;
                enemy = shoot(tower);
                return tower;
        }
        return null;
    }

    public boolean isDead() {
        player = arena.getPlayer();
        for (Iterator<ModelEnemy> iterator = arena.getEnemies().iterator(); iterator.hasNext();) {
            ModelEnemy enemy = iterator.next();
            if (!enemy.isAlive()) {
                iterator.remove();
                return true;

            }
        }
        return false;
    }
    
     public void newTowerPos(int y, int x, String towerid) {
        ModelBlock[][] grid = arena.getArena();

        x /= bsize;
        y /= bsize;
        switch (towerid) {
            case "tower":
                
                if ("grass".equals(grid[y][x].getid())) {
                    if (player.getMoney() >= price) {
                        arena.setTower(y, x, "tower");
                        player.reduceMoney(price);
                    } else {
                        System.out.print("no mani no hani");
                    }
            
                }else {
                    System.out.print("Wrong palace");
                }
        }
    }

    public void move(ModelEnemy enemy2) {
        
        bsize = arena.getBsize();
        
        ModelBlock[][] grid = arena.getArena();

            
            
            int x = enemy2.getMoveX();
            int y = enemy2.getMoveY();
            int xcoord = enemy2.getX();
            int ycoord = enemy2.getY();
           

            if ("finish".equals(grid[ycoord][xcoord].getid())) {
                enemy2.setHealt(10000000);
                if (isDead()) {
                    player = arena.getPlayer();
                    player.setHealt(enemy2.getDamage());
                    System.out.print(player.getHealt());
                    move = false;
                }
            }

            for (int i=0;i<enemy2.getSpeed();i++){
            if (!move) {
                 direction=enemy2.getDirection();
                 int movecounter = enemy2.getMCounter();
                if (direction == up) {
                    y--;
                    enemy2.setMoveY(y);
                    movecounter++;
                    enemy2.setMCounter(movecounter);
                    if (movecounter == bsize) {
                        ycoord--;
                        enemy2.setY(ycoord);
                        movecounter = 0;
                        move = true;
                        enemy2.setMCounter(movecounter);
                    }
                } else if (direction == down) {
                    y++;
                    enemy2.setMoveY(y);
                    movecounter++;
                    enemy2.setMCounter(movecounter);
                    if (movecounter == bsize) {
                        ycoord++;
                        enemy2.setY(ycoord);
                        movecounter = 0;
                        move = true;
                        enemy2.setMCounter(movecounter);
                    }
                } else if (direction == right) {
                    x++;
                    enemy2.setMoveX(x);
                    movecounter++;
                    enemy2.setMCounter(movecounter);
                    if (movecounter == bsize) {
                        xcoord++;
                        enemy2.setX(xcoord);
                        movecounter = 0;
                        move = true;
                        enemy2.setMCounter(movecounter);
                    }
                } else if (direction == left) {
                    x--;
                    enemy2.setMoveX(x);
                    movecounter++;
                    enemy2.setMCounter(movecounter);
                    if (movecounter == bsize) {
                        xcoord--;
                        enemy2.setX(xcoord);
                        movecounter = 0;
                        move = true;
                        enemy2.setMCounter(movecounter);
                    }
                }
              
            }
            if (move) {
                movable(enemy2);
                direction = enemy2.getDirection();
                move = false;
            } 
            
        }}

    public void movable(ModelEnemy enemy) {
        ModelBlock[][] grid = arena.getArena();
            int xcoord = enemy.getX();
            int ycoord = enemy.getY();
            direction = enemy.getDirection();

            try {

                if (direction != down && direction != up) {
                    if ("road".equals(grid[ycoord + 1][xcoord].getid())) {
                        direction = down;
                        enemy.setDirection(direction);

                    } else if ("road".equals(grid[ycoord - 1][xcoord].getid())) {
                        direction = up;
                        enemy.setDirection(direction);
                    }
                } else if (direction != right && direction != left) {
                    if ("road".equals(grid[ycoord][xcoord + 1].getid())) {
                        direction = right;
                        enemy.setDirection(direction);
                    } else if ("road".equals(grid[ycoord][xcoord - 1].getid())) {
                        direction = left;
                        enemy.setDirection(direction);
                    }
                }
                
            } catch (Exception e) {
                System.out.print(e);
            }
        }        
    }