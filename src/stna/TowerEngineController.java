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
    public final int up = 1, down = 2, right = 3, left = 4;//just for easier direction management
    private int direction;
    private int bsize;
    boolean move = true;
    private int[] shootList;

    public TowerEngineController(Arena a) {
        this.arena = a;
        player = arena.getPlayer();
    }

    public ModelEnemy shoot(ModelTower tower) {
        //enemies = arena.getEnemies();
        //ArrayList<ModelEnemy> enemies2 = new ArrayList();//for (ModelTower tower : arena.getTowers()) {
        for (ModelEnemy enemy : arena.getEnemies()) {
            int range = tower.getRange();

            if (Math.abs(enemy.getX() - tower.getX()) < range && Math.abs(enemy.getY() - tower.getY()) < range) {
                if ("tower2".equals(tower.getid())&&!enemy.isFrzn()) {
                    
                    
                    enemy.setSpeed(tower.getFrz());
                    
                    enemy.setFrzn(true);
                    return enemy;
                }else if("tower2".equals(tower.getid())&&enemy.isFrzn()){
                            return enemy;
                            
                } else {
                    enemy.setHealt(tower.getDamage());
                    if (isDead()) {
                        player.addMoney(enemy.getPrize());
                        System.out.print(player.getMoney());
                    }
                    //enemies2.add(enemy);
                    return enemy;
                }
            }
        }

        return null;
    }

    public int[] shootable(ModelTower tower) {

        ModelEnemy enemy;

        enemy = shoot(tower);
        int[] shootList = new int[5];
        int clr = tower.getClr().getRGB();
        shootList[0] = clr;
        shootList[1] = tower.getX();
        shootList[2] = tower.getY();
        shootList[3] = enemy.getMoveX();
        shootList[4] = enemy.getMoveY();
        return shootList;

    }

    public boolean shoottest(ModelTower tower, ModelEnemy enemy) {
        //for (ModelTower tower : arena.getTowers()) {
        //for (ModelEnemy enemy : arena.getEnemies()) {
        if (shootingpossible(tower, enemy)) {
            enemy.setHealt(tower.getDamage());

            if (isDead()) {
                player.addMoney(enemy.getPrize());
                System.out.print(player.getMoney());
            }
            //}
            return true;
            //}
        }
        return false;
    }

    public boolean shootingpossible(ModelTower tower, ModelEnemy enemy) {
        //for (ModelTower tower : arena.getTowers()) {
        //for (ModelEnemy enemy : arena.getEnemies()) {
        int range = tower.getRange();
        if (Math.abs(enemy.getX() - tower.getX()) < range && Math.abs(enemy.getY() - tower.getY()) < range) {
            return true;
        } else {
            return false;
        }

    }

    public int[] hover(int x, int y) {
        int xcoord = 0, ycoord = 0;
        int[] coords = new int[3];
        for (int i = 0; i <= arena.getArena().length; i++) {
            ycoord = bsize * i;
            for (int j = 0; j <= arena.getArena()[0].length; j++) {
                xcoord = bsize * j;

                if (x <= xcoord && x >= xcoord - bsize) {
                    coords[0] = xcoord;
                }
                if (y <= ycoord && y >= ycoord - bsize) {
                    coords[1] = ycoord;
                }
            }
        }
        /*if ("grass".equals(arena.getArena()[ycoord][xcoord].getid())){
         coords[2] = 1;
         }*/
        return coords;
    }

    public boolean isDead() {
        for (Iterator<ModelEnemy> iterator = arena.getEnemies().iterator(); iterator.hasNext();) {
            ModelEnemy enemy = iterator.next();
            ModelEnemy enemy2;
            if (!enemy.isAlive()) {
                if ("enemy3".equals(enemy.getid())) {
                    iterator.remove();
                    //for (int i = 0; i < 2; i++) {
                    enemy2 = new ModelEnemy(enemy.getY(), enemy.getX(), enemy.getY() * bsize, enemy.getX() * bsize, "enemy");
                    enemy2.setDirection(enemy.getDirection());
                    arena.getEnemies().add(enemy2);

                    return true;
                    //}
                }
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public void moving() {
        for (int i = 0; i < arena.getEnemies().size(); i++) {
            ModelEnemy enemy = arena.getEnemies().get(i);
            move(enemy);
            //repaint();
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

        for (double i = 0; i < enemy2.getSpeed(); i=i+0.5) {
            if (!move) {
                direction = enemy2.getDirection();
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

        }
    }

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
