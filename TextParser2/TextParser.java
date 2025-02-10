
/*

 * Author: Edd Joseph
 * Date: JULY 25, 2023
 * CLASS: MET CS342
 *
 * TextParser provides functionality to read a text file, parse it into words, 
 * and load the words into a LinkedList data structure for analysis.
 * 
 * The key methods are:
 * 
 * - readTextFile(String filePath)
 *   - Reads a text file and returns the contents as a string
 *   
 * - readAndAddToLL()  
 *   - Calls readTextFile to get file contents
 *   - Splits text into words and adds each word to a LinkedList
 *   
 * - getSize()
 *   - Returns number of words in the LinkedList
 *   
 * - getWordCount() 
 *   - Prints count of specified words in LinkedList
 *   
 * - getWordsMoreThan20()
 *   - Prints words that occur more than 20 times
 *   
 * - getMostFrequentWord()
 *   - Prints most frequently occurring word
 *   
 * Main() demonstrates calling these methods in sequence to:
 * 
 * 1. Read file
 * 2. Parse into LinkedList 
 * 3. Analyze LinkedList
 *
 */

import java.io.BufferedReader;
//import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class TextParser {

    // Create a new linked list
    LinkedList ll = new LinkedList();

    /**
     * The readTextFile function reads a text file and returns the contents of that
     * file as a String Array.
     * 
     *
     * @param String filePath Specify the file path of the text file that is being
     *               read
     *
     * @return A string
     *
     * @doc-author Edd Joseph
     */

    public static String readTextFile(String filePath) {
        System.out.println("READING TEXT FILE AT PATH: " + filePath);
        System.out.println();
        /*
         * Utilizing Buffer Reader to read the text file
         * A StringBuilder object called fileContent is created. StringBuilder is used
         * to efficiently construct and manipulate strings.
         */
        StringBuilder fileContent = new StringBuilder();
        /*
         * The code below starts a try block with a resource declaration. The
         * BufferedReader is used to read text from the file pointed by filePath.
         * The try-with-resources statement ensures that the BufferedReader is properly
         * closed after its operations within the block.
         */
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            /*
             * This while loop reads each line from the file using br.readLine() and assigns
             * it to the line variable.
             * The loop continues until readLine() returns null, indicating the end of the
             * file.
             * For each line read, the line is appended to the content StringBuilder using
             * content.append(line).
             * Additionally, a space character is appended after each line using
             * .append(" ").
             */
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.append(line).append(" ");
            }
            /*
             * The catch block catches any IOExceptions that may occur while reading the
             * file.
             * The stack trace is printed using e.printStackTrace().
             */
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
         * The fileContent StringBuilder is converted to a String using
         * fileContent.toString() and returned.
         */
        System.out.println();
        System.out.println("FINISHED READING TEXT FILE");
        System.out.println();
        return fileContent.toString();
    }

    /**
     * The readAndAddToLL function reads a text file and adds the contents of that
     * file to a string which is separated through text.split(" ") in a String array
     * and then to added to a linked list.
     *
     * @doc-author Edd Joseph
     */
    public void readAndAddToLL() {
        // file path
        String filepath = "/Users/egj/Desktop/TextParser2/pg174.txt";
        // file content initialized to String called text
        String text = readTextFile(filepath);
        System.out.println("CONTENT OF TEXT FILE TURNED INTO STRING");
        System.out.println();
        // Split the input 'text' string into an array of substrings using " " (space)
        // as the delimiter.
        // This will separate the 'text' string at each occurrence of a space character,
        // resulting in an array of words.
        String[] words = text.split(" ");
        System.out.println("CONTENT OF TEXT FILE SPLIT INTO STRING ARRAY");
        System.out.println();
        
        // For each word in the words array, insert the word into the linked list.
        for (String word : words) {
            ll.insert(word);
        }
        System.out.println("CONTENTS OF TEXT FILE ADDED TO LINKED LIST");
        System.out.println();
        System.out.println();
        // Print the contents of the linked list
        System.out.println("LINKED LIST CONTENTS AND THE AMOUNT OF INSTANCES OF THE WORDS:");
        ll.printList();
    }

    public int getSize() {
        return ll.getSize();
        
    }

   //get word count of the specified word in the link
    public String getWordCount() {
        ll.getWordCount();
        return null;
    }

public String getWordsMoreThan20(){
    ll.getWordsMoreThan20();
    return null;
}

public String getMostFrequentWord(){
    ll.getMostFrequentWord();
    return null;
}

    // Main entry point
    public static void main(String[] args) {
        // call class - TextParser
        TextParser TP = new TextParser();

        // Call readAndAddToLL to read the file and add it to the linked list
        TP.readAndAddToLL();

         //Call to get words occurring more than 20 times
        TP.getWordsMoreThan20();

        // Call getSize to get the size of the linked list
        TP.getSize();

         //Call to get most frequent word
        TP.getMostFrequentWord();

        //Call to get the longest word
        TP.ll.getLongestWord();

        // Call getWordCount to get the word count of the specified words in the linked list
        TP.getWordCount();

       

       



        System.out.println("PROCESSES COMPLETED");
    }
}
