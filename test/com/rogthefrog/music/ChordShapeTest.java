package com.rogthefrog.music;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rogthefrog.music.TuningFactory.Tunings;

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
    cs.setStringAt(0, 1);
    assertTrue(new Note(AbsNote.F, 2).equals(cs.noteAt(0)));
  }
  
  @Test
  public void testNoteAt() {
    // default tuning
    ChordShape cs = new ChordShape();

    // string 0 (low E), finger on fret 1
    cs.setStringAt(0, 1);
    // should be an F
    assertTrue(cs.noteAt(0).equals(new Note(AbsNote.F, 2)));
    
    // string 1 (low A), finger on fret 3
    cs.setStringAt(1, 3);
    // should be a C
    assertTrue(cs.noteAt(1).equals(new Note(AbsNote.C, 3)));

    // string 4 (high B), open
    cs.setStringAt(4, 0);
    // should be a B
    assertTrue(cs.noteAt(4).equals(new Note(AbsNote.B, 3)));
    
    // string 5 (high E), finger on fret 3
    cs.setStringAt(5, 3);
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
  public void testFindLowestNote() {
    // default tuning
    ChordShape cs = new ChordShape();

    // C major
    // string 0 (low E), unused
    // string 1 (A), finger on fret 3
    // string 2 (D), finger on fret 2
    // string 3 (G), open
    // string 4 (B), finger on fret 3
    // string 5 (high E), finger on fret 3
    cs.setStringAt(5, 0)
      .setStringAt(4, 1)
      .setStringAt(3, 0)
      .setStringAt(2, 3)
      .setStringAt(1, 3)
      .removeNote(0);
    assertTrue(cs.findLowestNote().equals(new Note(AbsNote.C, 3)));
    
    // add low G
    cs.setStringAt(0, 3);
    assertTrue(cs.findLowestNote().equals(new Note(AbsNote.G, 2)));
    
    // remove all notes
    cs.removeNote(0)
      .removeNote(1)
      .removeNote(2)
      .removeNote(3)
      .removeNote(4)
      .removeNote(5);
    assertNull(cs.findLowestNote());
  }

  @Test
  public void testGetChord() {
    // default tuning
    ChordShape cs = new ChordShape();
    cs.setStringAt(1, 3)    // C
      .setStringAt(2, 2)    // E
      .setStringAt(3, 0)    // G
      .setStringAt(4, 1)    // C
      .setStringAt(5, 0);   // E
    Chord chord = cs.getChord();
    System.out.println(chord.dump());
  }

  @Test
  public void testRemoveNote() {
    // default tuning
    ChordShape cs = new ChordShape();
    cs.setStringAt(1, 3)    // C
      .setStringAt(2, 2)    // E
      .setStringAt(3, 0)    // G
      .setStringAt(4, 1)    // C
      .setStringAt(5, 0);   // E
    assertTrue(cs.noteAt(1).equals(new Note(AbsNote.C, 3)));
    cs.removeNote(1);
    assertNull(cs.noteAt(1));
  }

  @Test
  public void testSize() {
    // default tuning
    ChordShape cs = new ChordShape();
    assertEquals(6, cs.size());
    cs = new ChordShape(TuningFactory.getTuning(Tunings.STANDARD_7));
    assertEquals(7, cs.size());
  }
  
  @Test
  public void testAnalyze() {
    ChordShape cs = new ChordShape();
    cs.setStringAt(1, 3)    // C
      .setStringAt(2, 2)    // E
      .setStringAt(3, 0)    // G
      .setStringAt(4, 1)    // C
      .setStringAt(5, 0);   // E
    Chord c = cs.analyze();
    assertTrue(c.getRoot().equals(AbsNote.C));
  }
}