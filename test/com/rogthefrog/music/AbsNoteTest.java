package com.rogthefrog.music;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbsNoteTest {

  @Test
  public void testFromPitch() {
    assertTrue(AbsNote.A.equals(AbsNote.fromPitch(0)));

    assertTrue(AbsNote.A_SHARP.equals(AbsNote.fromPitch(1)));
    assertTrue(AbsNote.B_FLAT.equals(AbsNote.fromPitch(1)));
    
    assertTrue(AbsNote.B.equals(AbsNote.fromPitch(2)));
    assertTrue(AbsNote.C_FLAT.equals(AbsNote.fromPitch(2)));
    
    assertTrue(AbsNote.C.equals(AbsNote.fromPitch(3)));
    assertTrue(AbsNote.B_SHARP.equals(AbsNote.fromPitch(3)));

    assertTrue(AbsNote.C_SHARP.equals(AbsNote.fromPitch(4)));
    assertTrue(AbsNote.D_FLAT.equals(AbsNote.fromPitch(4)));
    
    assertTrue(AbsNote.D.equals(AbsNote.fromPitch(5)));

    assertTrue(AbsNote.D_SHARP.equals(AbsNote.fromPitch(6)));
    assertTrue(AbsNote.E_FLAT.equals(AbsNote.fromPitch(6)));

    assertTrue(AbsNote.E.equals(AbsNote.fromPitch(7)));
    
    assertTrue(AbsNote.E_SHARP.equals(AbsNote.fromPitch(8)));
    assertTrue(AbsNote.F.equals(AbsNote.fromPitch(8)));
        
    assertTrue(AbsNote.F_SHARP.equals(AbsNote.fromPitch(9)));
    assertTrue(AbsNote.G_FLAT.equals(AbsNote.fromPitch(9)));

    assertTrue(AbsNote.G.equals(AbsNote.fromPitch(10)));
    
    assertTrue(AbsNote.G_SHARP.equals(AbsNote.fromPitch(11)));
    assertTrue(AbsNote.A_FLAT.equals(AbsNote.fromPitch(11)));
    
    // test the wrap
    assertTrue(AbsNote.A_FLAT.equals(AbsNote.fromPitch(-1)));
    assertTrue(AbsNote.G_SHARP.equals(AbsNote.fromPitch(-1)));
    assertTrue(AbsNote.G.equals(AbsNote.fromPitch(-2)));
    assertTrue(AbsNote.A.equals(AbsNote.fromPitch(12)));
    assertTrue(AbsNote.B.equals(AbsNote.fromPitch(14)));
    
  }

  @Test
  public void testWrapPitch() {
    assertEquals(0, AbsNote.wrapPitch(0));
    assertEquals(0, AbsNote.wrapPitch(12));
    assertEquals(1, AbsNote.wrapPitch(13));
    assertEquals(11, AbsNote.wrapPitch(-1));
  }

  @Test
  public void testEquals() {
    assertTrue(AbsNote.A.equals(AbsNote.A));
    assertTrue(AbsNote.C_SHARP.equals(AbsNote.D_FLAT));
    assertFalse(AbsNote.C_SHARP.equals(AbsNote.D));
  }

  @Test
  public void testGetPitch() {
    /*
     *   A       (0,   "A",  "la"),

  A_SHARP (1,  "A#", "la #"),
  B_FLAT  (1,  "Bb", "si b",   "ti b"),
  
  B       (2,  "B", "si",      "ti"),
  C_FLAT  (2,  "Cb", "do b"), 

  B_SHARP (3,   "B#", "si #",   "ti #"),

  C       (3,   "C",  "do"),
  
  C_SHARP (4,   "C#", "do #"),
  D_FLAT  (4,   "Db", "re b"),
  
  D       (5,   "D",  "re"),
  
  D_SHARP (6,   "D",  "re #"),
  E_FLAT  (6,   "D",  "mi b"),
  
  E       (7,   "E",  "mi"),
  
  F       (8,   "F",  "fa"),
  E_SHARP (8,   "E#", "mi #"),
  
  F_SHARP (9,   "F#", "fa #"),
  G_FLAT  (9,   "Gb", "sol b",  "so b"),
  
  G       (10,   "G",  "sol",    "so"),

  G_SHARP (11,   "G#", "sol #",  "so #"),
  A_FLAT  (11,   "Ab", "la b");
     * */
    
    
    assertEquals(0, AbsNote.A.getPitch());
    
    assertEquals(1, AbsNote.A_SHARP.getPitch());
    assertEquals(1, AbsNote.B_FLAT.getPitch());
    
    assertEquals(2, AbsNote.B.getPitch());
    assertEquals(2, AbsNote.C_FLAT.getPitch());
    
    assertEquals(3, AbsNote.C.getPitch());
    assertEquals(3, AbsNote.B_SHARP.getPitch());
    
    assertEquals(4, AbsNote.C_SHARP.getPitch());
    assertEquals(4, AbsNote.D_FLAT.getPitch());
    
    assertEquals(5, AbsNote.D.getPitch());
    
    assertEquals(6, AbsNote.D_SHARP.getPitch());
    assertEquals(6, AbsNote.E_FLAT.getPitch());
    
    assertEquals(7, AbsNote.E.getPitch());
    
    assertEquals(8, AbsNote.F.getPitch());
    assertEquals(8, AbsNote.E_SHARP.getPitch());
    
    assertEquals(9, AbsNote.F_SHARP.getPitch());
    assertEquals(9, AbsNote.G_FLAT.getPitch());
    
    assertEquals(10, AbsNote.G.getPitch());
    
    assertEquals(11, AbsNote.G_SHARP.getPitch());
    assertEquals(11, AbsNote.A_FLAT.getPitch());
    
  }

}
