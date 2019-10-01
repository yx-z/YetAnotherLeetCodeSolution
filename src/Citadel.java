import java.util.ArrayList;
import java.util.List;

public class Citadel {

    public static int countPals(String s) {
        int n = s.length();
        // single char palindromes
        int count = n;
        // odd len (and > 1) palindromes centered at s[i]
        for (int i = 1; i < n; i++) {
            int lo = i - 1;
            int hi = i + 1;
            while (lo >= 0 && hi < n && s.charAt(lo) == s.charAt(hi)) {
                count++;
                lo--;
                hi++;
            }
        }
        // even len (and > 0) palindromes centered at s[i] and s[i + 1]
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                count++;
                int lo = i - 1;
                int hi = i + 1 + 1;
                while (lo >= 0 && hi < n && s.charAt(lo) == s.charAt(hi)) {
                    count++;
                    lo--;
                    hi++;
                }
            }
        }
        return count;
    }

    public static int maxDepth(int[] arr) {
        int n = arr.length;
        Node root = new Node();
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) nodes[i] = new Node();
        for (int i = 0; i < n; i++) {
            int parent = arr[i];
            if (parent != -1) nodes[parent].children.add(nodes[i]);
            else root.children.add(nodes[i]);
        }
        return height(root) - 1;
    }

    private static int height(Node curr) {
        int max = 0;
        for (Node child : curr.children) max = Math.max(max, height(child));
        return max + 1;
    }

    public static int maxProfit(int[] arr) {
        int n = arr.length;
        // rightMax[i]: max in arr[i + 1 until n]
        int[] rightMax = new int[n];
        rightMax[n - 2] = arr[n - 1];
        for (int i = n - 3; i >= 0; i--)
            rightMax[i] = Math.max(rightMax[i + 1], arr[i + 1]);
        int maxProfit = 0;
        for (int i = 0; i < n - 1; i++)
            maxProfit += Math.max(0, rightMax[i] - arr[i]);
        return maxProfit;
    }

    public static void main(String[] args) {

    }

    private static class Node {
        List<Node> children = new ArrayList<>();
    }
}
