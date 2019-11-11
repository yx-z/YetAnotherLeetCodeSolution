package google;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class StackGoods {

    static int max = 0;
    static int n = 0;
    static int[] leftSmall = null;
    static int[] rightSmall = null;
    static int[] heights = null;
    static int[] goods = null;

    static int stack(@NotNull int[] heights, @NotNull int[] goods) {
        Arrays.sort(goods);
        var n = heights.length;
        var smallest = new int[n];
        smallest[0] = heights[0];
        for (var i = 1; i < n; i++) {
            smallest[i] = Math.min(smallest[i - 1], heights[i]);
        }
        var g = 0;
        var h = n - 1;
        while (h >= 0 && g < goods.length) {
            if (smallest[h] >= goods[g]) {
                g++;
            }
            h--;
        }
        return g;
    }

    static int doubleStack(@NotNull int[] pHeights, @NotNull int[] pGoods) {
        Arrays.sort(pGoods);
        heights = pHeights;
        goods = pGoods;
        n = heights.length;
        leftSmall = new int[n];
        leftSmall[0] = heights[0];
        for (var i = 1; i < n; i++) {
            leftSmall[i] = Math.min(leftSmall[i - 1], heights[i]);
        }
        rightSmall = new int[n];
        rightSmall[n - 1] = heights[n - 1];
        for (var i = n - 2; i >= 0; i--) {
            leftSmall[i] = Math.min(rightSmall[i + 1], heights[i]);
        }
        for (var i = 0; i < n; i++) {
            if (goods[0] <= leftSmall[i] || goods[0] <= rightSmall[i]) {
                helper(i - 1, i + 1, 1, 1);
            }
        }
        return max;
    }

    private static void helper(int lo, int hi, int g, int count) {
        if (g >= goods.length || (lo < 0 && hi >= n)) {
            max = Math.max(max, count);
            return;
        }
        if (lo >= 0 && leftSmall[lo] >= goods[g]) {
            helper(lo - 1, hi, g + 1, count + 1);
        }
        if (hi < n && rightSmall[hi] >= goods[g]) {
            helper(lo, hi + 1, g + 1, count + 1);
        }
        helper(lo - 1, hi + 1, g, count);
    }

    public static void main(String[] args) {
        System.out.println(
                doubleStack(new int[]{3, 4, 2, 3}, new int[]{5, 1, 3, 3, 4}));
    }
}
