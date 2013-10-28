package com.rogthefrog.music;

public final class MixolydianFlatSixModeSchema extends ScaleSchema {

  // remember schema starts from the right (root = 1)
  public static final int          SCHEMA           = 0b010110110101;
  public static final String       NAME             = "Mixolydian b6 Mode";
  private static MixolydianFlatSixModeSchema instance   = null;

  private MixolydianFlatSixModeSchema() {
    super(SCHEMA, NAME);
  }

  public static MixolydianFlatSixModeSchema getInstance() {
    if (instance == null) {
      instance = new MixolydianFlatSixModeSchema();
    }
    return instance;
  }
}
