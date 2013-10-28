package com.rogthefrog.music;

public final class AeolianModeSchema extends ScaleSchema {
  
  // remember schema starts from the right (root = 1)
  public static final int          SCHEMA    = 0b010110101101;
  public static final String       NAME      = "Aeolian Mode";
  private static AeolianModeSchema instance  = null;

  private AeolianModeSchema() {
    super(SCHEMA, NAME);
  }

  public static AeolianModeSchema getInstance() {
    if (instance == null) {
      instance = new AeolianModeSchema();
    }
    return instance;
  }
}
