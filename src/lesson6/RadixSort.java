package lesson6;

/**
 * [46, 24, 48, 25, 19, 19, 10, 15]
 *
 * [1, 0, 0, 0, 1, 2, 1, 0, 1, 2]
 * [10, 24, 25, 15, 46, 48, 19, 19]
 * [0, 4, 2, 0, 2, 0, 0, 0, 0, 0]
 * [10, 15, 19, 19, 24, 25, 46, 48]
 *
 * b = radix
 * Running time O(n*log(b))
 * Space O(n + b)
 */
public class RadixSort {

    public static void radixSort(Integer[] array, int startOffset, int endOffset){
        Integer[] swap = new Integer[endOffset - startOffset];
        int[] counts = new int[4];
        for(int b = 0; b < 16; b++){
            for(int i = 0; i < 4; i++)
                counts[i] = 0;
            for(int i = startOffset; i < endOffset; i++) {
                int bits = (array[i] >> (b * 2)) & 3;
                counts[bits] ++;
            }
            for(int i = 1; i < 4; i++){
                counts[i] += counts[i - 1];
            }
            for(int i = 3; i > 0; i--){
                counts[i] = counts[i - 1];
            }
            counts[0] = 0;
            for(int i = 0; i < endOffset - startOffset; i++){
                int bits = (array[i + startOffset] >> (b * 2)) & 3;
                int pos = counts[bits];
                swap[pos] = array[i + startOffset];
                counts[bits]++;
            }
            for(int i = 0; i < endOffset - startOffset; i++){
                array[i + startOffset] = swap[i];
            }
        }
    }

    public static void radixSort(Integer[] array){
        radixSort(array, 0, array.length);
    }

}
