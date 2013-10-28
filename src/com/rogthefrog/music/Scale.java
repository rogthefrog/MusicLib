package com.rogthefrog.music;

public class Scale {
  
  protected AbsNote key;
  protected ScaleSchema schema;
  protected AbsNote[] notes;
  
  public Scale(AbsNote key, ScaleSchema schema) {
    this.key    = key;
    this.schema = schema;
  }

  public AbsNote[] computeNotes() {
    notes = new AbsNote[schema.countNotes()];
    return notes;
  }

  public String toString() {
    return key + " " + schema.getName();
  }

}
