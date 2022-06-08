package title;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jad
 * @Description: Solution
 * @date 2022/6/6 下午8:33
 */
public class Sort {

    public static void main(String[] args) {
        int[] ints = {9, 11, 3, 4, 5, 1, 24, 51, 5, 61, 123};
//        new Sort().quickSort(ints);
//        Arrays.stream(ints).forEach(System.out::println);
//        System.out.println(-1 % 2);
        int[] arr = new int[]{2, 8, 7, 3, 6, 12, 25, 94, 76, 21, 110, 111, 112, 113};
        new Sort().heapSort(arr);
        Arrays.stream(arr).forEach(System.out::println);

    }


    public int[] dacSort(int[] arr) {

    }

    /**
     * 堆排序
     * 每次从最小堆中拿最小的元素
     * 相比简单排序，空间为O(n),时间为O(nlogn)
     */
    public int[] heapSort(int[] arr) {
        MinHeap minHeap = new MinHeap(arr);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = minHeap.get();
        }
        return arr;
    }

    /**
     * 插入排序
     * 每次将元素i与前面的数据进行比对，找到元素m < i，每次比较时进行swap，直到找到m
     */
    public int[] insertSort(int[] arr) {
        if (arr == null) {
            return arr;
        }
        for (int i = 0; i < arr.length; i++) {
            int latter = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                int front = arr[j];
                if (front > latter) {
                    arr[j + 1] = arr[j];
                } else {
                    arr[j + 1] = latter;
                    break;
                }
            }
        }
        return arr;
    }

    /**
     * 冒泡排序
     * 比较当前位置的元素与后面元素的值的大小，将值较大的元素放在后面
     *
     * 第一次优化
     * -- 当一次循环完后，一次元素交换都没有发生，则表示数组已经有序
     * 第二次优化
     * -- 当一次循环完后，发现元素在position-1之前的某个位置m开始就没有再交换了，则m到position之间的元素已经有序，position可以提前改为m
     */
    public int[] bubbleSort(int[] arr) {
        if (arr == null) {
            return arr;
        }
        int m = 0;
        int position = arr.length - 1;
        while (position > 0) {
            for (int i = 0; i < position; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                } else {
                    m = i;
                }
            }
            position = m;
            m = position - 1;
        }
        return arr;
    }

    public int[] quickSort(int[] arr) {
        quickSortDfs(arr, 0, arr.length - 1);
        return arr;
    }

    public void quickSortDfs(int[] arr, int left, int right) {
        if (right - left < 2) {
            return;
        }
        /**
         * 找到中位数
         * 划分子数据
         * 递归
         */
        int median = median(arr, left, right);
        int i = left, j = right - 1;
        for (; ; ) {
            while (arr[++i] < arr[median]) {
            }
            while (arr[--j] > arr[median]) {
            }
            if (i > j) {
                break;
            }
            swap(arr, i, j);
        }
        swap(arr, i, median);
        //进入递归
        quickSortDfs(arr, left, i - 1);
        quickSortDfs(arr, i + 1, right);
    }

    /**
     * 将三个位置的元素交换位置,left <= median <= right
     * 并把中位数元素换到right-1
     */
    private int median(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        if (arr[mid] > arr[right]) {
            swap(arr, mid, right);
        }
        swap(arr, mid, right - 1);
        return right - 1;
    }

    private void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    /**
     * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        return null;
    }

    //    在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
    public int findRepeatNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (map.containsKey(num)) {
                return num;
            }
            map.put(num, i);
        }
        return -1;
    }

    public String reverseWords(String s) {
        if (s.length() == 0) {
            return "";
        }
        char[] chars = s.toCharArray();
        int left = -1, right = -1;
        String result = "";
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar != ' ') {
                if (left == -1) {
                    left = i;
                    right = left;
                } else {
                    right = i;
                }
            } else {
                if (left != -1) {
                    result = result.equals("") ? new String(Arrays.copyOfRange(chars, left, right + 1))
                            : (new String(Arrays.copyOfRange(chars, left, right + 1)) + " " + result);
                    left = -1;
                    right = -1;
                }
            }

        }
        return result;
    }

    class MinHeap {

        public int[] num;

        public int size;

        private int captacity;

        public MinHeap(int[] arr) {
            buildHeap(arr);
            size = arr.length;
            num = Arrays.copyOf(arr, size);
        }

        public int get() {
            int min = num[0];
            delete(num);
            return min;
        }

        private void delete(int[] num) {
            /**
             * 删除根节点后，把最后一个节点放到根节点，然后对根节点往下进行较少值的交换
             */
            size--;
            num[0] = num[size];
            int i = 0;
            while (i * 2 + 1 < size) {
                int left = 2 * i + 1;
                int less = left;
                int right = 2 * i + 2;
                if (right < size) {
                    less = num[left] < num[right] ? left : right;
                }
                if (num[i] > num[less]) {
                    swap(num, less, i);
                }
                i = less;
            }
        }

        /**
         * 构建堆，然后从最后一个结点进行调整
         */
        public void buildHeap(int[] arr) {
            /**
             * 从根节点开始处理，递归处理根节点的左、右子树
             * 递推参数：int[] arr, 结点位置 i
             * 递归终止条件：当前节点无子树
             * 递推过程：将根节点与其两个子节点中较小的节点交换位置
             * 递归返回：void
             */
            buildChildHeap(arr, 0);
        }

        void buildChildHeap(int[] arr, int i) {
            if (2 * i + 1 >= arr.length) {
                return;
            }
            int left = 2 * i + 1;
            buildChildHeap(arr, 2 * i + 1);
            int less = left;
            int right = 2 * i + 2;
            if (right < arr.length) {
                buildChildHeap(arr, 2 * i + 2);
                less = arr[left] < arr[right] ? left : right;
            }
            if (arr[i] > arr[less]) {
                swap(arr, i, less);
            }
            return;
        }


    }
}
