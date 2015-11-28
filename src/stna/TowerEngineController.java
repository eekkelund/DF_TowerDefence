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
    private ModelTower tower2;
    private ModelPlayer player;
    private final Arena arena;
    private List enemies;
//    private List towers = arena.getTowers();
    public final int up = 1, down = 2, right = 3, left = 4;
    private int direction;
    private int bsize;
    boolean move = true;

    public TowerEngineController(Arena a) {
        this.arena = a;
    }

    public ModelEnemy shoot() {
        enemies = arena.getEnemies();

        for (ModelTower tower : arena.getTowers()) {
            for (ModelEnemy enemy : arena.getEnemies()) {
                int range = tower.getRange();
                if (Math.abs(enemy.getX() - tower.getX()) < range && Math.abs(enemy.getY() - tower.getY()) < range) {
                    enemy.setHealt(tower.getDamage());
                    if (isDead()) {
                        player.addMoney(enemy.getPrize());
                        System.out.print(player.getMoney());
                    }
                    return (enemy);
                }
            }
        }

        //return false;
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

    public void move(ModelEnemy enemy2) {
        
        bsize = arena.getBsize();
        
        ModelBlock[][] grid = arena.getArena();
        //for (int i = 0; i < arena.getEnemies().size(); i++) {

            //ModelEnemy enemy2 = arena.getEnemies().get(i);
            
            
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
            /*if ("start".equals(grid[ycoord][xcoord].getid())) {
                movable(enemy2);
                direction=enemy2.getDirection();
                
            }*/
            if (move) {
                movable(enemy2);
                direction = enemy2.getDirection();
                move = false;
            } 
            else if (!move) {
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
            
        }
    //}
    //           System.out.print(xcoord+""+ycoord);
    //System.out.print(grid[ycoord][xcoord].getid());

    public void movable(ModelEnemy enemy) {
        ModelBlock[][] grid = arena.getArena();
        //for (ModelEnemy enemy : arena.getEnemies()) {
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
                /*if (direction != right &&direction != left &&direction != up &&direction != down){
                    System.out.print("asd");
                    if ("road".equals(grid[ycoord][xcoord + 1].getid())) {
                        direction = right;
                        enemy.setDirection(direction);
                    } else if ("road".equals(grid[ycoord][xcoord - 1].getid())) {
                        direction = left;
                        enemy.setDirection(direction);
                    }
                    else if ("road".equals(grid[ycoord + 1][xcoord].getid())) {
                        direction = down;
                        enemy.setDirection(direction);

                    } else if ("road".equals(grid[ycoord - 1][xcoord].getid())) {
                        direction = up;
                        enemy.setDirection(direction);
                    }
                }*/
            } catch (Exception e) {
                System.out.print(e);
            }
        }
        //shoot();
        
    }

//}
