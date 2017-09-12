package music.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import music.Controller.IMusicController;
import music.Controller.MusicController;
import music.model.IMusicDatabase;
import music.model.MusicDatabase;
import music.model.Octave;
import music.model.Pitch;
import music.view.IMidiView;
import music.view.IMusicView;
import music.view.MidiView;
import music.view.MusicView;

/**
 * Adapter for database
 */
public class DataBase2BuilderAdapter implements
    CompositionBuilder<IMusicDatabase> {

  IMusicDatabase database;

  public DataBase2BuilderAdapter() {
    database = new MusicDatabase();
  }

  @Override
  public IMusicDatabase build() {
    return database;
  }

  @Override
  public CompositionBuilder<IMusicDatabase> setTempo(int tempo) {
    database.setTempo(tempo);
    return this;
  }

  @Override
  public CompositionBuilder<IMusicDatabase> addNote(int start, int end, int instrument, int pitch,
      int volume) {

    int duration = end - start;

    Pitch p = Midi2Pitch.getPitch(pitch);
    Octave o = Midi2Pitch.getOctave(pitch);
    database.addNote(p, o, volume, start, duration, instrument);

    return null;
  }

  public static void main(String[] args) {
    //String FILENAME = "mary-little-lamb.txt";
    String FILENAME = "mystery-1.txt";
    Reader in;
    try {
      in = new BufferedReader(new FileReader(FILENAME));
    } catch (FileNotFoundException e) {
      System.out.println("file not found");
      return;
    }
    CompositionBuilder<IMusicDatabase> builder = new DataBase2BuilderAdapter();

    IMusicDatabase data = MusicReader.parseFile(in, builder);

    IMusicController controller = new MusicController(data);
    IMusicView view = new MusicView(controller);
    MidiView midiView = new MidiView(controller);
    midiView.getNotes();
    midiView.setSequence();
    midiView.setSequencer();
    midiView.play();

    //printout all data
    //System.out.println(data.toString());

    //total number of notes = file number -1
    //System.out.println(data.toString().split("\n").length);

  }
}
