package google;

import org.jetbrains.annotations.NotNull;

public class SubarrSum {

    static int maxSubarrSum(@NotNull int[] arr) {
        var maxSoFar = Math.max(arr[0], 0);
        var maxEndingHere = arr[0];
        for (var i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }

    static int maxSubMatSum(@NotNull int[][] mat) {
        var rows = mat.length;
        var cols = mat[0].length;
        // sumPerRow[i][j]: sum of mat[i][0..j]
        var sumPerRow = new int[rows][cols];
        for (var r = 0; r < rows; r++) {
            sumPerRow[r][0] = mat[r][0];
            for (var c = 1; c < cols; c++)
                sumPerRow[r][c] = mat[r][c] + sumPerRow[r][c - 1];
        }
        var sumPerCol = new int[rows][cols];
        for (var c = 0; c < cols; c++) {
            sumPerCol[c][0] = mat[c][0];
            for (var r = 1; r < rows; r++)
                sumPerCol[r][c] = mat[r][c] + sumPerRow[r - 1][c];
        }
        var maxSum = 0;
        for (var r = 0; r < rows; r++) {
            for (var c = 0; c < cols; c++) {
                // top-left index is at mat[r][c]
                var curSum = mat[r][c];
                maxSum = Math.max(maxSum, curSum);
                for (var side = 1; r + side < rows && c + side < cols; side++) {
                    curSum += (sumPerRow[r + side][c] - sumPerRow[r][c]) +
                            (sumPerCol[r][c + side] - sumPerCol[r][c]) -
                            mat[r + side][c + side];
                    maxSum = Math.max(maxSum, curSum);
                }
            }
        }
        return maxSum;
    }
}
