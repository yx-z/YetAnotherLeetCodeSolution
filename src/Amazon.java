import java.util.*;

public class Amazon {
    private static int time;
    private static List<Integer>[] graph;
    private static int[] low; // low[u] = lowest label u can reach
    private static int[] dis; // dis[u] = clock time when u is discovered

    public static Map<String, List<String>> favoriteGenre(
            Map<String, List<String>> userSongs, // <user, [songs]>
            Map<String, List<String>> genreSongs // <genre, [songs]>
    ) {
        if (userSongs == null || genreSongs == null) {
            return Collections.emptyMap();
        }

        // <song, genre>
        Map<String, String> songGenre = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : genreSongs.entrySet()) {
            String genre = entry.getKey();
            List<String> songs = entry.getValue();
            for (String song : songs) {
                songGenre.put(song, genre);
            }
        }

        // <user, [genres]>
        Map<String, List<String>> userGenres = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : userSongs.entrySet()) {
            String user = entry.getKey();
            List<String> songs = entry.getValue();
            userGenres.put(user, new ArrayList<>());

            // <genre, count>
            Map<String, Integer> count = new HashMap<>();
            int maxFreq = 0;

            for (String song : songs) {
                if (songGenre.containsKey(song)) {
                    String genre = songGenre.get(song);
                    count.put(genre, count.getOrDefault(genre, 0) + 1);
                    maxFreq = Math.max(maxFreq, count.get(genre));
                }
            }

            for (Map.Entry<String, Integer> genreFreq : count.entrySet()) {
                String genre = genreFreq.getKey();
                int freq = genreFreq.getValue();
                if (freq == maxFreq) {
                    userGenres.get(user).add(genre);
                }
            }
        }
        return userGenres;
    }

    public static List<List<Integer>> criticalConnections(
            int n, List<List<Integer>> connections
    ) {
        // build graph
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> connection : connections) {
            int u = connection.get(0);
            int v = connection.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }

        // dfs
        low = new int[n + 1];
        dis = new int[n + 1];
        time = 0;
        for (int i = 0; i <= n; i++) {
            if (dis[i] == 0) {
                dfs(i, i);
            }
        }

        // find cut edges
        List<List<Integer>> res = new ArrayList<>();
        for (List<Integer> connection : connections) {
            int u = connection.get(0);
            int v = connection.get(1);
            // v cannot reach lower than u => no back edge or cycle => cut edge
            if (dis[u] < low[v] || dis[v] < low[u]) {
                res.add(connection);
            }
        }
        return res;
    }

    private static void dfs(int u, int p) {
        time++;
        dis[u] = time;
        low[u] = time;
        for (int v : graph[u]) {
            if (v == p) {
                continue;
            }
            if (dis[v] == 0) {
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);
            } else {
                low[u] = Math.min(low[u], dis[v]);
            }
        }
    }

    public static int twoSum(int[] nums, int target) {
        // <num, counted in result>
        Map<Integer, Boolean> seen = new HashMap<>();
        int res = 0;
        for (int num : nums) {
            int counterpart = target - num;
            if (seen.containsKey(counterpart)) {
                if (!seen.get(counterpart)) {
                    res++;
                    seen.put(counterpart, true);
                }
                seen.put(num, true);
            } else {
                seen.put(num, false);
            }
        }
        return res;
    }

    public static RandomNode copyRandomList(RandomNode head) {
        Map<RandomNode, RandomNode> srcToCopy = new HashMap<>();
        RandomNode curr = head;
        while (curr != null) {
            RandomNode copy = new RandomNode();
            copy.val = curr.val;
            srcToCopy.put(curr, copy);
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            RandomNode copy = srcToCopy.get(curr);
            copy.next = srcToCopy.get(curr.next);
            copy.random = srcToCopy.get(curr.random);
            curr = curr.next;
        }
        return srcToCopy.get(head);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        ListNode rem = l1;
        if (rem == null) {
            rem = l2;
        }
        while (rem != null) {
            pre.next = rem;
            rem = rem.next;
            pre = pre.next;
        }
        return dummy.next;
    }

    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return t == null;
        }
        if (isSameTree(s, t)) {
            return true;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    private static boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }

    public static void main(String[] args) {

    }

    public int[] searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[]{-1, -1};
        }
        int r = 0;
        int c = matrix[0].length - 1;
        while (r < matrix.length && c >= 0) {
            if (matrix[r][c] == target) {
                return new int[]{r, c};
            }
            if (matrix[r][c] > target) {
                c--;
            } else {
                // matrix[r][c] < target
                r++;
            }
        }
        return new int[]{-1, -1};
    }

    public static class RandomNode {
        int val;
        RandomNode next;
        RandomNode random;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}


