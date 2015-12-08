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


//THIS IS GAME ENGINE WOOOO
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

    public TowerEngineController(Arena a) {//TO MAKE THIS ENGINE WORK WE NEED ARENA AND PLAYER
        this.arena = a;
        player = arena.getPlayer();
    }

    //ONE OF SHOOTING METHODS THIS IS FOR LAZERTOWER AND FREEZETOWER
    public ModelEnemy shoot(ModelTower tower) {
        //enemies = arena.getEnemies();
        //ArrayList<ModelEnemy> enemies2 = new ArrayList();//for (ModelTower tower : arena.getTowers()) {
        for (ModelEnemy enemy : arena.getEnemies()) {
            int range = tower.getRange();

            if (Math.abs(enemy.getX() - tower.getX()) <= range && Math.abs(enemy.getY() - tower.getY()) <= range) {
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
                        //System.out.print(player.getMoney());
                    }
                    
                    return enemy;
                }
            }
        }

        return null;
    }
    //HERE IS THE LIST FOR LAZER AND FREEZE RETURNED. THIS IS NEEDED TO DRAW LINES
    public int[] shootable(ModelTower tower) {

        ModelEnemy enemy;

        enemy = shoot(tower);
        int[] shootList = new int[5];//LIST WHAT IS RETURNED TO PAINT
        int clr = tower.getClr().getRGB();
        shootList[0] = clr;
        shootList[1] = tower.getX();
        shootList[2] = tower.getY();
        shootList[3] = enemy.getMoveX();
        shootList[4] = enemy.getMoveY();
        return shootList;

    }
    //THIS SHOOT METHOD IS FOR ROUNDTOWER. IT SHOOTS ALL ENEMIES ON ITS RANGE WHILE LAZER AND FREEZE ONLY SHOOOTS FIRST ENEMY 
    public boolean shootround(ModelTower tower, ModelEnemy enemy) {
        //for (ModelTower tower : arena.getTowers()) {
        //for (ModelEnemy enemy : arena.getEnemies()) {
        if (shootRoundPossible(tower, enemy)) {
            enemy.setHealt(tower.getDamage());

            if (isDead()) {
                player.addMoney(enemy.getPrize());
                //System.out.print(player.getMoney());
            }
            //}
            return true;
            //}
        }
        return false;
    }
    //THIS IS TO CHECK IF THERE IS ENEMIES ON THE RANGE ON ROUNDT
    public boolean shootRoundPossible(ModelTower tower, ModelEnemy enemy) {
        //for (ModelTower tower : arena.getTowers()) {
        //for (ModelEnemy enemy : arena.getEnemies()) {
        int range = tower.getRange();
        if (Math.abs(enemy.getX() - tower.getX()) <= range && Math.abs(enemy.getY() - tower.getY()) <= range) {
            return true;
        } else {
            return false;
        }

    }
    public void shootImprove(BoostTower tower, ModelTower tower2) {//THIS IS FOR BOOSTTOWER IT "SHOOTS" TOWERS AROUND IT TO IMPROVE THEM
        //for (ModelTower tower : arena.getTowers()) {
        //for (ModelEnemy enemy : arena.getEnemies()) {
        int range = tower.getRange();
        if (Math.abs(tower2.getX() - tower.getX()) < range && Math.abs(tower2.getY() - tower.getY()) < range && !"tower4".equals(tower2.getid()) && !tower2.isBoosted()) {
            tower2.setDamage(tower2.getDamage()+tower.getImprove());
            tower2.setRange(tower2.getRange()+tower.getImprove());
            tower2.setBoosted(true);
            //return false;
        }
    }
    void shootmoney() {
        for (ModelTower tower : arena.getTowers()){
            if ("tower5".equals(tower.getid())){
                player.addMoney(tower.getDamage());
                System.out.println("asd"+player.getMoney());
            }
        }
            
    }


    //IF ENEMY IS DEAD
    public boolean isDead() {
        for (Iterator<ModelEnemy> iterator = arena.getEnemies().iterator(); iterator.hasNext();) {
            ModelEnemy enemy = iterator.next();
            ModelEnemy enemy2;
            if (!enemy.isAlive()) {
                if ("enemy3".equals(enemy.getid())) {//IF BLACKDUCK ALKA ENEMY3 IS KILLED IT WILL SPAWN YELLOW DUCK THERE WHERE IT DIED
                    iterator.remove();
                    enemy2 = new YDuck(enemy.getY(), enemy.getX(), enemy.getY() * bsize, enemy.getX() * bsize, "enemy");//YELLOWDUCK
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
    //THIS METHOD IS CALLED FROM GAME IT MOVES ENEMIES
    public void moving() {
        for (int i = 0; i < arena.getEnemies().size(); i++) {
            ModelEnemy enemy = arena.getEnemies().get(i);
            move(enemy);//actual moving happens here
            //repaint();
        }
    }
    //YEAH THE ACTUAL MOVING HAPPENS HERE
    public void move(ModelEnemy enemy2) {

        bsize = arena.getBsize();

        ModelBlock[][] grid = arena.getArena();

        int x = enemy2.getMoveX();//these are enemys objgrid coord
        int y = enemy2.getMoveY();
        int xcoord = enemy2.getX();//these are enemys screen coord
        int ycoord = enemy2.getY();

        if ("finish".equals(grid[ycoord][xcoord].getid())) {//If enemy gets to finish it dies and reduces players healt
            enemy2.setHealt(10000000);
            if (isDead()) {
                player = arena.getPlayer();
                player.setHealt(enemy2.getDamage());
                //System.out.print(player.getHealt());
                move = false;
            }
        }

        for (double i = 0; i < enemy2.getSpeed(); i=i+0.5) {//BLAAAAAH
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
