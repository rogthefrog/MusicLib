package com.rogthefrog.music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * String representation of a chord name
 * Give it a ChordAnalysis object
 * @author roger
 *
 */
public class ChordName {
  private String name;
  private ArrayList<ChordModifier> modifiers;
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
    modifiers = new ArrayList<ChordModifier>();
    ArrayList<AbsInterval> ints = new ArrayList<AbsInterval>();
    
    for (Map.Entry<AbsInterval, Boolean> entry: chord.getDefaultAnalysis().getDegrees().entrySet()) {
      boolean on = entry.getValue();
      if (on) {
        ints.add(entry.getKey());
      }
    }
    
    // let's add up the various bits
    boolean hasThird    = false;
    boolean hasSeventh  = false;
    boolean isSus2      = false;
    boolean isSus4      = false;
    boolean isMinor     = false;
    
    // deal with the third
    if (ints.contains(AbsInterval.MIN_3)) {
      hasThird = true;
      isMinor = true;
      // dim implies minor
      if (ints.contains(AbsInterval.DIM_5)) {
        modifiers.add(ChordModifier.DIM);
      } else {
        modifiers.add(ChordModifier.MIN);
      }
    }
    if (ints.contains(AbsInterval.MAJ_3)) {
      hasThird = true;
    }
    
    // no third? is this a sus chord?
    if (!hasThird) {
      if (ints.contains(AbsInterval.MAJ_2)) {
        isSus2 = true;
        modifiers.add(ChordModifier.SUS_2);
      } else if (ints.contains(AbsInterval.PERFECT_4)) {
        isSus4 = true;
        modifiers.add(ChordModifier.SUS_4);
      }
    }
    
    // is there a 5th?
    
    
    // is there a 7th
    if (ints.contains(AbsInterval.MIN_7)) {
      hasSeventh = true;
      modifiers.add(ChordModifier.MIN_7);
    }
    if (ints.contains(AbsInterval.MAJ_7)) {
      hasSeventh = true;
      modifiers.add(ChordModifier.MAJ_7);
    }
    
    // if there's a 7th, rename the other degrees 
    if (hasSeventh) {
      boolean removeSeventh = false;
      
      if (!isSus2 && ints.contains(AbsInterval.MIN_2)) {
        removeSeventh = true;
        modifiers.add(ChordModifier.FLAT_9);
      }
      if (!isSus2 && ints.contains(AbsInterval.MAJ_2)) {
        removeSeventh = true;
        modifiers.add(ChordModifier.ADD_9);
      }
      if (!isSus4 && ints.contains(AbsInterval.PERFECT_4)) {
        removeSeventh = true;
        modifiers.add(ChordModifier.ADD_11);
      }
      if (!isSus4 && ints.contains(AbsInterval.AUG_4)) {
        removeSeventh = true;
        modifiers.add(ChordModifier.AUG_11);
      }
      if (ints.contains(AbsInterval.MIN_6)) {
        removeSeventh = true;
        modifiers.add(ChordModifier.FLAT_13);
      }
      if (ints.contains(AbsInterval.MAJ_6)) {
        removeSeventh = true;
        modifiers.add(ChordModifier.ADD_13);
      }
      if (removeSeventh) {
        modifiers.remove(ChordModifier.MAJ_7);
        modifiers.remove(ChordModifier.MIN_7);
      }
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
    for (ChordModifier mod: modifiers) {
      sb.append((" " + mod.toString()).trim());
    }
    name = sb.toString();
    return name;
  }
}