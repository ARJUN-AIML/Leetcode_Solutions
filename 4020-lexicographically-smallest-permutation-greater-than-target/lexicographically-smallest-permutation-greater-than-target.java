class Solution {

    public String lexGreaterPermutation(String s, String target) {

        int[] count = new int[26];

        // Count characters in s
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        int pos = -1;
        char bigger = ' ';

        // Find the position where we can make the answer bigger
        for (int i = 0; i < s.length(); i++) {

            char need = target.charAt(i);

            // Find the smallest character greater than need
            for (char ch = (char) (need + 1); ch <= 'z'; ch++) {

                if (count[ch - 'a'] > 0) {
                    pos = i;
                    bigger = ch;
                    break;
                }
            }

            // Same character is not available
            if (count[need - 'a'] == 0) {
                break;
            }

            // Use the same character as target
            count[need - 'a']--;
        }

        // No greater permutation is possible
        if (pos == -1) {
            return "";
        }

        // Count characters again
        count = new int[26];

        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        StringBuilder ans = new StringBuilder();

        // Add the same prefix as target
        for (int i = 0; i < pos; i++) {
            char ch = target.charAt(i);

            ans.append(ch);
            count[ch - 'a']--;
        }

        // Add the bigger character
        ans.append(bigger);
        count[bigger - 'a']--;

        // Add remaining characters in sorted order
        for (char ch = 'a'; ch <= 'z'; ch++) {

            while (count[ch - 'a'] > 0) {
                ans.append(ch);
                count[ch - 'a']--;
            }
        }

        return ans.toString();
    }
}