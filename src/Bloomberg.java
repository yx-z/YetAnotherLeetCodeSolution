import java.util.*;
import java.util.stream.Collectors;

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

    public static Set<String> find(Set<String> dict, String sub) {
        return new Trie(dict).containSubstr(sub);
    }

    public static int[] swap(int[] a1, int[] a2) {
        int s1 = Arrays.stream(a1).sum();
        int s2 = Arrays.stream(a2).sum();
        if (s1 < s2) {
            int[] tmpArr = a2;
            a1 = a2;
            a2 = tmpArr;
            int tmpSum = s1;
            s1 = s2;
            s2 = tmpSum;
        }
        // now s1 = sum(a1) >= s2 = sum(a2)
        int d = s1 - s2;
        if (d == 0) {
            // do nothing if valid or find the same element in both arrays
            // O(n) using hashset seen
            return null; // mark that we have done nothing
        }
        // find an element i in a1 and another element j in a2 :
        // s1 - i + j = s2 + i - j => i = d / 2 + j, for even d
        if (d % 2 == 1) return new int[]{-1, -1}; // mark it impossible
        Set<Integer> seenA2 = Arrays.stream(a2).boxed()
                .collect(Collectors.toSet());
        for (int i : a1) {
            if (seenA2.contains(i - d / 2)) return new int[]{i, i - d / 2};
        }
        return new int[]{-1, -1};
    }

    public static int countPeople(int[] enter, int[] exit, int time) {
        int n = enter.length;
        if (n == 0 || n != exit.length) return 0;
        // <critical point, people count>
        TreeMap<Integer, Integer> freq = new TreeMap<>();
        int count = 0;
        freq.put(0, count);
        int enterIdx = 0;
        int exitIdx = 0;
        while (enterIdx < n && exitIdx < n) {
            if (enter[enterIdx] < exit[exitIdx]) {
                count++;
                freq.put(enter[enterIdx], count);
                enterIdx++;
            } else {
                count--;
                freq.put(exit[exitIdx], count);
                exitIdx++;
            }
        }
        while (exitIdx < n) {
            count--;
            freq.put(exit[exitIdx], count);
            exitIdx++;
        }
        return freq.floorEntry(time).getValue();
    }

    public static int findUniqueMissing(int[] arr) {
        // arr has n unique numbers in 1..n+1, find the unique missing number
        int n = arr.length;
        int res = 1;
        for (int i = 2; i <= n + 1; i++) res ^= i;
        for (int i : arr) res ^= i;
        return res;
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

    private static class Trie {
        private Node root = new Node('\0');
        private Map<Node, Set<String>> toStr = new HashMap<>();

        Trie(Set<String> dict) {
            dict.forEach(s -> {
                List<Node> pres = new ArrayList<>();
                pres.add(root);
                for (char c : s.toCharArray()) {
                    Node cur;
                    if (root.nex.containsKey(c)) {
                        cur = root.nex.get(c);
                    } else {
                        cur = new Node(c);
                        root.nex.put(c, cur);
                        toStr.put(cur, new HashSet<>());
                    }
                    toStr.get(cur).add(s);
                    pres.forEach(pre -> pre.nex.put(c, cur));
                    pres.add(cur);
                }
            });
        }

        public Set<String> containSubstr(String sub) {
            if (sub.isEmpty() || !root.nex.containsKey(sub.charAt(0)))
                return Collections.emptySet();
            Node cur = root.nex.get(sub.charAt(0));
            Set<String> res = new HashSet<>(toStr.get(cur));
            for (int i = 1; i < sub.length(); i++) {
                char c = sub.charAt(i);
                if (!cur.nex.containsKey(c)) return Collections.emptySet();
                cur = cur.nex.get(c);
                Node finalCur = cur;
                res.removeIf(s -> !toStr.get(finalCur).contains(s));
            }
            return res;
        }

        private static class Node {
            char c;
            Map<Character, Node> nex = new HashMap<>();

            Node(char c) {
                this.c = c;
            }

            @Override
            public String toString() {
                return String.valueOf(c);
            }
        }
    }
}
