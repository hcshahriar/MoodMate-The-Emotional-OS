<?php
$moodData = [
    "happy" => [
        "quote" => "Happiness is not by chance, but by choice.",
        "song" => "Pharrell - Happy",
        "food" => "Ice Cream"
    ],
    "sad" => [
        "quote" => "Tears come from the heart, not the brain.",
        "song" => "Adele - Someone Like You",
        "food" => "Dark Chocolate"
    ],
    "stressed" => [
        "quote" => "Just breathe. You are strong.",
        "song" => "Lo-fi Chill Mix",
        "food" => "Herbal Tea"
    ],
    "angry" => [
        "quote" => "Speak when you are calm, not when you are burning.",
        "song" => "Eminem - Lose Yourself",
        "food" => "Grilled Chicken"
    ],
    "romantic" => [
        "quote" => "Love is composed of a single soul inhabiting two bodies.",
        "song" => "Ed Sheeran - Perfect",
        "food" => "Strawberries & Wine"
    ],
    "neutral" => [
        "quote" => "Balance is the key to everything.",
        "song" => "Lo-fi Beats",
        "food" => "Fresh Salad"
    ]
];

function logMood($mood) {
    $timestamp = date('Y-m-d H:i:s');
    $logEntry = "$timestamp - Mood: $mood\n";
    file_put_contents('mood_log.txt', $logEntry, FILE_APPEND);
}

function prompt($prompt_msg){
    echo $prompt_msg;
    return trim(fgets(STDIN));
}

echo "Welcome to MoodMate - The Emotional Console\n";

$moods = array_keys($moodData);
foreach ($moods as $index => $mood) {
    echo ($index + 1) . ". " . ucfirst($mood) . "\n";
}

$choice = prompt("Enter choice (1-" . count($moods) . "): ");

if (!is_numeric($choice) || $choice < 1 || $choice > count($moods)) {
    echo "Invalid choice. Exiting.\n";
    exit(1);
}

$selectedMood = $moods[$choice - 1];
$info = $moodData[$selectedMood];

echo "\nQuote: \"{$info['quote']}\"\n";
echo "Suggested Song: {$info['song']}\n";
echo "Suggested Food: {$info['food']}\n";

logMood($selectedMood);

echo "\nYour mood has been logged. Take care!\n";
?>
