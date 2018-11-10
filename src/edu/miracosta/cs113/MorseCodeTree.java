package edu.miracosta.cs113;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> implements Serializable {

    private static final char EMPTY = '-' ;
    private Scanner inputFile ;
    public String[] table ;

    /**
     * Default constructor for MorseCodeTree. Uses a file input to build the MorseCodeTree
     */
    public MorseCodeTree() {
        root = new Node(EMPTY) ;
        populateCodeManually();
    }

    /**
     * Constructor that uses an input text file to build the morse code tree.
     * @param file Valid input file of decryption
     */
    public MorseCodeTree(String file) {
        root = new Node(EMPTY) ;
        readTextFile(file) ;
    }

    /**
     * Copy Constructor
     * @param tree Another MorseCodeTree to copy
     */
    public MorseCodeTree(MorseCodeTree tree) {
        this.root = tree.root ;
        this.table = tree.table ;
    }

    /**
     * Sets the array of String, table, to an incoming array of Strings
     * @param table an array of Strings that hold the values and codes of each node
     */
    public void setTable(String[] table) {
        this.table = table ;
    }

    /**
     * Gets the String of array for table
     * @return table
     */
    public String[] getTable() {
        return table ;
    }

    /**
     * Adds a letter to the MorseCodeTree.
     * @param letter A char that represents English letter
     * @param code Morse Code of String to use to navigate to proper place in tree
     */
    public void addLetter(char letter, String code) {
        Node current = root ;
        int loop = code.length() ;
        for (int i = 0 ; i < loop ; i++) {

            if (code.charAt(0) == '*') {
                if (current.left != null) {
                    current = current.left ;
                } else {
                    current.setLeft(new Node(null) ) ;
                    current = current.left ;
                }
            } else {
                if (current.right != null) {
                    current = current.right ;
                } else {
                    current.setRight(new Node(null)) ;
                    current = current.right ;
                }
            }
            code = code.substring(1) ;
        }
        current.setData(letter) ;

    }

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
     * @throws StringIndexOutOfBoundsException
     * @throws NumberFormatException
     */
    public String translateFromMorseCode(String morseCode) {
        String[] array = morseCode.split(" ") ;
        StringBuilder answer = new StringBuilder();

        for (String s : array) {
            Node current = root;
            int loop = s.length();

            // check for length
            if (loop > 4) {
                throw new StringIndexOutOfBoundsException() ;
            }

            for (int i = 0; i < loop; i++) {

                if (s.charAt(0) == '*') { // left
                    if (current.left != null) {
                        current = current.left;
                    } else {
                        System.exit(0);
                    }
                } else if (s.charAt(0) == '-') { // right
                    if (current.right != null) {
                        current = current.right;
                    } else {
                        System.exit(0);
                    }
                } else {  // incorrect character
                    if (s.charAt(0) != '*' || s.charAt(0) != '-') {
                        System.out.println("This is not a character. Exiting!") ;
                        throw new NumberFormatException() ;
                    }
                }
                // the last char in the code
                if (s.length() == 1) {
                    answer = answer.append(current.getData());
                }
                s = s.substring(1); // removing first char
            }
        }
        return  answer.toString() ;
    }


    /**
     * Helper method that opens an input file of decryption and build
     * the MorseCodeTree. File is closed before end of method.
     * @param file user-input file name
     */
    private  void readTextFile(String file) {
        String newLine = "" ;
        char letter = ' ' ;
        String code = " ";

        try {
            inputFile = new Scanner(new FileInputStream(file)) ;
        }
        catch (FileNotFoundException fnf) {
            System.out.println("File was not found. Exiting: " + file);
            System.exit(0);
        }

        while (inputFile.hasNext()) {
            newLine = inputFile.nextLine() ;
            letter = newLine.charAt(0) ;
            code = newLine.substring(2).trim() ;
            addLetter(letter, code);
        }
        inputFile.close();
    }


    /**
     * Helper method to build the MorseCodeTree by reading in a text file.
     */
    private void populateCodeManually(){
        readTextFile("tree.txt");

    }
    /**
     * Reads the Morse Code tree that was built in the constructor. This is a non-recursive method, as requested.
     * Populates an array (table) that can be accessed to print a table of the values and codes.
     */
    public void readMorseCodeTree() {

        table = new String[26] ;
        int count = 0 ;
        Node current = new Node(null) ;
        current = root ;
        int stars = 0 ;

        // go left 1
        while (current != null) {
            switch (stars) {
                case 1:
                    table[count] =  current.getData().toString() + " " + "*" ;
                    break ;
                case 2:
                    table[count] = current.getData().toString() + " " + "**" ;
                    break ;
                case 3:
                    table[count] = current.getData().toString() + " " +  "***" ;
                    break ;
                case 4:
                    table[count] = current.getData().toString() + " " +  "****" ;
                    break ;
            }
            stars++ ;
            if (table[count] != null) {
                count++;
            }
            current = current.left ;
        }

        // go right 2
        int dashes = 1 ;
        current = root.right ;
        while (current != null) {
            switch (dashes) {
                case 1:
                    table[count] = current.getData().toString() + " " + "-";
                    break ;
                case 2:
                    table[count] = current.getData().toString() + " " + "--" ;
                    break ;
                case 3:
                    table[count] = current.getData().toString() + " " +  "---" ;
                    break ;
                case 4:
                    table[count] = current.getData().toString() + " " +  "----" ;
                    break ;
            }
            dashes++ ;
            if (table[count] != null) {
                count++;
            }
            current = current.right ;
        }

        // left, then right branch 3
        current = root.left ;
        dashes = 1 ;
        current = current.right ;
        while (current != null) {
            switch (dashes) {
                case 1:
                    table[count] = current.getData().toString() + " " + "*-";
                    break ;
                case 2:
                    table[count] = current.getData().toString() + " " + "*--" ;
                    break ;
                case 3:
                    table[count] = current.getData().toString() + " " +  "*---" ;
                    break ;
            }
            dashes++ ;
            if (table[count] != null) {
                count++;
            }
            current = current.right ;
        }


        // right, then left branch 4
        current = root.right ;
        stars = 1 ;
        current = current.left ;
        while (current != null) {
            switch (stars) {
                case 1:
                    table[count] = current.getData().toString() + " " + "-*";
                    break ;
                case 2:
                    table[count] = current.getData().toString() + " " + "-**" ;
                    break ;
                case 3:
                    table[count] = current.getData().toString() + " " +  "-***" ;
                    break ;
            }
            stars++ ;
            if (table[count] != null) {
                count++;
            }
            current = current.left ;
        }

        // right, right, then left 5
        current = root.right ;
        current = current.right ;
        current = current.left ;

        stars = 1 ;
        while (current != null) {
            switch (stars) {
                case 1:
                    table[count] = current.getData().toString() + " " + "--*";
                    break ;
                case 2:
                    table[count] = current.getData().toString() + " " + "--**" ;
                    break ;
            }
            stars++ ;
            if (table[count] != null) {
                count++;
            }
            current = current.left ;
        }

        // right, left, then right  6
        current = root.right ;
        current = current.left ;
        current = current.right ;
        dashes = 1 ;
        while (current != null) {
            switch (dashes) {
                case 1:
                    table[count] = current.getData().toString() + " " + "-*-";
                    break ;
                case 2:
                    table[count] = current.getData().toString() + " " + "-*--" ;
                    break ;
            }
            dashes++ ;
            if (table[count] != null) {
                count++;
            }
            current = current.right ;
        }

        // left, right, then left  7
        current = root.left ;
        current = current.right ;
        current = current.left ;
        stars = 1 ;
        while (current != null) {

            switch (stars) {
                case 1:
                    table[count] = current.getData().toString() + " " + "*-*";
                    break ;
                case 2:
                    table[count] = current.getData().toString() + " " + "*-**" ;
                    break ;
            }
            stars++ ;

            if (table[count] != null) {
                count++;
            }
            current = current.left ;


        }

        // left, left, then right

        // u
        current = root.left ;
        current = current.left ;
        current = current.right ;
        table[count] = current.getData().toString() + " " + "**-";
        current = current.left ;
        count++ ;

        // f
        table[count] = current.getData().toString() + " " + "**-*";
        current = root.left ;

        // v
        current = current.left ;
        current = current.left ;
        current= current.right ;
        count++ ;
        table[count] = current.getData().toString() + " " + "***-";

        // p
        current = root.left ;
        current = current.right ;
        current = current.right ;
        current= current.left ;
        count++ ;
        table[count] = current.getData().toString() + " " + "*--*";

        // x
        current = root.right ;
        current = current.left ;
        current = current.left ;
        current= current.right;
        count++ ;
        table[count] = current.getData().toString() + " " + "-**-";

        // c
        current = root.right ;
        current = current.left ;
        current = current.right ;
        current= current.left ;
        count++ ;
        table[count] = current.getData().toString() + " " + "-*-*";

        // q
        current = root.right ;
        current = current.right ;
        current = current.left ;
        current= current.right ;
        count++ ;
        table[count] = current.getData().toString() + " " + "--*-";



    }



} // End of class MorseCodeTree