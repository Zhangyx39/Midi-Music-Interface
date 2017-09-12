package music.model;


/**
 * The note class represents a Note by its pitch, octave, volume, instrument, duration and start
 * time.
 */
public class Note {

  private Pitch pitch;
  private Octave octave;
  private int instrument;
  private int volume;
  private int duration;
  private int startTime;

  public Note(Pitch pitch, Octave octave, int instrument, int volume, int duration, int stateTime) {
    this.pitch = pitch;
    this.octave = octave;
    this.instrument = instrument;
    this.volume = volume;
    this.duration = duration;
    this.startTime = stateTime;
  }

  public Pitch getPitch() {
    return pitch;
  }

  public Octave getOctave() {
    return octave;
  }

  public int getVolume() {
    return volume;
  }

  public int getDuration() {
    return duration;
  }

  public int getStateTime() {
    return startTime;
  }

  public int getInstrument() {
    return instrument;
  }

  @Override
  public String toString() {
    return this.pitch.toString() + this.octave.getNum()
        + " from " + this.startTime + " to " + this.duration
        + " v " + this.volume + " in " + this.instrument;
  }

}
