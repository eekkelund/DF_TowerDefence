/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stna;

/**
 *
 * @author eetz1
 */
public class YDuck extends ModelEnemy{

    public YDuck(int y, int x, int MOVEy, int MOVEx, String id) {
        super(y, x, MOVEy, MOVEx, id);
        init();
    }
    public void init() {

                img = "images/duck.png";
                speed = 1;
                healt = 1000;
                damage = 10;
                prize = 5;
                
        
    }
}
