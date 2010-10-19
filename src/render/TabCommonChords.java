/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TabCommonChords.java
 *
 * Created on Mar 9, 2010, 3:35:47 PM
 */
package render;

/**
 *
 * @author Ilya
 */
public class TabCommonChords extends javax.swing.JPanel
{

  SeqColumnModel columnModel;
  SeqViewerController seqViewer;

  /** Creates new form TabCommonChords */
  public TabCommonChords()
  {
    initComponents();
    //ChordPickerDialog chordPicker = new ChordPickerDialog(util.Main._rootFrame, true);
    seqViewer = new SeqViewerController(seqTable, tableScrollPane);
    columnModel = seqViewer.columnModel;
  }

  

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    butSetChord = new javax.swing.JButton();
    butAddChord = new javax.swing.JButton();
    butRemoveChord = new javax.swing.JButton();
    butInsertChord = new javax.swing.JButton();
    optionsBut = new javax.swing.JButton();
    tableScrollPane = new javax.swing.JScrollPane();
    seqTable = new javax.swing.JTable();

    butSetChord.setText("Set Chord...");
    butSetChord.setFocusable(false);
    butSetChord.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    butSetChord.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    butSetChord.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        actionEditColumnChord(evt);
      }
    });

    butAddChord.setText("Add Chord...");
    butAddChord.setFocusable(false);
    butAddChord.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    butAddChord.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    butAddChord.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        butAddChordActionPerformed(evt);
      }
    });

    butRemoveChord.setText("Remove Chord");
    butRemoveChord.setFocusable(false);
    butRemoveChord.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    butRemoveChord.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    butRemoveChord.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        butRemoveChordActionPerformed(evt);
      }
    });

    butInsertChord.setText("Insert Chord...");
    butInsertChord.setFocusable(false);
    butInsertChord.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    butInsertChord.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    butInsertChord.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        butInsertChordActionPerformed(evt);
      }
    });

    optionsBut.setText("Options...");
    optionsBut.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        optionsButActionPerformed(evt);
      }
    });

    seqTable.setAutoCreateColumnsFromModel(false);
    seqTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    seqTable.setRowHeight(25);
    seqTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    tableScrollPane.setViewportView(seqTable);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(optionsBut)
          .addComponent(butSetChord)
          .addComponent(butAddChord)
          .addComponent(butInsertChord)
          .addComponent(butRemoveChord))
        .addGap(18, 18, 18)
        .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
            .addGap(17, 17, 17))
          .addGroup(layout.createSequentialGroup()
            .addComponent(butSetChord)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(butAddChord)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(butInsertChord)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(butRemoveChord)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
            .addComponent(optionsBut)
            .addGap(24, 24, 24))))
    );
  }// </editor-fold>//GEN-END:initComponents

    private void optionsButActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_optionsButActionPerformed
    {//GEN-HEADEREND:event_optionsButActionPerformed
      new render.OptionsDialog(null, false).setVisible(true);
    }//GEN-LAST:event_optionsButActionPerformed

    private void actionEditColumnChord(java.awt.event.ActionEvent evt)//GEN-FIRST:event_actionEditColumnChord
    {//GEN-HEADEREND:event_actionEditColumnChord
      //columnModel.editColumn(seqTable.getSelectedColumn());
}//GEN-LAST:event_actionEditColumnChord

    private void butAddChordActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_butAddChordActionPerformed
    {//GEN-HEADEREND:event_butAddChordActionPerformed
      //columnModel.addColumn(columnModel.getColumnCount());
}//GEN-LAST:event_butAddChordActionPerformed

    private void butInsertChordActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_butInsertChordActionPerformed
    {//GEN-HEADEREND:event_butInsertChordActionPerformed
      //columnModel.addColumn(seqTable.getSelectedColumn());
}//GEN-LAST:event_butInsertChordActionPerformed

    private void butRemoveChordActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_butRemoveChordActionPerformed
    {//GEN-HEADEREND:event_butRemoveChordActionPerformed
      columnModel.removeSelectedColumn();
    }//GEN-LAST:event_butRemoveChordActionPerformed
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton butAddChord;
  private javax.swing.JButton butInsertChord;
  private javax.swing.JButton butRemoveChord;
  private javax.swing.JButton butSetChord;
  private javax.swing.JButton optionsBut;
  private javax.swing.JTable seqTable;
  private javax.swing.JScrollPane tableScrollPane;
  // End of variables declaration//GEN-END:variables
}
