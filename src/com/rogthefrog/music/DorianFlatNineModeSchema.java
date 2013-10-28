package com.rogthefrog.music;

public final class DorianFlatNineModeSchema extends ScaleSchema {

  // remember schema starts from the right (root = 1)
  public static final int          SCHEMA           = 0b011010101011;
  public static final String       NAME             = "Dorian b9 Mode";
  private static DorianFlatNineModeSchema instance   = null;

  private DorianFlatNineModeSchema() {
    super(SCHEMA, NAME);
  }

  public static DorianFlatNineModeSchema getInstance() {
    if (instance == null) {
      instance = new DorianFlatNineModeSchema();
    }
    return instance;
  }
}
