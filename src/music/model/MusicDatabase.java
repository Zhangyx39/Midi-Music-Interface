package music.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Music Database stores Notes in a map.
 */
public class MusicDatabase implements IMusicDatabase {

  private int tempo;
  private List<Integer> endTimeList;

  //Pitch->Octave->Time->Instrument->Note
  private Map<Octave, Map<Pitch, Map<Integer, Map<Integer, Note>>>> sheet;


  public MusicDatabase() {
    this.sheet = new HashMap<>();
    this.endTimeList = new ArrayList<>();
  }

  @Override
  public List<Note> getNotes() {

    List<Note> all = new ArrayList<>();

    for (Octave o : this.sheet.keySet()) {
      for (Pitch p : this.sheet.get(o).keySet()) {
        for (Integer time : this.sheet.get(o).get(p).keySet()) {
          for (Integer instr : this.sheet.get(o).get(p).get(time).keySet()) {
            all.add(this.sheet.get(o).get(p).get(time).get(instr));
          }
        }
      }
    }

    return all;
  }

  @Override
  public boolean setTempo(int tempo) {
    this.tempo = tempo;
    return true;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public boolean addNote(Pitch pitch, Octave octave, int volume, int startTime,
      int duration, int instrument) {

    this.endTimeList.add(startTime + duration);

    Note newNote = new Note(pitch, octave, instrument, volume, duration, startTime);

    Map instrumentMap = new HashMap<Integer, Note>();
    instrumentMap.put(instrument, newNote);

    Map timeMap = new HashMap<Integer, Map<Integer, Note>>();
    timeMap.put(startTime, instrumentMap);

    Map pitchMap = new HashMap<Pitch, Map<Integer, Map<Integer, Note>>>();
    pitchMap.put(pitch, timeMap);

    if (!this.sheet.containsKey(octave)) {
      this.sheet.put(octave, pitchMap);
    } else if (!this.sheet.get(octave).containsKey(pitch)) {
      this.sheet.get(octave).put(pitch, timeMap);
    } else if (!this.sheet.get(octave).get(pitch).containsKey(startTime)) {
      this.sheet.get(octave).get(pitch).put(startTime, instrumentMap);
    } else if (!this.sheet.get(octave).get(pitch).get(startTime).containsKey(instrument)) {
      this.sheet.get(octave).get(pitch).get(startTime).put(instrument, newNote);
    } else {
      // note already exists
      return false;
    }
    return true;
  }

  @Override
  public int getTotalDuration() {
    Collections.sort(endTimeList);
    //from smallest to largest
    //System.out.println(endTimeList);
    return endTimeList.get(endTimeList.size() - 1);
  }


  @Override
  public Octave getHighestOctave() {

    //get the set of Octaves, find the highest
    Set allOctaves = this.sheet.keySet();
    Octave high = Octave.ONE;

    for (Object o : allOctaves) {
      Octave a = (Octave) o;
      if (a.getNum() > high.getNum()) {
        high = a;
      }
    }
    return high;
  }

  @Override
  public Octave getLowestOctave() {
    //get the set of Octaves, find the highest
    Set allOctaves = this.sheet.keySet();
    Octave low = Octave.TEN;
    for (Object o : allOctaves) {
      Octave a = (Octave) o;
      if (a.getNum() < low.getNum()) {
        low = a;
      }
    }
    return low;
  }

  @Override
  public Pitch getHighestPitch(Octave o) {
    Set allPitches = this.sheet.get(o).keySet();

    Pitch high = Pitch.C;

    for (Object obj : allPitches) {
      Pitch a = (Pitch) obj;
      if (a.ordinal() > high.ordinal()) {
        high = a;
      }
    }

    return high;
  }

  @Override
  public Pitch getLowestPitch(Octave o) {
    Set allPitches = this.sheet.get(o).keySet();

    Pitch low = Pitch.B;

    for (Object obj : allPitches) {
      Pitch a = (Pitch) obj;
      if (a.ordinal() < low.ordinal()) {
        low = a;
      }
    }
    return low;
  }

  @Override
  public boolean hasNote(int time, Pitch pitch, Octave octave, Integer instrument) {
    return this.sheet.get(octave).get(pitch).get(time).containsKey(instrument);
  }

  @Override
  public int getVolume(int time, Pitch pitch, Octave octave, Integer instrument) {
    return this.sheet.get(octave).get(pitch).get(time).get(instrument).getVolume();
  }

  @Override
  public int getDuration(int time, Pitch pitch, Octave octave, Integer instrument) {
    return this.sheet.get(octave).get(pitch).get(time).get(instrument).getDuration();
  }

  @Override
  public String toString() {
    String out = "";

    for (Octave o : this.sheet.keySet()) {
      for (Pitch p : this.sheet.get(o).keySet()) {
        for (Integer time : this.sheet.get(o).get(p).keySet()) {
          for (Integer instrument : this.sheet.get(o).get(p).get(time).keySet()) {
            out = out + (this.sheet.get(o).get(p).get(time).get(instrument).toString()) + "\n";
          }
        }
      }
    }
    return out;
  }
}
