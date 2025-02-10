package src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Heap<E> {
    private Object[] elements; // The array to store the elements
    private int size; // The number of elements in the heap
    private Comparator<? super E> comparator; // The comparator to use for element comparison

    // Constructors
    public Heap(int initialCapacity, Comparator<? super E> comp) {
        // Initialize the heap with the given initial capacity and comparator
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Initial capacity must be at least 1");
        }
        elements = new Object[initialCapacity];
        size = 0;
        comparator = comp;
    }

    public Heap(int initialCapacity) {
        // Initialize the heap with the given initial capacity and use natural ordering
        this(initialCapacity, null);
    }

    public Heap(Comparator<? super E> comp) {
        // Initialize the heap with capacity for 10 elements and use the given comparator
        this(10, comp);
    }

    public Heap() {
        // Initialize the heap with capacity for 10 elements and use natural ordering
        this(10, null);
    }

    // Public methods
    public int size() {
        // Return the number of elements in the heap
        return size;
    }

    @Override
    public String toString() {
        // Return the string representation of the heap
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(elements[i]);
        }
        sb.append("]");
        return sb.toString();
    }

    // Protected method to double the capacity of the heap
    protected void doubleCapacity() {
        // Increase the capacity of the heap to double its current capacity
        int newCapacity = elements.length * 2;
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    public boolean push(E element) {
        // Add the specified element to the end of the heap
        // Resize the heap if the capacity has been reached
        // Always return true
        if (size == elements.length) {
            doubleCapacity();
        }
        elements[size] = element;
        size++;
        siftUp(size - 1);
        return true;
    }

    public E pop() {
        // Remove and return the root element of the heap
        // Rebalance the heap before returning
        // If there are no elements left, return null
        if (size == 0) {
            return null;
        }
        E root = getElement(0);
        swap(0, size - 1);
        size--;
        siftDown(0);
        return root;
    }

    public E peek() {
        // Return the root element of the heap without removing it
        // If there are no elements left, return null
        if (size == 0) {
            return null;
        }
        return getElement(0);
    }

    public boolean contains(E element) {
        // Return true if the element is contained within the heap, false otherwise
        // Use the comparator for element comparison
        for (int i = 0; i < size; i++) {
            if (compareElements(element, getElement(i)) == 0) {
                return true;
            }
        }
        return false;
    }

    // Extra Credit - O(n) time complexity
    public static <E> Heap<E> heapify(List<E> elements, Comparator<? super E> comp) {
        // Construct a heap from the elements contained within the list
        // Run the method in O(n) time complexity
        Heap<E> heap = new Heap<>(comp);
        heap.elements = new Object[elements.size()];
        heap.size = elements.size();
        System.arraycopy(elements.toArray(), 0, heap.elements, 0, elements.size());
        for (int i = (heap.size / 2) - 1; i >= 0; i--) {
            heap.siftDown(i);
        }
        return heap;
    }

    // Helper methods
    @SuppressWarnings("unchecked")
    private int compareElements(E e1, E e2) {
        // Compare two elements using the specified comparator or natural ordering
        if (comparator != null) {
            return comparator.compare(e1, e2);
        } else {
            Comparable<? super E> comparableE1 = (Comparable<? super E>) e1;
            return comparableE1.compareTo(e2);
        }
    }

    @SuppressWarnings("unchecked")
    private E getElement(int index) {
        // Get the element at the specified index from the array
        return (E) elements[index];
    }

    private void swap(int i, int j) {
        // Swap elements at the specified indices in the array
        Object temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    private void siftUp(int index) {
        // Move the element up in the heap to maintain the heap property
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (compareElements(getElement(index), getElement(parentIndex)) > 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    private void siftDown(int index) {
        // Move the element down in the heap to maintain the heap property
        while (index < size / 2) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int minChildIndex = leftChildIndex;
            if (rightChildIndex < size && compareElements(getElement(rightChildIndex), getElement(leftChildIndex)) < 0) {
                minChildIndex = rightChildIndex;
            }
            if (compareElements(getElement(index), getElement(minChildIndex)) <= 0) {
                break;
            }
            swap(index, minChildIndex);
            index = minChildIndex;
        }
    }
}
