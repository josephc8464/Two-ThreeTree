/*+----------------------------------------------------------------------
 ||
 ||  Class Node
 ||
 ||         Author:  Joseph Corella
 ||
 ||        Purpose:  This class represents the data in a node in the two-three tree.
 ||                  It contains the word found, and locations of all the word occurences.
 ||
 ||  Inherits From:  None
 ||
 ||     Interfaces:  None
 ||
 |+-----------------------------------------------------------------------
 ||
 ||      Constants:  None
 ||
 |+-----------------------------------------------------------------------
 ||
 ||   Constructors:  Node() //default constructor
 ||                  Node(String word) -- assigns the word to given input
 ||
 ||  Class Methods:  none
 ||
 ||  Inst. Methods:  int compareTo(Node otherNode)  --compares a word to another word for two-three tree building
 ||                  int getOccurenceSize() -- returns the amount of occurences of the given word
 ||                  String getWord() -- returns the word stored
 ||                  void addLocation(int paragraphNum, int lineNum) -- adds the locations of a new occurence
 ||                  void setWord(String word) -- adds a word found in the text
 ||
 ++-----------------------------------------------------------------------*/

import java.util.ArrayList;

public class Node implements Comparable<Node> {

    //Word that is contained in the text
    private String word;

    //Location of word in text, includes paragraph and line
    private ArrayList<Location> locations = new ArrayList<>();
    
    public Node() 
    {
        this.word = null;
    }

    public Node(String word) 
    {
        this.word = word;
    }

    /*---------------------------------------------------------------------
    |  Method compareTo
    |
    |  Purpose:  Compares one node to another and returns the comparison result.
    |
    |  Pre-condition:  Node must have a string
    |
    |  Post-condition: Comparison is made
    |
    |  Parameters:
    |      otherNode -- Node we are comparing to. Must have a string for comparison.
    |
    |  Returns:  integer (depending on the comparison)
    *-------------------------------------------------------------------*/
    @Override
    public int compareTo (Node otherNode) 
    {
        if (this.word == null && otherNode.word == null) return 0;
        if (this.word == null) return -1;
        if (otherNode.word == null) return 1;
        return this.word.compareTo(otherNode.getWord());
    }

    /*---------------------------------------------------------------------
    |  Method getOccurenceSize
    |
    |  Purpose:  Returns the amount of occurences of the word.
    |
    |  Pre-condition:  Node should have a word stored and at least one location.
    |
    |  Post-condition: Size of locations is returned.
    |
    |  Parameters: None
    |
    |  Returns:  integer (number of occurences)
    *-------------------------------------------------------------------*/
    public int getOccurenceSize() 
    {
        return this.locations.size();
    }

    /*---------------------------------------------------------------------
    |  Method getWord
    |
    |  Purpose:  Returns the word stored
    |
    |  Pre-condition:  Node should have a word stored, or be initialized to null
    |
    |  Post-condition: Word is returned
    |
    |  Parameters: None
    |
    |  Returns:  string (word)
    *-------------------------------------------------------------------*/
    public String getWord() 
    {
        return this.word;
    }

    public int getLineAt(int i) 
    {
        return locations.get(i).line;
    }

    public int getParagraphAt(int i) 
    {
        return locations.get(i).paragraph;
    }
    
    /*---------------------------------------------------------------------
    |  Method addLocation
    |
    |  Purpose:  Adds the location of a word occurence found.
    |
    |  Pre-condition:  Location added should correlate to the word occurence (same words)
    |
    |  Post-condition: New occurence location is added
    |
    |  Parameters: int paragraphNum -- the paragraph in which the word was found.
    |              int lineNum -- the line in which the word was found. (relative to the paragraph)
    |
    |  Returns:  Nothing
    *-------------------------------------------------------------------*/
    public void addLocation(int paragraphNum, int lineNum) 
    {   
        this.locations.add(new Location(paragraphNum, lineNum));
    }

    /*---------------------------------------------------------------------
    |  Method setWord
    |
    |  Purpose:  Sets the word of a new occurence.
    |
    |  Pre-condition:  None really, this should be used when there is a new word found.
    |
    |  Post-condition: Word is set.
    |
    |  Parameters: String word -- new word found in text
    |
    |  Returns:  Nothing
    *-------------------------------------------------------------------*/
    public void setWord(String word) 
    {
        this.word = word;
    }
/*+----------------------------------------------------------------------
 ||
 ||  Class Location
 ||
 ||         Author:  Joseph Corella
 ||
 ||        Purpose:  Creates a data structure that stores the location in one
 ||                  data type. Since location contains "paragraph" and "line", 
 ||                  creating one data structure that contains two constants to
 ||                  store these two allows for easier additions of new locations.
 ||                  While also being able to easily locate both aspects of the location.
 ||
 ||  Inherits From:  None
 ||
 ||     Interfaces:  None
 ||
 |+-----------------------------------------------------------------------
 ||
 ||      Constants:  int paragraph -- stores the paragraph the word was found in
 ||                  int line -- stores the line the word was found in relative to the paragraph
 ||
 |+-----------------------------------------------------------------------
 ||
 ||   Constructors:  Location(int pNum, int lNum) -- declares a new location
 ||
 ||  Class Methods:  none
 ||
 ||  Inst. Methods:  none
 ||
 ++-----------------------------------------------------------------------*/
     
    public static class Location {
        public final int paragraph;
        public final int line;

        public Location(int pNum, int lNum) {
            this.paragraph = pNum;
            this.line = lNum;
        }
    }
}
