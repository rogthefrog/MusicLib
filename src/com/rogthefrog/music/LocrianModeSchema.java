package com.rogthefrog.music;

public final class LocrianModeSchema extends ScaleSchema {
  
  // remember schema starts from the right (root = 1)
  public static final int          SCHEMA    = 0b1010101101011;
  public static final String       NAME      = "Locrian Mode";
  private static LocrianModeSchema instance  = null;

  private LocrianModeSchema() {
    super(SCHEMA, NAME);
  }

  public static LocrianModeSchema getInstance() {
    if (instance == null) {
      instance = new LocrianModeSchema();
    }
    return instance;
  }
}
