import java.util.Scanner;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Initialize the Linked List.
        LinkedList linkedList = new LinkedList();

        // Read and pre-process the text file, insert words into the Linked List.
        TextParser textParser = new TextParser(linkedList);
        textParser.readAndProcessTextFile("/Users/EGJ/Downloads/174.txt");

        // Perform queries and display the results.
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println("Choose an option:");
            System.out.println("1. Count occurrences of specific words (portrait, persian, dorian)");
            System.out.println("2. Number of entries in the Linked List");
            System.out.println("3. Words occurring more than 20 times");
            System.out.println("4. Most frequently occurring word");
            System.out.println("5. Longest word in the book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            input = scanner.nextLine();

            switch (input) {
                case "1":
                    int portraitCount = linkedList.getCount("portrait");
                    int persianCount = linkedList.getCount("persian");
                    int dorianCount = linkedList.getCount("dorian");
                    System.out.println("Occurrences of 'portrait': " + portraitCount);
                    System.out.println("Occurrences of 'persian': " + persianCount);
                    System.out.println("Occurrences of 'dorian': " + dorianCount);
                    break;
                case "2":
                    int linkedListSize = linkedList.getSize();
                    System.out.println("Number of entries in the Linked List: " + linkedListSize);
                    break;
                case "3":
                    System.out.println("Words occurring more than 20 times:");
                    linkedList.displayWordsMoreThan(20);
                    break;
                case "4":
                    String mostFrequentWord = linkedList.getMostFrequentWord();
                    System.out.println("Most frequently occurring word: " + mostFrequentWord);
                    break;
                case "5":
                    String longestWord = linkedList.getLongestWord();
                    System.out.println("Longest word in the book: " + longestWord);
                    break;
                case "0":
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (!input.equals("0"));

        scanner.close();
    }
}
