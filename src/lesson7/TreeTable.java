package lesson7;

import lesson8.AVLTree;
import lesson8.RedBlackTree;
import lesson8.SplayTree;
import lesson8.Treap;

import java.util.function.Function;

public class TreeTable<T extends Comparable<T>, V, K extends BinarySearchTree<TreeTable.Entry<T, V>, K>> {

    public static class Entry<T extends Comparable<T>, V> implements Comparable<Entry<T, V>> {
        T key;
        V value;

        public Entry(T key, V value) {
            this.key = key;
            this.value = value;
        }

        public int compareTo(Entry o) {
            return key.compareTo((T) o.key);
        }

        public String toString() {
            return key + ": " + value;
        }
    }

    public K tree;
    private Function<Entry<T, V>, K> treeFactory;

    public TreeTable(Function<Entry<T, V>, K> treeFactory) {
        this.tree = null;
        this.treeFactory = treeFactory;
    }

    public V get(T key) {
        if(tree == null)
            return null;
        K node = (K) tree.get(new Entry(key, null));
        if(node == null)
            return null;
        return node.value.value;
    }

    public void put(T key, V value) {
        tree = BinarySearchTree.insert(tree, (K) treeFactory.apply(new Entry(key, value)));
    }

    public static class NaiveTreeTable<T extends Comparable<T>, V> extends TreeTable<T, V, NaiveBST<Entry<T, V>>> {
        public NaiveTreeTable() {
            super(NaiveBST::new);
        }
    }

    public static class TreapTable<T extends Comparable<T>, V> extends TreeTable<T, V, Treap<Entry<T, V>>> {
        public TreapTable() {
            super(Treap::new);
        }
    }

    public static class AVLTreeTable<T extends Comparable<T>, V> extends TreeTable<T, V, AVLTree<Entry<T, V>>> {
        public AVLTreeTable() {
            super(AVLTree::new);
        }
    }

    public static class SplayTreeTable<T extends Comparable<T>, V> extends TreeTable<T, V, SplayTree<Entry<T, V>>> {
        public SplayTreeTable() {
            super(SplayTree::new);
        }
    }

    public static class RedBlackTreeTable<T extends Comparable<T>, V> extends TreeTable<T, V, RedBlackTree<Entry<T, V>>> {
        public RedBlackTreeTable() {
            super(RedBlackTree::new);
        }
    }

}
