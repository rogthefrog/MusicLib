package com.rogthefrog.music;

import java.lang.IllegalArgumentException;

public class Tuning {
  public static final int DEFAULT_NUM_STRINGS = 6;
  protected int numStrings;
  protected int cursor = 0;
  protected Note[] notes;
  
  public Tuning() {
    numStrings = DEFAULT_NUM_STRINGS;
    notes = new Note[DEFAULT_NUM_STRINGS];
    cursor = 0;
  }
  
  public Tuning(int s) {
    numStrings = s;
    notes = new Note[numStrings];
    cursor = 0;
  }
  
  public Tuning(Note[] str) {
    numStrings = str.length;
    notes = str;
    cursor = numStrings - 1;
  }
  
  /**
   * set the strings all at once
   * @param str array of Note objects
   * @return this Tuning
   */
  public Tuning setNotes(Note[] str) {
    numStrings = str.length;
    notes = str;
    cursor = numStrings - 1;
    return this;
  }
  
  /**
   * adds string to the end of the array of strings if there's room
   * @param theNote note value
   * @return this tuning
   */
  public Tuning addNote(Note theNote) {
    if (cursor >= numStrings) {
      return this;
    }
    notes[cursor++] = theNote;
    return this;
  }

  /**
   * adds note to the specified position 
   * @param position
   * @param theString
   * @return
   */
  public Tuning setNoteAt(Note theNote, int position) {
    if (!stringInRange(position)) {
      throw new IllegalArgumentException("Bad position: " + position);
    }
    notes[position] = theNote;
    return this;
  }
  
  /**
   * @return the number of strings in the tuning
   */
  public int size() {
    return numStrings;
  }
  
  /**
   * @param which string
   * @return the note at that string 
   */
  public Note noteAt(int position) {
    if (!stringInRange(position)) {
      throw new IllegalArgumentException("Bad position: " + position);
    }
    return notes[position];
  }
  
  /**
   * convenience method to throw out strings not in the tuning
   * @param position
   * @return
   */
  private boolean stringInRange(int position) {
    return (position >= 0 && position < numStrings);
  }
}