package lesson5;

import java.util.*;

/**
 * Array backed list
 * @param <T>
 */
public class ArrayBackedList<T> implements List<T>, MyStack<T> {

    private static final int INITIAL_CAPACITY = 2;

    /* Holds the data of the array */
    private Object[] data;

    /* Number of filled elements */
    private int size;

    public ArrayBackedList() {
        data = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Double the size of the backing array
     */
    private void doubleArray() {
        Object[] newArray = new Object[data.length * 2];
        for(int i = 0; i < size; i++) {
            newArray[i] = data[i];
        }
        data = newArray;
    }

    /**
     * Add an element to the end of the list
     * @param t The element we are adding
     */
    public boolean add(T t) {
        if(size == data.length)
            doubleArray();
        data[size] = t;
        size++;
        return true;
    }

    /**
     * Retrieves the value at an index
     * @param index Index of value
     * @return Value at index
     */
    public T get(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index + " is out of range " + size);
        return (T) data[index];
    }

    @Override
    public T set(int index, T element) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index + " is out of range " + size);
        T oldValue = (T) data[index];
        data[index] = element;
        return oldValue;
    }

    /**
     * Removes the last element of the list
     * @return The removed element
     */
    public T removeFromEnd() {
        if(size == 0)
            throw new IllegalStateException("List is empty");
        T beingRemoved = (T) data[size - 1];
        size --;
        return beingRemoved;
    }

    /**
     * Insert at arbitrary position
     * @param index Index to insert at
     * @param value Value to insert
     */
    public void add(int index, T value) {
        if(size == data.length)
            doubleArray();
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException(index + " is out of range " + size);
        for(int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = value;
        size++;
    }

    /**
     * Remove the element at an arbitrary index
     * @param index Index of element
     * @return Removed element
     */
    public T remove(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index + " out of range " + size);
        T beingRemoved = (T) data[index];
        for(int i = index; i < size - 1; i++){
            data[i] = data[i + 1];
        }
        size--;
        return beingRemoved;
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < size; i++){
            if(data[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for(int i = size - 1; i >= 0; i--){
            if(data[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Not yet");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Not yet");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if(fromIndex < 0 || fromIndex >= size || toIndex < 0 || toIndex > size)
            throw new IndexOutOfBoundsException("Indices out of bound");
        List<T> subList = new ArrayBackedList<>();
        for(int i = fromIndex; i < toIndex; i++)
            subList.add(get(i));
        return subList;
    }

    /**
     *
     * @return The current size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Removes all elements
     */
    public void clear() {
        size = 0;
    }

    public void push(T value){
        add(value);
    }

    public T pop() {
        return removeFromEnd();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T peek() {
        return get(size - 1);
    }

    public boolean contains(Object obj) {
        for(int i = 0; i < size; i++) {
            if(data[i].equals(obj))
                return true;
        }
        return false;
    }

    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        for(int i = 0; i < size; i++)
            array[i] = data[i];
        return array;
    }

    public <T1> T1[] toArray(T1[] arr) {
        if(arr.length < size) {
            return (T1[]) Arrays.copyOf(data, size, arr.getClass());
        }
        for(int i = 0; i < size; i++)
            arr[i] = (T1) data[i];
        return arr;
    }

    public boolean remove(Object obj) {
        for(int i = 0; i < size; i++){
            if(data[i].equals(obj)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object obj : c){
            if(!contains(obj))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for(T t : c)
            add(t);
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        int i = 0;
        for(T t : c) {
            add(index + i, t);
            i++;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for(Object obj : c){
            if(remove(obj))
                changed = true;
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        for(Iterator<T> iterator = iterator(); iterator.hasNext();) {
            T element = iterator.next();
            if(!c.contains(element)) {
                iterator.remove();
                changed = true;
            }
        }
        return changed;
    }

    class ArrayListIterator implements Iterator<T> {

        private int position = 0;

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public T next() {
            if(!hasNext())
                throw new NoSuchElementException("End of list");
            T next = get(position);
            position ++;
            return next;
        }

        public void remove(){
            if(position == 0)
                throw new IllegalStateException("Next not called");
            ArrayBackedList.this.remove(position - 1);
        }
    }
}
