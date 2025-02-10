import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

class TextParser {
    private LinkedList<String> linkedList;

    public TextParser(LinkedList<String> linkedList) {
        this.linkedList = linkedList;
    }

    // Method to read and process the text file.
    public void readAndProcessTextFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
            
            reader.close();
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    // Method to process a line of text, split it into words, and insert into the Linked List.
    private void processLine(String line) {
        // Remove punctuation and convert to lowercase
        String[] words = line.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split("\\s+");

        for (String word : words) {
            if (!word.isEmpty()) {
                linkedList.insertWord(word);
            }
        }
    }
}
