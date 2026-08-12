class Solution {
    function maxSubarrayLength($nums, $k) {
    // Left pointer for the sliding window
    $left = 0;
    // Hash table to keep track of the counts of each element within the window
    $hash = [];
    $count = 0;
    for ($right = 0; $right < count($nums); $right++) {
        // Check if the current element is already in the hash table
        if (isset($hash[$nums[$right]])) {
            // Increment the count of the current element in the hash table
            $hash[$nums[$right]]++;
            // If the count of the current element exceeds $k, shrink the window from the left
            while ($hash[$nums[$right]] > $k) {
                // Decrease the count of the element at the left pointer in the hash table
                $hash[$nums[$left]]--;
                // Move the left pointer to the right, effectively shrinking the window
                $left++;
            }
        } else {
            // If the current element is not in the hash table, add it with a count of 1
            $hash[$nums[$right]] = 1;
        }
        // Update the maximum count with the larger value between the current count and the length of the current window
        $count = max($count, $right - $left + 1);
    }
    return $count;
    }
}