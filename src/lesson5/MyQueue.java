package lesson5;

public interface MyQueue<T> {

    /**
     * Enqueues value to the back of the queue
     * @param value Value to enqueue
     */
    void enqueue(T value);

    /**
     * Dequeues(removes and returns) the front value
     * @return the value at the front of the queue that was just removed
     */
    T dequeue();

    /**
     *
     * @return True if the queue is empty
     */
    boolean isEmpty();

    /**
     * Returns the front element without removing it
     * @return The front of the queue
     */
    T peek();

}
