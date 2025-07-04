type MoodKey = 'happy' | 'sad' | 'stressed' | 'angry' | 'romantic' | 'neutral';

interface MoodInfo {
  quote: string;
  song: string;
  food: string;
}

const moodData: Record<MoodKey, MoodInfo> = {
  happy: {
    quote: "Happiness is not by chance, but by choice.",
    song: "Pharrell - Happy",
    food: "Ice Cream"
  },
  sad: {
    quote: "Tears come from the heart, not the brain.",
    song: "Adele - Someone Like You",
    food: "Dark Chocolate"
  },
  stressed: {
    quote: "Just breathe. You are strong.",
    song: "Lo-fi Chill Mix",
    food: "Herbal Tea"
  },
  angry: {
    quote: "Speak when you are calm, not when you are burning.",
    song: "Eminem - Lose Yourself",
    food: "Grilled Chicken"
  },
  romantic: {
    quote: "Love is composed of a single soul inhabiting two bodies.",
    song: "Ed Sheeran - Perfect",
    food: "Strawberries & Wine"
  },
  neutral: {
    quote: "Balance is the key to everything.",
    song: "Lo-fi Beats",
    food: "Fresh Salad"
  }
};

function logMood(mood: MoodKey): void {
  const timestamp = new Date().toISOString();
  const history = JSON.parse(localStorage.getItem('moodHistory') || '[]') as {mood: MoodKey, time: string}[];
  history.push({ mood, time: timestamp });
  localStorage.setItem('moodHistory', JSON.stringify(history));
  console.log(`Logged mood "${mood}" at ${timestamp}`);
}

function displayMoodInfo(mood: MoodKey): void {
  const info = moodData[mood];
  console.log(`\nQuote: "${info.quote}"`);
  console.log(`Suggested Song: ${info.song}`);
  console.log(`Suggested Food: ${info.food}`);
}

function promptMood(): void {
  console.log("Welcome to MoodMate - The Emotional Console");
  const moods = Object.keys(moodData) as MoodKey[];
  moods.forEach((mood, index) => {
    console.log(`${index + 1}. ${mood.charAt(0).toUpperCase() + mood.slice(1)}`);
  });

  const choiceStr = prompt(`Enter choice (1-${moods.length}):`);
  if (!choiceStr) {
    console.log("No input received. Exiting.");
    return;
  }

  const choice = Number(choiceStr);
  if (isNaN(choice) || choice < 1 || choice > moods.length) {
    console.log("Invalid choice. Exiting.");
    return;
  }

  const selectedMood = moods[choice - 1];
  displayMoodInfo(selectedMood);
  logMood(selectedMood);
  console.log("\nYour mood has been logged. Take care!");
}

promptMood();
