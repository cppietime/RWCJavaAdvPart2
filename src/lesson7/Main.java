package lesson7;

/**
 * Hash functions
 *
 * .compareTo
 * .equals
 * .hashCode
 *
 * Hash table
 *
 * Binary Search Trees
 *
 *      6
 *     /
 *    1
 *     \
 *      3
 *
 *
 *        A
 *       / \
 *      B   C
 *     / \ / \
 *    D  E F G
 *
 *    B
 *   / \
 *  D   A
 *     / \
 *    E   C
 *       / \
 *      F  G
 *
 *
 *       C
 *      / \
 *     A   G
 *    / \
 *   B   F
 *  / \
 * D   E
 */
public class Main {

    public static void main(String[] args) {
        OpenHashTable<String, Integer> table = new OpenHashTable<>();
        table.put("String1", 1);
        table.put("String2", 2);
        table.put("str3", 30);
        table.put("String2", 20);
        table.printAll();
        System.out.println();
        table.remove("String1");
        table.remove("Strstr");
        table.printAll();
        System.out.println();
        table.put("String1", 100);
        table.put("Strstr", 0);
        table.printAll();
        System.out.println();

        OpenHashSet<String> set = new OpenHashSet<>();
        set.add("String");
        set.add("Message");
        set.add("Text");
        set.remove("Message");
        for(String str : set)
            System.out.println(str);
        System.out.println();

        NaiveBST<Integer> tree = BinarySearchTree.insert(null, new NaiveBST<>(0));
        tree = BinarySearchTree.insert(tree, new NaiveBST<>(1));
        tree = BinarySearchTree.insert(tree, new NaiveBST<>(3));
        tree = BinarySearchTree.insert(tree, new NaiveBST<>(5));
        tree = BinarySearchTree.insert(tree, new NaiveBST<>(6));
        for(int i = 0; i < 8; i++) {
            System.out.println(i + ": " + tree.contains(i));
        }
        System.out.println(tree);
        tree.right.right.rotateUp();
        tree.right.rotateUp();
        tree.rotateLeft();
        tree = tree.getRoot();
        System.out.println(tree);
        for(int i : tree) {
            System.out.println("TreeIterator: " + i);
        }

        TreeTable.NaiveTreeTable<Integer, String> treeTable = new TreeTable.NaiveTreeTable<>();
        treeTable.put(1, "One");
        treeTable.put(2, "two");
        System.out.println(treeTable.get(1));
        System.out.println(treeTable.get(2));
    }

}
