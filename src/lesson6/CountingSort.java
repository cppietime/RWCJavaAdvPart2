package lesson6;

import java.util.Arrays;

/**
 * 8, 6, 10, 10, 4, 4, 4, 4
 *
 * [4, 0, 1, 0, 1, 0, 2]
 * [3, 4, 5, 5, 6, 6, 8]
 * (4, 5, 6, 7, 8, 9, 10)
 *
 * [4, 4, 4, 4, 6, 8, 10, 10]
 *
 * Runs in O(n + k) time
 * k is the range of values
 * Space of O(n + k)
 */
public class CountingSort {

    public static void countingSort(Integer[] array, int startOffset, int endOffset){
        int min = array[0];
        int max = array[0];
        for(int i = startOffset + 1; i < endOffset; i++){
            if(array[i] < min)
                min = array[i];
            else if(array[i] > max)
                max = array[i];
        }
        int[] counts = new int[max + 1 - min];
        for(int i = startOffset; i < endOffset; i++){
            int value = array[i];
            counts[value - min]++;
        }
        for(int i = 1; i < max + 1 - min; i++) {
            counts[i] += counts[i - 1];
        }
        for(int i = max - min; i > 0; i--){
            counts[i] = counts[i - 1];
        }
        counts[0] = 0;
        Integer[] buffer = Arrays.copyOfRange(array, startOffset, endOffset);
        for(int i = startOffset; i < endOffset; i++){
            int value = buffer[i - startOffset];
            int pos = counts[value - min];
            array[startOffset + pos] = value;
            counts[value - min]++;
        }
    }

    public static void countingSort(Integer[] array){
        countingSort(array, 0, array.length);
    }

}
