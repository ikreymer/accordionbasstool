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
public class TabCommonChords extends javax.swing.JPanel {

    /** Creates new form TabCommonChords */
    public TabCommonChords() {
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

    noteModGroup = new javax.swing.ButtonGroup();
    jPanel1 = new javax.swing.JPanel();
    rootCombo = new javax.swing.JComboBox();
    radioNatural = new javax.swing.JRadioButton();
    radioSharp = new javax.swing.JRadioButton();
    radioFlat = new javax.swing.JRadioButton();
    jPanel2 = new javax.swing.JPanel();
    jComboBox1 = new javax.swing.JComboBox();

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Root Note:"));

    rootCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "E", "F", "G" }));
    rootCombo.setSelectedIndex(2);

    noteModGroup.add(radioNatural);
    radioNatural.setText("Natural");
    radioNatural.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        radioNaturalActionPerformed(evt);
      }
    });

    noteModGroup.add(radioSharp);
    radioSharp.setText("Sharp");

    noteModGroup.add(radioFlat);
    radioFlat.setText("Flat");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(rootCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(radioFlat)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
              .addComponent(radioSharp)
              .addGap(61, 61, 61)))
          .addComponent(radioNatural))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(24, 24, 24)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(rootCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(radioNatural)))
          .addComponent(radioSharp, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(2, 2, 2)
        .addComponent(radioFlat, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(39, Short.MAX_VALUE))
    );

    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Select Chord:"));

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jComboBox1, 0, 158, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(80, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(44, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
          .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap(42, Short.MAX_VALUE))
    );
  }// </editor-fold>//GEN-END:initComponents

    private void radioNaturalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioNaturalActionPerformed
      // TODO add your handling code here:
    }//GEN-LAST:event_radioNaturalActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox jComboBox1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.ButtonGroup noteModGroup;
  private javax.swing.JRadioButton radioFlat;
  private javax.swing.JRadioButton radioNatural;
  private javax.swing.JRadioButton radioSharp;
  private javax.swing.JComboBox rootCombo;
  // End of variables declaration//GEN-END:variables

}
