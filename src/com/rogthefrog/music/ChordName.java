package com.rogthefrog.music;

import java.util.ArrayList;
import java.util.Map;

/**
 * String representation of a chord name
 * Give it a ChordAnalysis object
 * @author roger
 *
 */
public class ChordName {
  private String name;
  private ArrayList<String> modifiers;
  private Chord chord;
  
  public ChordName(Chord c) {
    chord = c;
  }
  
  public String getName() {
    if (name == null || name == "") {
      findName();
    }
    return name;
  }
  
  /**
   * computes the name for this chord based on its analysis
   * @return the chord's name
   */
  private String findName() {
    modifiers = new ArrayList<String>();
    
    for (Map.Entry<AbsInterval, Boolean> entry: chord.getDefaultAnalysis().getDegrees().entrySet()) {
      boolean on = entry.getValue();
      if (!on) {
        continue;
      }
      AbsInterval interval = entry.getKey();
      modifiers.add(interval.chordName());
    }
    name = prettyPrint();
    return name; 
  }
  
  /**
   * formats the chord name
   * @return the chord name based on modifiers
   */
  private String prettyPrint() {
    name = "";
    StringBuilder sb = new StringBuilder(chord.getRoot().toString());
    for (String mod: modifiers) {
      sb.append((" " + mod).trim());
    }
    name = sb.toString();
    return name;
  }
}