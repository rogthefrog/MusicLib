package com.rogthefrog.music;

import java.lang.IllegalArgumentException;

public class Tuning {
  public static final int DEFAULT_NUM_STRINGS = 6;
  protected int numStrings;
  protected int cursor = 0;
  protected Note[] strings;
  
  public Tuning() {
    numStrings = DEFAULT_NUM_STRINGS;
    strings = new Note[DEFAULT_NUM_STRINGS];
    cursor = 0;
  }
  
  public Tuning(int s) {
    numStrings = s;
    strings = new Note[numStrings];
    cursor = 0;
  }
  
  public Tuning(Note[] str) {
    numStrings = str.length;
    strings = str;
    cursor = numStrings - 1;
  }
  
  /**
   * set the strings all at once
   * @param str array of Note objects
   * @return this Tuning
   */
  public Tuning setStrings(Note[] str) {
    numStrings = str.length;
    strings = str;
    cursor = numStrings - 1;
    return this;
  }
  
  /**
   * adds string to the end of the array of strings if there's room
   * @param theString note value
   * @return this tuning
   */
  public Tuning addString(Note theString) {
    if (cursor >= numStrings) {
      return this;
    }
    strings[cursor++] = theString;
    return this;
  }

  /**
   * adds note to the specified position 
   * @param position
   * @param theString
   * @return
   */
  public Tuning addString(Note theString, int position) {
    if (!stringInRange(position)) {
      throw new IllegalArgumentException("Bad position: " + position);
    }
    strings[position] = theString;
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
    return strings[position];
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