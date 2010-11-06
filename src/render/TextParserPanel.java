/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TextParserPanel.java
 *
 * Created on Oct 21, 2010, 8:29:48 PM
 */
package render;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import music.Note;

/**
 *
 * @author Ilya
 */
public class TextParserPanel extends javax.swing.JPanel implements ListSelectionListener, PropertyChangeListener
{

  SeqColumnModel columnModel;
  JTable seqTable;

  /** Creates new form TextParserPanel */
  public TextParserPanel()
  {
    initComponents();
  }

  void setSeqColModel(SeqColumnModel theModel, JTable theTable, String startChords)
  {
    columnModel = theModel;
    seqTable = theTable;
    columnModel.selComboModel.addListSelectionListener(this);

    if (startChords != null) {
      chordTextField.setText(startChords);
      computeButton.doClick();
    }

    transNotePicker.addPropertyChangeListener(this);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getSource() == transNotePicker) {
      Note newNote = (Note) evt.getNewValue();
      columnModel.transposeAllFromSelectedColumn(newNote);
    }
  }

  @Override
  public void valueChanged(ListSelectionEvent e)
  {
    if (isVisible() && (columnModel != null)) {
      this.chordTextField.setText(columnModel.toString());

      int selColumn = columnModel.getSelectedColumn();
      if (selColumn >= 0) {
        this.transNotePicker.setNote(columnModel.getChordDef(selColumn).rootNote);
      }
    }
  }

  @Override
  public void setVisible(boolean visible)
  {
    if (visible && (columnModel != null)) {
      this.chordTextField.setText(columnModel.toString());
    }
    super.setVisible(visible);
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jPanel1 = new javax.swing.JPanel();
    chordTextField = new javax.swing.JTextField();
    computeButton = new javax.swing.JButton();
    transNotePicker = new render.NotePickerAlt();

    setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chord Sequence Editor:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

    jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
    jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Current Sequence:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

    chordTextField.setFont(new java.awt.Font("Monospaced", 0, 18)); // NOI18N
    chordTextField.setToolTipText("<html> <li>Enter a sequence of comma seperated chords  as text below</li><br/> <li>See Chord Editor tab for all valid chord names. (ex. M = Major, m = minor)</li><br/> <li>To specify a custom chord by notes, put the notes in [ ], (ex. [ABC], [D F# G])</li><br/> </html>");
    chordTextField.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        chordTextFieldActionPerformed(evt);
      }
    });

    computeButton.setText("Update Sequence");
    computeButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        computeButtonActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(chordTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGap(134, 134, 134)
        .addComponent(computeButton)
        .addContainerGap(137, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addComponent(chordTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(computeButton))
    );

    transNotePicker.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Transpose:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
          .addGroup(layout.createSequentialGroup()
            .addComponent(transNotePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(317, Short.MAX_VALUE))))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
            .addGap(161, 161, 161))
          .addGroup(layout.createSequentialGroup()
            .addGap(21, 21, 21)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(transNotePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap())
    );
  }// </editor-fold>//GEN-END:initComponents

    private void computeButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_computeButtonActionPerformed
    {//GEN-HEADEREND:event_computeButtonActionPerformed
      String text = chordTextField.getText();
      if (text == null || text.length() == 0) {
        text = "C";
      }

      if (columnModel != null) {
        columnModel.populateFromText(text);
      }
      seqTable.requestFocusInWindow();
}//GEN-LAST:event_computeButtonActionPerformed

    private void chordTextFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_chordTextFieldActionPerformed
    {//GEN-HEADEREND:event_chordTextFieldActionPerformed
      this.computeButton.doClick();
    }//GEN-LAST:event_chordTextFieldActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTextField chordTextField;
  private javax.swing.JButton computeButton;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanel1;
  private render.NotePickerAlt transNotePicker;
  // End of variables declaration//GEN-END:variables
}
