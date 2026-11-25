class Solution {
    private long hash[]; 
    private int BASE = 1_000_003; 
    private long mod = 1_000_000_007; 
    private int n; 
    private long power[]; 

    private void preprocessPower() {
        power[0] = 1; 
        for(int i = 1; i <= n; i++) {
            power[i] = (power[i-1] * BASE) % mod; 
        }
    }
    private void buildHash(int nums[]) {
        hash[0] = 0; 
        for(int i = 0; i < n; i++) {
            hash[i + 1] = ((hash[i] * BASE) % mod + nums[i]) % mod; 
        }
    }

    private boolean isUnique(int nums[], int len) {
        if(len == 0) return false; 
        HashMap<Long, Integer> mp = new HashMap<>(); 
        for(int i = 0; i + len <= n; i++) {
            long h = (hash[i + len] - (hash[i] * power[len]) % mod + mod) % mod; 
            mp.put(h, mp.getOrDefault(h, 0) + 1); 
        }

        // System.out.println("len = " + len);
        // System.out.println(mp);
        for(Integer cnt: mp.values()) if(cnt == 1) return true; 
        return false; 
    }
    public int smallestUniqueSubarray(int[] nums) {
        // we can use binary search
        // for each length we can check if a subarray is identical to any other subarray 
        // can use hash function to caluclate hash, if hash equal then check if array is same or not.
        n = nums.length; 
        int lo = 1, hi = n;
        hash = new long[n + 1];
        power = new long[n + 1]; 
        preprocessPower();  
        buildHash(nums); 
        int ans = n;  
        while(lo <= hi) {
            int mid = (lo + hi) >> 1; 
            if(isUnique(nums, mid)) {
                ans = mid; 
                hi = mid - 1; 
            } else lo = mid + 1; 
        } 
        return ans; 
    }
}