package music.view;

import java.util.List;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import music.model.Note;

import music.Controller.IMusicController;

/**
 * Created by qiuyuanzhang on 4/13/17.
 */
public class MidiView {

  private IMusicController controller;
  private List<Note> notes;
  private Sequencer sequencer;
  private Sequence sequence;
  private int resolution;
  private int speed;

  public MidiView(IMusicController controller) {
    this.controller = controller;
    resolution = 10;
  }

  public void getNotes() {
    notes = controller.getNotes();
    speed = controller.getTempo();
  }

  private void addMidiEvent(Track track, Note note) {
    int channel = note.getInstrument() - 1;
    int vol = note.getVolume();
    int pitch = note.getPitch().getNum() + note.getOctave().getNum() * 12;
    int start = note.getStateTime() * resolution * 7000 / speed;
    int end = (start + note.getDuration()) * resolution * 7000 / speed;
    try {
      ShortMessage noteStart = new ShortMessage(ShortMessage.NOTE_ON, channel, pitch, vol);
      ShortMessage noteEnd = new ShortMessage(ShortMessage.NOTE_OFF, channel, pitch, vol);
      track.add(new MidiEvent(noteStart, start));
      track.add(new MidiEvent(noteEnd, end));
    } catch (Exception e) {

    }
  }

  public void setSequence() {
    try {
      sequence = new Sequence(sequence.PPQ, resolution);
      Track track = sequence.createTrack();
      for (Note n : notes) {
        addMidiEvent(track, n);
      }

    } catch (Exception e) {

    }
  }

  public void setSequencer() {
    try {
      sequencer = MidiSystem.getSequencer();
      sequencer.setSequence(sequence);
      //sequencer.setTempo
    } catch (Exception e) {

    }
  }

  public void play() {
    try {
      sequencer.open();
      sequencer.start();
      while (true) {
        if (sequencer.isRunning()) {
          try {
            Thread.sleep(1000); // Check every second
          } catch (InterruptedException ignore) {
            break;
          }
        } else {
          break;
        }
      }
    } catch (Exception e) {

    }
  }
}
