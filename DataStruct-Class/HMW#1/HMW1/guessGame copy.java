

/*
Guess Game

Author: Edd Joseph
Date: 07/10/2023
Class: MET CS342
Issues: None Known
 
Description:

This program is a Java program that plays the guess game. The computer will tell the user that they are thinking of a number between 1 and 1000. 
The computer will think of the number, and the user will repeatedly guess until they answer correctly. 
The program will tell the user if guesses are too high or too low depending on the number they guess.
When the user guesses the correct number, the game will show the user how many turns it took. 
The program will handle incorrect input (letters when numbers were expected). 

SEE PSEDOCODE AND COMMENTS FOR GUESS GAME FOR MORE UNDERSTANDING OF THE PROGRAM





- PSEUDOCODE FOR GUESS GAME -

1. Initialize number as a random value between 1 and 1000
2. Initialize guess, count, and validInput as 0 or false
3. Print "I am thinking of a number between 1 and 1000. Can you guess what it is?"

4. WHILE guess is not equal to number
    5. Print "Enter a guess:"
    6. Try
        7. Read guess from user
        8. Set validInput to true
        9. IF guess is equal to number
            10. Print "Congratulations. You guessed the number with [count] tries!"
        11. ELSE IF validInput is false
            12. Print "Invalid input. Please enter an integer."
            13. Read and discard the invalid input
            14. Set validInput to false
        15. ELSE IF guess is less than number
            16. Print "Your guess is too low. Try again."
        17. ELSE IF guess is greater than number
            18. Print "Your guess is too high. Try again."
        19. Increment count by 1
    20. END TRY
    21. Catch InputMismatchException
        22. Print "Invalid input. Please enter an integer."
        23. Read and discard the invalid input
        24. Set validInput to false
    25. END CATCH
26. END WHILE



*/

//LIBRARY IMPORTS
//IMPORT TO HANDLE INCORRECT INPUT FROM THE USER
import java.util.InputMismatchException;
//IMPORT TO RECIEVE INPUT FROM USER
import java.util.Scanner;


public class guessGame {
    //MAIN CLASS
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int number = (int) (Math.random() * 1000 + 1); //RANDOM NUMBER GENERATION FROM 1 TO 1000
        int guess = 0; // Initial guess (INTEGER)
        int count = 0; // COUNTER FOR AMOUNT OF GUESSES
        System.out.println("I am thinking of a number between 1 and 1000. Can you guess what it is?"); //COMPUTER OUTPUT
        boolean validInput = false; // Flag to indicate if the input is valid

        //WHILE GUESS ISN'T EQUAL TO NUMBER
        while (guess != number) {
            System.out.print("Enter a guess: "); //COMPUTER OUTPUT

            try {
                guess = input.nextInt(); // Read the input from the user
                validInput = true; // Input is valid if no exception occurs
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                input.next(); // Clear the invalid input from the scanner
                validInput = false; // Flag input as invalid
            }

            //IF THE INPUT IS VALID IT WILL DETERMINE THE OUTPUT BASED UPON WHETHER THE NUMBER IS LESS THAN, 
            //GREATER THAN, OR EQUAL TO THE NUMBER
            if (validInput) {
                count++;
                if (guess == number) {
                    System.out.println("Congratulations. You guessed the number with " + count + " tries!");
                } else if (guess < number) {
                    System.out.println("Your guess is too low. Try again.");
                } else if (guess > number) {
                    System.out.println("Your guess is too high. Try again.");
                }
            }
        }
    }
}




