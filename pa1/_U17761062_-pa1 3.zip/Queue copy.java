import java.util.LinkedList;

public class Queue<E> {
    private LinkedList<E> queue;

    public Queue() {
        queue = new LinkedList<>();
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void push(E element) {
        queue.addLast(element);
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        return queue.removeFirst();
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return queue.getFirst();
    }
}
