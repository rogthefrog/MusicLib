package com.rogthefrog.music;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class ScaleSchemaTest {

  @Test
  public void testHasNoRoot() {
    ScaleSchema scale = new ScaleSchema(0b000000000000, "Zero schema");
    assertTrue(ScaleSchema.hasNoRoot(scale.getSchema()));
    scale.setSchema(0b000000000001);
    assertFalse(ScaleSchema.hasNoRoot(scale.getSchema()));
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
  public void testSanitizeSemitones() {
    for (int i = 0; i < Music.SEMITONES_IN_OCTAVE; ++i) {
      assertEquals(i, ScaleSchema.sanitizeSemitones(i));
    }
    for (int i = Music.SEMITONES_IN_OCTAVE; i < 3 * Music.SEMITONES_IN_OCTAVE; ++i) {
      assertEquals(i % Music.SEMITONES_IN_OCTAVE, ScaleSchema.sanitizeSemitones(i));
    }
  }
  
  @Test
  public void testEquals() {
    ScaleSchema one = new ScaleSchema(0b101010101010, "one");
    ScaleSchema two = new ScaleSchema(0b101010101010, "two");
    assertTrue(one.equals(two));
    
    two.shiftUp(0);
    assertTrue(one.equals(two));
    
    two.shiftUp(Music.SEMITONES_IN_OCTAVE);
    assertTrue(one.equals(two));
    
    for (int i = 1; i < Music.SEMITONES_IN_OCTAVE - 1; ++i) {
      two.shiftUp(i);
      assertFalse(one.equals(two));
    }
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

    ScaleSchema dorian = DorianModeSchema.getInstance();
    assertTrue(dorian.contains(AbsInterval.MAJ_2));
    assertTrue(dorian.contains(AbsInterval.MIN_3));
    assertTrue(dorian.contains(AbsInterval.PERFECT_4));
    assertTrue(dorian.contains(AbsInterval.PERFECT_5));
    assertTrue(dorian.contains(AbsInterval.MAJ_6));
    assertTrue(dorian.contains(AbsInterval.MIN_7));

    assertFalse(dorian.contains(AbsInterval.MIN_2));
    assertFalse(dorian.contains(AbsInterval.MAJ_3));
    assertFalse(dorian.contains(AbsInterval.AUG_4));
    assertFalse(dorian.contains(AbsInterval.DIM_5));
    assertFalse(dorian.contains(AbsInterval.TRITONE));
    assertFalse(dorian.contains(AbsInterval.MIN_6));
    assertFalse(dorian.contains(AbsInterval.MAJ_7));

    ScaleSchema phrygian = PhrygianModeSchema.getInstance();
    assertTrue(phrygian.contains(AbsInterval.MIN_2));
    assertTrue(phrygian.contains(AbsInterval.MIN_3));
    assertTrue(phrygian.contains(AbsInterval.PERFECT_4));
    assertTrue(phrygian.contains(AbsInterval.PERFECT_5));
    assertTrue(phrygian.contains(AbsInterval.MIN_6));
    assertTrue(phrygian.contains(AbsInterval.MIN_7));

    assertFalse(phrygian.contains(AbsInterval.MAJ_2));
    assertFalse(phrygian.contains(AbsInterval.MAJ_3));
    assertFalse(phrygian.contains(AbsInterval.AUG_4));
    assertFalse(phrygian.contains(AbsInterval.DIM_5));
    assertFalse(phrygian.contains(AbsInterval.TRITONE));
    assertFalse(phrygian.contains(AbsInterval.MAJ_6));
    assertFalse(phrygian.contains(AbsInterval.MAJ_7));

    ScaleSchema lydian = LydianModeSchema.getInstance();
    assertTrue(lydian.contains(AbsInterval.MAJ_2));
    assertTrue(lydian.contains(AbsInterval.MAJ_3));
    // these are enharmonic, and I'm not handling enharmonics well yet
    assertTrue(lydian.contains(AbsInterval.AUG_4));
    assertTrue(lydian.contains(AbsInterval.DIM_5));
    assertTrue(lydian.contains(AbsInterval.TRITONE));
    assertTrue(lydian.contains(AbsInterval.PERFECT_5));
    assertTrue(lydian.contains(AbsInterval.MAJ_6));
    assertTrue(lydian.contains(AbsInterval.MAJ_7));

    assertFalse(lydian.contains(AbsInterval.MIN_2));
    assertFalse(lydian.contains(AbsInterval.MIN_3));
    assertFalse(lydian.contains(AbsInterval.PERFECT_4));
    assertFalse(lydian.contains(AbsInterval.MIN_6));
    assertFalse(lydian.contains(AbsInterval.MIN_7));
    
    ScaleSchema mixo = MixolydianModeSchema.getInstance();
    assertTrue(mixo.contains(AbsInterval.MAJ_2));
    assertTrue(mixo.contains(AbsInterval.MAJ_3));
    assertTrue(mixo.contains(AbsInterval.PERFECT_4));
    assertTrue(mixo.contains(AbsInterval.PERFECT_5));
    assertTrue(mixo.contains(AbsInterval.MAJ_6));
    assertTrue(mixo.contains(AbsInterval.MIN_7));

    assertFalse(mixo.contains(AbsInterval.MIN_2));
    assertFalse(mixo.contains(AbsInterval.MIN_3));
    assertFalse(mixo.contains(AbsInterval.AUG_4));
    assertFalse(mixo.contains(AbsInterval.DIM_5));
    assertFalse(mixo.contains(AbsInterval.TRITONE));
    assertFalse(mixo.contains(AbsInterval.MIN_6));
    assertFalse(mixo.contains(AbsInterval.MAJ_7));
    
    ScaleSchema aeolian = AeolianModeSchema.getInstance();
    assertTrue(aeolian.contains(AbsInterval.MAJ_2));
    assertTrue(aeolian.contains(AbsInterval.MIN_3));
    assertTrue(aeolian.contains(AbsInterval.PERFECT_4));
    assertTrue(aeolian.contains(AbsInterval.PERFECT_5));
    assertTrue(aeolian.contains(AbsInterval.MIN_6));
    assertTrue(aeolian.contains(AbsInterval.MIN_7));

    assertFalse(aeolian.contains(AbsInterval.MIN_2));
    assertFalse(aeolian.contains(AbsInterval.MAJ_3));
    assertFalse(aeolian.contains(AbsInterval.AUG_4));
    assertFalse(aeolian.contains(AbsInterval.DIM_5));
    assertFalse(aeolian.contains(AbsInterval.TRITONE));
    assertFalse(aeolian.contains(AbsInterval.MAJ_6));
    assertFalse(aeolian.contains(AbsInterval.MAJ_7));
    
    ScaleSchema locrian = LocrianModeSchema.getInstance();
    assertTrue(locrian.contains(AbsInterval.MIN_2));
    assertTrue(locrian.contains(AbsInterval.MIN_3));
    assertTrue(locrian.contains(AbsInterval.PERFECT_4));
    assertTrue(locrian.contains(AbsInterval.DIM_5));
    assertTrue(locrian.contains(AbsInterval.TRITONE));
    assertTrue(locrian.contains(AbsInterval.MIN_6));
    assertTrue(locrian.contains(AbsInterval.MIN_7));

    assertFalse(locrian.contains(AbsInterval.MAJ_2));
    assertFalse(locrian.contains(AbsInterval.MAJ_3));
    assertFalse(locrian.contains(AbsInterval.MAJ_6));
    assertFalse(locrian.contains(AbsInterval.MAJ_7));
 
    ScaleSchema melMinor = MelodicMinorScaleSchema.getInstance();
    assertTrue(melMinor.contains(AbsInterval.MAJ_2));
    assertTrue(melMinor.contains(AbsInterval.MIN_3));
    assertTrue(melMinor.contains(AbsInterval.PERFECT_4));
    assertTrue(melMinor.contains(AbsInterval.PERFECT_5));
    assertTrue(melMinor.contains(AbsInterval.MAJ_6));
    assertTrue(melMinor.contains(AbsInterval.MAJ_7));

    assertFalse(melMinor.contains(AbsInterval.MIN_2));
    assertFalse(melMinor.contains(AbsInterval.MAJ_3));
    assertFalse(melMinor.contains(AbsInterval.AUG_4));
    assertFalse(melMinor.contains(AbsInterval.DIM_5));
    assertFalse(melMinor.contains(AbsInterval.TRITONE));
    assertFalse(melMinor.contains(AbsInterval.MIN_6));
    assertFalse(melMinor.contains(AbsInterval.MIN_7));

    ScaleSchema harmMinor = HarmonicMinorScaleSchema.getInstance();
    assertTrue(harmMinor.contains(AbsInterval.MAJ_2));
    assertTrue(harmMinor.contains(AbsInterval.MIN_3));
    assertTrue(harmMinor.contains(AbsInterval.PERFECT_4));
    assertTrue(harmMinor.contains(AbsInterval.PERFECT_5));
    assertTrue(harmMinor.contains(AbsInterval.MIN_6));
    assertTrue(harmMinor.contains(AbsInterval.MAJ_7));

    assertFalse(harmMinor.contains(AbsInterval.MIN_2));
    assertFalse(harmMinor.contains(AbsInterval.MAJ_3));
    assertFalse(harmMinor.contains(AbsInterval.AUG_4));
    assertFalse(harmMinor.contains(AbsInterval.DIM_5));
    assertFalse(harmMinor.contains(AbsInterval.TRITONE));
    assertFalse(harmMinor.contains(AbsInterval.MAJ_6));
    assertFalse(harmMinor.contains(AbsInterval.MIN_7));
    
    // melodic minor scale modes
    ScaleSchema dorianFlatNine = DorianFlatNineModeSchema.getInstance();
    assertTrue(dorianFlatNine.contains(AbsInterval.MIN_2));
    assertTrue(dorianFlatNine.contains(AbsInterval.MIN_3));
    assertTrue(dorianFlatNine.contains(AbsInterval.PERFECT_4));
    assertTrue(dorianFlatNine.contains(AbsInterval.PERFECT_5));
    assertTrue(dorianFlatNine.contains(AbsInterval.MAJ_6));
    assertTrue(dorianFlatNine.contains(AbsInterval.MIN_7));

    assertFalse(dorianFlatNine.contains(AbsInterval.MAJ_2));
    assertFalse(dorianFlatNine.contains(AbsInterval.MAJ_3));
    assertFalse(dorianFlatNine.contains(AbsInterval.AUG_4));
    assertFalse(dorianFlatNine.contains(AbsInterval.DIM_5));
    assertFalse(dorianFlatNine.contains(AbsInterval.TRITONE));
    assertFalse(dorianFlatNine.contains(AbsInterval.MIN_6));
    assertFalse(dorianFlatNine.contains(AbsInterval.MAJ_7));
    
    ScaleSchema lydianAugmented = LydianAugmentedModeSchema.getInstance();
    assertTrue(lydianAugmented.contains(AbsInterval.MAJ_2));
    assertTrue(lydianAugmented.contains(AbsInterval.MAJ_3));
    assertTrue(lydianAugmented.contains(AbsInterval.AUG_4));
    assertTrue(lydianAugmented.contains(AbsInterval.AUG_5));
    assertTrue(lydianAugmented.contains(AbsInterval.MAJ_6));
    assertTrue(lydianAugmented.contains(AbsInterval.MAJ_7));

    assertFalse(lydianAugmented.contains(AbsInterval.MIN_2));
    assertFalse(lydianAugmented.contains(AbsInterval.MIN_3));
    assertFalse(lydianAugmented.contains(AbsInterval.PERFECT_4));
    assertFalse(lydianAugmented.contains(AbsInterval.PERFECT_5));
    assertFalse(lydianAugmented.contains(AbsInterval.MIN_7));
    
    ScaleSchema lydianDominant = LydianDominantModeSchema.getInstance();
    assertTrue(lydianDominant.contains(AbsInterval.MAJ_2));
    assertTrue(lydianDominant.contains(AbsInterval.MAJ_3));
    assertTrue(lydianDominant.contains(AbsInterval.AUG_4));
    assertTrue(lydianDominant.contains(AbsInterval.PERFECT_5));
    assertTrue(lydianDominant.contains(AbsInterval.MAJ_6));
    assertTrue(lydianDominant.contains(AbsInterval.MIN_7));

    assertFalse(lydianDominant.contains(AbsInterval.MIN_2));
    assertFalse(lydianDominant.contains(AbsInterval.MIN_3));
    assertFalse(lydianDominant.contains(AbsInterval.PERFECT_4));
    assertFalse(lydianDominant.contains(AbsInterval.MIN_6));
    assertFalse(lydianDominant.contains(AbsInterval.MAJ_7));
    
    ScaleSchema mixoFlatSix = MixolydianFlatSixModeSchema.getInstance();
    assertTrue(mixoFlatSix.contains(AbsInterval.MAJ_2));
    assertTrue(mixoFlatSix.contains(AbsInterval.MAJ_3));
    assertTrue(mixoFlatSix.contains(AbsInterval.PERFECT_4));
    assertTrue(mixoFlatSix.contains(AbsInterval.PERFECT_5));
    assertTrue(mixoFlatSix.contains(AbsInterval.MIN_6));
    assertTrue(mixoFlatSix.contains(AbsInterval.MIN_7));

    assertFalse(mixoFlatSix.contains(AbsInterval.MIN_2));
    assertFalse(mixoFlatSix.contains(AbsInterval.MIN_3));
    assertFalse(mixoFlatSix.contains(AbsInterval.AUG_4));
    assertFalse(mixoFlatSix.contains(AbsInterval.DIM_5));
    assertFalse(mixoFlatSix.contains(AbsInterval.TRITONE));
    assertFalse(mixoFlatSix.contains(AbsInterval.MAJ_6));
    assertFalse(mixoFlatSix.contains(AbsInterval.MAJ_7));

    ScaleSchema halfDim = HalfDiminishedScaleSchema.getInstance();
    assertTrue(halfDim.contains(AbsInterval.MAJ_2));
    assertTrue(halfDim.contains(AbsInterval.MIN_3));
    assertTrue(halfDim.contains(AbsInterval.PERFECT_4));
    assertTrue(halfDim.contains(AbsInterval.DIM_5));
    assertTrue(halfDim.contains(AbsInterval.TRITONE));
    assertTrue(halfDim.contains(AbsInterval.MIN_6));
    assertTrue(halfDim.contains(AbsInterval.MIN_7));

    assertFalse(halfDim.contains(AbsInterval.MIN_2));
    assertFalse(halfDim.contains(AbsInterval.MAJ_3));
    assertFalse(halfDim.contains(AbsInterval.MAJ_6));
    assertFalse(halfDim.contains(AbsInterval.MAJ_7));
    
    ScaleSchema superLocrian = SuperLocrianModeSchema.getInstance();
    assertTrue(superLocrian.contains(AbsInterval.MIN_2));
    assertTrue(superLocrian.contains(AbsInterval.MIN_3));
    assertTrue(superLocrian.contains(AbsInterval.DIM_4));
    assertTrue(superLocrian.contains(AbsInterval.DIM_5));
    assertTrue(superLocrian.contains(AbsInterval.TRITONE));
    assertTrue(superLocrian.contains(AbsInterval.MIN_6));
    assertTrue(superLocrian.contains(AbsInterval.MIN_7));

    assertFalse(superLocrian.contains(AbsInterval.MAJ_2));
    assertFalse(superLocrian.contains(AbsInterval.MAJ_6));
    assertFalse(superLocrian.contains(AbsInterval.MAJ_7));
    
    // synonyms
    assertTrue(aeolian.equals(minor));
    
    major = new ScaleSchema(MajorScaleSchema.SCHEMA);
    assertTrue(major.shiftUp(2).equals(dorian));
    assertTrue(major.shiftUp(2).equals(phrygian));
    assertTrue(major.shiftUp(1).equals(lydian));
    assertTrue(major.shiftUp(2).equals(mixo));
    assertTrue(major.shiftUp(2).equals(aeolian));
    assertTrue(major.shiftUp(2).equals(locrian));
  }

}