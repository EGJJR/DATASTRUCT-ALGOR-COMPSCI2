
class LinkedListNode {
    String word;
    int count;
    LinkedListNode next;
    
    // Constructor
    public LinkedListNode(String word) {
        this.word = word;
        this.count = 1;
        this.next = null;
    }
    
    // Getters and Setters for word, count, and next.
    // Implement as needed based on your requirements.
}

class LinkedList {
    LinkedListNode head;
    int size;
    
    // Constructor
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    // Method to insert a word into the Linked List or update its count if it already exists.
    public void insertWord(String word) {
        if (head == null) {
            head = new LinkedListNode(word);
            size++;
        } else {
            LinkedListNode current = head;
            while (current.next != null) {
                if (current.word.equals(word)) {
                    current.count++;
                    return; // Word already exists, count updated.
                }
                current = current.next;
            }
            
            // Check if the last node contains the word.
            if (current.word.equals(word)) {
                current.count++;
            } else {
                current.next = new LinkedListNode(word);
                size++;
            }
        }
    }
    
    // Method to get the count of a word in the Linked List.
    public int getCount(String word) {
        LinkedListNode current = head;
        while (current != null) {
            if (current.word.equals(word)) {
                return current.count;
            }
            current = current.next;
        }
        return 0; // Word not found in the Linked List.
    }
    
    // Method to get the number of entries in the Linked List.
    public int getSize() {
        return size;
    }
    
    // Method to display words that occur more than a given count.
    public void displayWordsMoreThan(int countThreshold) {
        LinkedListNode current = head;
        while (current != null) {
            if (current.count > countThreshold) {
                System.out.println(current.word + ": " + current.count);
            }
            current = current.next;
        }
    }
    
    // Method to get the word that occurs most frequently in the Linked List.
    public String getMostFrequentWord() {
        String mostFrequentWord = "";
        int maxCount = 0;
        
        LinkedListNode current = head;
        while (current != null) {
            if (current.count > maxCount) {
                maxCount = current.count;
                mostFrequentWord = current.word;
            }
            current = current.next;
        }
        
        return mostFrequentWord;
    }
    
    // Method to find the longest word in the Linked List.
    public String getLongestWord() {
        String longestWord = "";
        int maxLength = 0;
        
        LinkedListNode current = head;
        while (current != null) {
            if (current.word.length() > maxLength) {
                maxLength = current.word.length();
                longestWord = current.word;
            }
            current = current.next;
        }
        
        return longestWord;
    }
}

