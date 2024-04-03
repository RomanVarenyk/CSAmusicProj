import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Note {

    private Random r = new Random();
    // An array of possible note values.
    private double[] ptValues = {4.0, 2.0, 1.0, 0.5, 0.25};
    private String type;
    private double value;
    // A map to associate note values with their musical representations.
    private static HashMap<Double, String> notesMap = new HashMap<>();
    // A list to store a series of notes, representing measures.
    private ArrayList<String> measures = new ArrayList<>();

    /**
     * Gets the value of Note
     *
     * @param n The note for which the value is to be retrieved
     * @return The value of the note
     */
    public double getValue(Note n) {
        return value;
    }

    /**
     * Gets the type of the note based on its value
     *
     * @param noteVal The value of the note
     * @return The type of the note.
     */
    public String getType(double noteVal) {
        return notesMap.get(noteVal);
    }

    /**
     * Constructer, initializes a note with a random type and value.
     */
    public Note() {
        notesMap.put(4.0, "whole");
        notesMap.put(2.0, "half");
        notesMap.put(1.0, "quarter");
        notesMap.put(0.5, "eighth");
        notesMap.put(0.25, "sixteenth");
        int i = r.nextInt(5);
        value = ptValues[i];
        type = notesMap.get(value);
        measures.add("whole");
    }

    /**
     * Populates the measures with a set number of measures worth of notes.
     * Ensures that the total beats match the specified number of measures.
     *
     * @param numMeasures The number of measures to populate.
     */
    public void populateMeasures(int numMeasures) {
        double beats = (numMeasures+1 * 4.0); // Assuming 4 beats per measure
        while (beats != 0.0) {
            int rand = r.nextInt(0, 5);
            double tryit = ptValues[rand];

            if (beats - tryit >= 0) {
                measures.add(notesMap.get(tryit));
                beats -= tryit;
            }
        }
    }

    /**
     * Retrieves the populated measures.
     *
     * @return An ArrayList containing the series of notes in the measures.
     */
    public ArrayList<String> getMeasures() {
        return measures;
    }
}
