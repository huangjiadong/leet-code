package title.backtracking;

/**
 * @author jad
 * @Description:
 * @date 2022/5/20 下午8:16
 */
public class Test {

    public static void main(String[] args) {
        Test.work(3, 3, "");
    }

    public static void work(int left, int right, String s) {
        if (left == 0 && right == 0) {
            System.out.println(s);
        }
        if (left > 0) {
            work(left - 1, right, s + "(");
        }
        if (right > left) {
            work(left, right - 1, s + ")");
        }
    }
}
