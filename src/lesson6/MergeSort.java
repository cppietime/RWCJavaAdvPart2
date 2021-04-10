package lesson6;

import java.util.Arrays;

/**
 * Always O(n*log(n)) runtime
 * O(n) space
 *
 * 9, 1, 5, 12, 10, 10, 8, 2
 * Split the array into two sub-arrays
 *
 * 9, 1, 5, 12
 * Split
 *
 * 9, 1
 * Split
 *
 * 9 sorted subarray
 * 1 sorted subarray
 *
 * [9] [1] Merge them
 * [1, 9]
 *
 * [1, 9] [5, 12]
 * [1, 5, 9, 12]
 *
 * [1, 5, 9, 12] [2, 8, 10, 10]
 * [1, 2, 5, 8, 9, 10, 10, 12]
 */
public class MergeSort {

    public static <T extends Comparable<T>> void mergeSort(T[] array, int startOffset, int endOffset) {
        if(endOffset <= startOffset + 1)
            return;
        int mid = (endOffset - startOffset) / 2;
        mergeSort(array, startOffset, startOffset + mid);
        mergeSort(array, startOffset + mid, endOffset);
        T[] buffer = Arrays.copyOfRange(array, startOffset, endOffset);
        int lptr = 0, rptr = 0;
        while(lptr < mid && rptr + mid < (endOffset - startOffset)) {
            T left = buffer[lptr], right = buffer[rptr + mid];
            if(left.compareTo(right) <= 0) {
                array[lptr + rptr + startOffset] = left;
                lptr ++;
            }
            else {
                array[lptr+ rptr + startOffset] = right;
                rptr ++;
            }
        }
        while(lptr < mid){
            array[lptr + rptr + startOffset] = buffer[lptr];
            lptr ++;
        }
        while(rptr + mid < endOffset - startOffset){
            array[lptr + rptr + startOffset] = buffer[rptr + mid];
            rptr ++;
        }
    }

    public static <T extends Comparable<T>> void mergeSort(T[] array){
        mergeSort(array, 0, array.length);
    }

}
