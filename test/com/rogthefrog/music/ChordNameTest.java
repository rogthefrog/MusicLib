package com.rogthefrog.music;

import static org.junit.Assert.*;

import org.junit.Test;

public class ChordNameTest {

  @Test
  public void testGetName() {
    Note cee = new Note(AbsNote.C);
    Note dee = new Note(AbsNote.D);
    Note eee = new Note(AbsNote.E_FLAT);
    Note gee = new Note(AbsNote.G);
    Note bee = new Note(AbsNote.B);
    Chord c = new Chord(AbsNote.C);
    c.addNote(cee);
    c.addNote(dee);
    c.addNote(eee);
    c.addNote(gee);
    c.addNote(bee);
    
    c.analyze();
    
    ChordName cn = new ChordName(c);
    System.out.println(cn.getName());
    
  }
}
