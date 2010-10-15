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

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import music.BoardSearcher;
import music.ButtonComboSequence;
import music.Chord;

/**
 *
 * @author Ilya
 */
public class TabCommonChords extends javax.swing.JPanel
{

  SeqDataModel dataModel = new SeqDataModel();
  SeqColumnModel columnModel = new SeqColumnModel();

  /** Creates new form TabCommonChords */
  public TabCommonChords()
  {
    initComponents();
    chordPicker = new ChordPicker(util.Main._rootFrame, true);
    initTable();
  }
  
  ChordPicker chordPicker;

  class SeqColumnModel extends DefaultTableColumnModel
  {

    SeqColumnModel()
    {
      super();
      this.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    void addColumn(int index)
    {
      ChordDef def = chordPicker.showChordPicker(null);
      addColumn(def, index);
    }

    void addColumn(ChordDef def, int index)
    {
      if (def == null) {
        return;
      }

      int lastColIndex = getColumnCount();

      if (index < 0) {
        index = lastColIndex;
      }

      TableColumn column = new TableColumn(lastColIndex, 120, null, null);
      column.setHeaderValue(def);
      addColumn(column);

      if (index < lastColIndex) {
        moveColumn(lastColIndex, index);
      } else {
        computeSeqs(index);
      }
    }

    @Override
    public void moveColumn(int columnIndex, int newIndex)
    {
      super.moveColumn(columnIndex, newIndex);
      syncModelToView(Math.min(columnIndex, newIndex));
    }

    void removeSelectedColumn()
    {
      if (getSelectedColumnCount() < 1) {
        return;
      }

      if (getColumnCount() < 2) {
        return;
      }

      int index = getSelectedColumns()[0];
      removeColumn(getColumn(index));

      if (index < getColumnCount()) {
        syncModelToView(index);
      } else {
        computeSeqs(index - 1);
      }
    }

    private void syncModelToView(int index)
    {
      for (int i = index; i < getColumnCount(); i++) {
        this.getColumn(i).setModelIndex(i);
      }

      computeSeqs(index);
    }

    void editColumn(int index)
    {
      if ((index < 0) || (index > getColumnCount())) {
        return;
      }

      TableColumn column = this.getColumn(index);
      ChordDef def = (ChordDef) column.getHeaderValue();

      def = chordPicker.showChordPicker(def);
      if (def != null) {
        column.setHeaderValue(def);
        //TODO: selection refix?
        computeSeqs(index);
        //dataModel.fireTableDataChanged();
      }
    }

    ChordDef getChordDef(int index)
    {
      assert ((index >= 0) && (index < getColumnCount()));
      return (ChordDef) getColumn(index).getHeaderValue();
    }

    Vector<Chord> getAllChords()
    {
      Vector<Chord> vec = new Vector<Chord>();

      for (int i = 0; i < getColumnCount(); i++) {
        vec.add(getChordDef(i).getChord());
      }

      return vec;
    }

    ButtonComboSequence allComboSeqs[];
    BoardSearcher searcher = new BoardSearcher();
    music.BassBoard currBassBoard = null;

    void computeSeqs(int colIndex)
    {
      RenderBassBoard renderBoard = render.BassToolFrame.getRenderBoard();
      if (renderBoard == null)
        return;

      currBassBoard = renderBoard.getBassBoard();

      allComboSeqs =
              searcher.parseSequence(
              currBassBoard,
              columnModel.getAllChords());

      dataModel.fireTableStructureChanged();

      getSelectionModel().setSelectionInterval(colIndex, colIndex);
      if (seqTable.getRowCount() > 0) {
        seqTable.setRowSelectionInterval(0, 0);
      }

      //assert (allComboSeqs.length == columnModel.getColumnCount());
    }
  }

  class SeqDataModel extends AbstractTableModel
  {

    BoardSearcher searcher = new BoardSearcher();

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
      return String.class;
    }

    @Override
    public String getColumnName(int column)
    {
      return columnModel.getChordDef(column).getAbbrevHtml();
    }

    public SeqDataModel()
    {
    }

    @Override
    public int getColumnCount()
    {
      return columnModel.getColumnCount();
    }

    @Override
    public int getRowCount()
    {
      if (columnModel.allComboSeqs != null) {
        return columnModel.allComboSeqs.length;
      } else {
        return 0;
      }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
      music.ButtonCombo combo =
              columnModel.allComboSeqs[rowIndex].getCombo(columnIndex);

      String info = columnModel.allComboSeqs[rowIndex].debugForceHeur() + ": ";
      info += combo.toString();
      return info;
    }
  }

  class TableSelectionHandler implements ListSelectionListener
  {

    @Override
    public void valueChanged(ListSelectionEvent e)
    {
      RenderBassBoard renderBoard =
              render.BassToolFrame.getRenderBoard();

      if (renderBoard == null)
        return;

      if (e.getSource() == columnModel.getSelectionModel()) {
        int index = seqTable.getSelectedColumn();
        renderBoard.setSelectedSeqCombo(index);
        renderBoard.repaint();
      } else if (e.getSource() == seqTable.getSelectionModel()) {
        int index = seqTable.getSelectedRow();
        if (index >= 0) {
          renderBoard.setSelectedSeq(columnModel.allComboSeqs[index]);
        }
        renderBoard.repaint();
      }
    }
  }

  private void initTable()
  {
    tableScrollPane.setBorder(BorderFactory.createEmptyBorder());

    seqTable.setDefaultRenderer(String.class, new CellRenderer());

    TableSelectionHandler selHandler = new TableSelectionHandler();
    seqTable.getSelectionModel().addListSelectionListener(selHandler);
    columnModel.getSelectionModel().addListSelectionListener(selHandler);

    JTableHeader header = seqTable.getTableHeader();
    header.setResizingAllowed(false);
    header.setReorderingAllowed(true);

    MouseAdapter headerMouse = new HeaderMouseInputHandler();
    header.addMouseListener(headerMouse);
    header.addMouseMotionListener(headerMouse);

    header.setDefaultRenderer(new HeaderRenderer(header));

//    dataModel.addTableModelListener(new TableModelListener()
//    {
//      @Override
//      public void tableChanged(TableModelEvent e)
//      {
//        seqTable.setRowSelectionInterval(0, 0);
//        seqTable.setColumnSelectionInterval(0, 0);
//      }
//    });


    columnModel.getSelectionModel().addListSelectionListener(
            new javax.swing.event.ListSelectionListener()
            {

              @Override
              public void valueChanged(ListSelectionEvent e)
              {
                seqTable.getTableHeader().repaint();
              }
            });

    columnModel.addColumn(chordPicker.getDefaultChordDef(), 0);
  }

  class HeaderMouseInputHandler extends MouseAdapter
  {

    private void updateColumn(MouseEvent e)
    {
      JTableHeader h = (JTableHeader) e.getSource();
      int column = h.columnAtPoint(e.getPoint());

      if (column < 0) {
        return;
      }

      h.getTable().setColumnSelectionInterval(column, column);

    }

    @Override
    public void mousePressed(MouseEvent e)
    {
      updateColumn(e);
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
      updateColumn(e);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
      if (e.getClickCount() > 1) {
        JTableHeader h = (JTableHeader) e.getSource();
        int column = h.columnAtPoint(e.getPoint());

        columnModel.editColumn(column);
      }
    }
  }

  class HeaderRenderer extends DefaultTableCellRenderer
  {

    Font plain, bold;
    Color defColor, selColor;
    TableCellRenderer defHeader;
    JTableHeader header;
    Border lowered, raised;

    HeaderRenderer(JTableHeader head)
    {
      header = head;
      initUI();
    }

    private void initUI()
    {
      if (header == null) {
        return;
      }

      defHeader = header.getDefaultRenderer();
      defColor = header.getBackground();
      selColor = defColor.darker();
      lowered = BorderFactory.createLoweredBevelBorder();
      raised = BorderFactory.createRaisedBevelBorder();

      Font font = header.getFont().deriveFont(18.f);
      plain = font.deriveFont(Font.PLAIN);
      bold = font.deriveFont(Font.BOLD);

      this.setHorizontalAlignment(CENTER);

      //header.setPreferredSize(new Dimension(10, 50));
    }

    @Override
    public void updateUI()
    {
      super.updateUI();
      initUI();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
      ChordDef chordDef = (ChordDef) value;

      //Component comp = super.getTableCellRendererComponent(table, chordDef.getAbbrevHtml(), isSelected, hasFocus, row, column);
      //assert (comp == this);
      String info = "<html>" + chordDef.abbrevHtml + "</html>";

      String statusInfo = "<html><b>" + chordDef.getName() + "</b><br/>"
              + "(" + chordDef.getChord().toString("-", true) + ")</html>";

      this.setText(info);
      this.setToolTipText(statusInfo);

      if (table.isColumnSelected(column)) {
        this.setFont(bold);
        this.setBackground(selColor);
        this.setBorder(lowered);
      } else {
        this.setFont(plain);
        this.setBackground(defColor);
        this.setBorder(raised);
      }

      return this;
    }
  }

  class CellRenderer extends DefaultTableCellRenderer
  {

    Color defSelColor;
    Color defPlainColor;
    Border highliteBorder;
    Border stdBorder;

    CellRenderer()
    {
      initUI();
    }

    private void initUI()
    {
      highliteBorder = new CompoundBorder(
              new LineBorder(Color.black, 2),
              new EmptyBorder(2, 2, 2, 2));

      stdBorder = new EmptyBorder(4, 4, 4, 4);
      //stdBorder = BorderFactory.createMatteBorder(1, 1, 0, 0, Color.black);

      defSelColor = super.getTableCellRendererComponent(seqTable, "", true, false, 0, 0).getBackground();
      defPlainColor = super.getTableCellRendererComponent(seqTable, "", false, false, 0, 0).getBackground();
    }

    @Override
    public void updateUI()
    {
      super.updateUI();
      initUI();
      seqTable.repaint();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
      Component defRendComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      JComponent jcomp = (JComponent) defRendComp;
      assert (jcomp == this);


      if (isSelected) {
        // See if this is the anchor sell even if it doesn't have focus
        if (!hasFocus) {
          int r = table.getSelectionModel().getAnchorSelectionIndex();
          int c = table.getColumnModel().getSelectionModel().getAnchorSelectionIndex();
          hasFocus = (r == row) && (c == column);
        }

        if (hasFocus) {
          setBorder(highliteBorder);
          jcomp.setBackground(defSelColor.darker());
        } else {
          jcomp.setBackground(defSelColor);
          jcomp.setBorder(stdBorder);
        }
      } else {
        jcomp.setBackground(defPlainColor);
        jcomp.setBorder(stdBorder);
      }

      return jcomp;
    }
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
    seqTable.setModel(dataModel);
    seqTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
    seqTable.setRowHeight(25);
    seqTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    tableScrollPane.setViewportView(seqTable);
    seqTable.setColumnModel(columnModel);

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
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(454, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
            .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap())
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
      columnModel.editColumn(seqTable.getSelectedColumn());
}//GEN-LAST:event_actionEditColumnChord

    private void butAddChordActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_butAddChordActionPerformed
    {//GEN-HEADEREND:event_butAddChordActionPerformed
      columnModel.addColumn(columnModel.getColumnCount());
}//GEN-LAST:event_butAddChordActionPerformed

    private void butInsertChordActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_butInsertChordActionPerformed
    {//GEN-HEADEREND:event_butInsertChordActionPerformed
      columnModel.addColumn(seqTable.getSelectedColumn());
}//GEN-LAST:event_butInsertChordActionPerformed

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
