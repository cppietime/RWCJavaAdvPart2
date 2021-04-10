package lesson5;

public interface MyStack<T> {

    /**
     * Push value to top of stack
     * @param value New top of stack
     */
    void push(T value);

    /**
     * Pop the value on top of the stack
     * @return Value popped
     */
    T pop();

    /**
     *
     * @return Whether or not the stack is empty
     */
    boolean isEmpty();

    /**
     * Returns the top element of the stack without removing it
     * @return The top element
     */
    T peek();

}
