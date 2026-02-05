/*+----------------------------------------------------------------------
 ||
 ||  Class ThreeNode
 ||
 ||         Author:  Joseph Corella
 ||
 ||        Purpose:  Creates the base nodes of the two-three tree.
 ||                  Keeps track of relations between a three node, such
 ||                  as the two data point nodes and the three children
 ||                  references for easier traversal.
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
 ||   Constructors:  ThreeNode() -- default constructor
 ||
 ||  Class Methods:  None
 ||
 ||  Inst. Methods:  boolean isATwoNode() -- Returns true if it is a twonode
 ||                  boolean isAThreeNode() -- Returns true if it is a threenode
 ||                  boolean isALeaf() -- Returns whether the current node is a leaf node (no children)
 ||                  Node getLeftNode() -- Returns the left data point (also is the default node for a two node)
 ||                  Node getRightNode() -- Returns the right data point of the threenode
 ||                  ThreeNode getParent() -- Returns reference to the parent
 ||                  ThreeNode getLeftChild()  -- Returns reference to the left child
 ||                  ThreeNode getMiddleChild() -- Returns the reference to the middle child
 ||                  ThreeNode getRightChild() -- Returns the reference to the right child
 ||                  void setLeftNode(Node key) -- Sets the left data point
 ||                  void setRightNode(Node key) -- Sets the right data point
 ||                  void setParent(ThreeNode parent) -- Sets the reference for the parent
 ||                  void setLeftChild(ThreeNode child) -- Sets the left reference for the left child
 ||                  void setMiddleChild(ThreeNode child) -- Sets the middle reference for the middle child
 ||                  void setRightChild(ThreeNode child) -- Sets the reference for the right child
 ||
 ++-----------------------------------------------------------------------*/

public class ThreeNode {
    private Node leftNode;               //Left Node data (default node data for twonode)
    private Node rightNode;              //Right Node data

    private ThreeNode parent;                   //easier implementation of promoting and demoting

    private ThreeNode rightChild;               //Right child
    private ThreeNode middleChild;              //Middle child
    private ThreeNode leftChild;                //Lefft child
    
    ThreeNode() 
    {
        this.leftNode = null;
        this.rightNode = null;
        this.parent = null;
    }

    /*---------------------------------------------------------------------
    |  Method isATwoNode
    |
    |  Purpose:  Since every node in the tree is esentially a ThreeNode class,
    |            this method determines whether it is a twonode (with an inactive rightnode).
    |
    |  Pre-condition:  ThreeNode must have at least a left node (leftNode != null)
    |
    |  Post-condition: Node is determined to be a twonode
    |
    |  Parameters: None
    |
    |  Returns:   boolean (true if two node)
    *-------------------------------------------------------------------*/
    public boolean isATwoNode() 
    {
        //Returns false if the node is currently a threenode
        if(rightNode != null) 
        {
            return false;
        }

        //Returns true if the node is currently a twonode
        else {
            return true;
        }
    }

    /*---------------------------------------------------------------------
    |  Method isAThreeNode
    |
    |  Purpose:  Since every node in the tree is esentially a ThreeNode class,
    |            this method determines whether it is a threenode
    |
    |  Pre-condition:  ThreeNode must have at least a left node (leftNode != null)
    |
    |  Post-condition: Node is determined to be a threenode or twonode.
    |
    |  Parameters: None
    |
    |  Returns:   boolean (true if three node)
    *-------------------------------------------------------------------*/
    public boolean isAThreeNode() 
    {
        //Returns true if the node is currently a ThreeNode
        if(rightNode != null) 
        {
            return true;
        }

        //Returns false if the node is currently a TwoNode
        else {
            return false;
        }
    }

    /*---------------------------------------------------------------------
    |  Method isALeaf
    |
    |  Purpose:  Determines whether the node has any children.
    |
    |  Pre-condition:  ThreeNode must not be null.
    |
    |  Post-condition: Node is determined to be a leaf node.
    |
    |  Parameters: None
    |
    |  Returns:   boolean (true if it as an internal node, has children)
    *-------------------------------------------------------------------*/
    public Boolean isALeaf() 
    {
        if(this.leftChild == null && this.rightChild == null && this.middleChild == null) return true;

        return false;
    }

    /*---------------------------------------------------------------------
    |  Method getLeftNode
    |
    |  Purpose:  Return the left node (contains data)
    |
    |  Pre-condition:  Left node should not be null
    |
    |  Post-condition: Node is returned
    |
    |  Parameters: None
    |
    |  Returns:  Node
    *-------------------------------------------------------------------*/
    public Node getLeftNode() 
    {
        return this.leftNode;
    }

    /*---------------------------------------------------------------------
    |  Method getRightNode
    |
    |  Purpose:  Return the right node (contains data)
    |
    |  Pre-condition:  Right node should exist, can be null.
    |
    |  Post-condition: Node is returned
    |
    |  Parameters: None
    |
    |  Returns:  Node
    *-------------------------------------------------------------------*/
    public Node getRightNode() 
    {
        return this.rightNode;
    }
    
    /*---------------------------------------------------------------------
    |  Method getParent
    |
    |  Purpose:  Return the parent of the threenode.
    |
    |  Pre-condition:  ThreeNode should exist or be null.
    |
    |  Post-condition: parent is returned
    |
    |  Parameters: None
    |
    |  Returns:  ThreeNode
    *-------------------------------------------------------------------*/
    public ThreeNode getParent() {
        return this.parent;
    }

     /*---------------------------------------------------------------------
    |  Method getLeftChild
    |
    |  Purpose:  Return the left child of the threenode.
    |
    |  Pre-condition:  ThreeNode should exist or be null.
    |
    |  Post-condition: Left child is returned
    |
    |  Parameters: None
    |
    |  Returns:  ThreeNode
    *-------------------------------------------------------------------*/
    public ThreeNode getLeftChild() {
        return this.leftChild;
    }

    
     /*---------------------------------------------------------------------
    |  Method getMiddleChild
    |
    |  Purpose:  Return the middle child of the threenode.
    |
    |  Pre-condition:  ThreeNode should exist or be null.
    |
    |  Post-condition: Middle child is returned
    |
    |  Parameters: None
    |
    |  Returns:  ThreeNode
    *-------------------------------------------------------------------*/
    public ThreeNode getMiddleChild() 
    {
        return this.middleChild;
    }

    
     /*---------------------------------------------------------------------
    |  Method getRightChild
    |
    |  Purpose:  Return the right child of the threenode.
    |
    |  Pre-condition:  ThreeNode should exist or be null.
    |
    |  Post-condition: Right child is returned
    |
    |  Parameters: None
    |
    |  Returns:  ThreeNode
    *-------------------------------------------------------------------*/
    public ThreeNode getRightChild() 
    {
        return this.rightChild;
    }

     /*---------------------------------------------------------------------
    |  Method setLeftNode
    |
    |  Purpose:  Update the left node's data information.
    |
    |  Pre-condition:  Node should exist or be null
    |
    |  Post-condition: Left Node data is changed
    |
    |  Parameters: Node key -- contains the node information
    |
    |  Returns:  Nothing
    *-------------------------------------------------------------------*/
    public void setLeftNode(Node key) 
    {
        this.leftNode = key;
    }

    /*---------------------------------------------------------------------
    |  Method setRightNode
    |
    |  Purpose:  Update the right node's data information.
    |
    |  Pre-condition:  Node should exist or be null
    |
    |  Post-condition: Right Node data is changed
    |
    |  Parameters: Node key -- contains the node information
    |
    |  Returns:  Nothing
    *-------------------------------------------------------------------*/
    public void setRightNode(Node key) 
    {
        this.rightNode = key;
    }

    /*---------------------------------------------------------------------
    |  Method setParent
    |
    |  Purpose:  Update the parent reference.
    |
    |  Pre-condition:  ThreeNode should exist or be null
    |
    |  Post-condition: Parent reference is changed
    |
    |  Parameters: ThreeNode parent -- contains the reference to an existing parent
    |
    |  Returns:  Nothing
    *-------------------------------------------------------------------*/
    public void setParent(ThreeNode parent) 
    {
        this.parent = parent;
    }

   /*---------------------------------------------------------------------
    |  Method setLeftChild
    |
    |  Purpose:  Update the left child reference.
    |
    |  Pre-condition:  ThreeNode should exist or be null
    |
    |  Post-condition: Left Child reference is changed
    |
    |  Parameters: ThreeNode child -- contains the reference to a new child
    |
    |  Returns:  Nothing
    *-------------------------------------------------------------------*/
    public void setLeftChild(ThreeNode child) 
    {
        this.leftChild = child;
        if(child != null)
        {
            child.setParent(this);
        }
    }

     /*---------------------------------------------------------------------
    |  Method setMiddleChild
    |
    |  Purpose:  Update the middle child reference.
    |
    |  Pre-condition:  ThreeNode should exist or be null
    |
    |  Post-condition: Middle Child reference is changed
    |
    |  Parameters: ThreeNode child -- contains the reference to a new child
    |
    |  Returns:  Nothing
    *-------------------------------------------------------------------*/
    public void setMiddleChild(ThreeNode child) 
    {
        this.middleChild = child;
        if(child != null)
        {
            child.setParent(this);
        }
    }

    /*---------------------------------------------------------------------
    |  Method setRightChild
    |
    |  Purpose:  Update the right child reference.
    |
    |  Pre-condition:  ThreeNode should exist or be null
    |
    |  Post-condition: Right Child reference is changed
    |
    |  Parameters: ThreeNode child -- contains the reference to a new child
    |
    |  Returns:  Nothing
    *-------------------------------------------------------------------*/
    public void setRightChild(ThreeNode child) 
    {
        this.rightChild = child;
        if(child != null)
        {
            child.setParent(this);
        }
    }

}
