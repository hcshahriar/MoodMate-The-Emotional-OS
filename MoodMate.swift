import Foundation

struct MoodInfo {
    let quote: String
    let song: String
    let food: String
}

let moodData: [String: MoodInfo] = [
    "happy": MoodInfo(
        quote: "Happiness is not by chance, but by choice.",
        song: "Pharrell - Happy",
        food: "Ice Cream"
    ),
    "sad": MoodInfo(
        quote: "Tears come from the heart, not the brain.",
        song: "Adele - Someone Like You",
        food: "Dark Chocolate"
    ),
    "stressed": MoodInfo(
        quote: "Just breathe. You are strong.",
        song: "Lo-fi Chill Mix",
        food: "Herbal Tea"
    ),
    "angry": MoodInfo(
        quote: "Speak when you are calm, not when you are burning.",
        song: "Eminem - Lose Yourself",
        food: "Grilled Chicken"
    ),
    "romantic": MoodInfo(
        quote: "Love is composed of a single soul inhabiting two bodies.",
        song: "Ed Sheeran - Perfect",
        food: "Strawberries & Wine"
    ),
    "neutral": MoodInfo(
        quote: "Balance is the key to everything.",
        song: "Lo-fi Beats",
        food: "Fresh Salad"
    )
]

func logMood(mood: String) {
    let formatter = DateFormatter()
    formatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
    let timestamp = formatter.string(from: Date())
    let logEntry = "\(timestamp) - Mood: \(mood)\n"
    
    let fileURL = URL(fileURLWithPath: FileManager.default.currentDirectoryPath).appendingPathComponent("mood_log.txt")
    
    if FileManager.default.fileExists(atPath: fileURL.path) {
        if let fileHandle = try? FileHandle(forWritingTo: fileURL) {
            fileHandle.seekToEndOfFile()
            if let data = logEntry.data(using: .utf8) {
                fileHandle.write(data)
            }
            fileHandle.closeFile()
        }
    } else {
        try? logEntry.write(to: fileURL, atomically: true, encoding: .utf8)
    }
}

func main() {
    print("Welcome to MoodMate - The Emotional Console")
    let moods = Array(moodData.keys)
    
    for (index, mood) in moods.enumerated() {
        print("\(index + 1). \(mood.capitalized)")
    }
    
    print("Enter choice (1-\(moods.count)): ", terminator: "")
    guard let input = readLine(),
          let choice = Int(input),
          choice >= 1,
          choice <= moods.count else {
        print("Invalid choice. Exiting.")
        return
    }
    
    let selectedMood = moods[choice - 1]
    if let info = moodData[selectedMood] {
        print("\nQuote: \"\(info.quote)\"")
        print("Suggested Song: \(info.song)")
        print("Suggested Food: \(info.food)")
        logMood(mood: selectedMood)
        print("\nYour mood has been logged. Take care!")
    }
}

main()
