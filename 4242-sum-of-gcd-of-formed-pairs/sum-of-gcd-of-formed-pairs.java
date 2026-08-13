class Solution {
    public long gcdSum(int[] nums) {
        int n = nums.length;
        long[] prefixGcd = new long[n];

        long maxi = 0;

        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, (long) nums[i]);
            prefixGcd[i] = gcd((long) nums[i], maxi);
        }

        Arrays.sort(prefixGcd);

        long ans = 0;
        int l = 0, r = n - 1;

        while (l < r) {
            ans += gcd(prefixGcd[l], prefixGcd[r]);
            l++;
            r--;
        }

        return ans;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}