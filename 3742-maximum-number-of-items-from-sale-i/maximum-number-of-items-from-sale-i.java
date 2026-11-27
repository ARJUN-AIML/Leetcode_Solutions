class Solution {
    int[][][] dp;
    int[] free;
    int f(int idx, int taken, int[][] items, int budget) {
        int n = items.length;
        if(idx >= n || budget <= 0) return 0;
        if(dp[idx][budget][taken] != -1) return dp[idx][budget][taken];
        // Skip current item
        int notPick = f(idx+1, 0, items, budget);
        int pick = 0, price = items[idx][1];
        if (taken == 0) {
            // First purchase -> get free copies as well
            if(price <= budget) pick = 1 + free[idx] + f(idx, 1, items, budget - price);
        } else {
             // Additional copies only add 1 item
            if(price <= budget) pick = 1 + f(idx, 1, items, budget - price);
        }
        return dp[idx][budget][taken] = Math.max(pick, notPick);
    }
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        free = new int[n];
        dp = new int[n][budget+1][2];
        // Precompute free copies gained from first purchase
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(j != i && items[j][0] % items[i][0] == 0) free[i] += 1;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int b = 0; b <= budget; b++) {
                Arrays.fill(dp[i][b], -1);
            }
        }
        return f(0, 0, items, budget);
    }
}