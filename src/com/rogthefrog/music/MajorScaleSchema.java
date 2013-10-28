package com.rogthefrog.music;

public final class MajorScaleSchema extends ScaleSchema {
  
  // remember schema starts from the right (root = 1)
  public static final int MAJOR_SCHEMA  = 0b101010110101;
  public static final String NAME       = "Major Scale";
  private static MajorScaleSchema instance = null;
  
  private MajorScaleSchema() {
    super(MAJOR_SCHEMA, NAME);
  }

  public static MajorScaleSchema getInstance() {
    if (instance == null) {
      instance = new MajorScaleSchema();
    }
    return instance;
  }
}