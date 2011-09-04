package com.rogthefrog.music;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * ChordAnalysis is a container for a chord's degrees
 * It's a set of flags (true if you have an interval, false if not)
 * @author roger
 */
public class ChordAnalysis {
  private static final int SIZE = 12;
  // Java can be so annoying sometimes
  private static final HashSet<AbsInterval> keys = new HashSet<AbsInterval>(
    Arrays.asList(
      new AbsInterval[] {
        AbsInterval.ROOT,
        AbsInterval.MIN_2,
        AbsInterval.MAJ_2,
        AbsInterval.MIN_3,
        AbsInterval.MAJ_3,
        AbsInterval.PERFECT_4,
        AbsInterval.DIM_5,
        AbsInterval.PERFECT_5,
        AbsInterval.MIN_6,
        AbsInterval.MAJ_6,
        AbsInterval.MIN_7,
        AbsInterval.MAJ_7,
      }
    )
  );
  private HashMap<AbsInterval, Boolean> degrees = new HashMap<AbsInterval, Boolean>(SIZE);
  
  public ChordAnalysis() {
    clear();
  }
  
  /**
   * by default the analysis is empty
   * @return the analysis
   */
  public ChordAnalysis clear() {
    for (AbsInterval interval: keys) {
      unset(interval);
    }
    return this;
  }
  
  public HashMap<AbsInterval, Boolean> getDegrees() {
    return degrees;
  }
  
  /**
   * turns a particular degree on
   * @param interval degree to toggle
   */
  public ChordAnalysis set(AbsInterval interval) {
    set(interval, true);
    return this;
  }
  
  /**
   * turns a particular degree off
   * @param interval degree to toggle
   */
  public ChordAnalysis unset(AbsInterval interval) {
    set(interval, false);
    return this;
  }
  
  /**
   * turns a particular degree on or off
   * @param interval degree to toggle
   */
  public ChordAnalysis toggle(AbsInterval interval) {
    set(interval, !degrees.get(interval).booleanValue());
    return this;
  }
  
  /**
   * turns a particular degree on or off
   * @param interval degree to set
   * @param value true (on) or false (off)
   */
  private void set(AbsInterval interval, boolean value) {
    if (keys.contains(interval)) {
      degrees.put(interval, value);
    }
  }
  /**
   * @return a readable list of the degrees in this analysis
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<AbsInterval, Boolean> entry: degrees.entrySet()) {
      AbsInterval key = entry.getKey();
      boolean on = entry.getValue();
      if (on) {
        sb.append("[" + key.longName + "] ");
      }
    }
    return sb.toString().trim();
  }
}