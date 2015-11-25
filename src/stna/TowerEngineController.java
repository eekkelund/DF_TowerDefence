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

    private ModelEnemy enemy;
    private ModelTower tower;
    private Arena arena;
    private List enemies;
//    private List towers = arena.getTowers();
    public final int up = 1, down = 2, right = 3, left = 4;
    private int direction = right;

    public TowerEngineController(Arena a) {
        this.arena = a;
    }

    public void shoot(List enemies, List towers) {
        enemies = arena.getEnemies();
        for (int i = 0; i < towers.size(); i++) {
            tower = (ModelTower) towers.get(i);
            for (int j = 0; j < enemies.size(); j++) {

                enemy = (ModelEnemy) enemies.get(j);
                int range = (int) (Math.PI * Math.pow(tower.getRange(), 2));
                if (enemy.getX() < tower.getX() + range && enemy.getY() < tower.getY() + range) {
                    enemy.setHealt(tower.getDamage());
                    System.out.print("PEWPEW");
                }
            }
        }

    }

    public void isDead() {
        enemies = arena.getEnemies();

        for (int j = 0; j < enemies.size(); j++) {
            enemy = (ModelEnemy) enemies.get(j);
            if (!enemy.isAlive()) {
                //delete from screen
                //add moneys
            }
        }

    }

    public void movable() {
        //enemies = arena.getEnemies();
        //arena = new Arena();

        ModelBlock[][] grid = arena.getArena();
        //for (ModelEnemy enemy : arena.getEnemies()) {
        for (ModelEnemy enemy2 : arena.getEnemies()) {
            //enemy = (ModelEnemy) enemie;

            int xcoord = enemy2.getX();
            int ycoord = enemy2.getY();
            if (direction == up) {
                ycoord--;
                enemy2.setY(ycoord);
            } else if (direction == down) {
                ycoord++;
                enemy2.setY(ycoord);
            } else if (direction == right) {
                xcoord++;
                enemy2.setX(xcoord);
                System.out.print("oikeel");
            } else if (direction == left) {
                xcoord--;
                enemy2.setX(xcoord);
            }
            //           System.out.print(xcoord+""+ycoord);
            System.out.print(grid[ycoord][xcoord].getid());

            try {
                
             if (direction != down && direction != up) {
             if ("road".equals(grid[ycoord + 1][xcoord].getid())) {
             direction = down;
                        
             System.out.print("alaspls");
             } else if ("road".equals(grid[ycoord - 1][xcoord].getid())) {
             direction = up;
             System.out.print("whynotup");
             }
             } else if (direction != right && direction != left) {
             if ("road".equals(grid[ycoord][xcoord + 1].getid())) {
             direction = right;
             System.out.print("oikeepls");
             } else if ("road".equals(grid[ycoord][xcoord - 1].getid())) {
             direction = left;
             }
             }
             } catch (Exception e) {
             System.out.print(e);
             }
            
            
            
            
            /*VARIANT3 try { 

                if (direction != down) {
                    if ("road".equals(grid[ycoord + 1][xcoord].getid())) {
                        direction = down;

                        System.out.print("alaspls");
                    }
                } else if (direction != up) {
                    if ("road".equals(grid[ycoord - 1][xcoord].getid())) {
                        direction = up;
                        System.out.print("whynotup");
                    }
                } else if (direction != right) {
                    if ("road".equals(grid[ycoord][xcoord + 1].getid())) {
                        direction = right;
                        System.out.print("oikeepls");
                    }
                } else if (direction != left) {
                    if ("road".equals(grid[ycoord][xcoord - 1].getid())) {
                        direction = left;
                    }
                }
            } catch (Exception e) {
                System.out.print(e);
            }

            /*VARIANT2try {
                
             if ("road".equals(grid[ycoord + 1][xcoord].getid())) {
             direction = down;
             System.out.print("alas");
             } else if ("road".equals(grid[ycoord - 1][xcoord].getid())) {
             direction = up;
             System.out.print("ylös");
             } else if ("road".equals(grid[ycoord][xcoord + 1].getid())) {
             direction = right;
             System.out.print("oikeel");
             } else if ("road".equals(grid[ycoord][xcoord - 1].getid())) {
             direction = left;
             System.out.print("vasen");
             } 
             } catch (Exception e) {
             System.out.print(e);
             }*/
        }
    }

}
