/*=============================================================================
 |   Assignment:  Program #4: Concordance Construction with 2–3 Trees
 |       Author:  josephc4
 |       Grader:  Unknown
 |
 |       Course:  CSC 345
 |   Instructor:  L. McCann
 |     Due Date:  11/25/25 3:30 pm
 |
 |  Description:  This class parses through a given text and counts the amount
 |                of occurencces of a word in the text. Every word is put into
 |                lowercase before being added to the two-three tree. If duplicate
 |                words are found in the text, the location is added to the existing 
 |                Node of the word. 
 |
 |                The Node of the Two-Three tree contains the word, and an Array List 
 |                containing all of its locations found in the text. The Array List
 |                contains a class called Location which contains public constants
 |                of the paragraph and line number (or location of the word). This allows
 |                for easier access and updating of the location of a word.
 |
 |                After all occurences words have been added into the two-three tree,
 |                the program performs an in-order traversal of the nodes to print out the relevant
 |                information from each node. The maximum number of locations printed on a single line is 
 |                eight, so if a word exceeds that amount the rest are printed on the preceding lines (in
 |                groups of eight).
 |
 |                The Two-Three tree has no need for performing deletions, so the Two-Three tree 
 |                only supports insertions and searching (searching is implied since inserting requires searching).
 |                
 |                The twoThreeTree utilizes a splitNode class within itself to help resolve threeNode promotions. This
 |                is a fundamental usage for this class to work. Recursively calls until the insertion is resolved at the
 |                root or a two node. Two node insertions or existing instances require no recursion, as they are easily added
 |                to the tree.
 |
 |     Language:  Java 16
 | Ex. Packages:  None
 |                
 | Deficiencies:  None
 *===========================================================================*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Prog4 {
    public static void main(String[] args) 
    {
        //Name of file
        String textName = args[0]; 
        TwoThreeTree tree = new TwoThreeTree();                   

        try 
        {
            File text = new File(textName);                 //File that contains the story
            Scanner textScanner = new Scanner(text);        //Parses through the file

            if (text.length() == 0) 
            {
                System.out.printf("File %s is empty.\n", textName);
                System.exit(0);
            }
            
            /*
             * Parses through the file and 
             * adds valid words to count
             * and adds the correspoding
             * paragraph and line.
             */

            int paragraphNum = 1;   //Keeps track of paragraph
            int lineNum = 1;        //Keeps track of line in regards to paragraph
            
            while(textScanner.hasNext()) 
            {
                String Line = textScanner.nextLine();
                String word = new String();
                Scanner lineScanner = new Scanner(Line);
                
                if(Line.isEmpty()) {
                    paragraphNum++;
                    lineNum = 1;
                }
                else 
                {
                    
                    /*
                    * Parse through single lines in a paragraph
                    * Insert words into the two three tree
                    */

                    while(lineScanner.hasNext())
                    {
                        word = lineScanner.next();

                        word = word.replaceAll("[^a-zA-Z0-9\\-']", "");
                        word = word.toLowerCase();
                        
                        if( !(word.isBlank() || word.isEmpty()) ) 
                        {
                            tree.insert(word, paragraphNum, lineNum);
                        }
                    }

                    lineScanner.close();
                    lineNum++;
                }

            }
            
            /*
            * Printing formatting for the end of program, prints out the top and each word's occurence.
            */
            System.out.printf("%-25s | %-25s ", "Word", "Occurences (pargraph, line) ");
            System.out.println();
            System.out.print("---------------------------------------------------------------------------------------------\n");
            textScanner.close();
            tree.printInOrderTraversal();
        }
        catch (FileNotFoundException e)
        {   
            System.out.printf("File %s not found.\n", args[0]);
            System.exit(0);
        }
        
        
    }
}
