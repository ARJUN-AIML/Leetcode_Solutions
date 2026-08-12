class Solution {
    public long sumAndMultiply(int n) {
        long x=0;
        long sum=0;
        long temp=1;

        while (n>0) {
            int digit= n%10;
            if (digit!=0) {
                x= digit*temp+x;
                sum =sum+digit;
                temp=temp*10;
            }
            n= n/10;
        }
        return sum*x;
    }
}