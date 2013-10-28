package com.rogthefrog.music;

public final class DorianModeSchema extends ScaleSchema {

  // remember schema starts from the right (root = 1)
  public static final int         SCHEMA    = 0b011010101101;
  public static final String      NAME      = "Dorian mode";
  private static DorianModeSchema instance  = null;

  private DorianModeSchema() {
    super(SCHEMA, NAME);
  }

  public static DorianModeSchema getInstance() {
    if (instance == null) {
      instance = new DorianModeSchema();
    }
    return instance;
  }
}
