package stna;

/**
 *
 * @author eetz1
 */
public class YDuck extends ModelEnemy {

  public YDuck(int y, int x, int MOVEy, int MOVEx, String id) {
    super(y, x, MOVEy, MOVEx, id);
    init();
  }

  public void init() {

    img = "images/duck.png";
    speed = 1;
    healt = 70;
    damage = 10;
    prize = 1;

  }
}
