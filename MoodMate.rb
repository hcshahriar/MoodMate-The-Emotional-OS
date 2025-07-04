require 'time'

MOOD_DATA = {
  "happy" => {
    quote: "Happiness is not by chance, but by choice.",
    song: "Pharrell - Happy",
    food: "Ice Cream"
  },
  "sad" => {
    quote: "Tears come from the heart, not the brain.",
    song: "Adele - Someone Like You",
    food: "Dark Chocolate"
  },
  "stressed" => {
    quote: "Just breathe. You are strong.",
    song: "Lo-fi Chill Mix",
    food: "Herbal Tea"
  },
  "angry" => {
    quote: "Speak when you are calm, not when you are burning.",
    song: "Eminem - Lose Yourself",
    food: "Grilled Chicken"
  },
  "romantic" => {
    quote: "Love is composed of a single soul inhabiting two bodies.",
    song: "Ed Sheeran - Perfect",
    food: "Strawberries & Wine"
  },
  "neutral" => {
    quote: "Balance is the key to everything.",
    song: "Lo-fi Beats",
    food: "Fresh Salad"
  }
}

def log_mood(mood)
  File.open("mood_log.txt", "a") do |file|
    file.puts("#{Time.now.iso8601} - Mood: #{mood}")
  end
end

def prompt_mood
  puts "Welcome to MoodMate - The Emotional Console"
  puts "Choose your current mood:"
  MOOD_DATA.keys.each_with_index do |mood, index|
    puts "#{index + 1}. #{mood.capitalize}"
  end
  print "Enter choice (1-#{MOOD_DATA.size}): "
  choice = gets.to_i
  if choice.between?(1, MOOD_DATA.size)
    MOOD_DATA.keys[choice - 1]
  else
    nil
  end
end

def display_mood_info(mood)
  info = MOOD_DATA[mood]
  puts "\nQuote: \"#{info[:quote]}\""
  puts "Suggested Song: #{info[:song]}"
  puts "Suggested Food: #{info[:food]}"
end

mood = prompt_mood

if mood
  display_mood_info(mood)
  log_mood(mood)
  puts "\nYour mood has been logged. Take care!"
else
  puts "Invalid choice. Exiting."
end
