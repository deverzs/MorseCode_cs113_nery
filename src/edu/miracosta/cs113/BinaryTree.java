package edu.miracosta.cs113;

import java.io.Serializable;
import java.util.Scanner;

/**
 * BinaryTree is a class that creates a binary tree using the stored root Node
 * @param <E> Generic types for the BinaryTree
 */
public class BinaryTree<E> implements Serializable {

    protected  Node<E> root ;

    ///////  NODE  /////////////////////////////////////////////
    /**
     * Inner protected class of Nodes that are used by the BinaryTree Class
     * @param <E> Generic parameter for data in the Nodes
     */
    protected static class Node<E> implements Serializable {

        protected E data ;
        protected Node<E> left ;
        protected Node<E> right ;

        /**
         * Constructor that accepts a data of any type
         * @param data
         */
        public Node(E data) {
            this.data = data ;
            this.left = null ;
            this.right = null ;
        }

        /**
         * Default constructor. Sets all to null
         */
        public Node() {
            this.data = null ;
            this.left = null ;
            this.right = null ;
        }

        /**
         * To String the data inside the Node
         * @return String of data that represents the data in the Node
         */
        public  String toString() {
            return data.toString() ;
        }

        /**
         * Returns the left reference of the node
         * @return returns left
         */
        public Node<E> getLeft() {
            return  left ;
        }

        /**
         * Returns the right reference of the node
         * @return right
         */
        public Node<E> getRight() {
            return right ;
        }

        /**
         * Returns the data of the node
         * @return data
         */
        public E getData() {
            return data ;
        }

        /**
         * Sets the right Node
         * @param node for the right
         */
        public void setRight(Node<E> node) {
            this.right = node ;
        }

        /**
         * Sets the data of the Node
         * @param data Any type of data
         */
        public void setData(E data) {
            this.data = data ;
        }

        /**
         * Sets the left Node
         * @param node left
         */
        public void setLeft(Node<E> node) {
            this.left = node ;
        }
    } // end Node ///////////////////////////////


    /**
     * Default Constructor. Sets root to null.
     */
    public  BinaryTree() {
        this.root = null ;
    }

    /**
     * Copy Constructor that accepts a root as the copy
     * @param root to copy
     */
    protected BinaryTree(Node<E> root) {
        this.root = root ;
    }

    /**
     * Creates a BinaryTree from a piece of data and two other Binary Trees as its branches
     * @param data generic data
     * @param leftTree BinaryTree for the left
     * @param rightTree BinaryTree for the right
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        root = new Node<E>(data) ;

        if (leftTree != null) {
            root.left = leftTree.root ;
        }
        else {
            root.left = null ;
        }
        if (rightTree != null) {
            root.right = rightTree.root ;
        }
        else {
            root.right = null ;
        }
    }

    /**
     * Gets the left subtree of this Binary Tree
     * @return left subtree
     */
    public  BinaryTree<E> getLeftSubtree() {

        if (root !=null && root.left != null) {
            return new BinaryTree<E>(root.left) ;
        } else  {
            return null ;
        }

    }
    /**
     * Gets the right subtree of this Binary Tree
     * @return right subtree
     */
    public  BinaryTree<E> getRightSubtree() {
        if (root !=null && root.right != null) {
            return new BinaryTree<E>(root.right) ;
        } else  {
            return null ;
        }
    }

    /**
     * Gets the data in the tree
     * @return Generic data
     */
    public E getData() {

        return root.data ;
    }

    /**
     * Checks if the tree is barren
     * @return true if tree has no children, false if 1 or 2 children
     */
    public boolean isLeaf() {
        if (root.right == null && root.left == null) {
            return true ;
        }
        else {
            return false ;
        }
       // return root == null || (root.left == null && root.right == null) ;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder() ;
        preOrderTraverse(root, 1, sb);
        return sb.toString() ;
    }

    /**
     * Traverses the Binary Tree in pre-order using recursion
     * @param node the local root
     * @param depth height of tree
     * @param sb StringBuilder to save the output
     */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
        for (int i = 1; i < depth ; i++) {
            sb.append(" ") ;
        }
        if (node == null) {
            sb.append("null\n") ;
        }
        else {
            sb.append(node.toString()) ;
            sb.append("\n") ;
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    /**
     * Input is in pre-order traversal,
     * @param scan Scanner attached to the input file
     * @return the binary tree
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan) {
        String data = scan.next() ;
        if (data.equals("null")) {
            return null ;
        }
        else {
            BinaryTree<String> leftTree = readBinaryTree(scan) ;
            BinaryTree<String> rightTree = readBinaryTree(scan) ;
            return  new BinaryTree<String>(data, leftTree, rightTree) ;
        }
    }

    @Override
    public boolean equals(Object anObject) {
        if (!(anObject instanceof BinaryTree)) {
            return false ;
        }
        BinaryTree anotherTerm = (BinaryTree) anObject ;
        return ( (getRightSubtree() == (anotherTerm.getLeftSubtree()) )&&
                (getRightSubtree() == anotherTerm.getRightSubtree()) ) ;
    }

} // end BinaryTree
