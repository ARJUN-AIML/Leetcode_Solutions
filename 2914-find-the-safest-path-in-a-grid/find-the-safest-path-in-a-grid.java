import java.util.*;
class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int n = grid.size();
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1)
            return 0;
        int[][] dist = new int[n][n];
        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);

        Queue<int[]> q = new LinkedList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    dist[r][c] = 0;
                    q.offer(new int[]{r, c});
                }
            }
        }
        while (!q.isEmpty()) {

            int[] curr = q.poll();

            for (int[] d : dirs) {

                int nr = curr[0] + d[0];
                int nc = curr[1] + d[1];

                if (nr >= 0 && nr < n &&
                    nc >= 0 && nc < n &&
                    dist[nr][nc] == Integer.MAX_VALUE) {

                    dist[nr][nc] = dist[curr[0]][curr[1]] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
        int low = 0;
        int high = n;
        int ans = 0;

        while (low <= high) {

            int mid = low + (high - low) / 2;
            if (isValidPath(dist, n, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }
    private boolean isValidPath(int[][] dist, int n, int minSafeness) {
        if (dist[0][0] < minSafeness)
            return false;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            if (curr[0] == n - 1 && curr[1] == n - 1)
                return true;
            for (int[] d : dirs) {
                int nr = curr[0] + d[0];
                int nc = curr[1] + d[1];
                if (nr >= 0 && nr < n &&
                    nc >= 0 && nc < n &&
                    !visited[nr][nc] &&
                    dist[nr][nc] >= minSafeness) {

                    visited[nr][nc] = true;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
        return false;
    }
}