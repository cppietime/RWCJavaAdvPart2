package lesson5;

public class DoublyLinkedList<T> implements MyStack<T>, MyQueue<T> {

    class ListNode {
        private T value;
        private ListNode next;
        private ListNode previous;

        public ListNode(T value){
            this.value = value;
        }
    }

    private int size;
    private ListNode head;
    private ListNode tail;

    public DoublyLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    /**
     * Adds value to the tail
     * @param value New value to add
     */
    public void addToTail(T value) {
        ListNode newNode = new ListNode(value);
        if(size == 0) { // First element being added
            head = newNode;
        }
        else {
            newNode.previous = tail;
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    public void addToHead(T value) {
        ListNode newNode = new ListNode(value);
        if(size == 0) {
            tail = newNode;
        }
        else {
            head.previous = newNode;
            newNode.next = head;
        }
        head = newNode;
        size++;
    }

    private ListNode getNode(int index) {
        ListNode current;
        for(current = head; index != 0; current = current.next, index --);
        return current;
    }

    /**
     * Get the indexed value
     * @param index Index of value
     * @return Value at index
     */
    public T get(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index + " out of range " + size);
        ListNode node = getNode(index);
        return node.value;
    }

    /**
     * Remove the value at the head
     * @return The previous head value
     */
    public T removeFromHead() {
        if (head == null)
            throw new IllegalStateException("List is empty");
        T beingRemoved = head.value;
        if(size == 1) {
            head = null;
            tail = null;
        }
        else {
            head = head.next;
            head.previous = null;
        }
        size--;
        return beingRemoved;
    }

    /**
     * Remove the value at the tail
     * @return The previous head value
     */
    public T removeFromTail() {
        if (tail == null)
            throw new IllegalStateException("List is empty");
        T beingRemoved = tail.value;
        if(size == 1) {
            head = null;
            tail = null;
        }
        else {
            tail = tail.previous;
            tail.next = null;
        }
        size--;
        return beingRemoved;
    }

    /**
     * Insert at an arbitrary position
     * @param index Position of new value
     * @param value Value inserted
     */
    public void insert(int index, T value) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException(index + " out of range " + size);
        if(index == 0)
            addToHead(value);
        else if(index == size)
            addToTail(value);
        else {
            ListNode before = getNode(index - 1);
            ListNode after = before.next;
            ListNode newNode = new ListNode(value);
            newNode.next = after;
            newNode.previous = before;
            before.next = newNode;
            after.previous = newNode;
            size++;
        }
    }

    /**
     * Removes an arbitrary element
     * @param index Position to remove
     * @return Value removed
     */
    public T remove(int index){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index + " out of range " + size);
        if(index == 0)
            return removeFromHead();
        else if(index == size - 1)
            return removeFromTail();
        else {
            ListNode before = getNode(index - 1);
            ListNode removedNode = before.next;
            ListNode after = removedNode.next;
            before.next = after;
            after.previous = before;
            size--;
            return after.value;
        }
    }

    /**
     *
     * @return Current size of list
     */
    public int getSize() {
        return size;
    }

    /**
     * Clear the list
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public void push(T value) {
        addToHead(value);
    }

    public T pop() {
        return removeFromHead();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        return get(0);
    }

    public void enqueue(T value) {
        addToTail(value);
    }

    public T dequeue() {
        return removeFromHead();
    }

}
