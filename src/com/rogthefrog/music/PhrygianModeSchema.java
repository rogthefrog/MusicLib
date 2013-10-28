package com.rogthefrog.music;

public final class PhrygianModeSchema extends ScaleSchema {

  // remember schema starts from the right (root = 1)
  public static final int           SCHEMA    = 0b1010110101011;
  public static final String        NAME      = "Phrygian Mode";
  private static PhrygianModeSchema instance  = null;

  private PhrygianModeSchema() {
    super(SCHEMA, NAME);
  }

  public static PhrygianModeSchema getInstance() {
    if (instance == null) {
      instance = new PhrygianModeSchema();
    }
    return instance;
  }
}