package title.divide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jad
 * @Description:
 * @date 2022/5/30 下午5:01
 */
public class Solution {

    int result = 0;
    int[] arr;
    int[] temp;

    public static void main(String[] args) {
        int[] arr = {3, 6, 7, 9, 1, 5, 7, 11, 4, 7, 85, 71, 96, 43};
        Solution solution = new Solution();
        int[] ints = solution.divideSort(arr);
        Arrays.stream(ints).forEach(System.out::println);
    }

    /**
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
     */
//    public int reversePairs(int[] nums) {
//
//    }

//    void divideMerge(int leftIndex, int rightIndex) {
//        if (leftIndex >= rightIndex) {
//            return;
//        }
//        int mid = (rightIndex + leftIndex) / 2;
//        divideMerge(leftIndex, mid);
//        divideMerge(mid + 1, rightIndex);
//        int l = leftIndex, r = mid + 1;
//        int[] tmp = new int[rightIndex - leftIndex + 1];
//        int insert = 0;
//        while () {
//            if (arr[l] > arr[r]) {
//                tmp[insert] = arr[r];
//                result +=
//            } else if (arr[l] < arr[r]) {
//
//            } else {
//
//            }
//        }
//        int current = leftIndex;
//        for (int i = 0; i < tmp.length; i++) {
//            arr[current] = tmp[0];
//            current++;
//        }
//    }
    public int[] divideSort(int[] nums) {
        temp = new int[nums.length];
        divide(nums, 0, nums.length - 1);
        return nums;
    }

    void divide(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        divide(nums, left, mid);
        divide(nums, mid + 1, right);
        int l = left, r = mid + 1;
        int insert = left;
        while (insert <= right) {
            if (l == mid + 1) {
                temp[insert++] = nums[r++];
            } else if (r > right) {
                temp[insert++] = nums[l++];
            } else if (nums[l] <= nums[r]) {
                temp[insert++] = nums[l++];
            } else if (nums[l] > nums[r]) {
                temp[insert++] = nums[r++];
            }
        }
        for (int i = left; i <= right; i++) {
            nums[i] = temp[i];
        }
    }


    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
     *
     * 三个连续的数的大小情况：  i j k
     * i < k < j
     * k < i < j
     * i < j < k
     */
//    public boolean verifyPostorder(int[] postorder) {
//
//    }

    void postOrderTra() {

    }

    /**
     * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999
     *
     * (0) 1,2,3,4,5,6,7,8,9
     * 10 11 12 13 14 15 .. 99
     * 100
     *
     * 每多1位，多的数字为上一次迭代增加的数字对[0-9]做笛卡尔合并
     */
    public int[] printNumbers(int n) {
        List<Integer> resp = new LinkedList<>();
        List<Integer> pre = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i > 0) {
                resp.add(i);
                pre.add(i);
            }
            list.add(i);
        }
        while (--n > 0) {
            List<Integer> tmp = pre;
            pre = new ArrayList<>();
            for (int i = 0; i < tmp.size(); i++) {
                Integer integer = tmp.get(i);
                for (int j = 0; j < 10; j++) {
                    resp.add(integer * 10 + j);
                    pre.add(integer * 10 + j);
                }
            }
        }
        int[] result = new int[resp.size()];
        for (int i = 0; i < resp.size(); i++) {
            result[i] = resp.get(i);
        }
        return result;
    }

    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
     */
    public double myPow(double x, int n) {
        return 0;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        /**
         * 前序第一个元素为根节点
         * 在中序排序的数组中找到根节点，左边为左子树，右边为右子树
         * 对左、右子树继续进行操作
         */
        if (preorder == null || inorder == null) {
            return null;
        }
        int left = 0, right = preorder.length - 1, begin = 0, end = inorder.length - 1;
        return find(preorder, left, right, inorder, begin, end);
    }

    TreeNode find(int[] preorder, int left, int right, int[] inorder, int begin, int end) {
        TreeNode root = new TreeNode(preorder[left]);
        if (left == right) {
            return root;
        }
        int index = search(preorder, left, right, inorder, begin, end);
        root.left = find(preorder, left + 1, left + index - begin, inorder, begin, index - 1);
        root.right = find(preorder, left + index - begin + 1, right, inorder, index + 1, end);
        return root;
    }


    /**
     * 返回preorder[left]在inorder中的index
     */
    int search(int[] preorder, int left, int right, int[] inorder, int begin, int end) {
        int cur = (begin + end) / 2;
        while (begin < end) {
            if (inorder[cur] == preorder[left]) {
                return cur;
            } else if (inorder[cur] < preorder[left]) {
                begin = cur + 1;
            } else {
                right = right - 1;
            }
            cur = (begin + end) / 2;
        }
        return -1;
    }


    int binSearch(int[] arr, int value) {
        int left = 0, right = arr.length - 1, cur = right / 2;
        if (arr[0] == value) {
            return 0;
        }
        if (arr[arr.length - 1] == value) {
            return arr.length;
        }
        while (cur > left) {
            if (arr[cur] > value) {
                left = cur;
            } else if (arr[cur] < value) {
                right = cur;
            } else {
                return cur;
            }
            cur = (left + right) / 2;
        }
        return -1;
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
