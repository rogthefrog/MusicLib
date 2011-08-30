package com.rogthefrog.music;

import java.util.ArrayList;

public class Chord {
  private ArrayList<Note> notes;
  private AbsNote root      = null;
  private Note third        = null;
  private Note seventh      = null;
  private boolean analyzed  = false;

  /**
   * bare constructor
   */
  public Chord() {
    notes   = new ArrayList<Note>();
  }
  
  /**
   * constructor
   * @param n list of note objects
   */
  public Chord(ArrayList<Note> n) {
    notes   = new ArrayList<Note>();
    notes.addAll(n);
  }
  
  /**
   * constructor
   * @param root note
   */
  public Chord(AbsNote rt) {
    root    = rt;
    notes   = new ArrayList<Note>();
  }
  
  /**
   * constructor
   * @param rt root note
   * @param n list of notes 
   */
  public Chord(AbsNote rt, ArrayList<Note> n) {
    root    = rt;
    notes   = new ArrayList<Note>();
    notes.addAll(n);
  }

  /**
   * add a note to a chord
   * @param n the note to add
   * @return the newly modified chord
   */
  public Chord addNote(Note n) {
    if (notes == null) {
      notes = new ArrayList<Note>();
    }
    notes.add(n);
    analyze();
    return this;
  }
  
  /**
   * removes a note from a chord
   * @param removeMe the note to remove
   * @return the newly modified chord
   */
  public Chord removeExactNote(Note removeMe) {
    // can't use contains b/c of object copy v. exact object
    ArrayList<Note> toRemove = new ArrayList<Note>();
    for (Note n: notes) {
      if (removeMe.equals(n)) {
        toRemove.add(n);
      }
    }
    if (!toRemove.isEmpty()) {
      notes.removeAll(toRemove);
    }
    analyze();
    return this;
  }
  
  /**
   * removes note(s) from a chord given its value
   * e.g. if a chord has two C notes, it will remove them all
   * @param removeMe the abs note to remove
   * @return the newly modified chord
   */
  public Chord removeAll(AbsNote removeMe) {
    // can't use contains b/c of object copy v. exact object
    ArrayList<Note> toRemove = new ArrayList<Note>();
    for (Note n: notes) {
      if (removeMe.equals(n.getNote())) {
        toRemove.add(n);
      }
    }
    if (!toRemove.isEmpty()) {
      notes.removeAll(toRemove);
    }
    analyze();
    return this;
  }
  
  /**
   * removes all the notes
   * @return the newly emptied chord (keep the root)
   */
  public Chord clear() {
    notes.clear();
    third     = null;
    seventh   = null;
    analyzed  = false;
    return this;
  }
  
  /**
   * @param n exact note to look for (e.g. "C3")
   * @return true if found, false otherwise
   */
  public boolean containsExactNote(Note findMe) {
    // can't use contains due to object copying
    boolean found = false;
    for (Note note: notes) {
      if (note.equals(findMe)) {
        found = true;
        break;
      }
    }
    return found;
  }
  
  /**
   * @param n abstract note to find (e.g. "C")
   * @return true if found, false otherwise
   */
  public boolean containsNote(AbsNote findMe) {
    boolean found = false;
    for (Note note: notes) {
      if (note.isA(findMe)) {
        found = true;
        break;
      }
    }
    return found;
  }  
  
  /**
   * @return number of notes in the chord
   */
  public int size() {
    return (notes == null) ? 0 : notes.size();
  }
  
  /**
   * @return the chord's root
   */
  public AbsNote getRoot() {
    return root;
  }
  
  /**
   * sets the root note
   * @param n root note
   * @return this chord
   */
  public Chord setRoot(AbsNote n) {
    root = n;
    return this;
  }
  
  /**
   * @return true if there's a 3rd in the chord
   */
  public boolean hasThird() {
    if (!analyzed) {
      analyze();
    }
    return (third != null);
  }
  
  /**
   * @return true if there's a 7th in the chord
   */
  public boolean hasSeventh() {
    if (!analyzed) {
      analyze();
    }    
    return (seventh != null);
  }

  /**
   * finds the third in the chord
   * convenience for findNoteAtInterval(Interval.MIN_3) || findNoteAtInterval(Interval.MAJ_3)
   * @return the Note that is the third in the chord
   */
  public Note findThird() {
    third = findNoteAtInterval(AbsInterval.MIN_3);
    if (third == null) {
      third = findNoteAtInterval(AbsInterval.MAJ_3);
    }
    return third;
  }
  
  /**
   * finds the 7th in the chord
   * convenience for findNoteAtInterval(Interval.MIN_7) || findNoteAtInterval(Interval.MAJ_7)
   * we need to have a root for this
   * @return the Note that is the 7th in the chord
   */
  public Note findSeventh() {
    seventh = findNoteAtInterval(AbsInterval.MIN_7);
    if (seventh == null) {
      seventh = findNoteAtInterval(AbsInterval.MAJ_7);
    }
    return seventh;
  }
  
  /**
   * finds the root in the chord
   * returns the actual note in the chord whose AbsNote is this.root
   * if there isn't one, it returns a Note whose AbsNote is this.root
   * and whose octave is such that this Note is the lowest in the chord
   * @return the Note that is the root
   */
  public Note findRoot() {
    Note theRoot = new Note(AbsNote.C);
    root = theRoot.getNote();
    return theRoot;
  }
  
  /**
   * guesses the root if there isn't one
   * @return the chord's root 
   * (AbsNote, not the specific note in the list of notes)
   */
  public AbsNote guessRoot() {
    if (root != null) {
      return root;
    } else {
      root = AbsNote.C;
    }
    // TODO fix this
    return root;
  }
  
  /**
   * finds a given interval from the root in the chord
   * @param in interval to find
   * @return the note that's the given interval relative from root
   */
  public Note findNoteAtInterval(AbsInterval in) {
    if (root == null) {
      guessRoot();
    }
    // iterate through chord notes
    // find diff. between root and current note
    // if that diff is the interval, we can has
    Note rootNote = new Note(root);
    Note theNote = null;
    AbsInterval diff = AbsInterval.UNISON;
    
    for (Note note: notes) {
      diff = AbsInterval.compareNotes(rootNote, note);
      if (diff.equals(in)) {
        theNote = note;
        break;
      }
    }
    return theNote;
  }
  
  /**
   * analyzes the chord
   * @return the chord
   */
  public Chord analyze() {
    analyzed = false;
    
    guessRoot();
    findThird();
    findSeventh();
    
    analyzed = true;
    return this;
  }
  
  public String notesToString() {
    StringBuffer sb = new StringBuffer("Just the notes\n");
    for (Note n: notes) {
      sb.append(n.toString()).append("\n");
    }
    return sb.toString();
  }
  
  public String dump() {
    StringBuffer sb = new StringBuffer("Chord analysis\n");
    sb.append("Notes: ")
      .append(notes.toString())
      .append("\nRoot: ")
      .append(root.toString())
      .append("\nThird: ")
      .append(third == null ? "none" : third.toString())
      .append(third != null && AbsInterval.isMajor(root, third.getNote()) ? " (major)" : " (minor)")
      .append("\nSeventh: ")
      .append(seventh == null ? "none" : seventh.toString())
      .append(seventh != null && AbsInterval.isMajor(root, seventh.getNote()) ? " (major)" : " (minor)");
    return sb.toString();
  }
}
