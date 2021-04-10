package lesson6;

/**
 * For example:
 * 10, 11, 4, 4, 1, 0
 *
 * First pass: Find where the 11 should go?
 * Either before or after the element in position 0
 * 11 > 10, so it goes after, in position 1
 * Swap 11 into position 1
 * 10, 11, 4, 4, 1, 0
 *
 * Second pass: Where does the 4 go?
 * Sorted segment: 10, 11
 * 4 goes into position 0
 * 4, 10, 11, 4, 1, 0
 *
 * Third pass: Where does 4 go?
 * Sorted part: 4, 10, 11
 * 4 goes into position 1
 * 4, 4, 10, 11, 1, 0
 *
 * Fourth pass: Where does 1 go?
 * Sorted part: 4, 4, 10, 11
 * 1, 4, 4, 10, 11, 0
 *
 * Fifth pass: 0 goes into position 0
 * 0, 1, 4, 4, 10, 11
 *
 * Worst/average time: O(n^2)
 * Best time: O(n)
 * O(1) space
 *
 * Binary search example:
 * 0, 1, 6, 8, 8, 9, 11, 12, 12 (9 long)
 * Want to find where 4 goes
 * Position 4: 8
 * Compare 4 to 8: 4 < 8
 * 0, 1, 6, 8 (4 long), we search for 4
 * Position 1: 1
 * Compare 4 to 1: 4 > 1
 * 6, 8 (2 long), search for 4
 * Position 2: 6
 * Compare 4 to 6: 4 < 6
 * 4 goes into position 2
 */
public class InsertionSort {

    public static <T extends Comparable<T>> void insertionSort(T[] array, int startOffset, int endOffset) {
        for(int i = startOffset + 1; i < endOffset; i++) {
            int position = Util.binarySearch(array, startOffset, i, array[i]);
            Util.cycleRight1(array, position, i + 1);
        }
    }

    public static <T extends Comparable<T>> void insertionSort(T[] array) {
        insertionSort(array, 0, array.length);
    }

}
