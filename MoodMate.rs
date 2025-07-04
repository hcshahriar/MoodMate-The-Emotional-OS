use std::collections::HashMap;
use std::fs::OpenOptions;
use std::io::{self, Write};
use chrono::Local;

struct MoodInfo {
    quote: &'static str,
    song: &'static str,
    food: &'static str,
}

fn log_mood(mood: &str) -> io::Result<()> {
    let now = Local::now();
    let timestamp = now.format("%Y-%m-%d %H:%M:%S").to_string();
    let log_entry = format!("{} - Mood: {}\n", timestamp, mood);

    let mut file = OpenOptions::new()
        .create(true)
        .append(true)
        .open("mood_log.txt")?;
    file.write_all(log_entry.as_bytes())?;
    Ok(())
}

fn main() {
    let mood_data: HashMap<&str, MoodInfo> = [
        ("happy", MoodInfo {
            quote: "Happiness is not by chance, but by choice.",
            song: "Pharrell - Happy",
            food: "Ice Cream",
        }),
        ("sad", MoodInfo {
            quote: "Tears come from the heart, not the brain.",
            song: "Adele - Someone Like You",
            food: "Dark Chocolate",
        }),
        ("stressed", MoodInfo {
            quote: "Just breathe. You are strong.",
            song: "Lo-fi Chill Mix",
            food: "Herbal Tea",
        }),
        ("angry", MoodInfo {
            quote: "Speak when you are calm, not when you are burning.",
            song: "Eminem - Lose Yourself",
            food: "Grilled Chicken",
        }),
        ("romantic", MoodInfo {
            quote: "Love is composed of a single soul inhabiting two bodies.",
            song: "Ed Sheeran - Perfect",
            food: "Strawberries & Wine",
        }),
        ("neutral", MoodInfo {
            quote: "Balance is the key to everything.",
            song: "Lo-fi Beats",
            food: "Fresh Salad",
        }),
    ].iter().cloned().collect();

    let moods: Vec<&str> = mood_data.keys().cloned().collect();

    println!("Welcome to MoodMate - The Emotional Console");
    for (i, mood) in moods.iter().enumerate() {
        println!("{}. {}", i + 1, mood.capitalize_first());
    }

    println!("Enter choice (1-{}): ", moods.len());
    let mut input = String::new();
    io::stdin().read_line(&mut input).expect("Failed to read input");

    let choice: usize = match input.trim().parse() {
        Ok(num) if num >= 1 && num <= moods.len() => num,
        _ => {
            println!("Invalid choice. Exiting.");
            return;
        }
    };

    let selected_mood = moods[choice - 1];
    let info = &mood_data[selected_mood];

    println!("\nQuote: \"{}\"", info.quote);
    println!("Suggested Song: {}", info.song);
    println!("Suggested Food: {}", info.food);

    match log_mood(selected_mood) {
        Ok(_) => println!("\nYour mood has been logged. Take care!"),
        Err(e) => eprintln!("Error logging mood: {}", e),
    }
}


trait CapitalizeFirst {
    fn capitalize_first(&self) -> String;
}

impl CapitalizeFirst for &str {
    fn capitalize_first(&self) -> String {
        let mut c = self.chars();
        match c.next() {
            None => String::new(),
            Some(f) => f.to_uppercase().collect::<String>() + c.as_str(),
        }
    }
}
