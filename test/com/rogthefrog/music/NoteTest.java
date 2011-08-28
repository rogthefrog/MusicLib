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
}
