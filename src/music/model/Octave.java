package music.model;

/**
 * Octave enum allows 10 octaves.
 * Number can be retrieve by Octave.ONE.getNum.
 */
public enum Octave {

  ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
  SEVEN(7), EIGHT(8), NINE(9), TEN(10);

  private int num;

  private Octave(int num) {
    this.num = num;
  }

  public int getNum() {
    return num;
  }


  @Override
  public String toString() {
    return this.getNum() + "";
  }
}
