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
 ||                  void printInOrderTraversal() -- prints out the in order traversal of the tree according to
 ||                  the assignment requirements.
 ||                  void printInOrder() -- traverses the tree recursively.
 ||
 ||
 ++-----------------------------------------------------------------------*/

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
            else 
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
            else 
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
        Node insert = new Node(word);
        insert.addLocation(paragraphNum, line);

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

        else
        {
            insertRecursion(insert, insertLocation);
        }
    }

    /*---------------------------------------------------------------------
    |  Method insertRecursion
    |
    |  Purpose:  Recursively resolved threenode inserts. Has three main handles, a root node, 
    |            a two node, and a threenode. Keeps recursing until all threenodes are resolved,
    |            and the tree is balanced. Used in insert function when a threenode is detected
    |            at the expected insertion location.
    |
    |  Pre-condition:  Insert has been called.
    |
    |  Post-condition: Has resolved all threenodes till a parent node could absorb all 
    |                  insertions, or till it hit the root node.
    |
    |  Parameters: Node insert -- insertion node (contains word and location of word)
    |              ThreeNode insertLocation -- contains where the word should be inserted
    |
    |  Returns:  ThreeNode, returns the parent node of insertions
    *-------------------------------------------------------------------*/
    private ThreeNode insertRecursion(Node insert, ThreeNode insertLocation) 
    {   
        //InsertLocation is the root node
        if(insertLocation == null)
        {
            //Insert is the root node
            ThreeNode newParent = new ThreeNode();

            newParent.setLeftNode(insert);
            this.rootNode = newParent;

            return newParent;
        }

        //Insert location is a two node no recursion required.
        else if(insertLocation.isATwoNode()) 
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

            return insertLocation;
        }

        //Insert location is a three node
        else if(insertLocation.isAThreeNode()) 
        {   

            int leftCompare = insert.compareTo(insertLocation.getLeftNode());
            int rightCompare = insert.compareTo(insertLocation.getRightNode());
            
            Node L = insertLocation.getLeftNode();
            Node R = insertLocation.getRightNode();

            ThreeNode leftChild = new ThreeNode();
            ThreeNode rightChild = new ThreeNode();

            Node median = new Node();

            //Insertion word is less than the left node
            if(leftCompare < 0) 
            {
                leftChild.setLeftNode(insert);

                rightChild.setLeftNode(R);

                median = L;
            }
            
            //Insertion word is greater than the right node
            else if(rightCompare > 0) 
            {
                leftChild.setLeftNode(L);

                rightChild.setLeftNode(insert);

                median = R;
            }
            //Insert is median
            else 
            {
                leftChild.setLeftNode(L);

                rightChild.setLeftNode(R);
                
                median = insert;
            }
            
            insertRecursion(median, insertLocation.getParent());

            
        }

        return null;
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

    private void printOccurences(Node occurence) 
    {
        System.out.print(occurence.getWord());

        for(int i=0; i<occurence.getOccurenceSize(); i++) 
        {
            System.out.print(" ("+occurence.getParagraphAt(i)+", "+occurence.getLineAt(i)+") ");
        }
        System.out.println();
    }



    // Pretty-print the tree with ASCII lines
public void printTreeDiagram() {
    printDiagram(this.rootNode, "", true);
}

private void printDiagram(ThreeNode node, String prefix, boolean isTail) {
    if (node == null) {
        return;
    }

    // Print this node
    String nodeLabel;
    if (node.isATwoNode()) {
        nodeLabel = node.getLeftNode().getWord();
    } else {
        nodeLabel = node.getLeftNode().getWord() + " | " + node.getRightNode().getWord();
    }

    System.out.println(prefix + (isTail ? "└── " : "├── ") + nodeLabel);

    // Collect children
    java.util.List<ThreeNode> children = new java.util.ArrayList<>();
    if (node.getLeftChild() != null) children.add(node.getLeftChild());
    if (node.getMiddleChild() != null) children.add(node.getMiddleChild());
    if (node.getRightChild() != null) children.add(node.getRightChild());

    // Print children recursively
    for (int i = 0; i < children.size(); i++) {
        ThreeNode child = children.get(i);
        boolean last = (i == children.size() - 1);
        printDiagram(child, prefix + (isTail ? "    " : "│   "), last);
    }
}

}

