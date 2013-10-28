package com.rogthefrog.music;

public final class LydianAugmentedModeSchema extends ScaleSchema {

  // remember schema starts from the right (root = 1)
  public static final int          SCHEMA           = 0b101101010101;
  public static final String       NAME             = "Lydian Augmented Mode";
  private static LydianAugmentedModeSchema instance   = null;

  private LydianAugmentedModeSchema() {
    super(SCHEMA, NAME);
  }

  public static LydianAugmentedModeSchema getInstance() {
    if (instance == null) {
      instance = new LydianAugmentedModeSchema();
    }
    return instance;
  }
}
