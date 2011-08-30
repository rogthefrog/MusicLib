package com.rogthefrog.music;

import static org.junit.Assert.*;
import org.junit.Test;

public class TuningTest {
  
  @Test
  public void testAddString() {
    Tuning t = new Tuning(3);
    t.addNote(new Note(AbsNote.C, 3));
    try {
      t.setNoteAt(new Note(AbsNote.C, 3), 5);
    } catch (Exception e) {
      assertEquals("java.lang.IllegalArgumentException", e.getClass().getName());
      assertEquals("Bad position: 5", e.getMessage());
    }
  }
  
  @Test
  public void testSize() {
    Tuning t = new Tuning(new Note[]{ new Note(AbsNote.E, 2), new Note(AbsNote.A, 2), new Note(AbsNote.D, 2) });
    assertEquals(3, t.size());
  }
  
  @Test
  public void testFactory() {
    Tuning t = TuningFactory.getTuning(TuningFactory.Tunings.DADGAD);
    assertEquals(6, t.size());
  }
}