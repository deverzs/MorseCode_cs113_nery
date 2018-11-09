package edu.miracosta.cs113;


import javax.swing.text.Position;
import java.util.concurrent.ExecutionException;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {

    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.





    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) {
        String[] array = morseCode.split(" ") ;
        StringBuilder answer = new StringBuilder();

        for (String s : array) {
        Node current = root;
        int loop = s.length();
        if (loop > 4) {
            throw new StringIndexOutOfBoundsException() ;
        }
        for (int i = 0; i < loop; i++) {

            if (s.charAt(0) == '*') {
                if (current.left != null) {
                    current = current.getLeft();
                } else {
                    System.exit(0);
                }
            } else if (s.charAt(0) == '-') {
                if (current.getRight() != null) {
                    current = current.getRight();
                } else {
                    System.exit(0);
                }
            } else {
                if (s.charAt(0) != '*' || s.charAt(0) != '-') {
                    throw new NumberFormatException() ;
                    //System.exit(0);
                }
            }
            if (s.length() == 1) {

                answer = answer.append(current.getData());
            }
            s = s.substring(1);

        }
        }
        System.out.println(answer.toString());
        return  answer.toString() ;
    }

        private void populateCodeManually() {

            addLetter('e', "*") ;
            addLetter('t', "-") ;

            addLetter('i', "**") ;
            addLetter('a', "*-") ;

            addLetter('n', "-*") ;
            addLetter('m', "--") ;

            addLetter('s', "***") ;
            addLetter('u', "**-") ;


            addLetter('r', "*-*") ;
            addLetter('w', "*--") ;

            addLetter('d', "-**") ;
            addLetter('k', "-*-") ;

            addLetter('g', "--*") ;
            addLetter('o', "---") ;

            addLetter('h', "****") ;
            addLetter('v', "***-") ;
            addLetter('f', "**-*") ;



            addLetter('l', "*_**") ;
            addLetter('p', "*--*") ;

            addLetter('j', "*---") ;
            addLetter('b', "-***") ;

            addLetter('x', "-**-") ;
            addLetter('c', "-*-*") ;

            addLetter('y', "-*--") ;
            addLetter('z', "--**") ;
            addLetter('q', "--*-") ;



        }



        //private Node<Character> root ;
        private static final char EMPTY = '-' ;

        public MorseCodeTree() {
            root = new Node<Character>(EMPTY) ;
            populateCodeManually();
        }

        public void addLetter(char letter, String code) {
            Node current = root ;
            int loop = code.length() ;
            for (int i = 0 ; i < loop ; i++) {

                if (code.charAt(0) == '*') {
                    if (current.left != null) {
                        current = current.getLeft() ;
                    }
                    else {
                        current.setLeft(new Node(null) ) ;
                        current = current.getLeft() ;
                    }
                }
                else {
                    if (current.getRight() != null) {
                        current = current.getRight() ;
                    }
                    else {
                        current.setRight(new Node(null)) ;
                        current = current.getRight() ;
                    }
                }
                code = code.substring(1) ;
            }
            System.out.println("Setting letter: " + letter) ;
            current.setData(letter) ;

        }

        public void inOrderPrint() {
            Node current = root;
            inOrderTraverse(current);
        }


        public void inOrderTraverse(Node node) {
            if (node != null) {
                inOrderTraverse(node.getLeft()) ;
                System.out.println(node.getData()) ;
                inOrderTraverse(node.getRight());
            }
        }











} // End of class MorseCodeTree