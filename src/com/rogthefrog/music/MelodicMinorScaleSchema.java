package com.rogthefrog.music;

public final class MelodicMinorScaleSchema extends ScaleSchema {

  // remember schema starts from the right (root = 1)
  public static final int          SCHEMA           = 0b101010101101;
  public static final String       NAME             = "Melodic Minor Scale";
  private static MelodicMinorScaleSchema instance   = null;

  private MelodicMinorScaleSchema() {
    super(SCHEMA, NAME);
  }

  public static MelodicMinorScaleSchema getInstance() {
    if (instance == null) {
      instance = new MelodicMinorScaleSchema();
    }
    return instance;
  }
}
