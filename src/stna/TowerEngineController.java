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

    private ModelEnemy enemy2;
    private ModelTower tower;
    private ModelPlayer player;
    private Arena arena;
    private List enemies;
//    private List towers = arena.getTowers();
    public final int up = 1, down = 2, right = 3, left = 4;
    private int direction = right;

    public TowerEngineController(Arena a) {
        this.arena = a;
    }

    public boolean shoot() {
        enemies = arena.getEnemies();

        for (ModelTower tower : arena.getTowers()) {
            for (ModelEnemy enemy : arena.getEnemies()) {
                int range = tower.getRange();
                if (Math.abs(enemy.getX() - tower.getX()) < range && Math.abs(enemy.getY() - tower.getY()) < range) {
                    enemy.setHealt(tower.getDamage());
                    isDead();

                    return (true);
                }
            }
        }
        return false;

    }

    public void isDead() {
        player = arena.getPlayer();
        for (Iterator<ModelEnemy> iterator = arena.getEnemies().iterator(); iterator.hasNext();) {
            ModelEnemy enemy = iterator.next();
            if (!enemy.isAlive()) {
                iterator.remove();
                System.out.print("ded");
                player.addMoney(enemy.getPrize());
                System.out.print(player.getMoney());
            }
        }

    }

    public void movable() {
        //enemies = arena.getEnemies();
        //arena = new Arena();

        ModelBlock[][] grid = arena.getArena();
        for (int i = 0; i < arena.getEnemies().size(); i++) {
        //for (Iterator<ModelEnemy> iterator = arena.getEnemies().iterator(); iterator.hasNext();) {
            //ModelEnemy enemy2 = iterator.next();
            //for (ModelEnemy enemy2 : arena.getEnemies()) {
            enemy2 = arena.getEnemies().get(i);
            //enemy = (ModelEnemy) enemie;

            int xcoord = enemy2.getX();
            int ycoord = enemy2.getY();

            if ("finish".equals(grid[ycoord][xcoord].getid())) {
                enemy2.setHealt(1000);
                isDead();
                player = arena.getPlayer();
                player.setHealt(enemy2.getDamage());
            }
            if (direction == up) {
                ycoord--;
                enemy2.setY(ycoord);
            } else if (direction == down) {
                ycoord++;
                enemy2.setY(ycoord);
            } else if (direction == right) {
                xcoord++;
                enemy2.setX(xcoord);
            } else if (direction == left) {
                xcoord--;
                enemy2.setX(xcoord);
            }
            //           System.out.print(xcoord+""+ycoord);
            //System.out.print(grid[ycoord][xcoord].getid());

            try {

                if (direction != down && direction != up) {
                    if ("road".equals(grid[ycoord + 1][xcoord].getid())) {
                        direction = down;

                    } else if ("road".equals(grid[ycoord - 1][xcoord].getid())) {
                        direction = up;
                    }
                } else if (direction != right && direction != left) {
                    if ("road".equals(grid[ycoord][xcoord + 1].getid())) {
                        direction = right;
                    } else if ("road".equals(grid[ycoord][xcoord - 1].getid())) {
                        direction = left;
                    }
                }

            } catch (Exception e) {
                System.out.print(e);
            }
        }
        //shoot();
    }

}
