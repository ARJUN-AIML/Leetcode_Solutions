import java.util.*;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        List<Long> list = new ArrayList<>();

        Arrays.sort(coins);

        for (int coin : coins) {
            boolean red = false;

            for (long prev : list) {
                if (coin % prev == 0) {
                    red = true;
                    break;
                }
            }

            if (!red)
                list.add((long) coin);
        }

        long min = list.get(0);
        long high = (long) k * min;

        int n = list.size();
        int totalMasks = 1 << n;

        long[] lcms = new long[totalMasks];
        int[] signs = new int[totalMasks];

        for (int mask = 1; mask < totalMasks; mask++) {
            long currentLCM = 1;
            int bits = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    long coin = list.get(i);
                    long g = gcd(currentLCM, coin);

                    currentLCM /= g;

                    if (currentLCM > high / coin) {
                        currentLCM = high + 1;
                        break;
                    }

                    currentLCM *= coin;
                    bits++;
                }
            }

            lcms[mask] = currentLCM;
            signs[mask] = (bits % 2 == 1) ? 1 : -1;
        }

        while (min < high) {
            long m = min + (high - min) / 2;
            long count = 0;

            for (int mask = 1; mask < totalMasks; mask++) {
                if (lcms[mask] <= m)
                    count += signs[mask] * (m / lcms[mask]);
            }

            if (count >= k)
                high = m;
            else
                min = m + 1;
        }

        return min;
    }

    long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}