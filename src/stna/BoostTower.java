package stna;

import static java.awt.Color.*;

;

/**
 *
 * @author eetz1
 */
public class BoostTower extends ModelTower {

  private int improve;

  public BoostTower(int y, int x, String id) {
    super(y, x, id);
    init();
  }

  public void init() {

    img = "images/tower6.png";
    fireRate = 1000000000;
    damage = 0;
    range = 2;
    clr = YELLOW;
    update_price = 30;
    price = 20;
    max_level = 2;
    improve = 1;
    info = "<html>Boosts own<br />towers in<br />its range.</html>";// PLZ
  }

  @Override
  public void setLevel() {
    level = level + 1;
    switch (level) {
      case 2:
        img = "images/tower7.png";
        fireRate = 1000000000;
        damage = 0;
        range = 2;
        clr = BLUE;
        improve = 2;
        update_price = 0;
        break;
      default:
        break;
    }

  }

  public int getImprove() {
    return improve;
  }
}
