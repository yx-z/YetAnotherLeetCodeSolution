package median;

public class Sol142 {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
        }
        // slow == fast != null => cycle exists
        ListNode in = slow;
        ListNode out = head;
        while (in != out) {
            in = in.next;
            out = out.next;
        }
        return in;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
