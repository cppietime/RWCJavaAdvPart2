package lesson8;

import lesson7.BinarySearchTree;

import java.util.Random;

public class Treap<T extends Comparable<T>> extends BinarySearchTree<T, Treap<T>> {

    private static final Random random = new Random();

    protected int priority;

    public Treap(T value) {
        super(value);
        priority = random.nextInt();
    }

    @Override
    public boolean validate() {
        if(!super.validate())
            return false;
        if(left != null && left.priority > priority)
            return false;
        if(right != null && right.priority > priority)
            return false;
        return true;
    }

    public Treap<T> treeFromValue(T value) {
        return new Treap<T>(value);
    }

    public Treap<T> insert(Treap<T> tree) {
        Treap<T> newNode = super.insert(tree);
        if(newNode != null) {
            while (newNode.parent != null && newNode.priority > newNode.parent.priority) {
                newNode.rotateUp();
            }
        }
        return newNode;
    }
}
