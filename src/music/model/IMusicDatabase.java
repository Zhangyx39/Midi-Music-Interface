package music.model;

import java.util.List;

/**
 * Interface for Music Database. It represents a music sheet with tempo,
 * note, highest,lowest pitch, total duration and so on.
 */
public interface IMusicDatabase {

  List<Note> getNotes();

  boolean setTempo(int tempo);

  int getTempo();

  boolean addNote(Pitch pitch, Octave octave, int volume, int startTime, int
      duration, int instrument);

  int getTotalDuration();


  Octave getLowestOctave();

  Octave getHighestOctave();

  Pitch getLowestPitch(Octave o);

  Pitch getHighestPitch(Octave o);

  boolean hasNote(int time, Pitch pitch, Octave octave, Integer instrument);

  int getVolume(int time, Pitch pitch, Octave octave, Integer instrument);

  int getDuration(int time, Pitch pitch, Octave octave, Integer instrument);
}
