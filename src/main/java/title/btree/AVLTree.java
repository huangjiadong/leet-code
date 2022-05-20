package title.btree;

/**
 * @author jad
 * @Description:
 * @date 2022/5/19 下午5:22
 */
public class AVLTree {

    public int data;
    public AVLTree left;
    public AVLTree right;
    public int height;


    private int getHeight(AVLTree node) {
        return Math.max(node.left.height, node.right.height) + 1;
    }

    /**
     * 左单旋node,返回node.right
     */
    AVLTree singleLeftRotation(AVLTree node) {
        AVLTree b = node.right;
        node.right = b.left;
        b.left = node;
        //先修改node的高度,在改b的高度
        node.height = getHeight(node);
        b.height = getHeight(b);
        return b;
    }

    AVLTree singleRightRotation(AVLTree a) {
        AVLTree b = a.left;
        a.left = b.right;
        b.right = a;
        a.height = getHeight(a);
        b.height = getHeight(b);
        return b;
    }

    AVLTree doubleLeftRightRotation(AVLTree a) {
        /**
         *  a右节点为b， b左节点为c
         * 左右双旋 ： 对b和c进行右旋，然后对a和c进行左旋
         */
        a.right = singleRightRotation(a.right);
        return singleLeftRotation(a);
    }

    AVLTree doubleRightLeftRotation(AVLTree a) {
        a.left = singleLeftRotation(a.left);
        return singleRightRotation(a);
    }

    AVLTree insert(AVLTree root, int value) {
        if (root == null) {
            AVLTree tree = new AVLTree();
            tree.height = 1;
            tree.data = value;
            return tree;
        } else if (value < root.data) {
            //在节点的左子树上
            root.left = insert(root.left, value);
            //插入完后，根据平衡因子进行调整
            if (Math.abs(root.left.height - root.right.height) == 2) {
                if (value < root.left.data) {
                    //右单旋
                    root = singleRightRotation(root);
                } else {
                    //右左双旋
                    root = doubleRightLeftRotation(root);
                }
            }
        } else if (value > root.data) {
            //在节点的右子树上
            root.right = insert(root.right, value);
            if (Math.abs(root.left.height - root.right.height) == 2) {
                if (value > root.right.data) {
                    //左单旋
                    root = singleLeftRotation(root);
                } else {
                    //左右双旋
                    root = doubleLeftRightRotation(root);
                }
            }
        }
        //旋转时候已经更新节点的高度，这里给没进行旋转的情况进行高度更新
        root.height = getHeight(root);
        return root;
    }
}
