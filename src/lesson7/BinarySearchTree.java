package lesson7;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class BinarySearchTree<T extends Comparable<T>, K extends BinarySearchTree<T, K>> implements Iterable<T> {

    public class BSTIterator implements Iterator<T> {
        K currentNode;
        int state;

        public BSTIterator() {
            state = 0;
            currentNode = (K) BinarySearchTree.this;
            while(currentNode.left != null)
                currentNode = currentNode.left;
        }

        public boolean hasNext() {
            return currentNode != null;
        }

        public T next() {
            if(!hasNext())
                throw new NoSuchElementException("End of tree");
            T next = currentNode.value;
            if(currentNode.right != null) {
                currentNode = currentNode.right;
                while(currentNode.left != null)
                    currentNode = currentNode.left;
            }
            else {
                while(currentNode != null) {
                    K nextNode = currentNode.parent;
                    boolean leftChild = nextNode != null && nextNode.left == currentNode;
                    currentNode = nextNode;
                    if(leftChild)
                        break;
                }
            }
            return next;
        }
    }

    public T value;
    public K left, right, parent;

    public BinarySearchTree(T value) {
        this.value = value;
        left = right = parent = null;
    }

    public String toString(int tabs) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < tabs; i++)
            builder.append("  ");
        builder.append(value);
        if(left != null)
            builder.append("\n" + left.toString(tabs + 1));
        if(right != null)
            builder.append("\n" + right.toString(tabs + 1));
        return builder.toString();
    }

    public String toString() {
        return toString(0);
    }

    public void setLeftChild(K child) {
        this.left = child;
        if(child != null)
            child.parent = (K) this;
    }

    public void setRightChild(K child) {
        this.right = child;
        if(child != null)
            child.parent = (K) this;
    }

    public void replaceAsChild(K sibling) {
        if(parent == null) {
            sibling.parent = null;
        }
        else {
            if (parent.left == this)
                parent.setLeftChild(sibling);
            else if(parent.right == this)
                parent.setRightChild(sibling);
            else
                throw new RuntimeException("Wtf even");
        }
    }

    public K getRoot() {
        K root = (K) this;
        while(root.parent != null)
            root = root.parent;
        return root;
    }

    public boolean validate() {
        if(left != null) {
            if(left.value.compareTo(value) > 0)
                return false;
            if(!left.validate())
                return false;
        }
        if(right != null) {
            if(right.value.compareTo(value) < 0)
                return false;
            if(!right.validate())
                return false;
        }
        return true;
    }

    public K getParentNode(T t) {
        int comparison = value.compareTo(t);
        if(comparison == 0)
            return (K) this;
        else if(comparison > 0) {
            if(left == null)
                return (K) this;
            return left.getParentNode(t);
        }
        else {
            if(right == null)
                return (K) this;
            return right.getParentNode(t);
        }
    }

    public K getSuccessor() {
        if(right == null)
            return (K) this;
        K node = right;
        while(node.left != null)
            node = node.left;
        return node;
    }

    public K getPredecessor() {
        if(left == null)
            return (K) this;
        K node = left;
        while(node.right != null)
            node = node.right;
        return node;
    }

    public K insert(T t) {
        K newNode = treeFromValue(t);
        return insert(newNode);
    }

    public K insert(K tree) {
        K node = getParentNode(tree.value);
        int comparison = node.value.compareTo(tree.value);
        if(comparison < 0) {
            node.setRightChild(tree);
        }
        else if(comparison > 0) {
            node.setLeftChild(tree);
        }
        else {
            return null;
        }
        return tree;
    }

    public K get(T t) {
        K node = getParentNode(t);
        if(node.value.compareTo(t) == 0)
            return node;
        return null;
    }

    public boolean contains(T t) {
        return get(t) != null;
    }

    public K remove(T t) {
        K node = get(t);
        if(node == null)
            return null;
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
            K successor = node.getSuccessor();
            node.value = successor.value;
            successor.replaceAsChild(successor.right);
            return successor;
        }
    }

    public void rotateLeft(){
        if(right == null)
            throw new IllegalStateException("Cannot left-rotate with no right child");
        K rightChild = right;
        K rightLeft = right.left;
        right = rightLeft;
        if(right != null)
            right.parent = (K) this;
        rightChild.left = (K) this;
        replaceAsChild(rightChild);
        parent = rightChild;
    }

    public void rotateRight(){
        if(left == null)
            throw new IllegalStateException("Cannot right-rotate with no left child");
        K leftChild = left;
        K leftRight = left.right;
        left = leftRight;
        if(left != null)
            left.parent = (K) this;
        leftChild.right = (K) this;
        replaceAsChild(leftChild);
        parent = leftChild;
    }

    public Iterator<T> iterator() {
        return new BSTIterator();
    }

    protected boolean isRightChild() {
        return (parent != null && parent.right == this);
    }

    protected K getChild(boolean rightChild) {
        return rightChild ? right : left;
    }

    protected K getSibling() {
        if(parent == null)
            return null;
        if(parent.left != this && parent.right != this) {
            if(parent.left == null)
                return parent.right;
            return parent.left;
        }
        return parent.getChild(!isRightChild());
    }

    protected void rotate(boolean rightRotate) {
        if(rightRotate)
            rotateRight();
        else
            rotateLeft();
    }

    protected void rotateUp() {
        if(parent != null)
            parent.rotate(!isRightChild());
    }

    protected boolean isOuterGrandchild() {
        if(parent == null || parent.parent == null)
            return false;
        return parent.isRightChild() == isRightChild();
    }

    public abstract K treeFromValue(T value);

    public static <T extends Comparable<T>, K extends BinarySearchTree<T, K>> K insert(
            K tree,
            K value
    ) {
        if(tree == null)
            return value;
        BinarySearchTree<T, K> result = tree.insert(value);
        if(result == null)
            return tree.getRoot();
        return result.getRoot();
    }

    public static <T extends Comparable<T>, K extends BinarySearchTree<T, K>> K remove(
            K tree,
            T value
    ) {
        BinarySearchTree<T, K> result = tree.remove(value);
        if(result == null)
            return null;
        return result.getRoot();
    }

}
