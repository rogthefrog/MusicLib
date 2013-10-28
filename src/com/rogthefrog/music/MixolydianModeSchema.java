package com.rogthefrog.music;

public final class MixolydianModeSchema extends ScaleSchema {
  
  // remember schema starts from the right (root = 1)
  public static final int         SCHEMA    = 0b011010110101;
  public static final String      NAME      = "Mixolydian Mode";
  private static MixolydianModeSchema instance  = null;
  
  private MixolydianModeSchema() {
    super(SCHEMA, NAME);
  }

  public static MixolydianModeSchema getInstance() {
    if (instance == null) {
      instance = new MixolydianModeSchema();
    }
    return instance;
  }
}