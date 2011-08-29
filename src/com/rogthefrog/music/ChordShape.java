package com.rogthefrog.music;

import java.lang.IllegalArgumentException;

public class ChordShape {
  private Tuning tuning;
  private int baseFret = 0;
  private int[] positions;
  public static final int MAX_FRET = 36;
  
  /**
   * constructor with default tuning and base fret
   */
  public ChordShape() {
    tuning = TuningFactory.getTuning(TuningFactory.Tunings.STANDARD);
    positions = new int[tuning.size()];
    baseFret = 0;
  }
  
  /**
   * constructor with default tuning
   * @param base first fret (0 default)
   */
  public ChordShape(int base) {
    tuning = TuningFactory.getTuning(TuningFactory.Tunings.STANDARD);
    positions = new int[tuning.size()];
    try {
      setBaseFret(base);
    } catch (IllegalArgumentException e) {
      setBaseFret(0);
    }
  }

  /**
   * constructor with default base fret
   * @param t tuning
   */
  public ChordShape(Tuning t) {
    tuning = t;
    positions = new int[tuning.size()];
    baseFret = 0;
  }
  
  /**
   * constructor
   * @param t tuning
   * @param base first fret (0 default)
   */
  public ChordShape(Tuning t, int base) {
    tuning = t;
    positions = new int[tuning.size()];
    try {
      setBaseFret(base);
    } catch (IllegalArgumentException e) {
      setBaseFret(0);
    }
  }
  
  /**
   * adds a note to the shape (overwrite if existing)
   * @param string which string the finger is on
   * @param fret which fret on the string
   * @return this chord shape
   */
  public ChordShape addNote(int string, int fret) {
    if (!stringInRange(string)) {
      throw new IllegalArgumentException("Bad string: " + string);
    }
    if (!fretInRange(fret)) {
      throw new IllegalArgumentException("Bad fret: " + fret);
    }
    positions[string] = fret;
    return this;
  }

  /**
   * removes a note from the shape 
   * @param string which string to remove
   * @return this chord shape
   */
  public ChordShape removeNote(int string) {
    if (!stringInRange(string)) {
      throw new IllegalArgumentException("Bad string: " + string);
    }
    positions[string] = -1;
    return this;
  }
  
  /**
   * convenience method to throw out strings that are off the neck
   * @param string index of string (0 = lowest)
   * @return true if string is valid
   */
  private boolean stringInRange(int string) {
    return (string >= 0 && string < size());
  }
  
  /**
   * convenience method to throw out frets that are off the neck
   * @param string index of fret (0 = open)
   * @return true if fret is valid
   */
  private boolean fretInRange(int fret) {
    return (fret >= 0 && fret <= MAX_FRET);
  }

  /**
   * @return # of strings in tuning / chord shape
   */
  public int size() {
    return tuning.size();
  }
  
  /**
   * @return base fret (0 = open)
   */
  public int getBaseFret() {
    return baseFret;
  }
  
  /**
   * sets the base fret
   * @param fret first fret in the chord shape (0 = open)
   * @return this chord shape
   */
  public ChordShape setBaseFret(int fret) {
    if (!fretInRange(fret)) {
      throw new IllegalArgumentException("Bad fret: " + fret);
    }
    baseFret = fret;
    return this;
  }
  
  /**
   * changes the tuning
   * throws exception if the new tuning doesn't have the same number of strings 
   * @param t new tuning
   * @return this chord shape
   */
  public ChordShape setTuning(Tuning t) {
    if (tuning.size() != t.size()) {
      throw new IllegalArgumentException("New tuning doesn't have the same number of strings");
    }
    tuning = t;
    return this;
  }

  /**
   * returns the note on a given string 
   * @param string
   * @return
   */
  public Note noteAt(int string) {
    // -1 means you're not playing that string
    if (positions[string] < 0) {
      return null;
    }
    // the note is note from tuning at this string + base fret + position at this string 
    return tuning.noteAt(string)
            .add(baseFret)
            .add(positions[string]);
  }
}