package com.rogthefrog.music;

public final class LydianModeSchema extends ScaleSchema {

  // remember schema starts from the right (root = 1)
  public static final int           SCHEMA    = 0b101011010101;
  public static final String        NAME      = "Lydian Mode";
  private static LydianModeSchema instance  = null;

  private LydianModeSchema() {
    super(SCHEMA, NAME);
  }

  public static LydianModeSchema getInstance() {
    if (instance == null) {
      instance = new LydianModeSchema();
    }
    return instance;
  }
}