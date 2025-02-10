import java.util.Arrays; //Array library
import java.lang.reflect.Array; // For casting to generic array

/*
 * ArrayList is a generic resizable array implementation.
 * 
 * The key properties are:
 * - elements: the array storing the list elements 
 * - size: the number of elements currently in the list
 * - capacityDefault: the default initial capacity if not specified
 *
 * The main methods are:
 * 
 * - ArrayList(int): Constructor to create a list with initial capacity
 * - ArrayList(): Default constructor uses DEFAULT_CAPACITY
 * - size(): Get the number of elements
 * - get(int): Get the element at an index  
 * - toString(): Convert the list to a string
 * - doubleCapacity(): Increase the capacity of the backing array
 * - add(E): Append an element to the end
 * - add(int, E): Insert an element at the specified index
 * - remove(int): Remove and return the element at an index
 * - contains(E): Check if the list contains a given element
 *
 * This implements a dynamic array that resizes as needed. It provides 
 * common methods for adding, accessing, and removing elements.
 */


import java.util.Arrays;

public class ArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    // 1. Public constructor ArrayList(int initialCapacity)
    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative.");
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    // 2. Public constructor ArrayList()
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    // 3. Public method size()
    public int size() {
        return size;
    }

    // 4. Public method get(int idx)
    public E get(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        @SuppressWarnings("unchecked") // Suppress the unchecked warning here
        E element = (E) elements[idx];
        return element;
    }

    // 5. Public method toString()
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // 6. Protected method doubleCapacity()
    protected void doubleCapacity() {
        int newCapacity = elements.length * 2;
        elements = Arrays.copyOf(elements, newCapacity);
    }

    // 7. Public method add(E element)
    public boolean add(E element) {
        if (size == elements.length) {
            doubleCapacity();
        }
        elements[size] = element;
        size++;
        return true;
    }

    // 8. Public method add(int idx, E element)
    public void add(int idx, E element) {
        if (idx < 0 || idx > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        if (size == elements.length) {
            doubleCapacity();
        }
        for (int i = size; i > idx; i--) {
            elements[i] = elements[i - 1];
        }
        elements[idx] = element;
        size++;
    }

    // 9. Public method remove(int idx)
    public E remove(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        @SuppressWarnings("unchecked") // Suppress the unchecked warning here
        E removedElement = (E) elements[idx];
        for (int i = idx; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[size - 1] = null;
        size--;
        return removedElement;
    }

    // 10. Public method contains(E element)
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            @SuppressWarnings("unchecked") // Suppress the unchecked warning here
            E currentElement = (E) elements[i];
            if (currentElement.equals(element)) {
                return true;
            }
        }
        return false;
    }
}
