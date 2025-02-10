
/*
 * LinkedList implements a singly linked list data structure to store words from a text file.
 * 
 * The LinkedList contains LinkedListNode objects to represent each word and its frequency.
 * 
 * The key methods are:
 * 
 * - insert(String word)  
 *   Adds a new node for the word or increments count if word already exists
 *   
 * - getSize()
 *   Returns the number of nodes/words in the list
 *   
 * - getWordsMoreThan20() 
 *   Prints words that occur more than 20 times
 *   
 * - getLongestWord()
 *   Finds and prints the longest word in the list
 *   
 * - getMostFrequentWord()
 *   Finds and prints the most frequently occurring word
 *   
 * - getWordCount()
 *   Prints count of specified words in the list
 *   
 * - printList()  
 *   Prints all words and counts in the list
 *   
 * This provides a way to load text into a linked list of words and then 
 * analyze word counts, frequencies, etc.
 */

public class LinkedList {
    private LinkedListNode head;
    private int size;

    // This is the constructor for the LinkedList class.
    public LinkedList() {
        // Initialized the head of the linked list to null, indicating an empty list.
        this.head = null;
        // Initialized the size of the linked list to 0, as it currently has no
        // elements.
        this.size = 0;
    }

    // This method is used to insert a word into the linked list.
    public void insert(String word) {
        // Pre-process the 'word' before insertion:
        // Convert the word to lowercase and remove any non-alphanumeric characters
        // (except spaces).
        word = word.toLowerCase().replaceAll("[^a-z0-9\\s]", "");
        // If the linked list is empty, create the first node (head) with the provided
        // 'word'.
        if (head == null) {
            head = new LinkedListNode(word);
        } else {
            // If the linked list is not empty, traverse through it to check if the 'word'
            // already exists.
            LinkedListNode current = head;
            while (current.getNext() != null) {
                // If the 'word' already exists in a node, increment its instance count and
                // return.
                if (current.getWord().equals(word)) {
                    current.incrementInstanceCount();
                    return; // Word found, no need to add a new node
                }
                // Move to the next node in the linked list.
                current = current.getNext();
            }

            // If the 'word' doesn't exist in the linked list, add a new node to the end of
            // the list.
            current.setNext(new LinkedListNode(word));
        }
        // Increment the size of the linked list to reflect the new insertion.
        size++;
    }

    // This method returns the size of the linked list.
    public int getSize() {
        System.out.println();
        // 'this.size' refers to the size of the linked list, which is a member variable
        // of the class.
        System.out.println("SIZE OF LINKED LIST: " + this.size);
        System.out.println();
        // Return the size of the linked list.
        // 'this.size' is returned as the method's result.
        return this.size;
    }

    // How many words occur more than 20 times in the list (display them and their
    // respective counts)
    public void getWordsMoreThan20() {
        System.out.println("Words that occur more than 20 times in the list: ");
        System.out.println();
        // Start from the head of the linked list.
        LinkedListNode current = this.head;
        // Keeping track of the amount of words that occur more than 20 times
        int size = 0;
        // Traverse through the linked list until the end (null is reached).
        while (current != null) {
            // Output the current node's word and its instance count.
            // 'current.getWord()' retrieves the word stored in the current node.
            // 'current.getInstanceCount()' retrieves the count of occurrences of the word
            // in the current node.
            if (current.getInstanceCount() > 20) {
                System.out.println(current.getWord() + " (" + current.getInstanceCount() + ")");
                size++;
            }
            // Move to the next node in the linked list. REPEATS TILL IT REACHES LAST NODE
            current = current.getNext();
        } // END WHILE LOOP
        System.out.println("End of List - (Amount of words occurring more than 20 times (" + size + "))");
        System.out.println();
    } // END OF METHOD

    // longest word in the list
    public String getLongestWord() {
        // Start from the head of the linked list.
        LinkedListNode current = this.head;
        // Traverse through the linked list until the end (null is reached).
        String longestWord = "";
        while (current != null) {
            // Output the current node's word and its instance count.
            // 'current.getWord()' retrieves the word stored in the current node.
            // 'current.getInstanceCount()' retrieves the count of occurrences of the word
            // in the current node.
            if (current.getWord().length() > longestWord.length()) {
                longestWord = current.getWord();
            }
            // Move to the next node in the linked list. REPEATS TILL IT REACHES LAST NODE
            current = current.getNext();
        } // END WHILE LOOP
        System.out.println();
        System.out.println("LONGEST WORD IN THE LIST: " + longestWord);
        System.out.println();
        return longestWord;
    } // END OF METHOD

    // which word occurs most frequently in the list
    public String getMostFrequentWord() {
        // Start from the head of the linked list.
        LinkedListNode current = this.head;
        // Traverse through the linked list until the end (null is reached).
        String mostFrequentWord = "";
        int mostFrequentWordCount = 0;
        while (current != null) {
            // Output the current node's word and its instance count.
            // 'current.getWord()' retrieves the word stored in the current node.
            // 'current.getInstanceCount()' retrieves the count of occurrences of the word
            // in the current node.
            if (current.getInstanceCount() > mostFrequentWordCount) {
                mostFrequentWordCount = current.getInstanceCount();
                mostFrequentWord = current.getWord();
            }
            // Move to the next node in the linked list. REPEATS TILL IT REACHES LAST NODE
            current = current.getNext();
        } // END WHILE LOOP
        System.out.println();
        System.out.println("MOST FREQUENT WORD IN THE LIST: " + mostFrequentWord + " - " + mostFrequentWordCount + " times"); 
        System.out.println();
        System.out.println();
        return mostFrequentWord;
    } // END OF METHOD

    // how many many times do the following words appear in the text? (portrait,
    // persian, dorian, experimental, magnetic)
    public void getWordCount() {
        // Start from the head of the linked list.
        LinkedListNode current = this.head;
        System.out.println(
                "AMOUNT OF TIMES THE FOLLOWING WORDS OCCUR (ASSUMING THEY'RE FOUND) - (PERSIAN, PORTRAIT, DORIAN, EXPERIMENTAL, AND MAGNETIC):  ");
        System.out.println("WORD(S) THAT ARE NOT FOUND ARE NOT DISPLAYED");
        System.out.println();
        // Traverse through the linked list until the end (null is reached).
        while (current != null) {
            // Output the current node's word and its instance count.
            // 'current.getWord()' retrieves the word stored in the current node.
            // 'current.getInstanceCount()' retrieves the count of occurrences of the word
            // in the current node.
            if (current.getWord().equals("portrait")) {
                System.out.println("PORTRAIT: " + current.getInstanceCount());
            }
            if (current.getWord().equals("persian")) {
                System.out.println("PERSIAN: " + current.getInstanceCount());
            }
            if (current.getWord().equals("dorian")) {
                System.out.println("DORIAN: " + current.getInstanceCount());
            }
            if (current.getWord().equals("experimental")) {
                System.out.println("EXPERIMENTAL: " + current.getInstanceCount());
            }
            if (current.getWord().equals("magnetic")) {
                System.out.println("MAGNETIC: " + current.getInstanceCount());
            }

            // Move to the next node in the linked list. REPEATS TILL IT REACHES LAST NODE
            current = current.getNext();
        } // END WHILE LOOP
          //
        System.out.println();
    } // END OF METHOD

    // This method is used to print the contents of the linked list.
    public void printList() {
        // Start from the head of the linked list.
        LinkedListNode current = this.head;
        // Traverse through the linked list until the end (null is reached).
        while (current != null) {
            // Output the current node's word and its instance count.
            // 'current.getWord()' retrieves the word stored in the current node.
            // 'current.getInstanceCount()' retrieves the count of occurrences of the word
            // in the current node.
            System.out.println(current.getWord() + "(" + current.getInstanceCount() + ")");
            // Move to the next node in the linked list. REPEATS TILL IT REACHES LAST NODE
            current = current.getNext();
        } // END WHILE LOOP
        System.out.println();
        System.out.println();
    } // END OF METHOD
} // END OF CLASS
