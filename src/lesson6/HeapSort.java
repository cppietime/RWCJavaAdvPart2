package lesson6;

/**
 *
 */
public class HeapSort {

    private static int parent(int n) {
        return (n - 1) / 2;
    }

    private static int leftChild(int n){
        return n * 2 + 1;
    }

    private static int rightChild(int n){
        return n * 2 + 2;
    }

    private static <T extends Comparable<T>> void enqueue(T[] heap, int startOffset, int currentIndex) {
        if(currentIndex == 0)
            return;
        T me = heap[startOffset + currentIndex];
        int parentIndex = parent(currentIndex);
        T parent = heap[startOffset + parentIndex];
        if(me.compareTo(parent) > 0) {
            Util.swap(heap, startOffset + currentIndex, startOffset + parentIndex);
            enqueue(heap, startOffset, parentIndex);
        }
    }

    private static <T extends Comparable<T>> void sift(T[] array, int startOffset, int currentIndex, int size) {
        int leftIndex = leftChild(currentIndex), rightIndex = rightChild(currentIndex);
        if(leftIndex >= size)
            return;
        T me = array[startOffset + currentIndex], left = array[startOffset + leftIndex];
        int childIndex = leftIndex;
        T child = left;
        if(rightIndex < size) {
            T right = array[startOffset + rightIndex];
            if(right.compareTo(child) > 0) {
                child = right;
                childIndex = rightIndex;
            }
        }
        if(me.compareTo(child) < 0) {
            Util.swap(array, startOffset + currentIndex, startOffset + childIndex);
            sift(array, startOffset, childIndex, size);
        }
    }

    public static <T extends Comparable<T>> void heapSort(T[] array, int startOffset, int endOffset) {
        for(int i = 1; i < endOffset - startOffset; i++) {
            enqueue(array, startOffset, i);
        }
        for(int i = endOffset - startOffset - 1; i > 0; i--){
            Util.swap(array, startOffset, startOffset + i);
            sift(array, startOffset, 0, i);
        }
    }

    public static <T extends Comparable<T>> void heapSort(T[] array){
        heapSort(array, 0, array.length);
    }

}
