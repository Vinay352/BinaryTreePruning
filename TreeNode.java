package Leetcode.Medium;

/**
 *
 * Author: Vinay Jain
 * Contact: vinay.j3097@gmail.com
 * Question: Binary Tree pruning
 * Link: https://leetcode.com/problems/binary-tree-pruning/
 *
 */

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(){

    }

    public TreeNode(int val){
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
