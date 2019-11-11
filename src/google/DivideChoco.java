package google;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class DivideChoco {

    static int divide(@NotNull int[] sweets, int k) {
        var lo = 0;
        var hi = Arrays.stream(sweets).sum();
        while (lo < hi) {
            var mid = (lo + hi) / 2;
            if (possible(sweets, k, mid)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        // lo == hi
        return lo;
    }

    private static boolean possible(int[] sweets, int k, int mid) {
        var sum = 0;
        var count = 0;
        for (var i : sweets) {
            sum += i;
            if (sum >= mid) {
                count++;
                if (count > k) {
                    return true;
                }
                sum = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
