package easy;

import java.util.HashMap;
import java.util.Map;

public class Sol138 {

    public Node copyRandomList(Node head) {
        if (head == null) return null;

        Node newDummy = new Node();

        Map<Node, Node> copy = new HashMap<>();
        Node newCurr = newDummy;
        Node curr = head;
        while (curr != null) {
            newCurr.next = new Node();
            newCurr = newCurr.next;
            newCurr.val = curr.val;
            copy.put(curr, newCurr);
            curr = curr.next;
        }

        curr = head;
        newCurr = newDummy;
        while (curr != null) {
            newCurr.next.random = copy.get(curr.random);
            newCurr = newCurr.next;
            curr = curr.next;
        }

        return newDummy.next;
    }

    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {
        }

        public Node(int _val, Node _next, Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }
}
