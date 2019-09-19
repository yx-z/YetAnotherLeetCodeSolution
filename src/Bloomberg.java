import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class Bloomberg {

    public static int josephSim(int n, int k) {
        Node head = new Node(1);
        Node pre = head;
        for (int i = 2; i <= n; i++) {
            Node cur = new Node(i);
            pre.nex = cur;
            pre = cur;
        }
        pre.nex = head;

        Node cur = head;
        while (cur.nex != cur) {
            Node tmp = cur;
            for (int i = 1; i <= k - 2; i++) tmp = tmp.nex;
            tmp.nex = tmp.nex.nex;
            cur = tmp.nex;
        }
        return cur.i;
    }

    public static int josephRec(int n, int k) {
        if (n == 1) return 1;
        return (josephRec(n - 1, k) + k - 1) % n + 1;
    }

    public static int findPairMinDiff(int[] arr) {
        int minDiff = Integer.MAX_VALUE;
        Arrays.sort(arr);
        for (int i = 1; i < arr.length; i++) {
            int curDiff = Math.abs(arr[i] - arr[i - 1]);
            minDiff = Math.min(minDiff, curDiff);
        }
        return minDiff;
    }

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

    private static class Node {
        int i;
        Node nex;

        Node(int i) {
            this.i = i;
        }
    }
}
