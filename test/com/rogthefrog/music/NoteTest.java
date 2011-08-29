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
}
