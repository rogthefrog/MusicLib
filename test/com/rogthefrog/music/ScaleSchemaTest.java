package com.rogthefrog.music;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class ScaleSchemaTest {

  @Test
  public void testEnsureSchemaHasRoot() {
    ScaleSchema schema = new ScaleSchema(0b101010101010);
    assertTrue(schema.hasNoRoot());
    schema.ensureSchemaHasRoot();
    assertEquals(0b010101010101, schema.getSchema());
  }
  
  @Test
  public void testHasNoRoot() {
    ScaleSchema schema = new ScaleSchema(0b000000000000, "Zero schema");
    assertTrue(schema.hasNoRoot());
    schema.setSchema(0b000000000001);
    assertFalse(schema.hasNoRoot());
  }
  
  @Test
  public void testShiftUp() {
    ScaleSchema major = MajorScaleSchema.getInstance();
    int oldSchema = major.getSchema();
    String oldName = major.getName();

    // major scale shifted up 0 whole tones = no change
    major.shiftUp(0);
    assertEquals(oldSchema, major.getSchema());

    major.shiftUp(AbsInterval.UNISON);
    assertEquals(oldSchema, major.getSchema());

    // if we shift by an octave we should be back to 0
    major.shiftUp(Music.SEMITONES_IN_OCTAVE);
    assertEquals(oldSchema, major.getSchema());

    // shift by an octave interval
    major.shiftUp(AbsInterval.OCTAVE);
    assertEquals(oldSchema, major.getSchema());
    
    // shift by non-zero, should not match
    major.shiftUp(1);
    assertThat(oldSchema, not(equalTo(major.getSchema())));
    
    major.shiftUp(AbsInterval.MAJ_3);
    assertThat(oldSchema, not(equalTo(major.getSchema())));
    
    // start from a major scale, shift by a 6th, should be a minor scale
    ScaleSchema minor = MinorScaleSchema.getInstance();
    major.setSchema(oldSchema);
    major.setName(oldName);
    major.shiftUp(AbsInterval.MAJ_6);
    assertEquals(minor.getSchema(), major.getSchema());
  }
  
  @Test
  public void testContains() {
    ScaleSchema major = MajorScaleSchema.getInstance();
    assertTrue(major.contains(AbsInterval.MAJ_2));
    assertTrue(major.contains(AbsInterval.MAJ_3));
    assertTrue(major.contains(AbsInterval.PERFECT_4));
    assertTrue(major.contains(AbsInterval.PERFECT_5));
    assertTrue(major.contains(AbsInterval.MAJ_6));
    assertTrue(major.contains(AbsInterval.MAJ_7));

    assertFalse(major.contains(AbsInterval.MIN_2));
    assertFalse(major.contains(AbsInterval.MIN_3));
    assertFalse(major.contains(AbsInterval.AUG_4));
    assertFalse(major.contains(AbsInterval.DIM_5));
    assertFalse(major.contains(AbsInterval.TRITONE));
    assertFalse(major.contains(AbsInterval.MIN_6));
    assertFalse(major.contains(AbsInterval.MIN_7));

    
    ScaleSchema minor = MinorScaleSchema.getInstance();
    assertTrue(minor.contains(AbsInterval.MAJ_2));
    assertTrue(minor.contains(AbsInterval.MIN_3));
    assertTrue(minor.contains(AbsInterval.PERFECT_4));
    assertTrue(minor.contains(AbsInterval.PERFECT_5));
    assertTrue(minor.contains(AbsInterval.MIN_6));
    assertTrue(minor.contains(AbsInterval.MIN_7));

    assertFalse(minor.contains(AbsInterval.MIN_2));
    assertFalse(minor.contains(AbsInterval.MAJ_3));
    assertFalse(minor.contains(AbsInterval.AUG_4));
    assertFalse(minor.contains(AbsInterval.DIM_5));
    assertFalse(minor.contains(AbsInterval.TRITONE));
    assertFalse(minor.contains(AbsInterval.MAJ_6));
    assertFalse(minor.contains(AbsInterval.MAJ_7));

  }

}