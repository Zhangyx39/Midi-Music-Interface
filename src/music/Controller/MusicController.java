package music.Controller;


import java.util.List;

import music.model.IMusicDatabase;
import music.model.Note;
import music.model.Octave;
import music.model.Pitch;
import music.view.IMusicView;

/**
 * Created by qiuyuanzhang on 4/6/17.
 */
public class MusicController implements IMusicController {
  private IMusicDatabase database;
  private IMusicView view;

  public MusicController(IMusicDatabase database) {
    this.database = database;
  }

  @Override
  public int getTotalDuration() {
    return database.getTotalDuration();
  }

  @Override
  public Octave getLowestOctave() {
    return database.getLowestOctave();
  }

  @Override
  public Octave getHighestOctave() {
    return database.getHighestOctave();
  }

  @Override
  public Pitch getLowestPitch(Octave o) {
    return database.getLowestPitch(database.getLowestOctave());
  }

  @Override
  public Pitch getHighestPitch(Octave o) {
    return database.getHighestPitch(database.getHighestOctave());
  }

  @Override
  public List<Note> getNotes() {
    return database.getNotes();
  }

  @Override
  public int getTempo() {
    return database.getTempo();
  }
}
