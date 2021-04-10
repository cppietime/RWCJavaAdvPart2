package lesson6;

public class Util {

    public static <T> void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static <T extends Comparable<T>> int binarySearch(T[] array, int start, int end, T value) {
        if(end <= start)
            return start;
        int mid = (start + end - 1) / 2;
        T compare = array[mid];
        if(compare.compareTo(value) >= 0) {
            return binarySearch(array, start, mid, value);
        }
        else{
            return binarySearch(array, start + 1, end, value);
        }
    }

    public static <T> void cycleRight1(T[] array, int start, int end) {
        T carry = array[end - 1];
        for(int i = end - 1; i > start; i--) {
            array[i] = array[i - 1];
        }
        array[start] = carry;
    }

}
