package com.rogthefrog.music;

public final class HarmonicMinorScaleSchema extends ScaleSchema {

  // remember schema starts from the right (root = 1)
  public static final int          SCHEMA           = 0b100110101101;
  public static final String       NAME             = "Harmonic Minor Scale";
  private static HarmonicMinorScaleSchema instance  = null;

  private HarmonicMinorScaleSchema() {
    super(SCHEMA, NAME);
  }

  public static HarmonicMinorScaleSchema getInstance() {
    if (instance == null) {
      instance = new HarmonicMinorScaleSchema();
    }
    return instance;
  }
}
