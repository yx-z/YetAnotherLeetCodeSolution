package citadel;

public class MinDist {

    public static int dist(int[] arr, int n1, int n2) {
        int minDist = Integer.MAX_VALUE;
        int preIdx = -1;
        for (int i = 0; i < arr.length; i++) {
            int n = arr[i];
            if (n == n1 || n == n2) {
                if (n == n1) {
                    if (preIdx >= 0 && arr[preIdx] == n2) {
                        // arr[preIdx] == n2
                        minDist = Math.min(minDist, i - preIdx);
                    }
                } else {
                    // n == n2
                    if (preIdx >= 0 && arr[preIdx] == n1) {
                        // arr[preIdx] == n1
                        minDist = Math.min(minDist, i - preIdx);
                    }
                }
                preIdx = i;
            }
        }
        return minDist;
    }
}
