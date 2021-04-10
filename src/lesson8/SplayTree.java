package lesson8;

import lesson7.BinarySearchTree;

public class SplayTree<T extends Comparable<T>> extends BinarySearchTree<T, SplayTree<T>> {

    public SplayTree(T value){
        super(value);
    }

    @Override
    public SplayTree<T> treeFromValue(T value) {
        return new SplayTree<>(value);
    }

    private void splay() {
        while(parent != null) {
            if(parent.parent == null) {
                rotateUp();
                return;
            }
            if(isOuterGrandchild()) {
                parent.rotateUp();
                rotateUp();
            }
            else {
                rotateUp();
                rotateUp();
            }
        }
    }

    @Override
    public SplayTree<T> get(T t) {
        SplayTree<T> result = super.get(t);
        result.splay();
        return result;
    }

    @Override
    public SplayTree<T> insert(SplayTree<T> tree) {
        SplayTree<T> node = super.insert(tree);
        if(node != null)
            node.splay();
        return node;
    }

    @Override
    public SplayTree<T> remove(T t) {
        SplayTree<T> node = super.get(t);
        if(node == null)
            return null;
        if(node.parent != null)
            node.parent.splay();
        if(node.left == null) {
            if(node.right == null) {
                if(node.parent == null)
                    return null;
                node.replaceAsChild(null);
                return node;
            }
            node.replaceAsChild(node.right);
            return node;
        }
        else {
            if(node.right == null) {
                node.replaceAsChild(node.left);
                return node;
            }
            SplayTree<T> successor = node.getSuccessor();
            node.value = successor.value;
            successor.replaceAsChild(successor.right);
            return successor;
        }
    }
}
