/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RenderBoardHeader.java
 *
 * Created on Oct 25, 2010, 12:32:49 PM
 */
package render;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import music.BassBoard;
import music.BoardRegistry;
import music.BoardRegistry.BoardDef;

/**
 *
 * @author Ilya
 */
public class RenderBoardHeader extends javax.swing.JPanel implements ActionListener
{

  RenderBassBoard renderBoard;
  JScrollPane boardScrollPane;
  SeqColumnModel columnModel;

  /** Creates new form RenderBoardHeader */
  public RenderBoardHeader()
  {
    initComponents();
  }

  public void initBoardHeader(RenderBassBoard renderBoard,
          JScrollPane pane,
          SeqColumnModel model,
          Vector<BoardDef> boards)
  {
    this.renderBoard = renderBoard;
    this.columnModel = model;
    this.boardScrollPane = pane;

    if (boards != null) {
      boardCombo.setModel(new DefaultComboBoxModel(boards));
    } else {
      boardCombo.setModel(new DefaultComboBoxModel(BoardRegistry.mainRegistry().allBoardDefs));
    }

    boardCombo.addActionListener(this);

    // Init to standard 120 bass board
    BoardRegistry.BoardDef initialBoardDef = BoardRegistry.mainRegistry().findByBassCount(120);
    if (initialBoardDef != null) {
      boardCombo.setSelectedItem(initialBoardDef);
    }
  }
  
  public JPanel getExtPanel()
  {
    return this.extPanel;
  }
  
  Component hiddenBox;

  public void toggleOrientation(boolean isHoriz)
  {
    if (isHoriz) {
      setLayout(new FlowLayout());
    } else {
      this.jLabel1.setAlignmentX(CENTER_ALIGNMENT);
      this.boardCombo.setAlignmentX(CENTER_ALIGNMENT);
      this.infoLabel.setAlignmentX(CENTER_ALIGNMENT);
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

      if (hiddenBox == null) {
        hiddenBox = Box.createVerticalStrut(30);
        add(hiddenBox);
      }
    }
  }

  @Override
  public void paintComponent(Graphics g)
  {
    paintGradientBack(this, g);
  }

  private static void paintGradientBack(JComponent comp, Graphics g)
  {
    int height = comp.getHeight();
    Graphics2D g2 = (Graphics2D) g;
    Color lightCol = comp.getBackground();//new Color(53,180,209);
    g2.setPaint(new GradientPaint(0.f, height * 2 / 3, lightCol, 0.f, height, Color.black));
    g2.fillRect(0, 0, comp.getWidth(), comp.getHeight());
  }

  public Component getCornerComp()
  {
    JPanel corner = new JPanel()
    {
      @Override
      public void paintComponent(Graphics g)
      {
        paintGradientBack(this, g);
      }
    };
    corner.setBackground(this.getBackground());
    return corner;
  }

  private void autoSize()
  {
    Container cont = this.getTopLevelAncestor();

    // Autoresize -- horizontal
    if ((cont != null) && cont.isVisible() && (cont instanceof JFrame)) {
      JFrame frame = (JFrame) cont;

      if ((frame.getExtendedState() & JFrame.MAXIMIZED_BOTH) != 0) {
        return;
      }

      int diff = 0;
      if (renderBoard.isHorizontal()) {
        diff = (renderBoard.getPreferredSize().height - renderBoard.getHeight());
      } else {
        diff = (renderBoard.getPreferredSize().width - renderBoard.getWidth());
      }

//      if (!boardScrollPane.isValid() || !renderBoard.isValid()) {
//        return;
//      }

      if (renderBoard.isHorizontal()) {
        renderBoard.setSize(renderBoard.getWidth(), renderBoard.getHeight() + diff);
        frame.setSize(frame.getWidth(), frame.getHeight() + diff);
      } else {
        return;
//        renderBoard.setSize(renderBoard.getWidth() + diff, renderBoard.getHeight());
//        frame.setSize(frame.getWidth() + diff, frame.getHeight());
      }

      //     System.out.println("New RB Height: " + (renderBoard.getHeight()));
      //     System.out.println("New Frame Height: " + (frame.getHeight()));
    }

    //boardScrollPane.revalidate();
  }

  @Override
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == boardCombo) {
      if (renderBoard != null) {
        BoardRegistry.BoardDef def = (BoardRegistry.BoardDef) boardCombo.getSelectedItem();

        BassBoard newBoard = def.createBoard();

        renderBoard.setBassBoard(newBoard);
        boardScrollPane.revalidate();
        //boardScrollPane.doLayout();
        renderBoard.repaint();

        autoSize();

        if (columnModel != null) {
          columnModel.recomputeSeqs();
        }

        String info = "<html>Range: <b>";
        info += newBoard.getMinRootNote().toString(true);
        info += " to ";
        info += newBoard.getMaxRootNote().toString(true);
        info += "</b></html>";
        this.infoLabel.setText(info);
      }
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

    extPanel = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    boardCombo = new javax.swing.JComboBox();
    infoLabel = new javax.swing.JLabel();

    setBackground(java.awt.SystemColor.activeCaption);

    extPanel.setOpaque(false);
    add(extPanel);

    jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getSize()+5f));
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("Current Board:");
    add(jLabel1);

    boardCombo.setFont(boardCombo.getFont().deriveFont(boardCombo.getFont().getStyle() | java.awt.Font.BOLD, boardCombo.getFont().getSize()+5));
    boardCombo.setToolTipText("<html>\nClick and select one of the possible bass board layouts.<br/>\nStandard boards vary in the number of bass and chord rows and which<br/>\nchords are included.<br/>\nThe standard bass layout is 120-basses.<br/>\nThe smallest is usually 8 basses.<br/>\n</html>");
    add(boardCombo);

    infoLabel.setFont(infoLabel.getFont().deriveFont(infoLabel.getFont().getSize()+5f));
    infoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    infoLabel.setText("info");
    infoLabel.setToolTipText("<html>\nThe chord on the left/bottom of the bass side to<br/>\nThe chord on the right/top of the bass side.<br/>\nHigher basses have a wider ranger of chords.<br/>\n72-bass boards and up have chords for all 12 notes\n</html>");
    add(infoLabel);
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox boardCombo;
  private javax.swing.JPanel extPanel;
  private javax.swing.JLabel infoLabel;
  private javax.swing.JLabel jLabel1;
  // End of variables declaration//GEN-END:variables
}
