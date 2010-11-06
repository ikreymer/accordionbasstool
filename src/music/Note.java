package music;

import util.Main;

/**
 * 
 */
public class Note
{

  public final static int NUM_HALFSTEPS = 12;
  public final static int NUM_NOTES = 7;

  enum ScaleNote
  {

    C(0, 0),
    D(1, 2),
    E(2, 4),
    F(3, 5),
    G(4, 7),
    A(5, 9),
    B(6, 11);
    final short halfStep;
    final short scaleDist;

    private ScaleNote(int dist, int hs)
    {
      scaleDist = (short) dist;
      halfStep = (short) hs;
    }

    @Override
    public String toString()
    {

      switch (this) {
        case A:
          return "A";
        case B:
          return "B";
        case C:
          return "C";
        case D:
          return "D";
        case E:
          return "E";
        case F:
          return "F";
        case G:
          return "G";
        default:
          return "A";
      }
    }

    public static ScaleNote fromString(String input)
    {
      if (input.length() < 1) {
        return null;
      }

      int theChar = input.charAt(0);

      switch (theChar) {
        case 'A':
        case 'a':
          return A;

        case 'B':
        case 'b':
          return B;

        case 'C':
        case 'c':
          return C;

        case 'D':
        case 'd':
          return D;

        case 'E':
        case 'e':
          return E;

        case 'F':
        case 'f':
          return F;

        case 'G':
        case 'g':
          return G;

        default:
          return null;
      }
    }
  }
  final ScaleNote note;
  final short sharpsOrFlats;
  short octaveBit = 0;

  //Default note is C
  public Note()
  {
    this(ScaleNote.C, 0);
  }

  Note(ScaleNote n, int sof)
  {
    note = n;
    sharpsOrFlats = (short) sof;
  }

  private int halfStepValue()
  {
    return note.halfStep + sharpsOrFlats;
  }

  public int value()
  {
    return posmod(halfStepValue(), NUM_HALFSTEPS);
  }

  public int getSharpOrFlat()
  {
    return sharpsOrFlats;
  }

  public boolean equals(Note other)
  {
    return (value() == other.value());
  }

  public Interval diff(Note other)
  {
    return new Interval(halfStepValue() - other.halfStepValue(),
            note.scaleDist - other.note.scaleDist, null);
  }

  public Note add(Interval ival)
  {
    ScaleNote[] notes = ScaleNote.values();
    int newHalfStep = halfStepValue() + ival.interval;

    int newScaleNote = posmod(note.scaleDist + ival.scaleDist, NUM_NOTES);
    ScaleNote newNote = notes[newScaleNote];

    return new Note(newNote, signmod(newHalfStep - newNote.halfStep, NUM_HALFSTEPS));
  }

  public Note sub(Interval ival)
  {
    return add(ival.scale(-1));
  }

  public Note eharmonic()
  {
    if (sharpsOrFlats > 0) {
      return this.add(Interval.m2).sub(Interval.halfStep);
    } else if (sharpsOrFlats < 0) {
      return this.sub(Interval.m2).add(Interval.halfStep);
    } else {
      return this;
    }
  }

  public boolean isBassNote()
  {
    return Chord.Mask.isLowerOctaveBit(this.octaveBit);
  }

  static String printNote(ScaleNote note, int sharpsOrFlats, boolean html)
  {
    String base = note.toString();

    if (html && (sharpsOrFlats != 0)) {
      base += "<sup>";
    }

    if (sharpsOrFlats > 0) {
      for (int i = 0; i < sharpsOrFlats; i++) {
        //base += (html ? "\u266F" : "#");
        base += "#";
      }
    } else {
      for (int i = sharpsOrFlats; i < 0; i++) {
        //base += (html ? "\u266D" : "b");
        base += "b";
      }
    }

    if (html && (sharpsOrFlats != 0)) {
      base += "</sup>";
    }


    return base;
  }

  @Override
  public String toString()
  {
    return toString(2, false);
  }

  public String toString(boolean html)
  {
    return toString(2, html);
  }

  private String toString(int maxAccidental, boolean html)
  {
    ScaleNote newNote = note;
    int newSOF = sharpsOrFlats;
    int noteChange = 0;

    do {
      noteChange = 0;

      switch (newNote) {
        case B:
        case E:
          if (newSOF >= 1) {
            newSOF--;
            noteChange++;
          }
          break;

        case C:
        case F:
          if (newSOF <= -1) {
            newSOF++;
            noteChange--;
          }
          break;
      }

      if (noteChange == 0) {
        if (newSOF >= maxAccidental) {
          newSOF -= maxAccidental;
          noteChange++;
        } else if (newSOF <= -maxAccidental) {
          newSOF += maxAccidental;
          noteChange--;
        }
      }

      if (noteChange != 0) {
        newNote = ScaleNote.values()[posmod(newNote.scaleDist + noteChange, NUM_NOTES)];
      }

    } while (noteChange != 0);

    return printNote(newNote, newSOF, html);
  }
  static int lastParserOffset = 0;

  public static Note fromString(Main.StringParser parser)
  {
    Note note = fromString(parser.input());

    if (note != null) {
      parser.incOffset(lastParserOffset);
    }

    return note;
  }

  public static Note fromString(String input)
  {
    ScaleNote scaleNote = ScaleNote.fromString(input);

    if (scaleNote == null) {
      return null;
    }

    int sharpsOrFlats = 0;
    int offset;

    for (offset = 1; offset < input.length(); offset++) {
      int nextChar = input.charAt(offset);
      if ((nextChar == '#') && (sharpsOrFlats >= 0)) {
        sharpsOrFlats++;
      } else if ((nextChar == 'b') && (sharpsOrFlats <= 0)) {
        sharpsOrFlats--;
      } else {
        break;
      }
    }

    lastParserOffset = offset;

    return new Note(scaleNote, sharpsOrFlats);
  }

  // Range of [0, mod-1)
  public static int posmod(int value, int mod)
  {
    int m = value % mod;
    return (m + mod) % mod;
  }

  // Range of [-mod/2, mod/2)
  public static int signmod(int value, int mod)
  {
    int m = value % mod;
    return m - (m / (mod / 2)) * mod;
  }
}
