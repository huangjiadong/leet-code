package title.btree;

/**
 * @author jad
 * @Description: 堆(这里指最大堆)
 * @date 2022/5/20 下午3:13
 */
public class Heap {

    public int[] data;
    public int size; //堆中的元素个数
    public int capacity;//最大容量

    Heap createHeap(int maxSize) {
        Heap heap = new Heap();
        heap.data = new int[maxSize];
        heap.size = 0;
        heap.capacity = maxSize;
        return heap;
    }

    boolean isFull(Heap heap) {
        return heap.size == heap.capacity;
    }

    boolean insert(Heap heap, int value) {
        /**
         *插入到最后一个元素后面，然后向上比对交换元素
         */
        if (isFull(heap)) {
            return false;
        }
        heap.data[size] = value;
        for (int i = size; (i - 1) / 2 >= 0; i = (i - 1) / 2) {
            if (data[i] > data[(i - 1) / 2]) {
                data[i] = data[(i - 1) / 2];
            } else {
                break;
            }
        }
        size++;
        return true;
    }

    boolean isEmpty(Heap heap) {
        return size == 0;
    }

    int deleteMax(Heap heap) {
        /**
         * 删除根节点，用最后一个节点替换，然后向下交换
         */
        if (isEmpty(heap)) {
            return -1;
        }
        int max = heap.data[0];
        heap.data[0] = heap.data[size - 1];
        heap.data[size - 1] = 0;
        size--;
        int child;
        int tmp;
        for (int i = 0; (i * 2 + 1) < size; ) {
            int leftChild = i * 2 + 1;
            int rightChild = i * 2 + 2;
            child = heap.data[leftChild] > heap.data[rightChild] ? leftChild : rightChild;
            if (heap.data[i] < heap.data[child]) {
                tmp = heap.data[child];
                heap.data[child] = heap.data[i];
                heap.data[i] = tmp;
                i = child;
            } else {
                break;
            }
        }
        return max;
    }

    /**
     * 建造最大堆：
     * 将数据构造成完全二叉树，然后从最后一个元素的父节点开始 构造最大堆，往上构造最大堆
     */
    void buildHeap(Heap heap) {
        for (int i = (size - 1) / 2 - 1; i >= 0; i--) {
            precDown(heap, i);
        }
    }

    void precDown(Heap heap, int p) {
        int parent = heap.data[p];
        int child; // 较大的那个孩子
        int tmp; //用于交换用的临时存储变量
        for (int i = p; (i * 2 + 1) < size; ) {
            int leftChild = i * 2 + 1;
            int rightChild = i * 2 + 2;
            child = heap.data[leftChild] > heap.data[rightChild] ? leftChild : rightChild;
            if (heap.data[i] < heap.data[child]) {
                tmp = heap.data[child];
                heap.data[child] = heap.data[i];
                heap.data[i] = tmp;
                i = child;
            } else {
                break;
            }
        }
    }
}
