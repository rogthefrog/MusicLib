package com.rogthefrog.music;

public class ScaleSchema {
  
  public static final int SEMITONES_IN_OCTAVE = 12;
  public static final int ROTATOR_AND_MASK    = 0b111111111111;
  
  protected int     schema;
  protected String  name;

  // ************
  // housekeeping
  // ************

  public ScaleSchema() {
    schema = 0;
  }
  
  public ScaleSchema(int schema) {
    setSchema(schema);
  }
  
  public ScaleSchema(int schema, String name) {
    setSchema(schema);
    setName(name);
  }
  
  public String getName() {
    return name;
  }
  
  protected void setName(String name) {
    this.name = name;
  }
  
  public int getSchema() {
    return schema;
  }
  
  protected void setSchema(int schema) {
    this.schema = schema;
  }
  
  public Boolean hasZeroBasedSchema() {
    return schema % 2 == 0;
  }
  
  public static int flipSchema(int schema) {
    int flipped = 0;
    int power   = 0;
    for (int i = 0; i < SEMITONES_IN_OCTAVE; ++i) {
      power = (int)Math.pow(2,  i);
      if ((schema & power) == 0) {
        flipped += power;
      }
    }
    return flipped;
  }
  
  /**
   * a valid schema must have a root (1 as lowest bit)
   * if you shift a scale by an interval not in that scale
   * e.g. shift a major scale by a m6 (G#)
   * you wind up with a 0-based schema that needs flipping
   * This method flips the schema if necessary
   * @return
   */
  protected int ensureEvenSchema() {
    if (hasZeroBasedSchema()) {
      setSchema(flipSchema(schema));
    }
    return schema;
  }
  
  public String toString() {
    String prettyName = zeroPadSchema(this.schema);
    if (this.name != "") {
      prettyName = this.name + " = " + prettyName;
    }
    return prettyName;
  }
  
  // ****************
  // end housekeeping
  // ****************
  
  /**
   * shift a scale schema by an arbitrary number of semitones
   * @param numSteps number of semitones to shift by
   */
  public ScaleSchema shiftUp(int numSemitones) {
    if (numSemitones < 0 || numSemitones > SEMITONES_IN_OCTAVE) {
      return this;
    }
    // the rotator is a concat of the schema to itself
    // so we can take a slice
    // e.g. if the schema is 101 the rotator is 101101
    int rotator = schema + (schema << SEMITONES_IN_OCTAVE);

    // now you push the rotator to the right by however many semitones
    int rotated = rotator >>> numSemitones;

    // and you grab the 12 rightmost digits
    this.setSchema(rotated & ROTATOR_AND_MASK);
    this.setName(this.name + " + " + numSemitones + " semitone(s)");

    this.ensureEvenSchema();
    
    return this;
  }

  /**
   * 
   * @param interval AbsInterval object to shift by (e.g. "third")
   */
  public ScaleSchema shiftUp(AbsInterval interval) {
    String oldName = name;
    shiftUp(interval.getInterval());
    this.setName(oldName + " + 1 " + interval.longName());

    return this;
  }
  
  /**
   * pads the binary representation of the scale
   */
  public static String zeroPadSchema(int schema) {
    return String.format("%" + SEMITONES_IN_OCTAVE + "s", 
      Integer.toBinaryString(schema)).replace(" ", "0");
  }
}
