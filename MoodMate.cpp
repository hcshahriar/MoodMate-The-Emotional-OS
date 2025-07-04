#include <iostream>
#include <fstream>
#include <string>
#include <ctime>
#include <map>
using namespace std;

struct MoodInfo {
    string quote;
    string song;
    string food;
};

map<string, MoodInfo> moodData = {
    {"happy", {"Happiness is not by chance, but by choice.",
               "Pharrell - Happy",
               "Ice Cream"}},
    {"sad", {"Tears come from the heart, not the brain.",
             "Adele - Someone Like You",
             "Dark Chocolate"}},
    {"stressed", {"Just breathe. You are strong.",
                  "Lo-fi Chill Mix",
                  "Herbal Tea"}},
    {"angry", {"Speak when you are calm, not when you are burning.",
               "Eminem - Lose Yourself",
               "Grilled Chicken"}},
    {"romantic", {"Love is composed of a single soul inhabiting two bodies.",
                  "Ed Sheeran - Perfect",
                  "Strawberries & Wine"}},
    {"neutral", {"Balance is the key to everything.",
                 "Lo-fi Beats",
                 "Fresh Salad"}}
};

string getCurrentTime() {
    time_t now = time(0);
    char buf[80];
    strftime(buf, sizeof(buf), "%Y-%m-%d %X", localtime(&now));
    return string(buf);
}

void saveMoodLog(const string& mood) {
    ofstream file("mood_log.txt", ios::app);
    if (file.is_open()) {
        file << getCurrentTime() << " - Mood: " << mood << endl;
        file.close();
    } else {
        cerr << "Error: Unable to open mood_log.txt for writing.\n";
    }
}

int main() {
    cout << "Welcome to MoodMate - The Emotional Console\n";
    cout << "Choose your current mood:\n";
    cout << "1. Happy\n2. Sad\n3. Stressed\n4. Angry\n5. Romantic\n6. Neutral\n";
    cout << "Enter choice (1-6): ";

    int choice;
    cin >> choice;

    string moodKey;
    switch (choice) {
        case 1: moodKey = "happy"; break;
        case 2: moodKey = "sad"; break;
        case 3: moodKey = "stressed"; break;
        case 4: moodKey = "angry"; break;
        case 5: moodKey = "romantic"; break;
        case 6: moodKey = "neutral"; break;
        default:
            cout << "Invalid choice. Exiting.\n";
            return 1;
    }

    MoodInfo info = moodData[moodKey];

    cout << "\nQuote: \"" << info.quote << "\"\n";
    cout << "Suggested Song: " << info.song << "\n";
    cout << "Suggested Food: " << info.food << "\n";

    saveMoodLog(moodKey);

    cout << "\nYour mood has been logged. Take care!\n";
    return 0;
}
