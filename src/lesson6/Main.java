package lesson6;

import java.util.Arrays;
import java.util.Random;

/**
 * Sorting algorithms
 *
 * Comparison sorts:
 * O(n^2):
 * Selection
 * Insertion
 *
 * O(n*log(n)):
 * Quick sort
 * Merge sort
 * Heap sort
 *
 *
 * Comparison-less sorts:
 * Counting sort
 * Radix sort
 *
 * Some others we won't talk about include:
 * Shell sort
 * Timsort
 * Grailsort
 * Wikisort
 * Cocktail shaker sort
 * Gnome sort
 * Smoothsort
 *
 * 5, 3, 6, 9, 4
 * sorted:
 * 3, 4, 5, 6, 9
 *
 * a.compareTo(b) returns a value indicating whether a > b, a < b, or a == b
 * a.compareTo(a) should always indicate a == a
 * If a.compareTo(b) says a > b, b.compareTo(a) must say b < a
 * If a.compareTo(b) says a > b AND b.compareTo(c) says b > c,
 *      a.compareTo(c) must say a > c
 *
 */
public class Main {

    private static Random random = new Random();

    private static void testSort(Sorter sorter, int size) {
        Integer[] array = new Integer[size];
        for(int i = 0; i < size; i++){
            array[i] = random.nextInt(size);
        }
        System.out.println("Pre-sort array = " + Arrays.toString(array));
        sorter.sort(array);
        System.out.println("Post-sort array = " + Arrays.toString(array));
        boolean sorted = true;
        for(int i = 0; i < size - 1; i++) {
            if (array[i] > array[i + 1]){
                sorted = false;
                break;
            }
        }
        System.out.println("Sorted? " + sorted + "\n");
    }

    public static void main(String[] args) {
        testSort(SelectionSort::selectionSort, 10);

        testSort(InsertionSort::insertionSort, 10);

        testSort(QuickSort::quickSort, 10);

        testSort(MergeSort::mergeSort, 10);

        testSort(HeapSort::heapSort, 10);

        testSort((Sorter<Integer>) array -> CountingSort.countingSort(array), 10);

        testSort((Sorter<Integer>) array -> RadixSort.radixSort(array), 15);
    }

}
