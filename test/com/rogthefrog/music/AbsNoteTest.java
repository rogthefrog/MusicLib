package com.rogthefrog.music;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbsNoteTest {

  @Test
  public void testFromPitch() {
    assertTrue(AbsNote.C.equals(AbsNote.fromPitch(0)));
    assertTrue(AbsNote.B_SHARP.equals(AbsNote.fromPitch(0)));

    assertTrue(AbsNote.C_SHARP.equals(AbsNote.fromPitch(1)));
    assertTrue(AbsNote.D_FLAT.equals(AbsNote.fromPitch(1)));
    
    assertTrue(AbsNote.D.equals(AbsNote.fromPitch(2)));

    assertTrue(AbsNote.D_SHARP.equals(AbsNote.fromPitch(3)));
    assertTrue(AbsNote.E_FLAT.equals(AbsNote.fromPitch(3)));

    assertTrue(AbsNote.E.equals(AbsNote.fromPitch(4)));
    
    assertTrue(AbsNote.E_SHARP.equals(AbsNote.fromPitch(5)));
    assertTrue(AbsNote.F.equals(AbsNote.fromPitch(5)));
        
    assertTrue(AbsNote.F_SHARP.equals(AbsNote.fromPitch(6)));
    assertTrue(AbsNote.G_FLAT.equals(AbsNote.fromPitch(6)));

    assertTrue(AbsNote.G.equals(AbsNote.fromPitch(7)));
    
    assertTrue(AbsNote.G_SHARP.equals(AbsNote.fromPitch(8)));
    assertTrue(AbsNote.A_FLAT.equals(AbsNote.fromPitch(8)));
    
    assertTrue(AbsNote.A.equals(AbsNote.fromPitch(9)));

    assertTrue(AbsNote.A_SHARP.equals(AbsNote.fromPitch(10)));
    assertTrue(AbsNote.B_FLAT.equals(AbsNote.fromPitch(10)));
    
    assertTrue(AbsNote.B.equals(AbsNote.fromPitch(11)));
    assertTrue(AbsNote.C_FLAT.equals(AbsNote.fromPitch(11)));
    
    // test the wrap
    assertTrue(AbsNote.B.equals(AbsNote.fromPitch(-1)));
    assertTrue(AbsNote.A_SHARP.equals(AbsNote.fromPitch(-2)));
    assertTrue(AbsNote.B_FLAT.equals(AbsNote.fromPitch(-2)));
    assertTrue(AbsNote.C.equals(AbsNote.fromPitch(12)));
    assertTrue(AbsNote.D.equals(AbsNote.fromPitch(14)));
    
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
    assertEquals(0, AbsNote.C.getPitch());
    assertEquals(0, AbsNote.B_SHARP.getPitch());
    
    assertEquals(1, AbsNote.C_SHARP.getPitch());
    assertEquals(1, AbsNote.D_FLAT.getPitch());
    
    assertEquals(2, AbsNote.D.getPitch());
    
    assertEquals(3, AbsNote.D_SHARP.getPitch());
    assertEquals(3, AbsNote.E_FLAT.getPitch());
    
    assertEquals(4, AbsNote.E.getPitch());
    
    assertEquals(5, AbsNote.F.getPitch());
    assertEquals(5, AbsNote.E_SHARP.getPitch());
    
    assertEquals(6, AbsNote.F_SHARP.getPitch());
    assertEquals(6, AbsNote.G_FLAT.getPitch());
    
    assertEquals(7, AbsNote.G.getPitch());
    
    assertEquals(8, AbsNote.G_SHARP.getPitch());
    assertEquals(8, AbsNote.A_FLAT.getPitch());
    
    assertEquals(9, AbsNote.A.getPitch());
    
    assertEquals(10, AbsNote.A_SHARP.getPitch());
    assertEquals(10, AbsNote.B_FLAT.getPitch());
    
    assertEquals(11, AbsNote.B.getPitch());
    assertEquals(11, AbsNote.C_FLAT.getPitch());
  }

}
