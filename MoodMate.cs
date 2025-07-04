using System;
using System.Collections.Generic;
using System.IO;

class MoodInfo
{
    public string Quote { get; set; }
    public string Song { get; set; }
    public string Food { get; set; }

    public MoodInfo(string quote, string song, string food)
    {
        Quote = quote;
        Song = song;
        Food = food;
    }
}

class MoodMate
{
    static Dictionary<string, MoodInfo> moodData = new Dictionary<string, MoodInfo>
    {
        { "happy", new MoodInfo(
            "Happiness is not by chance, but by choice.",
            "Pharrell - Happy",
            "Ice Cream") },

        { "sad", new MoodInfo(
            "Tears come from the heart, not the brain.",
            "Adele - Someone Like You",
            "Dark Chocolate") },

        { "stressed", new MoodInfo(
            "Just breathe. You are strong.",
            "Lo-fi Chill Mix",
            "Herbal Tea") },

        { "angry", new MoodInfo(
            "Speak when you are calm, not when you are burning.",
            "Eminem - Lose Yourself",
            "Grilled Chicken") },

        { "romantic", new MoodInfo(
            "Love is composed of a single soul inhabiting two bodies.",
            "Ed Sheeran - Perfect",
            "Strawberries & Wine") },

        { "neutral", new MoodInfo(
            "Balance is the key to everything.",
            "Lo-fi Beats",
            "Fresh Salad") }
    };

    static void LogMood(string mood)
    {
        string timestamp = DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss");
        string entry = $"{timestamp} - Mood: {mood}\n";
        File.AppendAllText("mood_log.txt", entry);
    }

    static void Main()
    {
        Console.WriteLine("Welcome to MoodMate - The Emotional Console");
        var moodKeys = new List<string>(moodData.Keys);

        for (int i = 0; i < moodKeys.Count; i++)
        {
            Console.WriteLine($"{i + 1}. {CultureInfo.CurrentCulture.TextInfo.ToTitleCase(moodKeys[i])}");
        }

        Console.Write($"Enter choice (1-{moodKeys.Count}): ");
        if (!int.TryParse(Console.ReadLine(), out int choice) || choice < 1 || choice > moodKeys.Count)
        {
            Console.WriteLine("Invalid choice. Exiting.");
            return;
        }

        string selectedMood = moodKeys[choice - 1];
        MoodInfo info = moodData[selectedMood];

        Console.WriteLine($"\nQuote: \"{info.Quote}\"");
        Console.WriteLine($"Suggested Song: {info.Song}");
        Console.WriteLine($"Suggested Food: {info.Food}");

        LogMood(selectedMood);

        Console.WriteLine("\nYour mood has been logged. Take care!");
    }
}
