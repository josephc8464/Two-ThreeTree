# 2-3 Search Tree Implementation

## Overview
This repository contains a Java implementation of a balanced 2-3 Search Tree (excluding deletions). A 2-3 tree is a specific type of B-tree where every node with children (internal nodes) has either two children and one data element, or three children and two data elements. All leaf nodes are maintained at the same depth, ensuring logarithmic performance for core operations.

## Features
- **Guaranteed Balance**: The tree height is maintained at $O(\log n)$ through proactive node splitting and key promotion.
- **Dynamic Node Configuration**: Supports 2-nodes (1 key, 2 children) and 3-nodes (2 keys, 3 children).
- **In-Order Traversal**: Provides sorted output of all stored elements.
- **Recursive Logic**: Efficient implementation of insertion and search algorithms using recursive path-finding and upward balancing.

## Logic Flow: Insertion & Splitting
The implementation follows the standard 2-3 tree insertion protocol:
1. **Search**: Traverse to the appropriate leaf node.
2. **Add**: If the leaf is a 2-node, it becomes a 3-node.
3. **Split**: If the leaf is already a 3-node, it temporarily becomes a 4-node before splitting. 
4. **Promote**: The middle key is promoted to the parent. This process repeats recursively up the tree if the parent is also full. If the root splits, a new root is created, increasing the tree height by one.
