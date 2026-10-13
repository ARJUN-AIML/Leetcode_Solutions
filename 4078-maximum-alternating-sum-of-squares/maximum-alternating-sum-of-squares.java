class Solution {
    public long maxAlternatingSum(int[] nums) {
        long ans = 0;
        int n = nums.length;
        int l = 0, r = n - 1;
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * nums[i];
        }
        Arrays.sort(nums);
        while (l < r) {
            ans += nums[r];
            ans -= nums[l];
            l++;
            r--;
        }
        if (n % 2 != 0) return ans + nums[n / 2];
        return ans;
    }
}