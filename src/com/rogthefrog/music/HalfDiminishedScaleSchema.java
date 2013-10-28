package com.rogthefrog.music;

public final class HalfDiminishedScaleSchema extends ScaleSchema {

  // remember schema starts from the right (root = 1)
  public static final int          SCHEMA           = 0b010101101101;
  public static final String       NAME             = "Half-Diminished Scale";
  private static HalfDiminishedScaleSchema instance   = null;

  private HalfDiminishedScaleSchema() {
    super(SCHEMA, NAME);
  }

  public static HalfDiminishedScaleSchema getInstance() {
    if (instance == null) {
      instance = new HalfDiminishedScaleSchema();
    }
    return instance;
  }
}
