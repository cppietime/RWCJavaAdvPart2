package lesson6;

/**
 * Provide a static method to implement selection sort
 *
 * X, X, X, X, X
 *
 * 0, 1, 2, 3, 1, 10: We want to sort this via selection
 * First pass: swap 0 into position 0
 * 0, 1, 2, 3, 1, 10
 * Second pass: swap 1 into position 1
 * 0, 1, 2, 3, 1, 10
 * Third pass: swap 1 into position 2
 * 0, 1, 1, 3, 2, 10
 * Fourth pass: swap 2 into position 3
 * 0, 1, 1, 2, 3, 10
 * Fifth pass: swap 3 into position 4
 * 0, 1, 1, 2, 3, 10
 * 1 + 2 + 3 + ... n - 1
 * (n - 1) * n / 2 => O(n^2)
 *
 * Running time always O(n^2)
 * O(1) space
 */
public class SelectionSort {

    /**
     * Uses selection sort to sort section of array from startOffset to endOffset exclusive
     * @param array
     * @param startOffset
     * @param endOffset
     * @param <T>
     */
    public static <T extends Comparable<T>> void selectionSort(T[] array, int startOffset, int endOffset){
        for(int i = startOffset; i < endOffset - 1; i++) {
            int minPos = i;
            T minValue = array[i];
            for(int j = i + 1; j < endOffset; j++) {
                T lookAt = array[j];
                if(minValue.compareTo(lookAt) > 0) {
                    minPos = j;
                    minValue = lookAt;
                }
            }
            Util.swap(array, i, minPos);
        }
    }

    public static <T extends Comparable<T>> void selectionSort(T[] array) {
        selectionSort(array, 0, array.length);
    }

}
