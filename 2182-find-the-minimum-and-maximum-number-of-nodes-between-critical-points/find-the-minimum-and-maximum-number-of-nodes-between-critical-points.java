class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] res = {-1, -1};

        if(head == null || head.next == null || head.next.next == null){
            return res;
        }  

        ListNode prev = head;
        ListNode curr = head.next;

        int pos = 1;
        int firstCritical = -1;
        int prevCritical = -1;
        int minDistance = Integer.MAX_VALUE;

        while(curr.next != null){
            ListNode next = curr.next;

            boolean isCritical = (curr.val > prev.val && curr.val > next.val) ||
            (curr.val < prev.val && curr.val < next.val);

                if(isCritical){
                    if(firstCritical == -1){
                        firstCritical = pos;
                    } else {
                        minDistance = Math.min(
                            minDistance, pos - prevCritical
                        );
                    }

                    prevCritical = pos;
                }

                prev = curr;
                curr = next;
                pos++;
            }

            if(firstCritical == -1 || firstCritical == prevCritical){
                return res;
            }

            int maxDistance = prevCritical - firstCritical;

            return new int[]{
                minDistance, maxDistance
            };
        }
    }