package com.rogthefrog.music;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbsIntervalTest {

  @Test
  public void testCompareNotesNoteNote() {
    AbsInterval i = AbsInterval.compareNotes(new Note(AbsNote.C), new Note(AbsNote.C));
    assertEquals(i, AbsInterval.UNISON);

    i = AbsInterval.compareNotes(new Note(AbsNote.C_SHARP), new Note(AbsNote.E));
    assertEquals(i, AbsInterval.MIN_3);
}

  @Test
  public void testCompareNotesAbsNoteAbsNote() {
    AbsInterval i = AbsInterval.compareNotes(AbsNote.C, AbsNote.C);
    assertEquals(i, AbsInterval.UNISON);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.C_SHARP);
    assertEquals(i, AbsInterval.MIN_2);

    i = AbsInterval.compareNotes(AbsNote.B, AbsNote.C);
    assertEquals(i, AbsInterval.MIN_2);

    i = AbsInterval.compareNotes(AbsNote.C_SHARP, AbsNote.C);
    assertEquals(i, AbsInterval.MAJ_7);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.D);
    assertEquals(i, AbsInterval.MAJ_2);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.D_SHARP);
    assertEquals(i, AbsInterval.MIN_3);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.E_FLAT);
    assertEquals(i, AbsInterval.MIN_3);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.E);
    assertEquals(i, AbsInterval.MAJ_3);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.F);
    assertEquals(i, AbsInterval.PERFECT_4);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.E_SHARP);
    assertEquals(i, AbsInterval.PERFECT_4);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.F_SHARP);
    assertEquals(i, AbsInterval.DIM_5);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.G_FLAT);
    assertEquals(i, AbsInterval.DIM_5);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.G);
    assertEquals(i, AbsInterval.PERFECT_5);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.G_SHARP);
    assertEquals(i, AbsInterval.MIN_6);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.A_FLAT);
    assertEquals(i, AbsInterval.MIN_6);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.A);
    assertEquals(i, AbsInterval.MAJ_6);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.A_SHARP);
    assertEquals(i, AbsInterval.MIN_7);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.B_FLAT);
    assertEquals(i, AbsInterval.MIN_7);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.B);
    assertEquals(i, AbsInterval.MAJ_7);
    
    i = AbsInterval.compareNotes(AbsNote.C, AbsNote.B_SHARP);
    assertEquals(i, AbsInterval.UNISON);
    
    i = AbsInterval.compareNotes(AbsNote.G_SHARP, AbsNote.A_FLAT);
    assertEquals(i, AbsInterval.UNISON);
    
    i = AbsInterval.compareNotes(AbsNote.F_SHARP, AbsNote.C);
    assertEquals(i, AbsInterval.DIM_5);
  }

  @Test
  public void testFromPitchInterval() {
    AbsInterval i = AbsInterval.fromPitchInterval(0);
    assertEquals(i, AbsInterval.UNISON);

    i = AbsInterval.fromPitchInterval(1);
    assertEquals(i, AbsInterval.MIN_2);

    i = AbsInterval.fromPitchInterval(2);
    assertEquals(i, AbsInterval.MAJ_2);

    i = AbsInterval.fromPitchInterval(3);
    assertEquals(i, AbsInterval.MIN_3);

    i = AbsInterval.fromPitchInterval(4);
    assertEquals(i, AbsInterval.MAJ_3);

    i = AbsInterval.fromPitchInterval(5);
    assertEquals(i, AbsInterval.PERFECT_4);

    i = AbsInterval.fromPitchInterval(6);
    assertEquals(i, AbsInterval.DIM_5);

    i = AbsInterval.fromPitchInterval(7);
    assertEquals(i, AbsInterval.PERFECT_5);

    i = AbsInterval.fromPitchInterval(8);
    assertEquals(i, AbsInterval.MIN_6);

    i = AbsInterval.fromPitchInterval(9);
    assertEquals(i, AbsInterval.MAJ_6);

    i = AbsInterval.fromPitchInterval(10);
    assertEquals(i, AbsInterval.MIN_7);

    i = AbsInterval.fromPitchInterval(11);
    assertEquals(i, AbsInterval.MAJ_7);

    i = AbsInterval.fromPitchInterval(12);
    assertEquals(i, AbsInterval.UNISON);
  }

  @Test
  public void testShortName() {
    AbsInterval i = AbsInterval.MAJ_3;
    assertEquals(i.shortName(), "M3");
  }

  @Test
  public void testLongName() {
    AbsInterval i = AbsInterval.MIN_3;
    assertEquals(i.longName(), "minor 3rd");
  }

  @Test
  public void testToString() {
    AbsInterval i = AbsInterval.PERFECT_5;
    assertEquals(i.toString(), "5");
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