
import java.util.ArrayList;
import java.util.HashMap;


public class makeOwn {
    // secconds of each note
    private HashMap<Double, String> notes = new HashMap<>();

    // Stroes length of each note in play.
    private ArrayList<String> measures = new ArrayList<>();

    /**
     * Constructor for {@code makeOwn} class.
     * Sets note timings
     */
    public makeOwn() {
        notes.put(4.0, "whole");
        notes.put(2.0, "half");
        notes.put(1.0, "quarter");
        notes.put(0.5, "eighth");
        notes.put(0.25, "sixteenth");
    }

    /**
     * ability to add measure to custom music
     *
     * @param raw Unprocessed string
     */
    public void addMeasure(String raw) {
        String[] rawNotes = raw.split(" ");
        // Adds each note in the input string to the measures list
        for (String note : rawNotes) {
            measures.add(note);
        }
    }
    // Removes the last note added to the measures list
public void removeLast(){
        measures.remove(measures.size()-1);
}
    /**
     * gets current timingss.
     *
     * @return An ArrayList of notes to be used in game
     */
    public ArrayList<String> getMusic() {
        return measures;
    }
}
