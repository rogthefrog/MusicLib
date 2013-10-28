package com.rogthefrog.music;

import static org.junit.Assert.*;

import org.junit.Test;

public class NoteTest {

  @Test
  public void testNoteAbsNote() {
    Note n = new Note(AbsNote.A);
    assertEquals(n.getName(), AbsNote.A.toString());
  }

  @Test
  public void testNoteAbsNoteInt() {
    Note n = new Note(AbsNote.B, 2);
    assertEquals(n.getName(), AbsNote.B.toString());
    assertEquals(n.getFullName(), AbsNote.B.toString() + "2");
  }

  @Test
  public void testGetOctave() {
    Note n = new Note(AbsNote.B, 2);
    assertEquals(n.getOctave(), 2);
  }
  
  @Test
  public void testAdd() {
    Note n = new Note(AbsNote.C, 0);
    
    n.add(0);
    assertTrue(n.equals(new Note(AbsNote.C, 0)));
    
    n.add(1);
    assertTrue(n.equals(new Note(AbsNote.C_SHARP, 0)));

    n.add(-1);
    assertTrue(n.equals(new Note(AbsNote.C, 0)));
    
    n.add(2);
    assertTrue(n.equals(new Note(AbsNote.D, 0)));

    n.add(12);
    assertTrue(n.equals(new Note(AbsNote.D, 1)));
    
    n = new Note(AbsNote.C, 2);
    n.add(-3);
    assertTrue(n.equals(new Note(AbsNote.A, 1)));
    
    n = new Note(AbsNote.B, 3);
    n.add(1);
    assertTrue(n.equals(new Note(AbsNote.C, 4)));
    
  }
  
  @Test
  public void testIsLowerThan() {
    Note n = new Note(AbsNote.C, 2);
    
    assertTrue(n.isLowerThan(new Note(AbsNote.C_SHARP, 2)));
    assertTrue(n.isLowerThan(new Note(AbsNote.D, 2)));
    assertTrue(n.isLowerThan(new Note(AbsNote.D_SHARP, 2)));
    assertTrue(n.isLowerThan(new Note(AbsNote.E, 2)));
    assertTrue(n.isLowerThan(new Note(AbsNote.F, 2)));
    assertTrue(n.isLowerThan(new Note(AbsNote.F_SHARP, 2)));
    assertTrue(n.isLowerThan(new Note(AbsNote.G, 2)));
    assertTrue(n.isLowerThan(new Note(AbsNote.G_SHARP, 2)));
    assertTrue(n.isLowerThan(new Note(AbsNote.A, 3)));
    assertTrue(n.isLowerThan(new Note(AbsNote.A_SHARP, 3)));
    assertTrue(n.isLowerThan(new Note(AbsNote.B, 3)));
    assertTrue(n.isLowerThan(new Note(AbsNote.C, 3)));
    
    assertTrue(n.isHigherThan(new Note(AbsNote.C, 1)));
    assertTrue(n.isHigherThan(new Note(AbsNote.C_SHARP, 1)));
    assertTrue(n.isHigherThan(new Note(AbsNote.D, 1)));
    assertTrue(n.isHigherThan(new Note(AbsNote.D_SHARP, 1)));
    assertTrue(n.isHigherThan(new Note(AbsNote.E, 1)));
    assertTrue(n.isHigherThan(new Note(AbsNote.F, 1)));
    assertTrue(n.isHigherThan(new Note(AbsNote.F_SHARP, 1)));
    assertTrue(n.isHigherThan(new Note(AbsNote.G, 1)));
    assertTrue(n.isHigherThan(new Note(AbsNote.G_SHARP, 1)));
    assertTrue(n.isHigherThan(new Note(AbsNote.A, 1)));
    assertTrue(n.isHigherThan(new Note(AbsNote.A_SHARP, 1)));
    assertTrue(n.isHigherThan(new Note(AbsNote.B, 1)));
    
    assertFalse(n.isHigherThan(n));
    assertFalse(n.isLowerThan(n));
  }
}
