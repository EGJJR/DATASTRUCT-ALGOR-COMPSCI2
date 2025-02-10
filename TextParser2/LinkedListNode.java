/*
 * LinkedListNode represents a node in a linked list data structure. 
 * It contains a word, the number of instances of that word, and 
 * a reference to the next node in the list.
 *
 * The key properties are:
 * 
 * - word: The string containing the word for this node
 * 
 * - instanceCount: The number of occurrences of the word in the text
 * 
 * - next: A reference to the next LinkedListNode in the list
 * 
 * The methods allow:
 *  
 * - Getting and setting each property
 * 
 * - incrementInstanceCount(): Increments the instance count by 1
 * 
 * This is used by LinkedList to represent each word and link the nodes 
 * together to form the complete linked list.
 */

class LinkedListNode {
    private String word;
    private int instanceCount;
    private LinkedListNode next;

    public LinkedListNode(String word) {
        this.word = word;
        this.instanceCount = 1; // Set to 1 as we are inserting a new word
        this.next = null;
    }

    // Getter and setter methods for word
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    // Getter and setter methods for instanceCount
    public int getInstanceCount() {
        return instanceCount;
    }

    public void setInstanceCount(int instanceCount) {
        this.instanceCount = instanceCount;
    }

    // Getter and setter methods for next node
    public LinkedListNode getNext() {
        return next;
    }

    public void setNext(LinkedListNode next) {
        this.next = next;
    }

    // Method to increment the instance count
    public void incrementInstanceCount() {
        instanceCount++;
    }

}
