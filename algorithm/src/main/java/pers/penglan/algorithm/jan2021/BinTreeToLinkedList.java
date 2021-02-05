package pers.penglan.algorithm.jan2021;

import sun.reflect.generics.tree.Tree;

import java.util.Objects;

/**
 * 将二叉排序树转换为一个左子树为null的单向链表
 * @author penglanm@qq.com
 * 2021-01-29
 */
public class BinTreeToLinkedList {

    public TreeNode convertBiNode(TreeNode root) {
        return convert(root, null);
    }

    private TreeNode convert(TreeNode currentNode, TreeNode linkedNode) {
        if (Objects.isNull(currentNode)) {
            return linkedNode;
        }

        TreeNode linkHeader = convert(currentNode.right, linkedNode);
        if (Objects.isNull(linkHeader)) {
            linkHeader = currentNode;
            TreeNode leftToConvert = currentNode.left;
            linkHeader.left = null;
            linkHeader = convert(leftToConvert, linkHeader);
        } else {
            currentNode.right = linkHeader;
            /*先保留左节点，以待后续转换*/
            TreeNode leftToConvert = currentNode.left;
            linkHeader = currentNode;
            linkHeader.left = null;
            linkHeader = convert(leftToConvert, linkHeader);
        }

        return linkHeader;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
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

        TreeNode linkHeader = new BinTreeToLinkedList().convertBiNode(t1);
        printTreeNodeByNLR(linkHeader);
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