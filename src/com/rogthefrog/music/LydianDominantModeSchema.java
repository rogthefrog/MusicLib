package com.rogthefrog.music;

public final class LydianDominantModeSchema extends ScaleSchema {

  // remember schema starts from the right (root = 1)
  public static final int          SCHEMA           = 0b011011010101;
  public static final String       NAME             = "Lydian Dominant Mode";
  private static LydianDominantModeSchema instance   = null;

  private LydianDominantModeSchema() {
    super(SCHEMA, NAME);
  }

  public static LydianDominantModeSchema getInstance() {
    if (instance == null) {
      instance = new LydianDominantModeSchema();
    }
    return instance;
  }
}
