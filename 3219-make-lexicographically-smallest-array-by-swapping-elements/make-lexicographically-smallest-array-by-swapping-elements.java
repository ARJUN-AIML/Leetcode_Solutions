class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        
        Map<Integer, Integer> numToGroup = new HashMap<>();
        Map<Integer, Deque<Integer>> groupToList = new HashMap<>();
        
        int currentGroup = 0;
        numToGroup.put(sortedNums[0], currentGroup);
        groupToList.put(currentGroup, new LinkedList<>());
        groupToList.get(currentGroup).add(sortedNums[0]);
        
        for (int i = 1; i < n; i++) {
            if (sortedNums[i] - sortedNums[i - 1] > limit) {
                currentGroup++;
                groupToList.put(currentGroup, new LinkedList<>());
            }
            numToGroup.put(sortedNums[i], currentGroup);
            groupToList.get(currentGroup).add(sortedNums[i]);
        }
        
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int group = numToGroup.get(nums[i]);
            result[i] = groupToList.get(group).pollFirst();
        }
        
        return result;
    }
}