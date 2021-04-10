package lesson5;

/*

Array: Many values of the same type

Array-list:
[X, X, X, X] - array capacity = 4, size = 0
[0, X, X, X] - inserted 0, size = 1
[0, 1, 2, 3] - inserted 1, 2, and 3, size = 4

Insert a 4:
Step 1: Make a new array, with a greater capacity
[X, X, X, X, X, X, X, X] - capacity 8
Step 2: Copy the contents of the old array into the new one
[0, 1, 2, 3, X, X, X, X] - capacity 8, size 4
Step 3: Insert new value as normal
[0, 1, 2, 3, 4, X, X, X] - size = 5

[0, 1, 2, 3, 4, X, X, X] - want to remove the 2
[0, 1, X, 3, 4, X, X, X]
[0, 1, 3, 4, X, X, X, X]

Accessing: O(1) time
Inserting to end: O(1) time
Removing from end: O(1) time
Inserting or removing at arbitrary position: O(n) time

Linked list:
(1) -> (2) We want to insert a 3 at the tail
(1) -> (2) -> (3) We want to insert a 0 at the head
(0) -> (1) -> (2) -> (3) Remove 3
(0) -> (1) -> (2) Remove 0
(1) -> (2)

Doubly linked list:
(0) <-> (1) <-> (2) <-> (3)

Stack: LIFO (last-in, first-out)
push: Push a new item to the top of the stack
pop: Remove the item at the top of the stack and return it
[] push 0
[0] push 5
[0, 5] push 6
[0, 5, 6] pop
[0, 5] push 2
[0, 5, 2] pop
[0, 5] pop
[0]

Queue: FIFO (first-in first-out)
enqueue: Put an element at the back of the queue
dequeue: Remove and return the element at the front of the queue
[] enqueue 0
[0] enqueue 5
[0, 5] enqueue 6
[0, 5, 6] dequeue
[5, 6] enqueue 2
[5, 6, 2] dequeue
[6, 2] dequeue
[2]

 */

import java.util.Arrays;

public class Main {

    public static void testArrayBackedList() {
        System.out.println("Testing the array-backed list");
        ArrayBackedList<Integer> list = new ArrayBackedList<>();
        list.add(0);
        list.add(4);
        list.add(5);
        list.add(3);
        list.add(1, 10);
        list.add(3, 12);
        list.remove(0);
        list.remove(0);
        for(Integer i : list){
            System.out.println(i);
        }
        System.out.println("Array: " + Arrays.toString(list.toArray(new Integer[0])));
    }

    public static void testDoublyLinkedList(){
        System.out.println("Testing doubly linked list");
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addToTail(1); // 1
        list.addToTail(3); // 1 -> 3
        list.addToHead(5); // 5 -> 1 -> 3
        list.addToTail(4); // 5 -> 1 -> 3 -> 4
        System.out.println("Removed " + list.removeFromHead() + " from head"); // 1 -> 3 -> 4
        System.out.println("Removed " + list.removeFromTail() + " from tail"); // 1 -> 3
        list.insert(0, -1); // -1 -> 1 -> 3
        list.insert(2, 0); // -1 -> 1 -> 0 -> 3
        System.out.println("Arbitrarily removed " + list.remove(2));
        while(list.getSize() > 0) {
            System.out.println("Removed " + list.removeFromHead() + " from head");
        }
    }

    public static void testStack(MyStack<Float> stack){
        System.out.println("Testing a stack");
        for(float f = 1f; f <= 5f; f += .5f) {
            stack.push(f);
        }
        while(!stack.isEmpty()) {
            System.out.println("Popped " + stack.pop());
        }
    }

    public static void testQueue(MyQueue<Float> queue){
        System.out.println("Testing a queue");
        for(float f = 1f; f <= 5f; f += .5f) {
            queue.enqueue(f);
        }
        while(!queue.isEmpty()) {
            System.out.println("Dequeued " + queue.dequeue());
        }
    }

    public static void main(String[] args) {
        testArrayBackedList();
        testDoublyLinkedList();
        testStack(new ArrayBackedList<>());
        testStack(new DoublyLinkedList<>());
        testQueue(new DoublyLinkedList<>());
    }

}
