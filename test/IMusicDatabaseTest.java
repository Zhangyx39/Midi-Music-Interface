import static org.junit.Assert.*;

import music.model.IMusicDatabase;
import music.model.MusicDatabase;
import music.model.Octave;
import music.model.Pitch;
import org.junit.Test;

/**
 * Test cases for music database interface.
 */
public class IMusicDatabaseTest {

  IMusicDatabase dataset = new MusicDatabase();

  @Test
  public void testAddNote() {
    dataset.addNote(Pitch.A, Octave.ONE, 3, 1, 7, 1);
    dataset.addNote(Pitch.A, Octave.TEN, 3, 1, 10, 1);

    assertTrue(dataset.hasNote(1, Pitch.A, Octave.ONE, 1));
    assertEquals(Octave.TEN, dataset.getHighestOctave());
    assertEquals(Octave.ONE, dataset.getLowestOctave());

    assertEquals(Pitch.A, dataset.getHighestPitch(Octave.ONE));

    assertEquals(11, dataset.getTotalDuration());

    System.out.println(dataset.getNotes().toString());
  }
}