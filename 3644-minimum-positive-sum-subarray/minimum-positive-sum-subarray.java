class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int ans = Integer.MAX_VALUE;
        int n = nums.size();

        if (n < l) {
            return -1;
        }

        int wSum = 0;
        for (int i = 0; i < l; i++) {
            wSum += nums.get(i);
        }

        if (wSum > 0) {
            ans = Math.min(ans, wSum);
        }

        for (int i = l; i < n; i++) {
            int sum = wSum;
            int lIndex = Math.min(n, i + r - l);

            for (int j = i; j < lIndex; j++) {
                if (sum > 0) {
                    ans = Math.min(ans, sum);
                }
                sum += nums.get(j);
            }

            if (sum > 0) {
                ans = Math.min(ans, sum);
            }

            wSum += nums.get(i);
            wSum -= nums.get(i - l);
        }

        if (wSum > 0) {
            ans = Math.min(ans, wSum);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}