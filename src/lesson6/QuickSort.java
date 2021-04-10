package lesson6;

import java.util.Arrays;

/**
 * 9, 1, 3, 0, 3, 7, 5
 *
 * Choose 0 as pivot (middle of array)
 * 0, 1, 3, 5, 3, 7, 9
 *
 * Quicksort on 1, 3, 5, 3, 7, 9
 * pivot = 5
 * 1, 3, 3, 5, 7, 9
 *
 * Sort 1, 3, 3
 * Pivot = 3
 * 1, 3, 3
 *
 * Sort 7, 9
 * Pivot = 7
 * 7, 9
 *
 * 1, 3, 3, 5, 7, 9
 *
 * Average runtime O(n * log(n))
 * Worst case runtime O(n^2)
 * Space complexity O(log(n))
 */
public class QuickSort {

    public static <T extends Comparable<T>> void quickSort(T[] array, int startOffset, int endOffset) {
        if(endOffset - startOffset <= 1)
            return;
        int pivotIndex = (startOffset + endOffset - 1) / 2;
        T pivot = array[pivotIndex];
        Util.swap(array, pivotIndex, endOffset - 1);
        int lt = 0, gt = 0;
        for(int i = startOffset; i < endOffset - 1 && lt + gt < endOffset - startOffset - 1; i++) {
            T value = array[i];
            if(value.compareTo(pivot) > 0) {
                Util.swap(array, i, endOffset - 2 - gt);
                gt ++;
                i --;
            }
            else {
                Util.swap(array, i, startOffset + lt);
                lt ++;
            }
        }
        Util.swap(array, startOffset + lt, endOffset - 1);
        quickSort(array, startOffset, startOffset + lt);
        quickSort(array, startOffset + lt + 1, endOffset);
    }

    public static <T extends Comparable<T>> void quickSort(T[] array) {
        quickSort(array, 0, array.length);
    }

}
