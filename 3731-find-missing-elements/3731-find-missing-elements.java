class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        Arrays.sort(nums);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 1; i++) {
            int gap = nums[i + 1] - nums[i];
            if (gap > 1) {
                for (int x = nums[i] + 1; x < nums[i + 1]; x++) {
                    result.add(x);
                }
            }
        }
        return result;
    }
}