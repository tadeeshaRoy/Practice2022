// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
// https://www.youtube.com/watch?v=ihj4IQGZ2zc
// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/discuss/1780924/Easy-To-Understand-Java-Code-Line-By-Line-%3A

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    static int p_i = 0;
    private int findIndex(int[] inorder, int key, int start,int end) {
        for(int i=start; i<=end; i++) {
            if(inorder[i] == key) return i;
        }
        return -1;
    }
    
    private TreeNode util(int[] preorder, int[] inorder, int left, int right) {
        if(left > right) return null; // not left==right because we need new TreeNode(key) in that case not null and also need to update pre-order index
        int key = preorder[p_i++];
        TreeNode newNode = new TreeNode(key);
        int index = findIndex(inorder,key,left,right);
        
        newNode.left = util(preorder, inorder, left, index - 1);
        newNode.right = util(preorder, inorder, index + 1, right);
        return newNode;
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 1) return new TreeNode(preorder[0]);
        p_i = 0;
        return util(preorder, inorder,0, inorder.length - 1);
    }
}
