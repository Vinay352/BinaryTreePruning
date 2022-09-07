package Leetcode.Medium;

/**
 *
 * Author: Vinay Jain
 * Contact: vinay.j3097@gmail.com
 * Question: Binary Tree pruning
 * Link: https://leetcode.com/problems/binary-tree-pruning/
 *
 */

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePruning {
    public static void main(String[] args) {

        // String input = "[1,null,0,0,1]";

        // String input = "[1,null,1,null,null,null,1]";

        // String input = "[1,0,1,0,0,0,1]";

        String input = "[0,null,0,0,0]";

        input = input.substring(1, input.length() - 1);

        String[] arr = input.split(",");

        List<String> inputList = List.of(arr);

        for(int i = 0; i < inputList.size(); i++){
            System.out.println(inputList.get(i));
        }

        TreeNode root = createTree(inputList);

        root = pruneTree(root);

        printTree(root);

        System.out.println();
    }

    /**
     * Function to print the tree. not exactly as the answer expects it.
     * @param temp root node of the tree
     */
    private static void printTree(TreeNode temp) {
        if(temp == null){
            System.out.print("null ");
        }
        else{
            printTree(temp.left);
            System.out.print(temp.val + " ");
            printTree(temp.right);
        }
    }

    /**
     * Function to create the tree from the input in string form.
     *
     * @param inputList thel ist containing the input elements
     * @return root node of the tree created
     */
    public static TreeNode createTree(List inputList){
        if(inputList.isEmpty() || inputList.get(0) == "null"){
            return null;
        }

        // List<Integer> nullIndexArray = createNullIndex(inputList);

        List<Integer> nullIndexArray = new ArrayList<>(1);
        nullIndexArray.add(0);

        TreeNode root = new TreeNode();
        root = add(inputList, 0 , nullIndexArray);

        return root;

    }

    /**
     * A function created to produce an array to keep track of indices where
     * we get null value in the input.
     *
     * @param inputList
     * @return required list
     */
    private static List<Integer> createNullIndex(List inputList) {
        List<Integer> whereNullValueIsPresent = new ArrayList<>();
        for(int i =0; i < inputList.size(); i++){
            if( inputList.get(i) == "null" ){
                whereNullValueIsPresent.add(i);
            }
        }
        return whereNullValueIsPresent;
    }

    /**
     * Function to add the input elements one by one to the tree.
     *
     * @param inputList input list
     * @param i index of input element being added
     * @param nullIndexArray at what index null value is encountered
     * @return
     */
    private static TreeNode add(List<String> inputList, int i, List<Integer> nullIndexArray) {

        if( i < 0 || i >= inputList.size() ){
            return null;
        }

        int nullIndex = nullIndexArray.get(0);
        String elem = inputList.get(i);

        if( elem.equals( "null" ) ){
            nullIndexArray.remove(0);
            nullIndex = i + 1;
            nullIndexArray.add(nullIndex);
            return null;
        }
        else{
            TreeNode temp = new TreeNode();
            temp.val = Integer.parseInt(elem);

            int is1there = temp.val == 1 ? 1 : 0;

            if( nullIndex == 0 ){
                temp.left = add(inputList, 2*i + 1, nullIndexArray);

                temp.right = add(inputList, 2*i + 2, nullIndexArray);
            }
            else{
                temp.left = add(inputList, nullIndex + 2*(i - nullIndex) + 1, nullIndexArray);
                temp.right = add(inputList, nullIndex + 2*(i - nullIndex) + 2, nullIndexArray);
            }
            return temp;
        }
    }

    /**
     * The function to prune the tree into required format.
     *
     * @param root
     * @return
     */
    public static TreeNode pruneTree(TreeNode root){

        TreeNode temp = root;

        recurseToCheckIfOnePresent(temp);

        if(temp.val == -1){
            temp = null;
        }

        temp = inOrderTraversal(temp);

        return temp;

    }

    /**
     * Traverse tree in in-order manner and assigning null to nodes withval = -1.
     * @param temp
     * @return
     */
    private static TreeNode inOrderTraversal(TreeNode temp) {

        if(temp == null){
            return null;
        }

        if(temp.val != -1){
            temp.left = inOrderTraversal(temp.left);

            // parent node

            temp.right = inOrderTraversal(temp.right);

            return temp;
        }
        return null;

    }

    /**
     * To check if 1 is present in the subtree
     *
     * @param temp
     * @return
     */
    private static int recurseToCheckIfOnePresent(TreeNode temp) {

        if(temp == null || temp.val == -1){
            return 0;
        }

        int leftVal = recurseToCheckIfOnePresent( temp.left );
        int rightVal = recurseToCheckIfOnePresent( temp.right );

        if( temp.val != 1 && leftVal != 1 && rightVal != 1 ){
            temp.val = -1;
            temp.left = null;
            temp.right = null;
            return 0;
        }
        else{
            return 1;
        }
    }

}
