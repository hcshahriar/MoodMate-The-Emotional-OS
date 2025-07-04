import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class MoodInfo(val quote: String, val song: String, val food: String)

val moodData = mapOf(
    "happy" to MoodInfo(
        "Happiness is not by chance, but by choice.",
        "Pharrell - Happy",
        "Ice Cream"
    ),
    "sad" to MoodInfo(
        "Tears come from the heart, not the brain.",
        "Adele - Someone Like You",
        "Dark Chocolate"
    ),
    "stressed" to MoodInfo(
        "Just breathe. You are strong.",
        "Lo-fi Chill Mix",
        "Herbal Tea"
    ),
    "angry" to MoodInfo(
        "Speak when you are calm, not when you are burning.",
        "Eminem - Lose Yourself",
        "Grilled Chicken"
    ),
    "romantic" to MoodInfo(
        "Love is composed of a single soul inhabiting two bodies.",
        "Ed Sheeran - Perfect",
        "Strawberries & Wine"
    ),
    "neutral" to MoodInfo(
        "Balance is the key to everything.",
        "Lo-fi Beats",
        "Fresh Salad"
    )
)

fun logMood(mood: String) {
    val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    val logEntry = "$timestamp - Mood: $mood\n"
    File("mood_log.txt").appendText(logEntry)
}

fun main() {
    println("Welcome to MoodMate - The Emotional Console")
    val moods = moodData.keys.toList()
    moods.forEachIndexed { index, mood ->
        println("${index + 1}. ${mood.capitalize()}")
    }

    print("Enter choice (1-${moods.size}): ")
    val input = readLine()
    val choice = input?.toIntOrNull()

    if (choice == null || choice !in 1..moods.size) {
        println("Invalid choice. Exiting.")
        return
    }

    val selectedMood = moods[choice - 1]
    val info = moodData[selectedMood]

    println("\nQuote: \"${info?.quote}\"")
    println("Suggested Song: ${info?.song}")
    println("Suggested Food: ${info?.food}")

    logMood(selectedMood)
    println("\nYour mood has been logged. Take care!")
}
