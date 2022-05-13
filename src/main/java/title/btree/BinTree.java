package title.btree;

import java.util.LinkedList;

/**
 * @author jad
 * @Description: 二叉树
 * @date 2022/5/13 下午7:29
 */
public class BinTree {


    public static void main(String[] args) {
        BinTreeNode node1 = new BinTreeNode();
        node1.setData(1);
        BinTreeNode node2 = new BinTreeNode();
        node2.setData(2);
        BinTreeNode node3 = new BinTreeNode();
        node3.setData(3);
        BinTreeNode node4 = new BinTreeNode();
        node4.setData(4);
        BinTreeNode node5 = new BinTreeNode();
        node5.setData(5);

        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setLeft(node4);
        node3.setRight(node5);
//                    1
//                   / \
//                  2   3
//                     / \
//                    4   5
        System.out.println("中序遍历：");
        new BinTree().inOrderTraversal(node1);
        System.out.println("");
        System.out.println("前序遍历：");
        new BinTree().preOrderTraversal(node1);
        System.out.println("");
        System.out.println("后续遍历：");
        new BinTree().postOrderTraversal(node1);


    }

    /**
     * 中序遍历 - 递归
     */
    public void inOrderTraversal(BinTreeNode bt) {
        if (bt == null) {
            return;
        }
        inOrderTraversal(bt.getLeft());
        System.out.print(bt.getData());
        inOrderTraversal(bt.getRight());
    }

    /**
     * 中序遍历 - 堆栈
     * 1、遇到节点先压栈，并去遍历它的左子树
     * 2、当左子树遍历结束后，pop出节点并访问它
     * 3、然后中序遍历该节点的右子树
     *
     * 可以理解为，先循环拿到node的最左节点，然后从栈中pop出node，输出， 然后对node的右节点进行同样的操作
     */
    public void inOrderTraversalNotRecursion(BinTreeNode bt) {
        if (bt == null) {
            return;
        }
        LinkedList<BinTreeNode> vector = new LinkedList<>();
        BinTreeNode curr = bt;
        while (curr != null || !vector.isEmpty()) {  //右节点为空的情况时，也从vector里pop出节点
            while (curr != null) {
                vector.push(curr);
                curr = curr.getLeft();
            }
            if (!vector.isEmpty()) {
                curr = vector.pop();
                System.out.print(curr.getData());
                curr = curr.getRight();
            }
        }
    }


    public void preOrderTraversal(BinTreeNode bt) {
        if (bt == null) {
            return;
        }
        System.out.print(bt.getData());
        preOrderTraversal(bt.getLeft());
        preOrderTraversal(bt.getRight());
    }

    public void postOrderTraversal(BinTreeNode bt) {
        if (bt == null) {
            return;
        }
        postOrderTraversal(bt.getLeft());
        postOrderTraversal(bt.getRight());
        System.out.print(bt.getData());
    }

    /**
     * 后续遍历 - 非递归
     * 借助变量记录前一个输出的node；  第一次碰到时直接push进栈，第二次碰到时先pop出来，用node的右节点与变量相比，若不同则在push进去，并对其right节点进行遍历，若同或者着为空则直接输出，并修改变量的值
     */
    public void postOrderTraversalNotRecursion(BinTreeNode bt) {
    }
}
