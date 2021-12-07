package stna;

/**
 *
 * @author eetz1
 */
public class BDuck extends ModelEnemy {

  public BDuck(int y, int x, int MOVEy, int MOVEx, String id) {
    super(y, x, MOVEy, MOVEx, id);
    init();
  }

  public void init() {

    img = "images/duck3.png";
    speed = 1;
    healt = 100;
    damage = 10;
    prize = 3;

  }

}
