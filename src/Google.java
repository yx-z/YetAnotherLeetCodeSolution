import java.util.Arrays;

public class Google {

    public static void main(String[] args) {
        System.out.println(new Google().shortestLength(new int[]{1, 2, 2, 3, 4, 6, 3, 1, 4, 2, 8}, 5));
    }

    public int shortestLength(int[] source, int K) {
        int n = source.length;
        int min = Integer.MAX_VALUE;
        // left[i] : min length of the interval that sum up to K, in the array [0..i]
        int[] left = new int[n];
        // right[i]: min length of the interval that sum up to K, in [i..n-1]
        int[] right = new int[n];
        Arrays.fill(left, Integer.MAX_VALUE);
        Arrays.fill(right, Integer.MAX_VALUE);
        int lo = 0;
        if (source[0] == K) {
            left[0] = 1;
        }
        int sum = source[0];
        for (int hi = 1; hi < n; hi++) {
            sum += source[hi];
            if (sum >= K) {
                while (sum - source[lo] >= K) {
                    sum -= source[lo];
                    lo++;
                }
                if (sum == K) left[hi] = Math.min(left[hi - 1], hi - lo + 1);
                else left[hi] = left[hi - 1];
            } else {
                // sum < K
                left[hi] = left[hi - 1];
            }
        }
        int hi = n - 1;
        if (source[n - 1] == K) {
            right[n - 1] = 1;
        }
        sum = source[n - 1];
        for (int l = n - 2; l >= 0; l--) {
            sum += source[l];
            if (sum >= K) {
                while (sum - source[hi] >= K) {
                    sum -= source[hi];
                    hi--;
                }
                if (sum == K) right[l] = Math.min(right[l + 1], hi - l + 1);
                else right[l] = right[l + 1];
            } else {
                // sum < K
                right[l] = right[l + 1];
            }
        }
        for (int i = 1; i < n - 1; i++) {
            if (left[i - 1] != Integer.MAX_VALUE && right[i + 1] != Integer.MAX_VALUE) {
                min = Math.min(min, left[i - 1] + right[i + 1]);
            }
        }
        if (min == Integer.MAX_VALUE) {
            return -1;
        } else {
            return min;
        }
    }
}
