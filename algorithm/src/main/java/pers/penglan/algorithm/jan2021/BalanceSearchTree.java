package pers.penglan.algorithm.jan2021;

import jdk.nashorn.internal.ir.BaseNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 将一个二叉搜索树转变为平衡树
 *
 * @author penglanm@qq.com
 * 2021-01-30
 */
public class BalanceSearchTree {
    private Map<Integer, Integer> balanceNum = new HashMap<>();
    private Integer height = 0;

    public TreeNode balanceBST(TreeNode root) {
        balanceNum.clear();
        height = 0;
        root = convert(root, null);

        return root;
    }

    private TreeNode convert(TreeNode searchTree, TreeNode balanceTree) {
        if (Objects.nonNull(searchTree.left)) {
            balanceTree = convert(searchTree.left, balanceTree);
        }
        if (Objects.nonNull(searchTree.right)) {
            balanceTree = convert(searchTree.right, balanceTree);
        }
        searchTree.left = null;
        searchTree.right = null;
        balanceTree = insertToBalanceTree(balanceTree, searchTree);

        return balanceTree;
    }

    /**
     * 插入节点到平衡二叉树中
     * @param balanceTreeRootNode
     * @param newNode
     * @return
     */
    private TreeNode insertToBalanceTree(TreeNode balanceTreeRootNode, TreeNode newNode) {
        if (Objects.isNull(balanceTreeRootNode)) {
            height = 1;
            balanceNum.put(newNode.val, 0);
            return newNode;
        }

        /*左子树插入*/
        if (newNode.val < balanceTreeRootNode.val) {
            TreeNode leftBT = insertToBalanceTree(balanceTreeRootNode.left, newNode);
            balanceTreeRootNode.left = leftBT;
            if (height == 1) {
                int bn = balanceNum.get(balanceTreeRootNode.val);
                switch (bn) {
                    case -1:
                        balanceNum.replace(balanceTreeRootNode.val, 0);
                        height = 0;
                        break;
                    case 0:
                        balanceNum.replace(balanceTreeRootNode.val, 1);
                        break;
                    case 1:
                        /*LL\LR转换*/
                        balanceTreeRootNode = lChange(balanceTreeRootNode);
                        height = 0;
                        break;
                }
            }
        }
        /*右子树插入*/
        else if (newNode.val > balanceTreeRootNode.val) {
            TreeNode rightBT = insertToBalanceTree(balanceTreeRootNode.right, newNode);
            balanceTreeRootNode.right = rightBT;
            if (height == 1) {
                int bn = balanceNum.get(balanceTreeRootNode.val);
                switch (bn) {
                    case -1:
                        /*RR\RL转换*/
                        balanceTreeRootNode = rChange(balanceTreeRootNode);
                        height = 0;
                        break;
                    case 0:
                        balanceNum.replace(balanceTreeRootNode.val, -1);
                        break;
                    case 1:
                        balanceNum.replace(balanceTreeRootNode.val, 0);
                        height = 0;
                        break;
                }
            }
        }

        return balanceTreeRootNode;
    }

    /**
     * LL\LR转换
     * @param root
     * @return
     */
    private TreeNode lChange(TreeNode root) {
        TreeNode leftNode = root.left;
        /*LL转*/
        if (balanceNum.get(leftNode.val) == 1) {
            root.left = leftNode.right;
            leftNode.right = root;
            balanceNum.replace(root.val, 0);
            root = leftNode;
        }
        /*LR转*/
        else {
            TreeNode leftRightNode = leftNode.right;
            leftNode.right = leftRightNode.left;
            leftRightNode.left = leftNode;
            root.left = leftRightNode.right;
            leftRightNode.right = root;
            if (balanceNum.get(leftRightNode.val) == 1) {
                balanceNum.replace(leftNode.val, 0);
                balanceNum.replace(root.val, -1);
            }
            else if (balanceNum.get(leftRightNode.val) == 0){
                balanceNum.replace(leftNode.val, 0);
                balanceNum.replace(root.val, 0);
            }
            else {
                balanceNum.replace(leftNode.val, 1);
                balanceNum.replace(root.val, 0);
            }
            root = leftRightNode;
        }
        balanceNum.replace(root.val, 0);

        return root;
    }

    /**
     * RR\RL转
     * @param root
     * @return
     */
    private TreeNode rChange(TreeNode root) {
        TreeNode rightNode = root.right;
        /*RL转*/
        if (balanceNum.get(rightNode.val) == 1) {
            TreeNode rightLeftNode = rightNode.left;
            rightNode.left = rightLeftNode.right;
            rightLeftNode.right = rightNode;
            root.right = rightLeftNode.left;
            rightLeftNode.left = root;
            if (balanceNum.get(rightLeftNode.val) == 1) {
                balanceNum.replace(root.val, 0);
                balanceNum.replace(rightNode.val, -1);
            }
            else if (balanceNum.get(rightLeftNode.val) == 0){
                balanceNum.replace(root.val, 0);
                balanceNum.replace(rightNode.val, 0);
            }
            else {
                balanceNum.replace(root.val, 1);
                balanceNum.replace(rightNode.val, 0);
            }
            root = rightLeftNode;
        }
        /*RR转*/
        else {
            root.right = rightNode.left;
            rightNode.left = root;
            balanceNum.replace(root.val, 0);
            root = rightNode;
        }
        balanceNum.replace(root.val, 0);

        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(6);
        TreeNode t4 = new TreeNode(2);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.right = t6;

        TreeNode root = new BalanceSearchTree().balanceBST(t1);
        printTreeNodeByNLR(root);
    }

    public static void printTreeNodeByNLR(TreeNode root) {
        if (Objects.isNull(root)) {
            System.out.print(root + " -> ");
            return;
        }

        System.out.print(root.val + " -> ");
        printTreeNodeByNLR(root.left);
        printTreeNodeByNLR(root.right);
    }
}
