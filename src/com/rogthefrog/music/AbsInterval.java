/**
 * @author roger
 * @enum AbsInterval
 * abstract representation of an interval
 */
package com.rogthefrog.music;

public enum AbsInterval {
  ROOT      (0, "root",           "Root"),
  UNISON    (0, "unison",         "Un."),
  SEMITONE  (1, "semitone",       "1/2T"),
  MIN_2     (1, "minor 2nd",      "m2"),
  MAJ_2     (2, "major 2nd",      "M2"),
  WHOLETONE (2, "whole tone",     "1T"),
  MIN_3     (3, "minor 3rd",      "m3"),
  MAJ_3     (4, "major 3rd",      "M3"),
  PERFECT_4 (5, "perfect 4th",    "4"),
  AUG_4     (6, "augmented 4th",  "#4"),
  DIM_5     (6, "diminished 5th", "b5"),
  TRITONE   (6, "tritone",        "TT"),
  PERFECT_5 (7, "perfect 5th",    "5"),
  MIN_6     (8, "minor 6th",      "m6"),
  MAJ_6     (9, "major 6th",      "6"),
  MIN_7     (10, "minor 7th",     "7"),
  MAJ_7     (11, "major 7th",     "M7"),
  OCTAVE    (12, "octave",        "8ve");
  
  protected int interval;
  protected String longName;
  protected String shortName;
  
  AbsInterval(int i, String lnm, String snm) {
    interval  = i;
    longName  = lnm;
    shortName = snm;
  }
  
  public static AbsInterval compareNotes(Note from, Note to) {
    return compareNotes(from.getNote(), to.getNote());
  }
  
  public static AbsInterval compareNotes(AbsNote from, AbsNote to) {
    int fromNote  = from.pitch();
    int toNote    = to.pitch();
    int diff = toNote - fromNote;
    if (diff < 0) {
      diff += 12;
    }; 
    return AbsInterval.fromPitchInterval(diff);
  }
  
  public static boolean isMajor(Note from, Note to) {
    return isMajor(from.getNote(), to.getNote());
  }

  public static boolean isMajor(AbsNote from, AbsNote to) {
    AbsInterval ai = compareNotes(from, to);
    return ai.equals(MAJ_2) || ai.equals(MAJ_3) || ai.equals(MAJ_6) || ai.equals(MAJ_7);
  }
  
  public static boolean isMinor(Note from, Note to) {
    return isMinor(from.getNote(), to.getNote());
  }
  
  public static boolean isMinor(AbsNote from, AbsNote to) {
    AbsInterval ai = compareNotes(from, to);
    return ai.equals(MIN_2) || ai.equals(MIN_3) || ai.equals(MIN_6) || ai.equals(MIN_7);
  }

  /**
   * returns an AbsInterval based on the pitch diff. between notes
   * TODO: handle pseudo-synonyms (#4 and b5)
   * @param i an interval in semitones
   * @return the default name for that interval
   */
  public static AbsInterval fromPitchInterval(int i) {
    if (i > 12) {
      i = i % 12;
    }
    AbsInterval itv;
    switch(i) {
      case 1:
        itv = AbsInterval.MIN_2;
        break;
      case 2:
        itv = AbsInterval.MAJ_2;
        break;
      case 3:
        itv = AbsInterval.MIN_3;
        break;
      case 4:
        itv = AbsInterval.MAJ_3;
        break;
      case 5:
        itv = AbsInterval.PERFECT_4;
        break;
      case 6:
        itv = AbsInterval.DIM_5;
        break;
      case 7:
        itv = AbsInterval.PERFECT_5;
        break;
      case 8:
        itv = AbsInterval.MIN_6;
        break;
      case 9:
        itv = AbsInterval.MAJ_6;
        break;
      case 10:
        itv = AbsInterval.MIN_7;
        break;
      case 11:
        itv = AbsInterval.MAJ_7;
        break;
      case 0:
      default:
        itv = AbsInterval.UNISON;
    }
    return itv;
  }
  
  public boolean equals(AbsInterval that) {
    return (this.interval == that.getInterval());
  }
  
  public String shortName() {
    return shortName;
  }
  
  public String longName() {
    return longName;
  }
  
  public String toString() {
    return shortName;
  }
  
  public int getInterval() {
    return interval;
  }
}
