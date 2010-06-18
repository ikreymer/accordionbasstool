/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TabCustomBass.java
 *
 * Created on Apr 21, 2010, 12:46:12 PM
 */

package render;

import java.util.Vector;
import music.Chord;
import music.ChordParser;
import util.Main;
import util.Main.StringParser;

/**
 *
 * @author Ilya
 */
public class TabCustomBass extends javax.swing.JPanel {

    /** Creates new form TabCustomBass */
    public TabCustomBass() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    chordTextTP = new javax.swing.JPanel();
    chordTextSP = new javax.swing.JScrollPane();
    chordTextArea = new javax.swing.JTextArea();
    computeButton = new javax.swing.JButton();

    chordTextTP.setBorder(javax.swing.BorderFactory.createTitledBorder("Enter a Chord/Bass Sequence:"));

    chordTextArea.setColumns(20);
    chordTextArea.setRows(5);
    chordTextArea.setText("[C], [D], [E], [F], [G], [A], [B], [C]");
    chordTextSP.setViewportView(chordTextArea);

    javax.swing.GroupLayout chordTextTPLayout = new javax.swing.GroupLayout(chordTextTP);
    chordTextTP.setLayout(chordTextTPLayout);
    chordTextTPLayout.setHorizontalGroup(
      chordTextTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(chordTextSP, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
    );
    chordTextTPLayout.setVerticalGroup(
      chordTextTPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(chordTextSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );

    computeButton.setText("Compute Optimal Bass Sequences");
    computeButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        computeButtonActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(chordTextTP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addGap(119, 119, 119)
            .addComponent(computeButton)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(chordTextTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(computeButton)
        .addContainerGap(49, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

    private void computeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_computeButtonActionPerformed
      // TODO add your handling code here:
      //TODO: err check
      String text = chordTextArea.getText();
      Vector<Chord> currChords = ChordParser.parseChords(new StringParser(text));
      Main._mainBoardPanel.computeOptimalCombosFromChords(currChords);
    }//GEN-LAST:event_computeButtonActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JTextArea chordTextArea;
  private javax.swing.JScrollPane chordTextSP;
  private javax.swing.JPanel chordTextTP;
  private javax.swing.JButton computeButton;
  // End of variables declaration//GEN-END:variables

  // Custom Init
  void customInit()
  {
    // For Debug
    String t = "[C], [D], [E], [F], [G], [A], [B], [C]";
		chordTextArea.setText(t);
  }
}
