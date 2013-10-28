package com.rogthefrog.music;

public final class SuperLocrianModeSchema extends ScaleSchema {

  // remember schema starts from the right (root = 1)
  public static final int          SCHEMA           = 0b010101011011;
  public static final String       NAME             = "Super Locrian Mode";
  private static SuperLocrianModeSchema instance   = null;

  private SuperLocrianModeSchema() {
    super(SCHEMA, NAME);
  }

  public static SuperLocrianModeSchema getInstance() {
    if (instance == null) {
      instance = new SuperLocrianModeSchema();
    }
    return instance;
  }
}
