package util;

import java.util.LinkedList;

public class Stack<E> {
    private LinkedList<E> storage = new LinkedList<>();

    /**
     * Pushes an item onto the top of this stack. This has exactly
     * the same effect as:
     * <blockquote><pre>
     * addElement(item)</pre></blockquote>
     * @param item the item to be pushed onto this stack.
     * @return  the <code>item</code> argument.
     * @see     java.util.LinkedList#addLast(Object)
     * @return
     */
    public E push(E item) {
        storage.addFirst(item);

        return item;
    }

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     * @return The object at the top of this stack.
     */
    public E pop() {
        return storage.removeFirst();
    }

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     * @return the object at the top of this stack.
     */
    public E peek() {
        return storage.getFirst();
    }

    /**
     * Tests if this stack is empty.
     * @return <code>true</code> if and only if this stack contains
     *          no items; <code>false</code> otherwise.
     */
    public boolean empty() {
        return storage.size() == 0;
    }

    public String toString() {
        return storage.toString();
    }
}
