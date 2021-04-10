package lesson8;

import lesson7.BinarySearchTree;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T, AVLTree<T>> {

    protected int height;

    public AVLTree(T value) {
        super(value);
        height = 1;
    }

    public String toString(int tabs) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < tabs; i++)
            builder.append("  ");
        builder.append(value + "H(" + height + ")");
        if(left != null)
            builder.append("\n" + left.toString(tabs + 1));
        if(right != null)
            builder.append("\n" + right.toString(tabs + 1));
        return builder.toString();
    }

    @Override
    public boolean validate() {
        if(!super.validate())
            return false;
        height = Math.max(getHeight(left), getHeight(right)) + 1;
        int bf = getHeight(right) - getHeight(left);
        if(bf < -1 || bf > 1)
            return false;
        return true;
    }

    public AVLTree<T> treeFromValue(T value) {
        return new AVLTree<>(value);
    }

    private void updateHeight() {
        height = Math.max(getHeight(left), getHeight(right)) + 1;
    }

    private void rebalance() {
        int bf = getHeight(right) - getHeight(left);
        updateHeight();
        int oldHeight = height;
        if(bf < -1 || bf > 1) {
            AVLTree<T> higherChild = getChild(bf > 0);
            int subBf = getHeight(higherChild.right) - getHeight(higherChild.left);
            if(bf * subBf < 0) {
                higherChild.rotate(subBf < 0);
                higherChild.updateHeight();
                higherChild = getChild(bf > 0);
                higherChild.updateHeight();
            }
            rotate(bf < 0);
            updateHeight();
            parent.updateHeight();
        }
        if(parent != null)
            parent.rebalance();
    }

    @Override
    public AVLTree<T> insert(AVLTree<T> tree) {
        AVLTree<T> node = super.insert(tree);
        if(node != null) {
            if (node.parent != null)
                node.parent.rebalance();
        }
        return node;
    }

    @Override
    public AVLTree<T> remove(T t) {
        AVLTree<T> node = super.remove(t);
        if(node != null && node.parent != null)
            node.parent.rebalance();
        return node;
    }

    protected static int getHeight(AVLTree<?> tree) {
        if(tree == null)
            return 0;
        return tree.height;
    }
}
