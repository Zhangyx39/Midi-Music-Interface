package music.Controller;

import java.util.List;

import music.model.Note;
import music.model.Octave;
import music.model.Pitch;

/**
 * Created by qiuyuanzhang on 4/6/17.
 */
public interface IMusicController {

  int getTotalDuration();


  Octave getLowestOctave();

  Octave getHighestOctave();

  Pitch getLowestPitch(Octave o);

  Pitch getHighestPitch(Octave o);

  List<Note> getNotes();

  int getTempo();
}
