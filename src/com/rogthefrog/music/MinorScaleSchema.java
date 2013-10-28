package com.rogthefrog.music;

public final class MinorScaleSchema extends ScaleSchema {

  // remember schema starts from the right (root = 1)
  public static final int         SCHEMA    = 0b010110101101;
  public static final String      NAME      = "Minor Scale";
  private static MinorScaleSchema instance  = null;

  private MinorScaleSchema() {
    super(SCHEMA, NAME);
  }

  public static MinorScaleSchema getInstance() {
    if (instance == null) {
      instance = new MinorScaleSchema();
    }
    return instance;
  }
}
