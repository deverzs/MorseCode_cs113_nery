import edu.miracosta.cs113.MorseCodeTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Driver class that translates Morse code to English characters
 *
 * @author  Zsuzsanna Dianovics
 * November 9, 2018
 *
 * 1. Print Menu
 *      (1) Table of codes and their English chars
 *          - use MorseCodeTree to create the table
 *      (2) Translate file with morse code
 *          - open file and parse Strings
 *          - use MorseCodeTree to translate
 *      (3) Translate morse code from console
 *          - use MorseCodeTree to translate
 *      (4) Exit
 * 2. In a loop, cycle through options until user exits (#4)
 *
 */
public class MorseCodeDriver {

    public static void main (String args[]) {

        boolean done = false ;
        int toDo = 0 ;
        Scanner kb = new Scanner(System.in) ;
        Scanner kb2 = new Scanner(System.in) ;
        Scanner kb3 = new Scanner(System.in) ;
        String morse ;
        String fileName ;

        // loop with menu
        while (!done) {
            printMenu();
            toDo = kb.nextInt() ;
            switch (toDo) {
                case 1 : // output a table
                    createTable() ;
                    break ;
                case 2 : // decode file
                    System.out.println("Please enter file name... ");
                    fileName = kb2.nextLine() ;
                    decodeFile(fileName) ;
                    break ;
                case 3 : // decode the console
                    System.out.println("Please enter the morse codes, each separated by a space... ");
                    morse = kb3.nextLine() ;
                    decodeConsole(morse) ;
                    break ;
                case 4 : // exit
                    System.out.println("Thanks! Bye!");
                    done = true ;
                    break ;
                default: // wrong choice
                    System.out.println(" That is not an option.");
            } // end switch

        } // loop until user exits


    } // end main


    /**
     * Takes input from user through console. The input is in binary code.
     * PRECONDITION: code is in morse, only - and *
     *               code is separated by a space
     * POSTCONDITION: String of english-translated characters
     * @param input String of input characters in morse
     */
    public static void decodeConsole(String input) {
        System.out.println("... decoding... \n");
        MorseCodeTree mct = new MorseCodeTree() ;
        String output ;
        String[] array = input.split(" ") ;
        for (String s : array) {
            output = mct.translateFromMorseCode(s) ;
            System.out.print(output);
        }
        System.out.println("\n");
    }

    /**
     * Method to accept a file that has morse code delimited by space.
     * PRECONDITION file of morse code to translate, separated by a space
     * PPOSTCONDITION: Output to the console the translated String of english characters
     * @throws  FileNotFoundException
     * @param file valid file name
     */
    public static void decodeFile(String file) {
        MorseCodeTree mct = new MorseCodeTree() ;
        String newCode ;
        String decoded ;
        Scanner inputFile ;
        try {
            inputFile = new Scanner(new FileInputStream(file)) ;
            System.out.println(".... decoding .... \n");
            while (inputFile.hasNext()) {
               newCode = inputFile.next() ;
               decoded = mct.translateFromMorseCode(newCode) ;
               System.out.print(decoded);

            }
            System.out.println("\n");

            inputFile.close();
        }
        catch (FileNotFoundException fnf) {
            System.out.println("File was not found.");

        }

    }

    /**
     * Prints the menu of options to the console. Choices are (1) see all morse codes (2) decode from file
     * (3) decode from console (4) exit
     */
    public static void printMenu() {
        System.out.println("\n----------------------------------------------");
        System.out.println("PLEASE CHOOSE AN OPTION. ENTER THE DIGIT.\n");
        System.out.println("[1] See all morse codes with their letters.");
        System.out.println("[2] Decode a morse code file. Will need file name.");
        System.out.println("[3] Manually enter morse code to decode.");
        System.out.println("[4] Exit.");
        System.out.println("----------------------------------------------\n");

    }

    /**
     * Creates a simple table of alphabetically sorted English letters and their equivalent morse code
     */
    public static void createTable() {
       char letter ;
        String code  ;
        MorseCodeTree mct = new MorseCodeTree() ;
        mct.readMorseCodeTree() ;

        System.out.println("----------------------");
        System.out.println("- Letter ---- Code  -");
        Arrays.sort(mct.table);
        for (String s : mct.table) {
            letter = s.charAt(0) ;
            code = s.substring(2) ;
                System.out.println("   " + letter + "         " + code) ;

        }
        System.out.println("----------------------");
    }
} // end class
