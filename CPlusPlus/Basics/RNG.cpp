// Include.
#include <iostream>;
#include <string>;
#include <ctime>;
#include <cstdlib>;

// Using.
using std::cout;
using std::cin;
using std::endl;
using std::string;

// RNG - Random Number Generator - Generates a random number with a set seed that uses the computer's clock.
void generateRNG() {
	srand(time(NULL)); // COM. SIGHT: randomSeed(secondsSince01/01/1970);
	cout << (rand()%10) << endl; // COM. SIGHT: randomIntegerWithSettedSeed()Between0-10
	cout << (rand() % 20) + 1 << endl; // COM.SIGHT: randomIntegerWithSettedSeed()Between1 - 20[Minimum is added].
	cout << (rand() % 20) + 10 << endl; // COM.SIGHT: randomIntegerWithSettedSeed()Between10 - 30[Minimum is added].
}

// Main function definition.
int main() {
	generateRNG();
	return 0;
}
