/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NoteChordSelector.java
 *
 * Created on Jan 8, 2011, 3:47:22 PM
 */
package render;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import music.core.Chord;
import music.core.Interval;
import music.core.Note;

/**
 *
 * @author Ilya
 */
public class NoteChordSelector extends javax.swing.JPanel implements ActionListener
{

  JToggleButton noteButtons[][];
  //JToolBar toolbar[];

  /** Creates new form NoteChordSelector */
  public NoteChordSelector()
  {
    initComponents();

    JToolBar toolbar[] = {tool2, tool1};

    noteButtons = new JToggleButton[2][];

    for (int j = 0; j < 2; j++) {
      //toolbar[j] = new JToolBar();
      toolbar[j].setFloatable(false);
      toolbar[j].setBorderPainted(false);

      noteButtons[j] = new JToggleButton[Note.NUM_HALFSTEPS];
      Note note = new Note();

      for (int i = 0; i < Note.NUM_HALFSTEPS; i++) {

        String str = note.toString();
        
        JToggleButton newButton = new JToggleButton(str);
        newButton.setFont(labelMid.getFont().deriveFont(Font.BOLD));
        newButton.setFocusPainted(false);
        newButton.addActionListener(this);
        //newButton.setActionCommand(toolbar[j].getName() + str);

        toolbar[j].add(newButton);
        noteButtons[j][i] = newButton;

        note = note.add(Interval.halfStep).normalize(2);
      }

      //this.add(toolbar[j]);
    }
  }

  private void nameButtons()
  {
    for (int j = 0; j < 2; j++) {
      Note note = new Note();

      if (flatBut.isSelected()) {
        for (int i = Note.NUM_HALFSTEPS - 1; i >= 0; i--) {
          note = note.sub(Interval.halfStep).normalize(2);
          noteButtons[j][i].setText(note.toString());
        }
      } else {
        for (int i = 0; i < Note.NUM_HALFSTEPS; i++) {
          noteButtons[j][i].setText(note.toString());
          note = note.add(Interval.halfStep).normalize(2);
        }
      }
    }
  }

  public void setChord(Chord.Mask mask)
  {
    for (int j = 0; j < 2; j++) {
      for (int i = 0; i < Note.NUM_HALFSTEPS; i++) {
        int bit = i + (j * Note.NUM_HALFSTEPS);
        noteButtons[j][i].setSelected(mask.contains(bit));
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    boolean found = false;

    for (int j = 0; j < 2; j++) {
      for (int i = 0; i < Note.NUM_HALFSTEPS; i++) {
        if (e.getSource() == noteButtons[j][i]) {

          if (noteButtons[j][i].isSelected()) {
            if (j == 0) {
              noteButtons[1][i].setSelected(true);
            }
          } else {
            if (j == 1) {
              noteButtons[0][i].setSelected(false);
            }
          }

          found = true;
          break;
        }
      }
      if (found) {
        break;
      }
    }

    if (found) {
      Chord chord = buildChord();
      this.firePropertyChange("Chord", null, chord);
    }
  }

  public Chord buildChord()
  {
    Vector<Note> notes = new Vector<Note>();

    for (int j = 0; j < 2; j++) {
      for (int i = 0; i < Note.NUM_HALFSTEPS; i++) {
        if (noteButtons[j][i].isSelected()) {
          notes.add(Note.fromString(noteButtons[j][i].getText()));
        }
      }
    }

    if (notes.isEmpty()) {
      return null;
    }

    return new Chord(notes);
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    sharpFlatGroup = new javax.swing.ButtonGroup();
    labelMid = new javax.swing.JLabel();
    labelBass = new javax.swing.JLabel();
    tool1 = new javax.swing.JToolBar();
    tool2 = new javax.swing.JToolBar();
    sharpBut = new javax.swing.JToggleButton();
    flatBut = new javax.swing.JToggleButton();

    labelMid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    labelMid.setText("Mid Notes:");

    labelBass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    labelBass.setText("Bass Notes:");

    tool1.setRollover(true);
    tool1.setName("mid"); // NOI18N

    tool2.setRollover(true);
    tool2.setName("bass"); // NOI18N

    sharpFlatGroup.add(sharpBut);
    sharpBut.setSelected(true);
    sharpBut.setText("#");
    sharpBut.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        nameButtons(evt);
      }
    });

    sharpFlatGroup.add(flatBut);
    flatBut.setText("b");
    flatBut.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        nameButtons(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelMid)
          .addComponent(labelBass))
        .addGap(10, 10, 10)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
          .addComponent(tool2, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
          .addComponent(tool1, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(sharpBut)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(flatBut)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(tool1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(flatBut)
              .addComponent(sharpBut)))
          .addComponent(labelMid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(tool2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(labelBass, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)))
    );
  }// </editor-fold>//GEN-END:initComponents

  private void nameButtons(java.awt.event.ActionEvent evt)//GEN-FIRST:event_nameButtons
  {//GEN-HEADEREND:event_nameButtons
    nameButtons();
  }//GEN-LAST:event_nameButtons

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JToggleButton flatBut;
  private javax.swing.JLabel labelBass;
  private javax.swing.JLabel labelMid;
  private javax.swing.JToggleButton sharpBut;
  private javax.swing.ButtonGroup sharpFlatGroup;
  private javax.swing.JToolBar tool1;
  private javax.swing.JToolBar tool2;
  // End of variables declaration//GEN-END:variables
}
