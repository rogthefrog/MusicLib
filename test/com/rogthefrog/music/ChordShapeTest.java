package com.rogthefrog.music;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChordShapeTest {

  @Test
  public void testChordShape() {
    ChordShape cs = new ChordShape();
    assertEquals(0, cs.getBaseFret());
    assertEquals(6, cs.size());
  }

  @Test
  public void testChordShapeInt() {
    ChordShape cs = new ChordShape(5);
    assertEquals(5, cs.getBaseFret());
  }

  @Test
  public void testChordShapeTuning() {
    ChordShape cs = new ChordShape(TuningFactory.getTuning(TuningFactory.Tunings.STANDARD_7));
    assertEquals(0, cs.getBaseFret());
    assertEquals(7, cs.size());
  }

  @Test
  public void testChordShapeTuningInt() {
    ChordShape cs = new ChordShape(TuningFactory.getTuning(TuningFactory.Tunings.STANDARD_4), 5);
    assertEquals(5, cs.getBaseFret());
    assertEquals(4, cs.size());
  }

  @Test
  public void testAddNote() {
    ChordShape cs = new ChordShape();
    cs.addNote(0, 1);
  }
  
  @Test
  public void testNoteAt() {
    // default tuning
    ChordShape cs = new ChordShape();

    // string 0 (low E), finger on fret 1
    cs.addNote(0, 1);
    // should be an F
    assertTrue(cs.noteAt(0).equals(new Note(AbsNote.F, 2)));
    
    // string 1 (low A), finger on fret 3
    cs.addNote(1, 3);
    // should be a C
    assertTrue(cs.noteAt(1).equals(new Note(AbsNote.C, 3)));

    // string 4 (high B), open
    cs.addNote(4, 0);
    // should be a B
    assertTrue(cs.noteAt(4).equals(new Note(AbsNote.B, 3)));
    
    // string 5 (high E), finger on fret 3
    cs.addNote(5, 3);
    // should be a G
    assertTrue(cs.noteAt(5).equals(new Note(AbsNote.G, 4)));
    
    // let's give it a different tuning
    cs.setTuning(TuningFactory.getTuning(TuningFactory.Tunings.DADGAD));
    assertTrue(cs.noteAt(0).equals(new Note(AbsNote.D_SHARP, 2)));
    assertTrue(cs.noteAt(1).equals(new Note(AbsNote.C, 3)));
    assertTrue(cs.noteAt(4).equals(new Note(AbsNote.A, 3)));
    assertTrue(cs.noteAt(5).equals(new Note(AbsNote.F, 4)));
    
  }

  @Test
  public void testRemoveNote() {
    fail("Not yet implemented");
  }

  @Test
  public void testSize() {
    fail("Not yet implemented");
  }

}
