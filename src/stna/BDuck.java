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
public class BDuck extends ModelEnemy{

    public BDuck(int y, int x, int MOVEy, int MOVEx, String id) {
        super(y, x, MOVEy, MOVEx, id);
        init();
    }
    public void init() {

                img = "images/duck3.png";
                speed = 1;
                healt = 50;
                damage = 15;
                prize = 5;
                
        
    }
    
}
