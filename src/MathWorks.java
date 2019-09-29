import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MathWorks {

    private static int[] root;
    private static int[] numChildren;

    public static int[] countingBits(int n) {
        int len = 1 + Integer.numberOfTrailingZeros(Integer.highestOneBit(n));
        int numOnes = Integer.bitCount(n);
        int[] res = new int[numOnes + 1];
        res[0] = numOnes;
        int numIdx = 0;
        for (int i = numOnes; i >= 1; i--) {
            while ((n & 1) != 1) {
                n >>= 1;
                numIdx++;
            }
            // last bit of n is 1
            res[i] = len - numIdx;
            n >>= 1;
            numIdx++;
        }
        return res;
    }

    public static int minDist(int w, int h, int n) {
        return gen(w, h, n).stream().mapToInt(board -> {
            int max = 0;
            for (int r = 0; r < h; r++)
                for (int c = 0; c < w; c++)
                    max = Math.max(max, board[r][c]);
            return max;
        }).min().orElse(-1);
    }

    public static List<int[][]> gen(int w, int h, int n) {
        List<int[][]> res = new ArrayList<>(w * h);
        if (n == 1) {
            for (int r = 0; r < h; r++) {
                for (int c = 0; c < w; c++) {
                    int[][] board = new int[h][w];
                    for (int dr = 0; dr < h; dr++)
                        for (int dc = 0; dc < w; dc++)
                            board[dr][dc] = Math.abs(r - dr) + Math.abs(c - dc);
                    res.add(board);
                }
            }
        } else {
            gen(w, h, n - 1).forEach(board -> {
                for (int r = 0; r < h; r++) {
                    for (int c = 0; c < w; c++) {
                        if (board[r][c] != 0) {
                            int[][] copy = new int[h][w];
                            for (int cr = 0; cr < h; cr++)
                                System.arraycopy(board[cr], 0, copy[cr], 0, w);
                            copy[r][c] = 0;
                            for (int dr = 0; dr < h; dr++)
                                for (int dc = 0; dc < w; dc++)
                                    copy[dr][dc] = Math.min(copy[dr][dc],
                                            Math.abs(r - dr) + Math.abs(c - dc));
                            res.add(copy);
                        }
                    }
                }
            });
        }
        return res;
    }

    public static String merge(String s1, String s2) {
        StringBuilder sb = new StringBuilder();
        int i1 = 0;
        int i2 = 0;
        while (i1 < s1.length() || i2 < s2.length()) {
            if (i1 < s1.length()) {
                sb.append(s1.charAt(i1));
                i1++;
            }
            if (i2 < s2.length()) {
                sb.append(s2.charAt(i2));
                i2++;
            }
        }
        return sb.toString();
    }

    public static int sort(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        int count = 0;
        while (lo < hi) {
            if (arr[lo] % 2 == 0) {
                lo++;
            } else if (arr[hi] % 2 == 1) {
                hi--;
            } else {
                // arr[lo] is odd AND arr[hi] is even
                // we now need to swap indeed
                count++;
                lo++;
                hi--;
            }
        }
        // break when lo and hi point to the same number
        return count;
    }

    public static int minSum(int[] arr) {
        Arrays.sort(arr);
        int sum = arr[0];
        int pre = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] <= pre) {
                pre++;
                sum += pre;
            } else {
                sum += arr[i];
                pre = arr[i];
            }
        }
        return sum;
    }

    public static int[] connectedCities(int n, int g,
                                        int[] originCities,
                                        int[] destinationCities) {
        root = new int[n + 1];
        numChildren = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            root[i] = i;
            numChildren[i] = 1;
        }
        for (int i = g + 1; i <= n; i++)
            for (int j = 2 * i; j <= n; j += i) union(i, j);
        int[] res = new int[originCities.length];
        for (int i = 0; i < originCities.length; i++)
            if (root(originCities[i]) == root(destinationCities[i])) res[i] = 1;
        return res;
    }

    private static void union(int a, int b) {
        int aRoot = root(a);
        int bRoot = root(b);
        if (aRoot != bRoot) {
            if (numChildren[aRoot] < numChildren[bRoot]) {
                root[aRoot] = root[bRoot];
                numChildren[bRoot] += numChildren[aRoot];
            } else {
                root[bRoot] = root[aRoot];
                numChildren[aRoot] += numChildren[bRoot];
            }
        }
    }

    private static int root(int a) {
        if (a == root[a]) return a;
        root[a] = root(root[a]);
        return root[a];
    }

    public static void main(String[] args) {

    }
}
