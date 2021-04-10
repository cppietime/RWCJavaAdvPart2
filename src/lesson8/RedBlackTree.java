package lesson8;

import lesson7.BinarySearchTree;

public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T, RedBlackTree<T>> {

    protected boolean isRed;

    private int blackHeight;

    public RedBlackTree(T value){
        super(value);
        isRed = true;
    }

    public String toString(int tabs) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < tabs; i++)
            builder.append("  ");
        builder.append(value + (isRed ? "R" : "B"));
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
        if(parent == null && isRed)
            return false;
        if((isRed(left) || isRed(right)) && isRed)
            return false;
        int lbh = left != null ? left.blackHeight : 1;
        int rbh = right != null ? right.blackHeight : 1;
        if(lbh != rbh)
            return false;
        blackHeight = lbh + (isRed ? 0 : 1);
        return true;
    }

    private static boolean isRed(RedBlackTree<?> tree) {
        return tree != null && tree.isRed;
    }

    @Override
    public RedBlackTree<T> treeFromValue(T value) {
        return new RedBlackTree<>(value);
    }

    @Override
    public RedBlackTree<T> insert(RedBlackTree<T> tree) {
        RedBlackTree<T> node = super.insert(tree);
        if(node == null)
            return null;
        RedBlackTree<T> returnNode = node;
        while(true) {
            if (node.parent == null) {
                node.isRed = false;
                return returnNode;
            }
            if (!isRed(node.parent))
                return returnNode;
            RedBlackTree<T> uncle = node.parent.getSibling();
            if (isRed(uncle)) {
                uncle.isRed = false;
                node.parent.isRed = false;
                node.parent.parent.isRed = true;
                node = node.parent.parent;
            } else {
                break;
            }
        }
        if (!node.isOuterGrandchild()) {
            RedBlackTree<T> next = node.parent;
            node.rotateUp();
            node = next;
        }
        node.parent.rotateUp();
        node.parent.isRed = false;
        if(node.getSibling() != null)
            node.getSibling().isRed = true;
        return returnNode;
    }

    @Override
    public RedBlackTree<T> remove(T t) {
        RedBlackTree<T> node = super.remove(t);
        if(node == null)
            return null;
        if(node.left != null && node.right == null) {
            node.left.isRed = node.isRed;
        }
        else if(node.left == null && node.right != null) {
            node.right.isRed = node.isRed;
        }
        RedBlackTree<T> removed = node;
        RedBlackTree<T> sibling, inner, outer;
        while(true) {
            if(node.parent == null) {
                node.isRed = false;
                return removed;
            }
            sibling = node.getSibling();
            inner = outer = null;
            if(sibling != null) {
                inner = sibling.getChild(!sibling.isRightChild());
                outer = sibling.getChild(sibling.isRightChild());
            }
            if(!isRed(node.parent) &&
                !isRed(sibling) &&
                !isRed(inner) &&
                !isRed(outer)) {
                if(sibling != null)
                    sibling.isRed = true;
                node = node.parent;
            }
            else
                break;
        }
        if(node.parent == null) {
            node.isRed = false;
            return removed;
        }
        if(isRed(sibling) && !isRed(inner) && !isRed(outer)) {
            sibling.isRed = false;
            node.parent.isRed = true;
            sibling.rotateUp();
            sibling = node.getSibling();
            inner = outer = null;
            if(sibling != null) {
                inner = sibling.getChild(!sibling.isRightChild());
                outer = sibling.getChild(sibling.isRightChild());
            }
        }
        if(isRed(node.parent) && !isRed(inner) && !isRed(outer)) {
            node.parent.isRed = false;
            if(sibling != null)
                sibling.isRed = true;
            return removed;
        }
        if(!isRed(outer) && isRed(inner)) {
            inner.rotateUp();
            inner.isRed = false;
            sibling.isRed = false;
            outer = sibling;
            sibling = inner;
        }
        if(sibling != null && isRed(outer)) {
            sibling.rotateUp();
            sibling.isRed = node.parent.isRed;
            node.parent.isRed = false;
            outer.isRed = false;
        }
        return removed;
    }
}
