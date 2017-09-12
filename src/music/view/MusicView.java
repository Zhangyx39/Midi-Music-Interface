package music.view;

import java.awt.*;

import javax.swing.*;
import java.util.List;

import music.Controller.IMusicController;
import music.model.Note;
import music.model.Octave;
import music.model.Pitch;
import music.util.Midi2Pitch;

/**
 * Music view class.
 */
public class MusicView extends JFrame implements IMusicView {

  IMusicController controller;


  private JPanel drawPanel;
  private int HEIGHT = 500;
  private int WIDTH = 1000;
  private int OFFSET = 50;
  private int deltaX = 100;
  private int deltaY = 20;
  //todo controller

  public MusicView(IMusicController controller) {
    super();
    this.controller = controller;
    setHEIGHT();
    setWIDTH();

    setTitle("Music Display");
    setSize(1000, HEIGHT + OFFSET * 4);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    drawPanel = new UpperPanel();
    drawPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

    JScrollPane scrolls = new JScrollPane(drawPanel);

    this.add(scrolls);

    setVisible(true);
  }

  public void setHEIGHT() {
    Octave lowestOctave = controller.getLowestOctave();
    Octave highestOctave = controller.getHighestOctave();
    Pitch lowestPitch = controller.getLowestPitch(lowestOctave);
    Pitch highestPitch = controller.getHighestPitch(lowestOctave);

    MusicView.this.HEIGHT = OFFSET * 2 + deltaY * ((highestOctave.getNum() -
            lowestOctave.getNum()) * 12 + (highestPitch.getNum() -
            lowestPitch.getNum()));
  }

  public void setWIDTH() {


    MusicView.this.WIDTH = OFFSET * 2 + deltaX * (controller.getTotalDuration()
            /4 + 1);

  }


  class UpperPanel extends JPanel {

    public UpperPanel() {
      super();
      setBackground(Color.white);
    }

    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);

      g.setColor(Color.black);

      drawPitch(g);

      drawBeat(g);

      drawGrid(g);

      drawNote(g);

    }

    private void drawGrid(Graphics g) {
      //draw grid
      g.setColor(Color.BLACK);
      for (int i = OFFSET * 2; i < MusicView.this.WIDTH; i = i + deltaX) {
        for (int j = OFFSET * 2; j <= MusicView.this.HEIGHT; j = j + deltaY) {
          g.drawRect(i, j, deltaX, deltaY);
        }
      }
    }

    private void drawNote(Graphics g) {
      int lowMidi = controller.getLowestPitch(controller.getLowestOctave())
              .getNum() + 12 * controller.getLowestOctave().getNum();
      int highMidi = controller.getHighestPitch(controller.getHighestOctave()
      ).getNum() + 12 * controller.getHighestOctave().getNum();
      List<Note> notes = controller.getNotes();

      for (Note note : notes) {
        int midi = note.getOctave().getNum() * 12 + note.getPitch().getNum();
        int x = note.getStateTime() * deltaX/4 + OFFSET * 2;
        int y = OFFSET * 2 + (highMidi - midi) * deltaY;

        g.setColor(Color.BLACK);
        g.fillRect(x, y, deltaX/4, 20);
        g.setColor(Color.GREEN);
        g.fillRect(x + deltaX/4, y, (note.getDuration() - 1) * deltaX / 4,
                20);
      }

    }


    private void drawPitch(Graphics g) {
      g.setColor(Color.black);

      int lowMidi = controller.getLowestPitch(controller.getLowestOctave())
              .getNum() + 12 * controller.getLowestOctave().getNum();
      int highMidi = controller.getHighestPitch(controller.getHighestOctave()
      ).getNum() + 12 * controller.getHighestOctave().getNum();
      int currentMidi = highMidi;

      for (int i = OFFSET * 2 + deltaY; currentMidi >= lowMidi; i +=
              deltaY) {
        String str = Midi2Pitch.getPitch(currentMidi).toString()
                + Midi2Pitch.getOctave(currentMidi).toString();
        g.drawString(str, OFFSET, i);
        currentMidi--;
      }
    }

    private void drawBeat(Graphics g) {
      g.setColor(Color.black);

      int beatCounter = 0;
      //draw beat
      for (int i = OFFSET * 2; i < MusicView.this.WIDTH; i += deltaX) {
        g.drawString(String.valueOf(beatCounter), i, MusicView.this.OFFSET);
        beatCounter += 4;
      }
    }
  }
}
