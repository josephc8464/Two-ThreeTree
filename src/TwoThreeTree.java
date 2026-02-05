/*+----------------------------------------------------------------------
 ||
 ||  Class TwoThreeTree 
 ||
 ||         Author:  Joseph Corella
 ||
 ||        Purpose:  Contains the representation of a twothree tree. Contains
 ||                  only the root node of the tree since the entire tree can be
 ||                  accessed from the references to other nodes. The class supports
 ||                  insertion and searching, but not deletion since it is not needed
 ||                  for this programming assignment.
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
 ||   Constructors:  TwoThreeTree() //Default constructor (sets root to null)
 ||
 ||  Class Methods:  None
 ||
 ||  Inst. Methods:  Node searchFor(String word, ThreeNode currNode) 
 ||                  -- returns a node if the word occurence already exists, otherwise returns null.
 ||                  The function requires the root node and the word to be inserted.
 ||                  
 ||                  ThreeNode findInsertLocation(String word, ThreeNode currNode) -- returns where a node should be inserted if the word does not
 ||                  exist in the tree yet. (Returns the lowest twoThree node, so the insert can determine where to insert)
 ||
 ||                  void insert(String word, int paragraphNum, int line) 
 ||                  -- inserts a given word occurence with the given locations, if the word already exists the
 ||                  occurences location is simply added to the node location list.
 ||
 ||                 void insertRecursion(Node insert, ThreeNode insertLocation, splitNode promotions)
 ||                 -- uses recursive methods to resolve insertions if the instance does not exist. 
 ||
 ||                  splitNode splitThreeNode(ThreeNode insert, Node insertion) 
 ||                  --splits a threenode, keeps track of the promoted key and the "left" and "right" children.
 ||
 ||                  void printInOrderTraversal() -- prints out the in order traversal of the tree according to
 ||                  the assignment requirements.
 ||                  void printInOrder() -- traverses the tree recursively.
 ||
 ||                  void printOccurences(Node occurence) -- print all occurences of a given word
 ||
 ||
 ++-----------------------------------------------------------------------*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwoThreeTree {
    private ThreeNode rootNode; //Contains the root node of the tree!

    TwoThreeTree() 
    {
        this.rootNode = null;
    }

    /*---------------------------------------------------------------------
    |  Method searchFor
    |
    |  Purpose:  Searches for a given word instance. If the instance already exists, the node is returned,
    |            otherwise a null pointer is returned.
    |
    |  Pre-condition:  None.
    |
    |  Post-condition: Instance is determined to be in the tree or not.
    |
    |  Parameters: String word -- word we are searching for
    |              ThreeNode currNode -- node we are currently searching at (starts with root)
    |
    |  Returns:  Null or the node in which the instance already exists.
    *-------------------------------------------------------------------*/
    private Node searchFor(String word, ThreeNode currNode) 
    {
        Node newNode = new Node(word);

        if(currNode == null) 
        {
            return null;
        }

        //CurrNode is a three node
        if(currNode.isAThreeNode()) 
        {
            int rightCompare = currNode.getRightNode().compareTo(newNode);
            int leftCompare = currNode.getLeftNode().compareTo(newNode);

            //The node is equivalent to the left node, return left node!
            if(leftCompare == 0) 
            {
                return currNode.getLeftNode();
            }
            //The node is equivalent to the right node, return right node!
            else if(rightCompare == 0) 
            {
                return currNode.getRightNode();
            }
            
            //The node is less than left node, recurse down left the tree (if there is a child)
            else if(leftCompare > 0 && currNode.getLeftChild() != null) 
            {
                return searchFor(word, currNode.getLeftChild());
            }
            //The node is greater than the right node, recurse down right the tree (if there is a child)
            else if(rightCompare < 0 && currNode.getRightChild() != null) 
            {
                return searchFor(word, currNode.getRightChild());
            }
            //The node is between both values, recurse down the middle. (if there is a child)
            else 
            {
                if(currNode.getMiddleChild() != null) 
                {
                    return searchFor(word, currNode.getMiddleChild());
                }
            }
        }

        //CurrNode is a two node
        else 
        {
            int leftCompare = currNode.getLeftNode().compareTo(newNode);

            //The node is equal to the left node! Return left node
            if(leftCompare == 0) 
            {
                return currNode.getLeftNode();
            }
            //The node is less than the left node, recurse down left the tree (if there is a child)
            else if(leftCompare > 0 && currNode.getLeftChild() != null) 
            {
                return searchFor(word, currNode.getLeftChild());
            }
            //The node is greater than the left node, recurse down right the tree (if there is a child)
            else 
            {
                if(currNode.getMiddleChild() != null) 
                {
                    return searchFor(word, currNode.getMiddleChild());
                }
            }
            
        }

        //Falls through all if statements, meaning that there is no further node to check for
        //Instance does not exist
        return null;
    }


    /*---------------------------------------------------------------------
    |  Method findInsertLocation
    |
    |  Purpose:  Finds the threenode in where an instance should exist. Used in the 
    |            insert function to find where an instance should be added.
    |
    |  Pre-condition:  Word instance should not be found in the tree.
    |
    |  Post-condition: ThreeNode position where the instance should be inserted is returned.
    |
    |  Parameters: String word -- word that is being inserted
    |              ThreeNode currNode -- the node we are currently searching in (starts with root)
    |
    |  Returns:  Nothing
    *-------------------------------------------------------------------*/
    private ThreeNode findInsertLocation(String word, ThreeNode currNode) 
    {
        Node newNode = new Node(word);
        if(currNode == null) 
        {
            return null;
        }

        //CurrNode is a three node
        if(currNode.isAThreeNode()) 
        {
            int rightCompare = currNode.getRightNode().compareTo(newNode);
            int leftCompare = currNode.getLeftNode().compareTo(newNode);
            
            //The node is less than left node, recurse down left the tree (if there is a child)
            if(leftCompare > 0 && currNode.getLeftChild() != null) 
            {   
                return findInsertLocation(word, currNode.getLeftChild());
            }
            //The node is greater than the right node, recurse down right the tree (if there is a child)
            else if(rightCompare < 0 && currNode.getRightChild() != null) 
            {
                return findInsertLocation(word, currNode.getRightChild());
            }
            //The node is between both values, recurse down the middle. (if there is a child)
            else if(leftCompare < 0 && rightCompare > 0)
            {
                if(currNode.getMiddleChild() != null) 
                {
                    return findInsertLocation(word, currNode.getMiddleChild());
                }
            }
        }

        //CurrNode is a two node
        else 
        {
            int leftCompare = currNode.getLeftNode().compareTo(newNode);

            //The node is less than the left node, recurse down left the tree (if there is a child)
            if(leftCompare > 0 && currNode.getLeftChild() != null) 
            {
                return findInsertLocation(word, currNode.getLeftChild());
            }
            //The node is greater than the left node, recurse down right the tree (if there is a child)
            else if(leftCompare < 0)
            {
                if(currNode.getMiddleChild() != null) 
                {
                    return findInsertLocation(word, currNode.getMiddleChild());
                }
            }
            
        }

        //Falls through all if statements, meaning that there is node further node to check for
        //Return the leaf node where the node should be inserted
        return currNode;
    }

    /*---------------------------------------------------------------------
    |  Method Insert
    |
    |  Purpose: Insert a new word into the two three tree. Uses recursive methods to 
    |           resolve three nodes. Keeps the twoThree tree balanced.
    |
    |  Pre-condition:  Tree object should be declared.
    |
    |  Post-condition: Word has been inserted at the correct node.
    |
    |  Parameters: String word -- word to be inserted
    |              int paragraphNum -- paragraph location of the word
    |              int line -- line location of the word
    |
    |  Returns:  Nothing
    *-------------------------------------------------------------------*/
    public void insert(String word, int paragraphNum, int line) 
    {   
        //Sets up word as a new Node
        Node insert = new Node(word);
        insert.addLocation(paragraphNum, line);
        
        //Searches for word, and finds the lowest insert location
        Node searchLocation = searchFor(insert.getWord(), this.rootNode);
        ThreeNode insertLocation = findInsertLocation(insert.getWord(), this.rootNode);
        
        //Tree does not exist
        if(insertLocation == null) 
        {
            ThreeNode newRoot = new ThreeNode();
            newRoot.setLeftNode(insert);
            this.rootNode = newRoot;
        }

        //Instance already exists!
        else if(searchLocation != null) 
        {
            searchLocation.addLocation(insert.getParagraphAt(0), insert.getLineAt(0));
        }

        //Instance does not exist
        else
        {
            insertRecursion(insert, insertLocation, null);
        }
    }

    /*---------------------------------------------------------------------
    |  Method insertRecursion
    |
    |  Purpose:  Recursively resolved threenode inserts. Has three main handles, a root node, 
    |            a two node, and a threenode. Keeps recursing until all threenodes are resolved,
    |            and the tree is balanced. Used in insert function when instance does not exist,
    |            keeps recursing until all nodes are resolved.
    |
    |  Pre-condition:  Insert has been called.
    |
    |  Post-condition: Has resolved all threenodes till a parent node could absorb all 
    |                  insertions, or till it hit the root node.
    |
    |  Parameters: Node insert -- insertion node (contains word and location of word)
    |              ThreeNode insertLocation -- contains where the word should be inserted
    |              splitNode promotions -- contains the split node promoted node and children nodes
    |
    |  Returns:  Nothing
    *-------------------------------------------------------------------*/
    private void insertRecursion(Node insert, ThreeNode insertLocation, splitNode promotions) 
    {   
        //InsertLocation is the root node
        if(insertLocation == null)
        {
            //Insert is the root node
            ThreeNode newParent = new ThreeNode();

            newParent.setLeftNode(promotions.promotedChild.getLeftNode());
            newParent.setLeftChild(promotions.leastChild);
            newParent.setMiddleChild(promotions.greatestChild);

            this.rootNode = newParent;

            return;
        }

        //Insert location is a two node no recursion required.
        else if(insertLocation.isATwoNode()) 
        {   
            //Insertion is a leaf (do not need to assign children)
            if(insertLocation.isALeaf()) 
            {
                //Insert in left node of three node
                if(insertLocation.getLeftNode().compareTo(insert) > 0) 
                {
                    Node leftNode = insertLocation.getLeftNode();
                    
                    insertLocation.setLeftNode(insert);
                    insertLocation.setRightNode(leftNode);
                }
                //Insert in right node of three node,
                else 
                {
                    insertLocation.setRightNode(insert);
                }
            }
            else 
            {   
                //Insertion is an internal node (need to assign children)

                //Insertion is less than the current left node
                if(insertLocation.getLeftNode().compareTo(promotions.promotedChild.getLeftNode()) > 0)
                {
                    Node leftNode = insertLocation.getLeftNode();

                    insertLocation.setLeftNode(promotions.promotedChild.getLeftNode());
                    insertLocation.setRightNode(leftNode);
                }
                
                //Insertion is greater than the current left node
                else 
                {
                    insertLocation.setRightNode(promotions.promotedChild.getLeftNode());
                }

                    //Assigns children based on where the promotion occurred from
                    if(promotions.leftSubTree) 
                    {
                        insertLocation.setLeftChild(promotions.leastChild);
                        insertLocation.setRightChild(insertLocation.getMiddleChild());

                        insertLocation.setMiddleChild(promotions.greatestChild);
                    }
                    else if(promotions.rightSubTree)
                    {
                        insertLocation.setMiddleChild(promotions.leastChild);
                        insertLocation.setRightChild(promotions.greatestChild);
                    }
            }
            return;
        }

        //Insert location is a three node
        else if(insertLocation.isAThreeNode()) 
        {   
            splitNode promote = new splitNode();
            
            //If the insertion place is a leaf, no need to assign children (or use promoted key)
            if(insertLocation.isALeaf())
            {
               promote = splitThreeNode(insertLocation, insert);
            }

            //If the insertion is an internal node, we need to assign children correctly based on the splitNode
            else 
            {
                promote = splitThreeNode(insertLocation, promotions.promotedChild.getLeftNode());

                //Places children accordingly based on the promotion direction
                if(promotions.leftSubTree) 
                {
                    promote.leastChild.setLeftChild(promotions.leastChild);
                    promote.leastChild.setMiddleChild(promotions.greatestChild);

                    promote.greatestChild.setLeftChild(insertLocation.getMiddleChild());
                    promote.greatestChild.setMiddleChild(insertLocation.getRightChild());
                }
                else if(promotions.midSubTree) 
                {
                    promote.leastChild.setLeftChild(insertLocation.getLeftChild());
                    promote.leastChild.setMiddleChild(promotions.leastChild);

                    promote.greatestChild.setLeftChild(promotions.greatestChild);
                    promote.greatestChild.setMiddleChild(insertLocation.getRightChild());
                }
                else if(promotions.rightSubTree) 
                {
                    promote.leastChild.setLeftChild(insertLocation.getLeftChild());
                    promote.leastChild.setMiddleChild(insertLocation.getMiddleChild());

                    promote.greatestChild.setLeftChild(promotions.leastChild);
                    promote.greatestChild.setMiddleChild(promotions.greatestChild);
                }

            }

            //Non-root node
            if(insertLocation.getParent() != null) 
            {   
                //Deletes old insert locations, so that we can update the children later
                //Disconnects the children from their parents after splitting up nodes
                if(insertLocation.getParent().isAThreeNode()) 
                {
                    if(insertLocation.getParent().getLeftChild() == insertLocation) 
                    {
                        insertLocation.getParent().setLeftChild(null);
                        promote.leftSubTree = true;
                    }
                    else if(insertLocation.getParent().getMiddleChild() == insertLocation) 
                    {
                        insertLocation.getParent().setMiddleChild(null);
                        promote.midSubTree = true;
                    }
                    else if(insertLocation.getParent().getRightChild() == insertLocation) 
                    {
                        insertLocation.getParent().setRightChild(null);
                        promote.rightSubTree = true;
                    }
                }
                else if(insertLocation.getParent().isATwoNode()) 
                {
                    if(insertLocation.getParent().getLeftChild() == insertLocation) 
                    {
                        insertLocation.getParent().setLeftChild(null);
                        promote.leftSubTree = true;
                    }
                    else if(insertLocation.getParent().getMiddleChild() == insertLocation) 
                    {
                        insertLocation.getParent().setMiddleChild(null);
                        promote.rightSubTree = true;
                    }
                }
            }

            insertRecursion(null, insertLocation.getParent(), promote);
        }


    }

    /*---------------------------------------------------------------------
    |  Method splitThreeNode
    |
    |  Purpose:  Splits a threenode based on the insertion key, and existing three node.
    |            Assigns the promoted child as the median of the three, and the left child
    |            and right child accordingly.
    |
    |  Pre-condition:  ThreeNode should not be null, insertion should not exist in tree
    |
    |  Post-condition: splitNode is returned, promoted key is discovered.
    |
    |  Parameters: ThreeNode insert -- Insertion location where "insertion" should be added
    |              Node insertion -- Insertion node that is being added to the tree.
    |
    |  Returns:  splitNode
    *-------------------------------------------------------------------*/
    private splitNode splitThreeNode(ThreeNode insert, Node insertion) 
    {
        splitNode split = new splitNode();

        Node leftNode = insert.getLeftNode();
        Node rightNode = insert.getRightNode();

        List<Node> sorted = new ArrayList<>();
        
        sorted.add(leftNode);
        sorted.add(rightNode);
        sorted.add(insertion);


        Collections.sort(sorted);

        split.greatestChild.setLeftNode(sorted.get(2));
        split.promotedChild.setLeftNode(sorted.get(1));
        split.leastChild.setLeftNode(sorted.get(0));

        return split;
    }
/*+----------------------------------------------------------------------
 ||
 ||  Class splitNode
 ||
 ||         Author:  Joseph Corella
 ||
 ||        Purpose:  Helper to make splitting keys easier and better to implement.
 ||                  Keeps track of the promoted child, least child and greatest child from the split.
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
 ||   Constructors:  Default
 ||
 ||  Class Methods:  None
 ||
 ||  Inst. Methods:  None
 ||
 ||
 ++-----------------------------------------------------------------------*/
    public class splitNode 
    {
        public ThreeNode greatestChild = new ThreeNode();
        public ThreeNode leastChild = new ThreeNode();
        public ThreeNode promotedChild = new ThreeNode();
        
        public boolean leftSubTree = false;
        public boolean midSubTree = false;
        public boolean rightSubTree = false;
    }

    /*---------------------------------------------------------------------
    |  Method printInOrderTraversal / printInOrder
    |
    |  Purpose:  Gives a formatted printing method for a word and all occurences of that word.
    |            Traverses the twothree tree and prints in order each node.
    |
    |  Pre-condition:  None
    |
    |  Post-condition: Tree is traversed, words and locations are printed.
    |
    |  Parameters: None
    |
    |  Returns:  Nothing
    *-------------------------------------------------------------------*/
    public void printInOrderTraversal() {
        printInOrder(this.rootNode);
    }

    private void printInOrder(ThreeNode node) {

        if (node == null) {
            return;
        }

        //Go as far as possible
        if (node.isATwoNode()) {
            printInOrder(node.getLeftChild());

            printOccurences(node.getLeftNode());

            printInOrder(node.getMiddleChild());
            return;
        }

        printInOrder(node.getLeftChild());

        printOccurences(node.getLeftNode());

        printInOrder(node.getMiddleChild());

        printOccurences(node.getRightNode());

        printInOrder(node.getRightChild());
    }

    /*---------------------------------------------------------------------
    |  Method printOccurences
    |
    |  Purpose:  Print all of the occurences from a certain word. (formats 
    |            by program requirements).
    |
    |  Pre-condition:  None
    |
    |  Post-condition: All occurences of the word are printed.
    |
    |  Parameters: None
    |
    |  Returns:  Nothing
    *-------------------------------------------------------------------*/
    private void printOccurences(Node occurence) 
    {
        System.out.printf("%-25s", occurence.getWord());
        System.out.print(" | ");

        int occurenceNum = 0;       //keep track of index

        //Prints only 8 at a time (executes if there is more than 8 occurences)
        for(int i=1; i<=occurence.getOccurenceSize()/8; i++) 
        {
            if(i > 1) 
            {  
                System.out.printf( "---                       | ");
            }

            for(int j=0; j<8; j++)
            {
                System.out.printf(" (%3d, %3d) ", occurence.getParagraphAt(occurenceNum), occurence.getLineAt(occurenceNum));
                occurenceNum++;
            }  
            System.out.println();
        }

        //If there is no more to print return
        if(occurenceNum == occurence.getOccurenceSize()) 
        {
            return;
        }
        
        //Prints if there is one line of locations printed already
        if(occurenceNum > 7 && occurenceNum < occurence.getOccurenceSize()) 
        {
            System.out.print("---                       | ");
        }

        //Prints the rest of the location (executes if there is less than 8 occurences left)
        while(occurenceNum < occurence.getOccurenceSize())
        {
            System.out.printf(" (%3d, %3d) ", occurence.getParagraphAt(occurenceNum), occurence.getLineAt(occurenceNum));
            occurenceNum++;
        }

        System.out.println();
    }

}

