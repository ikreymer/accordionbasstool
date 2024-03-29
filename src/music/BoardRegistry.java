/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package music;

import music.core.Note;
import java.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import app.Main;

/**
 *
 * @author Ilya
 */
public class BoardRegistry
{

  public class BoardDef
  {

    public final String name;
    public final String desc;
    public final Note middleNote;
    public final int numCols;
    public final String[] rowLayout;

    BoardDef(Element root)
    {
      name = root.getAttribute("name");
      desc = root.getAttribute("desc");

      Note note = Note.fromString(root.getAttribute("middleNote"));
      if (note == null) {
        note = new Note();
      }
      middleNote = note;

      int cols = 1;
      try {
        cols = Integer.parseInt(root.getAttribute("numColumns"));
      } catch (NumberFormatException n) {
        cols = 1;
      }
      numCols = cols;

      NodeList rows = root.getElementsByTagName("row");

      rowLayout = new String[rows.getLength()];

      for (int i = 0; i < rowLayout.length; i++) {
        Element row = (Element) rows.item(i);
        rowLayout[i] = row.getAttribute("name");
      }
    }

    public BassBoard createBoard()
    {
      return new BassBoard(rowLayout, middleNote, numCols);
    }

    @Override
    public String toString()
    {
      String str = "";
      if (name.length() > 0) {
        str += "(" + name + ") ";
      }
      str += numBasses() + " Bass (" + numCols + " X " + rowLayout.length + ")";
      return str;
    }

    public int numBasses()
    {
      return rowLayout.length * numCols;
    }
  }
  private static BoardRegistry _main;

  public static BoardRegistry mainRegistry()
  {
    if (_main == null) {
      _main = new BoardRegistry("boarddefs.xml");
    }

    return _main;
  }
  public final BoardDef allBoardDefs[];

  BoardRegistry(String filename)
  {
    Document doc = Main.loadXmlFile(filename, "./xml/");

    if (doc == null) {
      allBoardDefs = new BoardDef[0];
      return;
    }

    Element root = doc.getDocumentElement();
    NodeList nodes;


    BoardRow.loadRows(root);

    nodes = root.getElementsByTagName("boards");

    if (nodes.getLength() == 0) {
      allBoardDefs = new BoardDef[0];
      return;
    }

    nodes = ((Element) nodes.item(0)).getElementsByTagName("board");

    allBoardDefs = new BoardDef[nodes.getLength()];

    for (int i = 0; i < allBoardDefs.length; i++) {
      allBoardDefs[i] = new BoardDef((Element) nodes.item(i));
    }
  }

  public BoardDef findByBassCount(int num)
  {
    for (BoardDef def : allBoardDefs) {
      if (def.numBasses() == num) {
        return def;
      }
    }

    return null;
  }

  public BoardDef findByDims(int rows, int cols)
  {
    for (BoardDef def : allBoardDefs) {
      if ((def.numCols == cols) && (def.rowLayout.length == rows)) {
        return def;
      }
    }

    return null;
  }

  public Vector<BoardDef> getStandardBoards()
  {
    Vector<BoardDef> standardBoards = new Vector<BoardDef>();

    for (BoardDef def : allBoardDefs) {
      if (def.name.isEmpty()) {
        standardBoards.add(def);
      }
    }

    return standardBoards;
  }
}
