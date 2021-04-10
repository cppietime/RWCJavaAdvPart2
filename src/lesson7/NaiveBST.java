package lesson7;

public class NaiveBST<T extends Comparable<T>> extends BinarySearchTree<T, NaiveBST<T>> {
    public NaiveBST(T value) {
        super(value);
    }

    @Override
    public NaiveBST<T> treeFromValue(T value) {
        return new NaiveBST<>(value);
    }
}
