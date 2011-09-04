package com.rogthefrog.music;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ChordTest {

  @Test
  public void testChord() {
    Chord c = new Chord();
    assertNull(c.getRoot());
  }

  @Test
  public void testChordArrayListOfNote() {
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(AbsNote.C, 2));
    notes.add(new Note(AbsNote.E, 2));
    notes.add(new Note(AbsNote.G, 2));
    Chord c = new Chord(notes);
    assertEquals(c.size(), notes.size());
  }

  @Test
  public void testChordAbsNote() {
    Chord c = new Chord(AbsNote.D);
    assertEquals(c.getRoot(), AbsNote.D);
  }

  @Test
  public void testChordAbsNoteArrayListOfNote() {
    ArrayList<Note> notes = new ArrayList<Note>();
    notes.add(new Note(AbsNote.C, 2));
    notes.add(new Note(AbsNote.E, 2));
    notes.add(new Note(AbsNote.G, 2));
    Chord c = new Chord(AbsNote.C, notes);
    assertEquals(c.getRoot(), AbsNote.C);
    assertEquals(c.size(), notes.size());
  }

  @Test
  public void testAddNote() {
    Chord c = new Chord(AbsNote.C);
    c.addNote(new Note(AbsNote.C, 3));
    assertEquals(c.size(), 1);
    
    c.addNote(new Note(AbsNote.E, 3));
    assertEquals(c.size(), 2);
  }
  
  @Test
  public void testRemoveExactNote() {
    Chord c = new Chord(AbsNote.C);
    Note cee = new Note(AbsNote.C, 3);
    c.addNote(cee);
    assertEquals(c.size(), 1);

    c.addNote(new Note(AbsNote.E, 3));
    assertEquals(c.size(), 2);
    
    c.removeExactNote(cee);
    assertEquals(c.size(), 1);
    
    c.addNote(cee);
    assertEquals(c.size(), 2);
    
    c.removeExactNote(new Note(AbsNote.C, 2)); // shouldn't remove
    assertEquals(c.size(), 2);
    
    c.removeExactNote(new Note(AbsNote.C, 3)); // should remove
    assertEquals(c.size(), 1);
  }
  
  @Test
  public void testRemoveAll() {
    Chord c = new Chord(AbsNote.C);
    Note cee = new Note(AbsNote.C, 1);
    Note ceeee = new Note(AbsNote.C, 5);
    Note gsh = new Note(AbsNote.G_SHARP, 3);
    c.addNote(cee);
    c.addNote(ceeee);
    c.addNote(gsh);
    assertEquals(c.size(), 3);
    
    c.removeAll(AbsNote.C);
    assertEquals(c.size(), 1); // should remove both Cs
  }
  
  @Test
  public void testContainsExactNote() {
    Chord c = new Chord(AbsNote.C);
    Note cee = new Note(AbsNote.C, 3);
    c.addNote(cee);
    assertTrue(c.containsExactNote(cee));
    assertTrue(c.containsExactNote(new Note(AbsNote.C, 3))); // diff object but same as cee
    assertFalse(c.containsExactNote(new Note(AbsNote.C, 2))); // same note but diff octave
  }
  
  @Test
  public void testContainsNote() {
    Chord c = new Chord(AbsNote.C);
    Note cee = new Note(AbsNote.C, 3);
    Note ceeee = new Note(AbsNote.C, 5);
    c.addNote(cee);
    c.addNote(ceeee);
    assertTrue(c.containsNote(AbsNote.C));
  }
    
  @Test
  public void testSize() {
    Chord c = new Chord(AbsNote.C);
    c.addNote(new Note(AbsNote.C, 3));
    assertEquals(c.size(), 1);
  }

  @Test
  public void testSetRoot() {
    Chord c = new Chord(AbsNote.A);
    assertEquals(c.getRoot(), AbsNote.A);
    
    c.setRoot(AbsNote.F_SHARP);
    assertEquals(c.getRoot(), AbsNote.F_SHARP);
  }

  @Test
  public void testHasThird() {
    Note cee = new Note(AbsNote.C);
    Note eee = new Note(AbsNote.E);
    Chord c = new Chord(AbsNote.C);
    c.addNote(cee);
    c.addNote(eee);
    c.analyze();
    assertTrue(c.hasThird());

    c.removeExactNote(eee);
    assertFalse(c.hasThird());
    
  }

  @Test
  public void testHasSeventh() {
    Note cee = new Note(AbsNote.C);
    Note eee = new Note(AbsNote.E);
    Note bee = new Note(AbsNote.B);
    Chord c = new Chord(AbsNote.C);
    c.addNote(cee);
    c.addNote(eee);
    c.addNote(bee);
    c.analyze();
    assertTrue(c.hasThird());

    c.removeExactNote(eee);
    assertFalse(c.hasThird());
  }

  @Test
  public void testFindRoot() {
    fail("not implemented");
  }

  @Test
  public void testAnalyze() {
    Note cee = new Note(AbsNote.C);
    Note eee = new Note(AbsNote.E);
    Note gee = new Note(AbsNote.G);
    Note bee = new Note(AbsNote.B);
    
    Chord c = new Chord(AbsNote.C);
    c.addNote(cee);
    c.addNote(eee);
    c.addNote(gee);

    assertNotNull(c.findThird());
    assertNull(c.findSeventh());
    
    c.addNote(bee);
    assertNotNull(c.findSeventh());
    
    c.removeExactNote(eee);
    assertNull(c.findThird());
    
    c.addNote(eee);
    c.addNote(new Note(AbsNote.A));
    
    System.out.println(c.dump());
  }

  @Test
  public void testFindThird() {
    Note cee = new Note(AbsNote.C);
    Note eee = new Note(AbsNote.E);
    Note gee = new Note(AbsNote.G);
    Note bee = new Note(AbsNote.B);
    Chord c = new Chord(AbsNote.C);
    c.addNote(cee);
    c.addNote(eee);
    c.addNote(gee);
    c.addNote(bee);
    assertNotNull(c.findThird());
    assertTrue(c.findThird().equals(new Note(AbsNote.E)));
    assertTrue(c.findThird().equals(eee));
    
    // remove the third now
    c.removeExactNote(eee);
    assertNull(c.findThird());
  }
  
  @Test
  public void testFindSeventh() {
    Note cee = new Note(AbsNote.C);
    Note eee = new Note(AbsNote.E);
    Note gee = new Note(AbsNote.G);
    Note bee = new Note(AbsNote.B);
    Chord c = new Chord(AbsNote.C);
    c.addNote(cee);
    c.addNote(eee);
    c.addNote(gee);
    c.addNote(bee);
    assertNotNull(c.findSeventh());
    assertTrue(c.findSeventh().equals(new Note(AbsNote.B)));
    assertTrue(c.findSeventh().equals(bee));
    
    // remove the seventh now
    c.removeExactNote(bee);
    assertNull(c.findSeventh());
  }
  
  @Test
  public void testFindNoteAtInterval() {
    Note cee = new Note(AbsNote.C);
    Note eee = new Note(AbsNote.E);
    Note gee = new Note(AbsNote.G);
    Note fsh = new Note(AbsNote.F_SHARP);
    Chord c = new Chord(AbsNote.C);
    c.addNote(cee);
    c.addNote(eee);
    c.addNote(gee);
    assertEquals(c.findNoteAtInterval(AbsInterval.MAJ_3), eee);
    assertEquals(c.findNoteAtInterval(AbsInterval.PERFECT_5), gee);
    
    c.addNote(fsh);
    assertEquals(c.findNoteAtInterval(AbsInterval.DIM_5), fsh);
    assertEquals(c.findNoteAtInterval(AbsInterval.AUG_4), fsh);
  }
}
