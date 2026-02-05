# Balanced 2-3 Search Tree Implementation

## Project Overview
This project is a custom, from-scratch implementation of a **2-3 Tree** in Java. Unlike a standard Binary Search Tree, a 2-3 Tree is a self-balancing data structure that guarantees $O(\log n)$ time complexity for search, insertion, and deletion by ensuring all leaf nodes remain at the same depth.

## Key Features
* **Self-Balancing Logic**: Implemented complex node-splitting and key-promotion algorithms to ensure the tree never becomes skewed.
* **Efficient Data Handling**: Optimized for $O(\log n)$ performance, making it suitable for large-scale dataset parsing.
* **Dynamic Node Structure**: Designed custom `Node` classes capable of holding either one or two keys and up to three children.
* **Recursive Operations**: Utilized robust recursion for both tree traversal and the "upward" ripple effect required during node splits.

## Technical Implementation
### The Split Algorithm
The core of a 2-3 Tree’s power lies in how it handles overflow. When a 3-node (a node already containing two keys) receives a third key, the implementation:
1.  Temporarily creates a 4-node.
2.  Identifies the median key.
3.  Promotes the median key to the parent node.
4.  Splits the remaining keys into two separate 2-nodes.```
