package music;

import java.io.File;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;


/**
 * Runnable
 */
public class MusicMain {

  public static void main(String[] s) throws MidiUnavailableException {
    //System.out.println(Pitch.ASharp.toString());
    File midiFile = new File("around_the_world.midi");

    if (!midiFile.exists() || midiFile.isDirectory() || !midiFile.canRead()) {
      System.out.println("No file");
    }
    // Play once
    try {
      Sequencer sequencer = MidiSystem.getSequencer();
      Sequence mySeq;
      try {
        mySeq = new Sequence(Sequence.PPQ, 10);
        // Start playing the note Middle C (60),
        // moderately loud (velocity = 93).
        Track tr = mySeq.createTrack();

        ShortMessage myMsg = new ShortMessage();
        myMsg.setMessage(ShortMessage.NOTE_ON, 1, 60, 93);
        ShortMessage myMsg_End = new ShortMessage(ShortMessage.NOTE_OFF, 60, 93);
        tr.add(new MidiEvent(myMsg, 0));
        tr.add(new MidiEvent(myMsg_End, 5));
//
//        ShortMessage myMsg2 = new ShortMessage();
//        myMsg2.setMessage(ShortMessage.NOTE_ON, 1, 62, 93);
//        ShortMessage myMsg2_End = new ShortMessage(ShortMessage.NOTE_OFF, 60, 93);
//        tr.add(new MidiEvent(myMsg2, 1));
//        tr.add(new MidiEvent(myMsg2_End, 2));
//
//        ShortMessage myMsg3 = new ShortMessage();
//        myMsg3.setMessage(ShortMessage.NOTE_ON, 1, 64, 93);
//        ShortMessage myMsg3_End = new ShortMessage(ShortMessage.NOTE_OFF, 60, 93);
//        tr.add(new MidiEvent(myMsg3, 2));
//        tr.add(new MidiEvent(myMsg3_End, 3));

        //sequencer.setSequence(MidiSystem.getSequence(midiFile));
        sequencer.setSequence(mySeq);
        sequencer.setTempoInBPM(10);

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
        // Close the MidiDevice & free resources
        sequencer.stop();
        sequencer.close();
      } catch (MidiUnavailableException mue) {
        System.out.println("Midi device unavailable!");
      } catch (InvalidMidiDataException imde) {
        System.out.println("Invalid Midi data!");
      } /*catch (IOException ioe) {
      System.out.println("I/O Error!");
    } */
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }
}
/*
      try {
      Synthesizer synthesizer = MidiSystem.getSynthesizer();
      synthesizer.open();
      MidiChannel[] channels = synthesizer.getChannels();
      channels[0].noteOn(60, 100);
      Thread.sleep(1000);
     // channels[0].noteOff(60);
     // Thread.sleep(200);
     // synthesizer.close();
    } catch (Exception e)
    {
      e.printStackTrace();
    }
  }*/