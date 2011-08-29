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
  
  public boolean equals(Note that) {
    return (this.note == that.getNote() && this.octave == that.getOctave());
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
