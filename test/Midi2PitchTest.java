import static org.junit.Assert.*;

import music.model.Octave;
import music.model.Pitch;
import music.util.Midi2Pitch;
import org.junit.Test;

/**
 * Test for midi output.
 */
public class Midi2PitchTest {

  @Test
  public void getOctave() {
    assertEquals(Octave.ZERO,Midi2Pitch.getOctave(0));
    assertEquals(Octave.ZERO,Midi2Pitch.getOctave(11));
  }
  @Test(expected = IllegalArgumentException.class)
  public void getOctaveException() {
    Midi2Pitch.getOctave(-3);
  }

  @Test
  public void getPitch() throws Exception {
    assertEquals(Pitch.C,Midi2Pitch.getPitch(0));
    assertEquals(Pitch.ASharp,Midi2Pitch.getPitch(10));
  }

}