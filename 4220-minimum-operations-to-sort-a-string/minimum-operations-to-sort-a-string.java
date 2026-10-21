import java.util.Arrays;

class Solution {
    public int minOperations(String s) {
        int n = s.length();

        char[] ans = s.toCharArray();
        Arrays.sort(ans);

        if (s.equals(new String(ans))) return 0;
        if (n == 2) return -1;

        char mini = s.charAt(0);
        char maxi = s.charAt(0);

        for (int i = 1; i < n; i++) {
            mini = (char) Math.min(mini, s.charAt(i));
            maxi = (char) Math.max(maxi, s.charAt(i));
        }

        int idx1 = s.indexOf(mini);
        int idx2 = s.lastIndexOf(maxi);

        if (s.charAt(0) == mini || s.charAt(n - 1) == maxi) return 1;
        else if (idx1 != n - 1 || idx2 != 0) return 2;
        else return 3;
    }
}