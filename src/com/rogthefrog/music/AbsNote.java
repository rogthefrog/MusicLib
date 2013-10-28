/**
 * @author roger
 * @enum AbsNote
 * abstract representation of a note
 */
package com.rogthefrog.music;

public enum AbsNote {
  A       (0,   "A",  "la"),

  A_SHARP (1,  "A#", "la #"),
  B_FLAT  (1,  "Bb", "si b",   "ti b"),
  
  B       (2,  "B", "si",      "ti"),
  C_FLAT  (2,  "Cb", "do b"), 

  B_SHARP (3,   "B#", "si #",   "ti #"),

  C       (3,   "C",  "do"),
  
  C_SHARP (4,   "C#", "do #"),
  D_FLAT  (4,   "Db", "re b"),
  
  D       (5,   "D",  "re"),
  
  D_SHARP (6,   "D",  "re #"),
  E_FLAT  (6,   "D",  "mi b"),
  
  E       (7,   "E",  "mi"),
  
  F       (8,   "F",  "fa"),
  E_SHARP (8,   "E#", "mi #"),
  
  F_SHARP (9,   "F#", "fa #"),
  G_FLAT  (9,   "Gb", "sol b",  "so b"),
  
  G       (10,   "G",  "sol",    "so"),

  G_SHARP (11,   "G#", "sol #",  "so #"),
  A_FLAT  (11,   "Ab", "la b");

  public final int pitch; // offset from A in semitones
  public final String name; // A, B, etc.
  public final String stdName; // la, si, etc
  public final String englishName; // la, ti, etc
  
  public static final AbsNote[] namesFromPitch = new AbsNote[] {
      AbsNote.A,
      AbsNote.A_SHARP,
      AbsNote.B,
      AbsNote.C, 
      AbsNote.C_SHARP,
      AbsNote.D,
      AbsNote.D_SHARP,
      AbsNote.E,
      AbsNote.F,
      AbsNote.F_SHARP,
      AbsNote.G,
      AbsNote.G_SHARP
  };
  
  enum NameStyle {
    SHORT, STANDARD, ENGLISH
  };
  
  protected static NameStyle nameStyle = NameStyle.SHORT; 
  
  AbsNote(int val, String nm, String snm) {
    pitch       = val;
    name        = nm;
    stdName     = snm;
    englishName = snm;
  }
  
  AbsNote(int val, String nm, String snm, String enm) {
    pitch       = val;
    name        = nm;
    stdName     = snm;
    englishName = enm;
  }

  public static AbsNote fromPitch(int p) {
    return namesFromPitch[wrapPitch(p)];
  }
  
  /**
   * given a pitch outside of 0 - 11, maps it to the right pitch
   * @param p pitch
   * @return wrapped pitch
   */
  public static int wrapPitch(int p) {
    int mod = p % Music.SEMITONES_IN_OCTAVE;
    return (mod < 0) ? Music.SEMITONES_IN_OCTAVE + mod : mod;
  }
  
  public int pitch() {
    return pitch;
  }
  
  @Override
  public String toString() {
    String n = "";
    switch(nameStyle) {
      case STANDARD:
        n = stdName;
        break;
      case ENGLISH:
        n = englishName;
        break;
      case SHORT:
      default:
        n = name;
    }
    return n;
  }
  
  public String getName() {
    return name;
  }
  
  public String getStdName() {
    return stdName;
  }
  
  public String getEnglishName() {
    return englishName;
  }
  
  /**
   * sets which version of the name is used
   * @param style
   * @return note name
   */
  public static NameStyle setDefaultNameStyle(NameStyle style) {
    nameStyle = style;
    return style;
  }
  
  public boolean equals(AbsNote that) {
    return (this.pitch == that.getPitch());
  }
  
  /**
   * compares a note to another
   * @param that other AbsNote to compare
   * @return true if this note is lower than that
   */
  public boolean isLowerThan(AbsNote that) {
    return (this.pitch < that.getPitch());
  }

  public int getPitch() {
    return pitch;
  }
  
}