package com.rogthefrog.music;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbsIntervalTest {

  @Test
  public void testCompareNotesNoteNote() {
    AbsInterval i = AbsInterval.compareNotes(new Note(AbsNote.C), new Note(AbsNote.C));
    assertEquals(AbsInterval.UNISON, i);

    i = AbsInterval.compareNotes(new Note(AbsNote.C_SHARP), new Note(AbsNote.E));
    assertEquals(AbsInterval.MIN_3, i);
}

  @Test
  public void testCompareNotesAbsNoteAbsNote() {
    AbsInterval i = AbsInterval.compareNotes(AbsNote.C, AbsNote.C);
    assertEquals(AbsInterval.UNISON, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.C_SHARP);
    assertEquals(AbsInterval.MIN_2, i);

    i = AbsInterval.compareNotes(AbsNote.B, AbsNote.C);
    assertEquals(AbsInterval.MIN_2, i);

    i = AbsInterval.compareNotes(AbsNote.C_SHARP, AbsNote.C);
    assertEquals(AbsInterval.MAJ_7, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.D);
    assertEquals(AbsInterval.MAJ_2, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.D_SHARP);
    assertEquals(AbsInterval.MIN_3, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.E_FLAT);
    assertEquals(AbsInterval.MIN_3, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.E);
    assertEquals(AbsInterval.MAJ_3, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.F);
    assertEquals(AbsInterval.PERFECT_4, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.E_SHARP);
    assertEquals(AbsInterval.PERFECT_4, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.F_SHARP);
    assertEquals(AbsInterval.DIM_5, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.G_FLAT);
    assertEquals(AbsInterval.DIM_5, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.G);
    assertEquals(AbsInterval.PERFECT_5, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.G_SHARP);
    assertEquals(AbsInterval.MIN_6, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.A_FLAT);
    assertEquals(AbsInterval.MIN_6, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.A);
    assertEquals(AbsInterval.MAJ_6, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.A_SHARP);
    assertEquals(AbsInterval.MIN_7, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.B_FLAT);
    assertEquals(AbsInterval.MIN_7, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.B);
    assertEquals(AbsInterval.MAJ_7, i);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.B_SHARP);
    assertEquals(AbsInterval.UNISON, i);
    
    i = AbsInterval.compareNotes(AbsNote.G_SHARP, AbsNote.A_FLAT);
    assertEquals(AbsInterval.UNISON, i);
    
    i = AbsInterval.compareNotes(AbsNote.F_SHARP, AbsNote.C);
    assertEquals(AbsInterval.DIM_5, i);
  }

  @Test
  public void testFromPitchInterval() {
    AbsInterval i = AbsInterval.fromPitchInterval(0);
    assertEquals(AbsInterval.UNISON, i);

    i = AbsInterval.fromPitchInterval(1);
    assertEquals(AbsInterval.MIN_2, i);

    i = AbsInterval.fromPitchInterval(2);
    assertEquals(AbsInterval.MAJ_2, i);

    i = AbsInterval.fromPitchInterval(3);
    assertEquals(AbsInterval.MIN_3, i);

    i = AbsInterval.fromPitchInterval(4);
    assertEquals(AbsInterval.MAJ_3, i);

    i = AbsInterval.fromPitchInterval(5);
    assertEquals(AbsInterval.PERFECT_4, i);

    i = AbsInterval.fromPitchInterval(6);
    assertEquals(AbsInterval.DIM_5, i);

    i = AbsInterval.fromPitchInterval(7);
    assertEquals(AbsInterval.PERFECT_5, i);

    i = AbsInterval.fromPitchInterval(8);
    assertEquals(AbsInterval.MIN_6, i);

    i = AbsInterval.fromPitchInterval(9);
    assertEquals(AbsInterval.MAJ_6, i);

    i = AbsInterval.fromPitchInterval(10);
    assertEquals(AbsInterval.MIN_7, i);

    i = AbsInterval.fromPitchInterval(11);
    assertEquals(AbsInterval.MAJ_7, i);

    i = AbsInterval.fromPitchInterval(12);
    assertEquals(AbsInterval.UNISON, i);
  }

  @Test
  public void testShortName() {
    AbsInterval i = AbsInterval.MAJ_3;
    assertEquals("M3", i.shortName());
  }

  @Test
  public void testLongName() {
    AbsInterval i = AbsInterval.MIN_3;
    assertEquals("minor 3rd", i.longName());
  }

  @Test
  public void testToString() {
    AbsInterval i = AbsInterval.PERFECT_5;
    assertEquals("5", i.toString());
  }
  
  @Test
  public void testIsMajor() {
    assertTrue(AbsInterval.isMajor(new Note(AbsNote.C), new Note(AbsNote.E)));
    assertTrue(AbsInterval.isMajor(new Note(AbsNote.D), new Note(AbsNote.F_SHARP)));
    assertTrue(AbsInterval.isMajor(new Note(AbsNote.D), new Note(AbsNote.B)));
    assertTrue(AbsInterval.isMajor(new Note(AbsNote.D), new Note(AbsNote.C_SHARP)));
  }

  @Test
  public void testIsMinor() {
    assertTrue(AbsInterval.isMinor(new Note(AbsNote.C), new Note(AbsNote.E_FLAT)));
    assertTrue(AbsInterval.isMinor(new Note(AbsNote.D), new Note(AbsNote.F)));
    assertTrue(AbsInterval.isMinor(new Note(AbsNote.D), new Note(AbsNote.B_FLAT)));
    assertTrue(AbsInterval.isMinor(new Note(AbsNote.D), new Note(AbsNote.C)));
  }
}