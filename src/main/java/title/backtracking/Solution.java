package title.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lombok.Data;

/**
 * @author jad
 * @Description:
 * @date 2022/5/25 下午5:04
 */
public class Solution {

    List<Integer> list = new ArrayList<>();
    List<TreeNode> nodeList = new ArrayList<>();
    int size;
    TreeNode resp = null;
    int pIndex, qIndex;

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int i = solution.movingCount(1, 2, 1);
//        System.out.println(i);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        solution.modify(list);
        list.forEach(System.out::println);
    }

    void modify(List<Integer> list) {
        list.add(2);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //中序遍历 所有节点的下表 父节点为自己点 (n-1)/2  找出最近公共祖先节点
        inOrderTravel3(root);
        return nodeList.get(findIndex(pIndex, qIndex));
    }

    int findIndex(int a, int b) {
        while (a >= 0 && b >= 0) {
            if (a > b) {
                a = (a - 1) / 2;
            } else if (a == b) {
                return a;
            } else {
                b = (b - 1) / 2;
            }
        }
        return 0;
    }

    void inOrderTravel3(TreeNode root) {
        if (root == null) {
            nodeList.add(null);
            return;
        }
        inOrderTravel3(root.left);
        nodeList.add(root);
        if (pIndex == root.val) {
            pIndex = list.size() - 1;
        }
        if (qIndex == root.val) {
            qIndex = list.size() - 1;
        }
        inOrderTravel3(root.right);
    }


    public boolean isBalanced(TreeNode root) {
        int resp = isBalancedDfs(root);
        return resp != -1;
    }

    int isBalancedDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int leftRes = isBalancedDfs(root.left);
        int rightRes = isBalancedDfs(root.right);
        if (leftRes == -1 || rightRes == -1) {
            return -1;
        }
        if (Math.abs(leftRes - rightRes) < 2) {
            return Math.max(leftRes, rightRes) + 1;
        } else {
            return -1;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public int kthLargest(TreeNode root, int k) {
        size = k;
        //中序遍历完，取倒数第K个数据
//        inOrderTravel(root);
//        return list.get(list.size() - k);

        rightFirst(root);
        return list.get(size - 1);
    }

    void rightFirst(TreeNode root) {
        if (root == null || list.size() == size) {
            return;
        }
        rightFirst(root.right);
        list.add(root.val);
        rightFirst(root.left);
    }

    void inOrderTravel(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTravel(root.left);
        list.add(root.val);
        inOrderTravel(root.right);
    }

    //字符串的排列可能
//    public String[] permutation(String s) {
//        char[] chars = s.toCharArray();
//
//    }


    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        dfs(root, target, new ArrayList<Integer>(), result, 0);
        return result;
    }

    void dfs(TreeNode node, int target, List<Integer> cur, List<List<Integer>> result, int sum) {
        sum += node.val;
        /*if (sum > target) {
            //大于目标值了
            return;
        } else */
        if (sum == target) {
            // 1 叶子结点 ok 2 非叶子节点  继续
            if (isLeftNode(node)) {
                cur.add(node.val);
                List<Integer> tmp = new ArrayList<>(cur);
                result.add(tmp);
                cur.remove(cur.size() - 1);
                return;
            } else {
                cur.add(node.val);
                if (node.left != null) {
                    dfs(node.left, target, cur, result, sum);
                }
                if (node.right != null) {
                    dfs(node.right, target, cur, result, sum);
                }
                cur.remove(cur.size() - 1);
            }
        } else {
            //1 叶子节点 false 2 非叶子节点 递推
            if (isLeftNode(node)) {
                return;
            } else {
                cur.add(node.val);
                if (node.left != null) {
                    dfs(node.left, target, cur, result, sum);
                }
                if (node.right != null) {
                    dfs(node.right, target, cur, result, sum);
                }
                cur.remove(cur.size() - 1);
            }
        }

    }

    boolean isLeftNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> res = new LinkedList<>();
        while (!queue.isEmpty()) {
            List<Integer> one = new LinkedList<>();
            Queue<TreeNode> tmp = new LinkedList<>();
            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                if (poll == null) {
                    continue;
                }
                one.add(poll.val);
                tmp.offer(poll);
            }
            if (!one.isEmpty()) {
                res.add(one);
            }
            while (!tmp.isEmpty()) {
                TreeNode poll = tmp.poll();
                queue.offer(poll.left);
                queue.offer(poll.right);
            }
        }
        return res;
    }

    public int[] levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> res = new LinkedList<>();
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll == null) {
                continue;
            }
            res.add(poll.val);
            queue.offer(poll.left);
            queue.offer(poll.right);
        }
        int[] resp = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resp[i] = res.get(i);
        }
        return resp;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    boolean isMirror(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.val != b.val) {
            return false;
        }
        return isMirror(a.left, b.right) && isMirror(a.right, b.left);
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return traversalTree(A, B);
    }

    boolean traversalTree(TreeNode A, TreeNode B) {
        if (nodeMatch(A, B)) {
            return true;
        }
        boolean res = false;
        if (A.left != null) {
            res = traversalTree(A.left, B);
        }
        if (A.right != null) {
            res = res || traversalTree(A.right, B);
        }
        return res;
    }

    boolean nodeMatch(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null) {
            return false;
        }
        if (A.val != B.val) {
            return false;
        }
        return nodeMatch(A.left, B.left) && nodeMatch(A.right, B.right);
    }


    public int movingCount(int m, int n, int k) {
        int[][] board = new int[m][n];
        return dfs(board, 0, 0, k);
    }

    int dfs(int[][] board, int i, int j, int k) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 1) {
            return 0;
        }
        if ((i % 10 + i / 10 % 10 + i / 100 + j % 10 + j / 10 % 10 + j / 100) > k) {
            return 0;
        }
        board[i][j] = 1;
        int res = 1;
        //上下左右
        res += dfs(board, i - 1, j, k);
        res += dfs(board, i + 1, j, k);
        res += dfs(board, i, j - 1, k);
        res += dfs(board, i, j + 1, k);
        return res;
    }

    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(board, words, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        //可往四周移动，所以判断边界情况; 或者当前位置值不等于要匹配的字符
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word[k]) {
            return false;
        }
        //下面是当前字符匹配上后的逻辑
        // word的最后一个字符也匹配上了，则返回true
        if (k == word.length - 1) {
            return true;
        }
        //需要将 当前位置字符替换为空，避免重复匹配
        board[i][j] = '\0';
        //向下递推
        boolean res = dfs(board, word, i + 1, j, k + 1) ||
                dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) ||
                dfs(board, word, i, j - 1, k + 1);
        //匹配完后需要将当前位置置回，供后面递推分支使用
        board[i][j] = word[k];
        return res;
    }


    @Data
    class Position {

        public int x;
        public int y;

    }

    public class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
