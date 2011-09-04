/**
 * @author roger
 * concrete implementation of a note
 */
package com.rogthefrog.music;

public class Note {
  private AbsNote note;
  private int octave;
  
  /**
   * constructor with default octave
   * @param n note enum
   */
  public Note(AbsNote n) {
    note = n;
    octave = 0;
  }
  /**
   * constructor with specified octave
   * @param n note enum
   * @param oct octave
   */
  public Note(AbsNote n, int oct) {
    note = n;
    octave  = oct;
  }
  
  public Note clone() {
    return new Note(note, octave);
  }
  
  public int getOctave() {
    return octave;
  }
  
  public String getName() {
    return note.toString();
  }
  
  public String getFullName() {
    return note.toString() + octave;
  }
  
  public AbsNote getNote() {
    return note;
  }
  
  /**
   * compares a note to another 
   * @param that note to compare
   * @return true if the two notes are the same
   */
  public boolean equals(Note that) {
    return (this.note == that.getNote() && this.octave == that.getOctave());
  }
  
  /**
   * compares a note's pitch to another 
   * @param that note to compare
   * @return true if this note is lower than that note
   */
  public boolean isLowerThan(Note that) {
    if (equals(that)) {
      return false;
    }
    if (octave == that.getOctave()) {
      return note.isLowerThan(that.getNote());
    } 
    else {
      return (octave < that.getOctave()); 
    }
  }
  
  /**
   * compares a note's pitch to another 
   * @param that note to compare
   * @return true if this note is higher than that note
   */
  public boolean isHigherThan(Note that) {
    return (!equals(that) && !isLowerThan(that));
  }

  public boolean isA(AbsNote noteType) {
    return (noteType.equals(this.note));
  }
  
  public String toString() {
    return note.toString() + octave;
  }
  
  /**
   * adds or subtracts semitones
   * @param semitones number of semitones to add/subtract
   * @return modified note
   */
  public Note add(int semitones) {
    if (semitones == 0) {
      return this;
    }
    
    int step = (semitones < 0) ? -1 : 1;
    // wrapping B -> C or C -> B? change octave
    if (step == -1 && note.equals(AbsNote.C)) {
      --octave;
    } else if (step == 1 && note.equals(AbsNote.B)) {
      ++octave;
    }
    // add step by step recursively
    note = AbsNote.fromPitch(note.getPitch() + step); 
    add(semitones - step);
      
    return this;
  }
}
