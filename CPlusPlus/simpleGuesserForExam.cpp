// This simple program will give the user a chance of passing their next exam based on the last three marks.
// NOTE 15/02/2023: In this code I used the std namespace because I wasn't aware of its problem.

#include <iostream>

using namespace std;

int main()
{

    int examsDone = 3;

    int firstMark;
    int secondMark;
    int thirdMark;

    int daysToStudyNextExam;

    int examsPassed = 0;
    int examsFailed = 0;

    cout << "Input the mark of your first exam:\n";
    cin >> firstMark;

    cout << "Input the mark of your second exam:\n";
    cin >> secondMark;

    cout << "Input the mark of your third exam:\n";
    cin >> thirdMark;

    if (firstMark <= 4) {
        examsFailed += 1;
    } else if (firstMark >= 5) {
        examsPassed += 1;
    };
    if (secondMark <= 4) {
        examsFailed += 1;
    } else if (secondMark >= 5) {
        examsPassed += 1;
    };
    if (thirdMark <= 4) {
        examsFailed += 1;
    } else if (thirdMark >= 5) {
        examsPassed += 1;
    };

    cout << "Exams passed: " << examsPassed << endl;
    cout << "Exams failed: " << examsFailed << endl;

    double probabilityPassing = double(examsPassed) / double(examsDone);

    switch(examsPassed) {
        case 3:
            cout << "Your chances of passing the next exam if you keep this trajectory: 100%";
            break;
        case 0:
            cout << "Your chances of passing the next exam if you keep this trajectory: 0%";
            break;
        default:
            cout << "Your chances of passing the next exam if you keep this trajectory: " << (probabilityPassing * 100) << "%";
            break;
    };

    return 0;
}
