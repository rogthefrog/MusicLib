package com.rogthefrog.music;

public class TuningFactory {
  
  public enum Tunings {
    STANDARD,
    STANDARD_4,
    STANDARD_5,
    STANDARD_6,
    STANDARD_7,
    STANDARD_HALF_STEP_DOWN,
    STANDARD_WHOLE_STEP_DOWN,
    DROP_D,
    DADGAD;
  };
  
  
  public static Tuning getTuning(Tunings name) {
    // todo: put this in a config file
    Note[] notes;
    
    switch(name) {
      case STANDARD_4:
        notes = new Note[] {  new Note(AbsNote.E, 1), 
                              new Note(AbsNote.A, 1), 
                              new Note(AbsNote.D, 2), 
                              new Note(AbsNote.G, 2) };
        break;
      case STANDARD_5:
        notes = new Note[] {  new Note(AbsNote.B, 0), 
                              new Note(AbsNote.E, 1), 
                              new Note(AbsNote.A, 1), 
                              new Note(AbsNote.D, 2), 
                              new Note(AbsNote.G, 2) };
        break;
      case STANDARD_7:
        notes = new Note[] {  new Note(AbsNote.B, 1), 
                              new Note(AbsNote.E, 2), 
                              new Note(AbsNote.A, 2), 
                              new Note(AbsNote.D, 3), 
                              new Note(AbsNote.G, 3), 
                              new Note(AbsNote.B, 3), 
                              new Note(AbsNote.E, 4) };
        break;
      case STANDARD_HALF_STEP_DOWN:
        notes = new Note[] {  new Note(AbsNote.E_FLAT, 2), 
                              new Note(AbsNote.A_FLAT, 2), 
                              new Note(AbsNote.D_FLAT, 3), 
                              new Note(AbsNote.G_FLAT, 3), 
                              new Note(AbsNote.B_FLAT, 3), 
                              new Note(AbsNote.E_FLAT, 4) };
        break;
      case STANDARD_WHOLE_STEP_DOWN:
        notes = new Note[] {  new Note(AbsNote.D, 2), 
                              new Note(AbsNote.G, 2), 
                              new Note(AbsNote.C, 3), 
                              new Note(AbsNote.F, 3), 
                              new Note(AbsNote.A, 3), 
                              new Note(AbsNote.D, 4) };
        break;
      case DROP_D:
        notes = new Note[] {  new Note(AbsNote.D, 2), 
                              new Note(AbsNote.A, 2), 
                              new Note(AbsNote.D, 3), 
                              new Note(AbsNote.G, 3), 
                              new Note(AbsNote.B, 3), 
                              new Note(AbsNote.E, 4) };
        break;
      case DADGAD:
        notes = new Note[] {  new Note(AbsNote.D, 2), 
                              new Note(AbsNote.A, 2), 
                              new Note(AbsNote.D, 3), 
                              new Note(AbsNote.G, 3), 
                              new Note(AbsNote.A, 3), 
                              new Note(AbsNote.D, 4) };
        break;
      case STANDARD:
      case STANDARD_6:
      default:
        notes = new Note[] {  new Note(AbsNote.E, 2), 
                              new Note(AbsNote.A, 2), 
                              new Note(AbsNote.D, 3), 
                              new Note(AbsNote.G, 3), 
                              new Note(AbsNote.B, 3), 
                              new Note(AbsNote.E, 4) };
        break;
    }
    return new Tuning(notes);
  }
}