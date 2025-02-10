/*
 * LinkedList implements a doubly linked list data structure.
 * It is generic, allowing storage of elements of any type E.
 * 
 * The main properties are:
 * - head: Points to the first node in the list
 * - tail: Points to the last node in the list  
 * - size: Tracks the number of elements  
 * 
 * Nodes are implemented via a nested Node class with prev/next references.
 *
 * The main methods are:
 *
 * - LinkedList(): Constructor, initializes empty list
 * - size(): Get the number of elements 
 * - get(int): Get element at specified index
 * - toString(): Convert list to string representation
 * - add(E): Append element to end of list  
 * - add(int, E): Insert element at specified index
 * - remove(int): Remove/return element at specified index
 * - contains(E): Check if list contains given element
 * 
 * This implements a doubly linked list allowing fast prepend/append/
 * insert/delete operations in constant time.
 */



public class LinkedList<E> {

  private Node<E> head;
  private Node<E> tail;
  private int size;

  public LinkedList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
  }

  public int size() {
    return this.size;
  }

  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    Node<E> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    return current.data;
  }

  public String toString() {
    if (head == null) {
      return "[]";
    }
    StringBuilder sb = new StringBuilder("[");
    Node<E> current = head;
    while (current != null) {
      sb.append(current.data);
      if (current.next != null) {
        sb.append(", ");  
      }
      current = current.next;
    }
    sb.append("]");
    return sb.toString();
  }

  public boolean add(E element) {
    Node<E> newNode = new Node<>(element);
    if (head == null) {
      head = newNode;
      tail = newNode;
    } else {
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    }
    size++;
    return true;
  }

  public void add(int index, E element) {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }
    Node<E> newNode = new Node<>(element);
    if (index == 0) {
      newNode.next = head;
      head.prev = newNode;
      head = newNode;
    } else {
      Node<E> current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.next;
      }
      newNode.next = current.next;
      newNode.prev = current;
      current.next.prev = newNode;
      current.next = newNode;
    }
    size++;
  }
  
  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    Node<E> current = head;
    for (int i = 0; i < index; i++) {
      current = current.next;
    }
    E element = current.data;
    current.prev.next = current.next;
    if (current.next != null) {
      current.next.prev = current.prev; 
    }
    size--;
    return element;
  }

  public boolean contains(E element) {
    Node<E> current = head;
    while (current != null) {
      if (current.data.equals(element)) {
        return true;
      }
      current = current.next;
    }
    return false;
  }

  private static class Node<E> {
    E data;
    Node<E> prev;
    Node<E> next;

    public Node(E data) {
      this.data = data;
    }
  }

}
