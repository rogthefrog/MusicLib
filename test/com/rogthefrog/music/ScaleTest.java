package com.rogthefrog.music;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScaleTest {

  @Test
  public void testToString() {
    Scale scale = new Scale(AbsNote.A, MinorScaleSchema.getInstance());
    assertEquals("A Minor Scale", scale.toString());
  }

}
