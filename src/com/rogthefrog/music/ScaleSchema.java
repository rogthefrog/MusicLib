package com.rogthefrog.music;
import java.lang.Exception;

public class ScaleSchema {
  
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
    if (schema > Math.pow(2, Music.SEMITONES_IN_OCTAVE)) {
      throw new RuntimeException("Schema " + schema + "is too big");
    }
    this.schema = schema;
  }
  
  protected void setSchema(ScaleSchema scale) {
    setSchema(scale.getSchema());
  }
  
  public String toString() {
    String prettyName = zeroPadSchema(this.schema);
    if (this.name != "") {
      prettyName = this.name + " = " + prettyName;
    }
    return prettyName;
  }

  /**
   * checks whether a scale schema contains a given interval
   * e.g. a major scale contains a AbsInterval.MAJ_3 but not a AbsInterval.MIN_7
   * @param interval
   * @return true if that interval is found in the scale
   */
  public Boolean contains(AbsInterval interval) {
    int intervalBinaryValue = (int)Math.pow(2, interval.getInterval());
    int andResult           = (schema & intervalBinaryValue);
    
    return (andResult == intervalBinaryValue);
  }

  /**
   * zero-pads the binary representation of the scale
   */
  public static String zeroPadSchema(int schema) {
    return String.format("%" + Music.SEMITONES_IN_OCTAVE + "s", 
      Integer.toBinaryString(schema)).replace(" ", "0");
  }

  /**
   * sanitize number of semitones
   */
  public static int sanitizeSemitones(int numSemitones) {
    if (numSemitones < 0) {
      numSemitones = 0;
    } else if (numSemitones >= Music.SEMITONES_IN_OCTAVE) {
      numSemitones = numSemitones % Music.SEMITONES_IN_OCTAVE;
    }
    return numSemitones;
  }
  
  /**
   * compares this schema to another
   * @param that ScaleSchema to compare this to
   * @return true if this and the other ScaleSchema have the same schema
   */
  public Boolean equals(ScaleSchema that) {
    return (this.schema == that.getSchema());
  }

  // ****************
  // end housekeeping
  // ****************

  /**
   * zero-based schemas can be bad (rootless scales)
   * @return true if a schema has no root
   */
  public static Boolean hasNoRoot(int schema) {
    return schema % 2 == 0;
  }

  /**
   * flips a schema (e.g. from 10101 to 01010)
   * used when flipping zero-based, rootless schemas
   * @param schema any integer version of a schema
   * @return its flipped schema
   */
  public static int flipSchema(int schema) {
    int flipped = 0;
    int power   = 0;
    for (int i = 0; i < Music.SEMITONES_IN_OCTAVE; ++i) {
      power = (int)Math.pow(2,  i);
      if ((schema & power) != power) {
        flipped += power;
      }
    }
    return flipped;
  }

  /**
   * flips a schema, using a ScaleSchema object
   * @param scale a ScaleSchema object
   * @return the flipped schema
   */
  public static int flipSchema(ScaleSchema scale) {
    return flipSchema(scale.getSchema());
  }

  /**
   * a valid schema must have a root (1 as lowest bit)
   * if you shift a scale by an interval not in that scale
   * e.g. shift a major scale by a m6 (G#)
   * you wind up with a 0-based schema that needs flipping
   * This method flips the schema if necessary
   * @return
   */
  protected ScaleSchema ensureSchemaHasRoot() {
    if (hasNoRoot(this.schema)) {
      setSchema(flipSchema(this.schema));
    }
    return this;
  }

  /**
   * compute a schema based on this schema shifted up n semitones
   * @param scale ScaleSchema object whose schema you want to shift
   * @param numSemitones number of semitones to shift by
   * @return a new ScaleSchema object with the schema shifted up
   */
  public static ScaleSchema computeUpShiftSchema(ScaleSchema scale, int numSemitones) {
    numSemitones = sanitizeSemitones(numSemitones);
    if (numSemitones == 0) {
      return scale;
    }
    
    int theSchema = scale.getSchema();

    // the rotator is a concat of the schema to itself
    // so we can take a slice
    // e.g. if the schema is 100 the rotator is 100100
    int rotator = theSchema + (theSchema << Music.SEMITONES_IN_OCTAVE);

    // now you push the rotator to the right by however many semitones
    int rotated = rotator >>> numSemitones;

    // and you grab the 12 rightmost digits
    theSchema = (rotated & ROTATOR_AND_MASK);

    // we like our schemas to have a root, so flip the schema if necessary
    if (hasNoRoot(theSchema)) {
      theSchema = flipSchema(theSchema);
    }

    return new ScaleSchema(theSchema);
  }
  
  /**
   * compute a schema based on this schema shifted up n semitones
   * @param scale ScaleSchema object whose schema you want to shift
   * @param interval AbsInterval object you want to shift by
   * @return a new ScaleSchema object with the schema shifted up
   */
  public static ScaleSchema computeUpShiftSchema(ScaleSchema scale, AbsInterval interval) {
    return computeUpShiftSchema(scale, interval.getInterval());
  }
  
  /**
   * shift a scale schema by an arbitrary number of semitones
   * @param numSteps number of semitones to shift by
   * @return this ScaleSchema, shifted up
   */
  public ScaleSchema shiftUp(int numSemitones) {
    setSchema(computeUpShiftSchema(this, numSemitones));
    setName(this.name + " + " + sanitizeSemitones(numSemitones) + " semitone(s)");
    return this;
  }

  /**
   * 
   * @param interval AbsInterval object to shift by (e.g. "shift up a maj third")
   * @return this ScaleSchema, shifted up
   */
  public ScaleSchema shiftUp(AbsInterval interval) {
    String oldName = name;
    shiftUp(interval.getInterval());
    setName(oldName + " + 1 " + interval.longName());
    return this;
  }
}
