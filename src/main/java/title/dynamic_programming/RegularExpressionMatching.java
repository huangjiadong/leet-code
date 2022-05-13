package title.dynamic_programming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jad
 * @Description: 正则表达式匹配
 * @date 2022/5/7 下午2:10
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        /**
         * "aasdfasdfasdfasdfas"
         * "aasdf.*asdf.*asdf.*asdf.*s"
         */
//        new RegularExpressionMatching().isMatch("ab", "a.*b");

//        int i = new RegularExpressionMatching().translateNum(18822);
//        System.out.println(i);

//        new RegularExpressionMatching().dicesProbability(3);
        new RegularExpressionMatching().maxProfit(new int[]{7, 1, 5, 3, 6, 4});
    }

    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length == 1) {
            return 0;
        }
        int result = 0;
        int tmp = 0;
        for (int i = 1; i < prices.length; i++) {
            if (tmp == 0) {
                tmp = Math.max(prices[i] - prices[i - 1], 0);
            } else {
                tmp = Math.max(tmp + prices[i] - prices[i - 1], 0);
            }
            result = Math.max(result, tmp);
        }
        return result;
    }

    public double[] dicesProbability(int n) {
        double[] a = new double[6];
        Arrays.fill(a, 1.0 / 6);
        double[] tmp = a;
        for (int i = 2; i <= n; i++) {
            double[] resp = new double[5 * i + 1];
            for (int j = 0; j < tmp.length; j++) {
                for (int k = 1; k <= 6; k++) {
                    resp[j + k - 1] += tmp[j] * 1 / 6;
                }
            }
            tmp = resp;
        }
        return tmp;
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> position = new HashMap<>();
        int resp = 0, tmp = 0, length = s.length();
        for (int i = 0; i < length; i++) {
            Integer j = position.getOrDefault(s.charAt(i), -1);
            position.put(s.charAt(i), i);
            if (i - j > tmp) {
                tmp++;
            } else {
                tmp = i - j;
            }
            resp = Math.max(resp, tmp);
        }
        return resp;
    }

    public int maxValue(int[][] grid) {
        int m = grid[0].length;
        int n = grid.length;
        if (m == 1 && n == 1) {
            return grid[0][0];
        }
        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];
        //初始化第0排
        for (int i = 1; i < grid[0].length; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (j < 1) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    public int translateNum(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        if (chars.length == 1) {
            return 1;
        }
        int[] dp = new int[chars.length];
        dp[0] = 1;
        int m = (chars[0] - 48) * 10 + chars[1] - 48;
        if (10 <= m && m <= 25) {
            dp[1] = 2;
        } else {
            dp[1] = 1;
        }
        for (int i = 2; i < chars.length; i++) {
            int j = (chars[i - 1] - 48) * 10 + chars[i] - 48;
            if (10 <= j && j <= 25) {
                dp[i] = dp[i - 2] + dp[i - 1];
            } else {
                dp[i] = dp[i - 1];
            }

        }
        return dp[chars.length - 1];
    }

    public boolean isMatch(String s, String p) {
        int m = s.length() + 1;
        int n = p.length() + 1;
        boolean[][] dp = new boolean[m][n];
        //空串与空规则匹配
        dp[0][0] = true;
        //初始化首行, 当dp[0][j]的j为奇数时，其值肯定为false；只有j为偶数位时并且为*，其值才会为true
        for (int j = 2; j < n; j += 2) {
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        }
        //状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) { // 第一列，除了第一行为true，其他都为false，因为不为空的s是不满足规则空p的。 故j从1开始循环
                if (p.charAt(j - 1) == '*') {
                    /**
                     * p[j-1] == *
                     *  p[j-2]出现0次  dp[i][j-2]  注意不需要判断s[i-1] != p[j-2]，s[i-1]字符算在p[j-2]的规则匹配中
                     *        出现1次 s[i-1] == p[j-2] && dp[i-1][j-2]
                     *        出现多次 s[i-1] == p[j-2] && dp[i-1][j]
                     */
                    if (/*!match(s.charAt(i - 1), p.charAt(j - 2)) && */dp[i][j - 2]) {
                        dp[i][j] = true;
                    } else if (match(s.charAt(i - 1), p.charAt(j - 2)) && dp[i - 1][j - 2]) {
                        dp[i][j] = true;
                    } else if (match(s.charAt(i - 1), p.charAt(j - 2)) && dp[i - 1][j]) {
                        dp[i][j] = true;
                    }
                } else {
                    //如果p[j-1]不为*，为字母，则需要p[j-1] == s[i-1]  && dp[i-1][j-1] , 为 . ，则 dp[i][j] = d[i-1][j-1]
                    if (dp[i - 1][j - 1] && s.charAt(i - 1) == p.charAt(j - 1)) {
                        dp[i][j] = true;
                    }
                    if (dp[i - 1][j - 1] && '.' == p.charAt(j - 1)) {
                        dp[i][j] = true;
                    }

                }
            }
        }

        for (int i = 0; i < dp.length; i++) {
            boolean[] booleans = dp[i];
            for (int j = 0; j < booleans.length; j++) {
                boolean aBoolean = booleans[j];
                System.out.print(aBoolean);
                System.out.print(" ");

            }
            System.out.println();
        }
        return dp[m - 1][n - 1];
    }

    boolean match(char s, char p) {
        if (p == '.') {
            return true;
        }
        return s == p;
    }
}
