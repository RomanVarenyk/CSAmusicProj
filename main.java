import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Denotes using user input or auto generated
        boolean useOwn = true;

        // Creating an instance of Note
        Note note = new Note();
        // Populating measures with randomly
        note.populateMeasures(2);

        // ArrayList to store measures
        ArrayList<String> music = new ArrayList<>();

        // Creating an instance of makeOwn
        makeOwn makeOwn = new makeOwn();

        // decide if used user generated or auto generated
        if (useOwn) {
            makeOwn.addMeasure("quarter quarter quarter quarter");
            music = makeOwn.getMusic();
        } else {
            music = note.getMeasures();
        }

        // ArrayList to store the duration of each note
        ArrayList<Double> notes = new ArrayList<>();

        // Convert string notes to computer usable timing
        for (int i = 0; i < music.size(); i++) {
            System.out.print(music.get(i) + " ");
            switch (music.get(i)) {
                case "whole":
                    notes.add(4.0);
                    break;
                case "half":
                    notes.add(2.0);
                    break;
                case "quarter":
                    notes.add(1.0);
                    break;
                case "eighth":
                    notes.add(0.5);
                    break;
                case "sixteenth":
                    notes.add(0.25);
                    break;
            }
        }

        // Prompt for user to start the game
        System.out.println();
        System.out.println("Press ENTER when you are ready to start with the provided notes. Remember that there is always a whole note at the begining");
        scan.nextLine();

        // handling of paralel processing of music and game
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Playing the music in separate thread
        executor.execute(() -> {
            StdAudio.play("100bmp.wav");
        });

        executor.execute(() -> {
            ArrayList<Double> results = new ArrayList<>();
            for (int i = 0; i < notes.size(); i++) {
                long startTime = System.currentTimeMillis();
                scan.nextLine();
                int endTime = (int) (System.currentTimeMillis() - startTime);

                // Checking if the user's timing is within the acceptable range
                // .2 s tollerance
                // adjust by changing the added time to i
                if (endTime / 100 <= i * 10 + 50 && endTime / 100 >= i * 10 - 50) {
                    System.out.println("Just right");
                    results.add(0.0);
                } else {
                    System.out.println("You're off");
                    results.add((double) (endTime / 100));
                }
            }

            // total deviation from goal time
            double t = 0;
            for (int i = 0; i < results.size(); i++) {
                t = t + results.get(i);
            }
            System.out.println("You were " + t / 100 + " seconds off");
        });
        executor.shutdown();
    }
}
