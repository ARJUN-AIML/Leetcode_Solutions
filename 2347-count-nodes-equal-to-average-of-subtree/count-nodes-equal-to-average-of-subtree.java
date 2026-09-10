class Solution {
    int ans = 0;

    int[] get(TreeNode root) {
        if (root == null) return new int[]{0, 0};

        int[] left = get(root.left);
        int[] right = get(root.right);

        return new int[]{
            root.val + left[0] + right[0],
            1 + left[1] + right[1]
        };
    }

    public int averageOfSubtree(TreeNode root) {
        if (root == null) return 0;

        int[] left = get(root.left);
        int[] right = get(root.right);

        int sum = root.val + left[0] + right[0];
        int cnt = 1 + left[1] + right[1];

        if (root.val == sum / cnt)
            ans++;

        averageOfSubtree(root.left);
        averageOfSubtree(root.right);

        return ans;
    }
}