/* 
 * Name: EDD JOSEPH  
 * Date: AUGUST 1, 2023
 * CLASS: MET CS342
 * Purpose: 
 * A program that generates a pattern of asterisks using recursion.
 * The pattern starts with a single asterisk in the first line, and each subsequent line contains one more asterisk than the previous line,
 * up to the nth line, which contains 'n' asterisks. Then, the pattern continues with decreasing asterisks until line number 2n, which has a single asterisk.
 */

public class recursion {

    /**
     * Prints a line of asterisks to System.out.
     *
     * @param num - The number of asterisks to print.
     */
    public static void printLine(int num) {
        // Base case: if num is less than or equal to 0, return and do not print anything.
        if (num <= 0) {
            return;
        }
        // Print an asterisk and recursively call the function with 'num-1' to print the remaining asterisks in the line.
        System.out.print("*");
        printLine(num - 1);
    }

    /**
     * Prints a pattern of asterisks to System.out. This is a recursive method to print the pattern starting at 'currentLine'.
     *
     * @param n           - The number of lines in the pattern.
     * @param currentLine - The current line in the pattern (1-based index).
     */
    public static void printPattern(int n, int currentLine) {
        // Base case: if the current line is greater than 'n', we stop recursion.
        if (currentLine > n) {
            return;
        }

        // Print the current line with the specified number of asterisks using the 'printLine' method.
        printLine(currentLine);
        System.out.println(); // Move to the next line

        // Recursively call 'printPattern' with the next line (currentLine+1) to continue generating the pattern.
        printPattern(n, currentLine + 1);

        // After printing the first 'n' lines, print the second half of the pattern in decreasing order.
        // This step is skipped for the last line (currentLine = n) to avoid duplicating the line with 'n' asterisks.
        if (currentLine != n) {
            printLine(currentLine);
            System.out.println(); // Move to the next line
        }
    }

    /**
     * The main method of the program (entry point of the program). Prints the pattern to standard output with 'n' lines.
     *
     * @param n - Number of lines in the pattern. In this case, 10 lines.
     * @param printPattern - Method call to print the pattern.
     */
    public static void main(String[] args) {
        int n = 10; // number of lines in the pattern
        printPattern(n, 1);
    }
}
