// Simple number guesser that will end if user fails up to 3 times.
// NOTE 15/02/2023: In this code I used the std namespace because I wasn't aware of its problem.

#include <iostream>

using namespace std;

int main()
{
    string message;
    int selectedNumber = 10;
    int guessLimit = 3;
    int attempts = 0;
    int guess;

    do{
        cout << "Your guess: ";
        cin >> guess;

        if(guess != selectedNumber) {
            guess = false;
            message = "Wrong answer!";
            cout << message << endl;
            attempts++;
        } else {
            guess = true;
            message = "Correct answer, you win!";
            cout << message << endl;
        }

        if(attempts == 2 && guess == false) {
            cout << "You only have one attempt remaining!" << endl;
        } else if(attempts == 3) {
            cout << "Game over" << endl;
        }

    } while(guess == false && attempts < 3);

    return 0;
}
