package title.dynamic_programming;

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
        new RegularExpressionMatching().isMatch("ab", "a.*b");
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
