import java.util.LinkedList;

public class Stack<E> {
    private LinkedList<E> stack;

    public Stack() {
        stack = new LinkedList<>();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(E element) {
        stack.addFirst(element);
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }
        return stack.removeFirst();
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return stack.getFirst();
    }
}
