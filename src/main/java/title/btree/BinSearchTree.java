package title.btree;

/**
 * @author jad
 * @Description: 二叉搜索树
 * @date 2022/5/16 下午2:19
 */
public class BinSearchTree extends BinTree {


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
        BinSearchTree binSearchTree = new BinSearchTree();
        binSearchTree.insert(1, node5);
        binSearchTree.insert(7, node5);
        binSearchTree.insert(6, node5);
        binSearchTree.insert(4, node5);
//        binSearchTree.insert(5, node5);
        binSearchTree.delete(1, node5);
        binSearchTree.inOrderTraversalNotRecursion(node5);
    }

    public BinTreeNode find(Integer data, BinTreeNode root) {
        if (root == null) {
            return null;
        }
        BinTreeNode curr = root;
        while (curr != null) {
            if (curr.getData().equals(data)) {
                return curr;
            } else if (root.getData() < data) {
                curr = curr.getRight();
            } else if (root.getData() > data) {
                curr = curr.getLeft();
            }
        }
        return null;
    }

    public BinTreeNode findMin(BinTreeNode node) {
        if (node == null) {
            return null;
        }
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public BinTreeNode findMax(BinTreeNode node) {
        if (node == null) {
            return null;
        }
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    public BinTreeNode insert(Integer data, BinTreeNode root) {
        BinTreeNode newNode = new BinTreeNode();
        newNode.setData(data);
        if (root == null) {
            return newNode;
        }
        BinTreeNode curr = root;
        while (true) {
            if (curr.getData() < data) {
                if (curr.getRight() == null) {
                    curr.setRight(newNode);
                    break;
                } else {
                    curr = curr.getRight();
                }
            } else if (curr.getData() > data) {
                if (curr.getLeft() == null) {
                    curr.setLeft(newNode);
                    break;
                } else {
                    curr = curr.getLeft();
                }
            }
        }
        return root;
    }

    /**
     * 删除二叉树中的元素
     * 1、删除的为叶子节点，可直接删除
     * 2、删除的节点只有左子树，用左子树中最大值替换节点，然后删除左子树中最大值的节点
     * 3、删除的节点有右子树，用右子树的最小元素替代被删除的元素，删除右子树中的最小元素节点
     */
    public BinTreeNode delete(Integer data, BinTreeNode node) {
        if (node == null) {
            return null;
        } else if (data > node.getData()) {
            node.setRight(delete(data, node.getRight()));
        } else if (data < node.getData()) {
            node.setLeft(delete(data, node.getLeft()));
        } else {
            if (node.getRight() != null) {
                BinTreeNode min = findMin(node.getRight());
                node.setData(min.getData());
                node.setRight(delete(min.getData(), node.getRight()));
            } else if (node.getLeft() != null) {
                BinTreeNode max = findMax(node.getLeft());
                node.setData(max.getData());
                node.setLeft(delete(max.getData(), node.getLeft()));
            } else {
                node = null;
            }
        }
        return node;
    }
}
