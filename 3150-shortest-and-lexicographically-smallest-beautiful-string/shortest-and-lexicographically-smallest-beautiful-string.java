class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int val = Integer.MAX_VALUE;
        String ans = "";
        for(int i=0;i<n;i++){
            int ones = 0;
            String check = "";
            int count1 = 0;
            for(int j=i;j<n;j++){
                char ch = s.charAt(j);
                check+=ch;
                
                if(ch=='1'){
                    ones++;
                }
                if(ones>k){
                    break;
                }
                if(ones==k){
                    if(check.length()<val || check.length()==val && check.compareTo(ans)<0){
                        val = check.length();
                        ans = check;
                    }
                }
            }
        }
        return ans;
    }
}