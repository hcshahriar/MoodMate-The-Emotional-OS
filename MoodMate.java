import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class MoodMate {
    static class MoodInfo {
        String quote, song, food;
        MoodInfo(String quote, String song, String food) {
            this.quote = quote;
            this.song = song;
            this.food = food;
        }
    }

    static Map<Integer, String> moodKeys = new HashMap<>();
    static Map<String, MoodInfo> moodData = new HashMap<>();

    static {
        moodKeys.put(1, "happy");
        moodKeys.put(2, "sad");
        moodKeys.put(3, "stressed");
        moodKeys.put(4, "angry");
        moodKeys.put(5, "romantic");
        moodKeys.put(6, "neutral");

        moodData.put("happy", new MoodInfo(
            "Happiness is not by chance, but by choice.",
            "Pharrell - Happy",
            "Ice Cream"));

        moodData.put("sad", new MoodInfo(
            "Tears come from the heart, not the brain.",
            "Adele - Someone Like You",
            "Dark Chocolate"));

        moodData.put("stressed", new MoodInfo(
            "Just breathe. You are strong.",
            "Lo-fi Chill Mix",
            "Herbal Tea"));

        moodData.put("angry", new MoodInfo(
            "Speak when you are calm, not when you are burning.",
            "Eminem - Lose Yourself",
            "Grilled Chicken"));

        moodData.put("romantic", new MoodInfo(
            "Love is composed of a single soul inhabiting two bodies.",
            "Ed Sheeran - Perfect",
            "Strawberries & Wine"));

        moodData.put("neutral", new MoodInfo(
            "Balance is the key to everything.",
            "Lo-fi Beats",
            "Fresh Salad"));
    }

    public static void logMood(String mood) {
        try (FileWriter fw = new FileWriter("mood_log.txt", true)) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            fw.write(timestamp + " - Mood: " + mood + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error writing to mood_log.txt: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to MoodMate - The Emotional Console");
        System.out.println("Choose your current mood:");
        for (Map.Entry<Integer, String> entry : moodKeys.entrySet()) {
            System.out.printf("%d. %s%n", entry.getKey(), capitalize(entry.getValue()));
        }
        System.out.print("Enter choice (1-6): ");

        int choice = scanner.nextInt();
        String moodKey = moodKeys.get(choice);
        if (moodKey == null) {
            System.out.println("Invalid choice. Exiting.");
            scanner.close();
            return;
        }

        MoodInfo info = moodData.get(moodKey);
        System.out.println("\nQuote: \"" + info.quote + "\"");
        System.out.println("Suggested Song: " + info.song);
        System.out.println("Suggested Food: " + info.food);

        logMood(moodKey);
        System.out.println("\nYour mood has been logged. Take care!");

        scanner.close();
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }
}
