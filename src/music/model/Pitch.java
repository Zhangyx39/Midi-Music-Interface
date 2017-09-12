package music.model;

/**
 * A pitch enum class list out all allowable pitches in music sheet. Order is specific according to
 * midi guid lines. http://www.electronics.dit.ie/staff/tscarff/Music_technology/midi/midi_note_numbers_for_octaves.htm
 */
public enum Pitch {
  C(0), CSharp(1), D(2), DSharp(3), E(4), F(5), FSharp(6), G(7), GSharp(8), A
          (9), ASharp(10), B(11);

  private int num;

  private Pitch(int num) {
    this.num = num;
  }

  public int getNum() {
    return num;
  }


  @Override
  public String toString() {
    if (super.toString().length() > 1) {
      return super.toString().substring(0, 1) + "#";
    } else {
      return super.toString();
    }
  }
}
