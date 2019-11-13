package median;

public class Sol430 {

    public Node flatten(Node head) {
        flattenTail(head);
        return head;
    }

    private Node flattenTail(Node head) {
        if (head == null) return null;
        if (head.child == null) {
            if (head.next == null) return head;
            return flattenTail(head.next);
        }
        Node child = head.child;
        Node next = head.next;
        Node tail = flattenTail(child);

        head.child = null;
        child.prev = head;
        head.next = child;

        tail.next = next;
        if (next != null) {
            next.prev = tail;
            return flattenTail(next);
        }
        return tail;
    }
}

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {
    }

    public Node(int _val, Node _prev, Node _next, Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
}