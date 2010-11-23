package music;

import java.util.Vector;


public class ChordParser
{

  public static Chord parseNoteList(StringParser parser)
  {
    Vector<Note> notes = new Vector<Note>();
    Note newNote;

    while ((newNote = Note.fromString(parser)) != null) {
      notes.add(newNote);
      parser.skipWhiteSpace();
    }

    Note[] noteArray = new Note[notes.size()];
    return new Chord(notes.toArray(noteArray));
  }

  public static Vector<ParsedChordDef> parseChords(StringParser parser)
  {
    Vector<ParsedChordDef> chordVec = new Vector<ParsedChordDef>();

    while (!parser.isDone()) {
      chordVec.add(parse(parser));

      parser.skipThrough(", -+");
    }

    return chordVec;
  }

  public static ParsedChordDef parse(StringParser parser)
  {
    // If starting with [ then we have a list of notes
    // so parse them individually
    if (parser.input().startsWith("[")) {
      parser.incOffset(1);
      Chord chord = parseNoteList(parser);
      if (parser.input().startsWith("]")) {
        parser.incOffset(1);
      }
      return new ParsedChordDef(chord);
    }

    // Otherwise, parse the chord name
    Note rootNote = Note.fromString(parser);

    // Convert invalid note to a default C
    if (rootNote == null) {
      rootNote = new Note();
    }
    //Vector<Interval> ivals = new Vector<Interval>();

    String input = parser.input();

    // Single note
    if (input.length() == 0) {
      return new ParsedChordDef(rootNote);
    }

    RegistryChordDef result = ChordRegistry.mainRegistry().findChord(ChordRegistry.ALL_CHORDS, parser);

    // Additional Bass, non-root

    if (parser.nextChar() == '\\') {
      parser.incOffset(1);

      Note bassNote = Note.fromString(parser);

      //return new Chord(rootNote, result.ivals, bassNote, true);
      return new ParsedChordDef(rootNote, bassNote, result, false);
    }

    // Additional Bass, root

    if (parser.nextChar() == '/') {
      parser.incOffset(1);

      Note bassNote = Note.fromString(parser);

      //return new Chord(rootNote, result.ivals, bassNote, false);
      return new ParsedChordDef(rootNote, bassNote, result, true);
    }

    //return new Chord(rootNote, result.ivals);
    return new ParsedChordDef(rootNote, null, result, false);
  }
}
