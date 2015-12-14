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
public class BigDuck  extends ModelEnemy{

    public BigDuck(int y, int x, int MOVEy, int MOVEx, String id) {
        super(y, x, MOVEy, MOVEx, id);
        init();
    }
    public void init() {

                img = "images/duck4.png";
                speed = 0.5;
                healt = 1000;
                damage = 30;
                prize = 20;
                size =0.5;
                
        
    }
}
