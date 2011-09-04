package com.rogthefrog.music;

public enum ChordModifier {
  
  FLAT_9      ("b9"),
  SUS_2       ("sus2"),
  ADD_9       ("9"),
  
  MIN       ("m"),

  FLAT_11      ("b11"),
  SUS_4       ("sus4"),
  ADD_11      ("11"),
  AUG_4       ("#4"),
  AUG_11      ("#11"),
  FLAT_5      ("b5"),
  
  MIN_6       ("m6"),
  FLAT_13     ("b13"),
  ADD_6       ("6"),
  ADD_13      ("13"),
  AUG_13      ("#13"),
  
  DIM         ("dim"),
  
  MAJ_7     ("M7"),
  MIN_7     ("m7");  
  
  protected String name;
  
  ChordModifier(String name) {
    this.name = name;
  }
  
  public String toString() {
    return name;
  }
}
