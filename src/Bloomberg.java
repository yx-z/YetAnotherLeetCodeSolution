import java.util.AbstractMap;
import java.util.Map;
import java.util.Stack;

public class Bloomberg {

    /*
// O(nk)
fun josephSim(n: Int, k: Int): Int {
    class Node(val i: Int, var nex: Node? = null)

    val head = Node(1)
    var pre = head
    for (i in 2..n) {
        val new = Node(i)
        pre.nex = new
        pre = new
    }
    pre.nex = head

    var cur = head
    while (cur.nex != cur) {
        var tmp = cur
        for (i in 1..k - 2) tmp = tmp.nex!!
        tmp.nex = tmp.nex!!.nex
        cur = tmp.nex!!
    }
    return cur.i
}

// O(n)
fun josephRec(n: Int, k: Int): Int =
    if (n == 1) 1 else (josephRec(n - 1, k) + k - 1) % n + 1
*/
    public static String candyCrush(String input) {
        Stack<Map.Entry<Character, Integer>> stack = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            char cur = input.charAt(i);
            if (stack.isEmpty()) {
                stack.push(new AbstractMap.SimpleEntry<>(cur, 1));
            } else {
                if (stack.peek().getKey().equals(cur)) {
                    stack.peek().setValue(stack.peek().getValue() + 1);
                } else {
                    if (stack.peek().getValue() >= 3) {
                        stack.pop();
                    }
                    if (stack.isEmpty() || !stack.peek().getKey().equals(cur)) {
                        stack.push(new AbstractMap.SimpleEntry<>(cur, 1));
                    } else {
                        stack.peek().setValue(stack.peek().getValue() + 1);
                    }
                }
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            Map.Entry<Character, Integer> popped = stack.pop();
            for (int i = 0; i < popped.getValue(); i++)
                res.insert(0, popped.getKey());
        }
        return res.toString();
    }


    public static boolean canSeat(int n, int[][] classroom) {
        for (int r = 0; r < classroom.length; r++) {
            for (int c = 0; c < classroom[r].length; c++) {
                n = seat(r, c, classroom, n);
                if (n == 0) return true;
            }
        }
        return n == 0;
    }

    private static int seat(int r, int c, int[][] room, int rem) {
        if (r < 0 || c < 0 || r == room.length || c == room[r].length ||
                room[r][c] == 2 || room[r][c] == 1 || rem == 0) return rem;
        room[r][c] = 2;
        rem--;
        rem = seat(r + 1, c, room, rem);
        rem = seat(r - 1, c, room, rem);
        rem = seat(r, c + 1, room, rem);
        rem = seat(r, c - 1, room, rem);
        return rem;
    }

    public static void main(String[] args) {
    }
}
