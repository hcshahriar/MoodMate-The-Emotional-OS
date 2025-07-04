import datetime

mood_data = {
    "happy": {
        "quote": "Happiness is not by chance, but by choice.",
        "song": "Pharrell - Happy",
        "food": "Ice Cream"
    },
    "sad": {
        "quote": "Tears come from the heart, not the brain.",
        "song": "Adele - Someone Like You",
        "food": "Dark Chocolate"
    },
    "stressed": {
        "quote": "Just breathe. You are strong.",
        "song": "Lo-fi Chill Mix",
        "food": "Herbal Tea"
    },
    "angry": {
        "quote": "Speak when you are calm, not when you are burning.",
        "song": "Eminem - Lose Yourself",
        "food": "Grilled Chicken"
    },
    "romantic": {
        "quote": "Love is composed of a single soul inhabiting two bodies.",
        "song": "Ed Sheeran - Perfect",
        "food": "Strawberries & Wine"
    },
    "neutral": {
        "quote": "Balance is the key to everything.",
        "song": "Lo-fi Beats",
        "food": "Fresh Salad"
    }
}

def log_mood(mood):
    with open("mood_log.txt", "a") as file:
        time_str = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        file.write(f"{time_str} - Mood: {mood}\n")

def main():
    print("Welcome to MoodMate - The Emotional Console")
    moods = list(mood_data.keys())
    for i, mood in enumerate(moods, start=1):
        print(f"{i}. {mood.capitalize()}")
    choice = input(f"Enter choice (1-{len(moods)}): ")

    if not choice.isdigit() or not (1 <= int(choice) <= len(moods)):
        print("Invalid choice. Exiting.")
        return

    mood = moods[int(choice)-1]
    info = mood_data[mood]

    print(f"\nQuote: \"{info['quote']}\"")
    print(f"Suggested Song: {info['song']}")
    print(f"Suggested Food: {info['food']}")

    log_mood(mood)
    print("\nYour mood has been logged. Take care!")

if __name__ == "__main__":
    main()
