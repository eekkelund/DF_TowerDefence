package stna;

/**
 *
 * @author eetz1
 */
public class PDuck extends ModelEnemy {

  public PDuck(int y, int x, int MOVEy, int MOVEx, String id) {
    super(y, x, MOVEy, MOVEx, id);
    init();
  }

  public void init() {

    img = "images/duck2.png";
    speed = 2;
    healt = 120;
    damage = 15;
    prize = 5;

  }
}
