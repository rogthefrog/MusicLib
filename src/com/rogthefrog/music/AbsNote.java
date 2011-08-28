/**
 * @author roger
 * @enum AbsNote
 * abstract representation of a note
 */
package com.rogthefrog.music;

public enum AbsNote {
  C       (0,   "C",  "do"),
  
  C_SHARP (1,   "C#", "do #"),
  D_FLAT  (1,   "Db", "re b"),
  
  D       (2,   "D",  "re"),
  
  D_SHARP (3,   "D",  "re #"),
  E_FLAT  (3,   "D",  "mi b"),
  
  E       (4,   "E",  "mi"),
  
  F       (5,   "F",  "fa"),
  E_SHARP (5,   "E#", "mi #"),
  
  F_SHARP (6,   "F#", "fa #"),
  G_FLAT  (6,   "Gb", "sol b",  "so b"),
  
  G       (7,   "G",  "sol",    "so"),

  G_SHARP (8,   "G#", "sol #",  "so #"),
  A_FLAT  (8,   "Ab", "la b"),
  
  A       (9,   "A",  "la"),

  A_SHARP (10,  "A#", "la #"),
  B_FLAT  (10,  "Bb", "si b",   "ti b"),
  
  B       (11,  "B", "si",      "ti"),
  C_FLAT  (11,  "Cb", "do b"), 

  B_SHARP (0,   "B#", "si #",   "ti #");


  public final int pitch; // offset from C in semitones
  public final String name; // A, B, etc.
  public final String stdName; // la, si, etc
  public final String englishName; // la, ti, etc
  
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
  
  public int getPitch() {
    return pitch;
  }
}