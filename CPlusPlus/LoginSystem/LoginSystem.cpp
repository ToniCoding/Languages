// Includes.
#include "./incluSing.cpp"

// Global varibales.
bool running = true;

// Program functions.
// Function to read credentials. Returns TRUE if correct, FALSE if incorrect.
bool confirmCredentials(string usernameVerify, string passwordVerify) {
    // Creates an ifstream class and opens a file.
    ifstream credentialsFile;
    credentialsFile.open("./credentialsFile.txt");

    // Creates two string variables to store correct credentials.
    string fileUsername;
    string filePassword;

    // Creates a new variable to store the current line and makes a loop to read it line by line.
    string currentLine;
    bool indexIter = false;
    while (getline(credentialsFile, currentLine)) {
        if (!indexIter) {
            fileUsername = currentLine;
        } else {
            filePassword = currentLine;
        }
        indexIter = true;
    }

    // Check if user prompted credentials are correct.
    if (fileUsername == usernameVerify && filePassword == passwordVerify) {
        return true;
    } else {
        return false;
    }
}

// Program main loop.
int mainLoop() {
    while (running) {
        string username;
        string password;

        // Process to ask user for credentials with buffer cleaning after integration.
        cout << "Welcome back! Login with your credentials:\nUsername: ";
        cin >> username;
        cin.ignore();
        cout << "Password: ";
        cin >> password;
        cin.ignore();

        // Call confirmCredentials with the respective arguments.
        if (confirmCredentials(username, password)) {
            cout << "Correct credentials. Access granted.";
        } else {
            cout << "Incorrect credentials. Access denied.";
        }
        break;
    }
    return 0;
}

// Code main function.
int main() {
    mainLoop();
    return 0;
}