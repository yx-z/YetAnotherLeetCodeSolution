public class Google {

    public static int water(int[] plants, int cap) {
        if (plants == null || plants.length == 0 || cap == 0) return 0;
        int step = 0;
        int rem = cap;
        for (int i = 0; i < plants.length; i++) {
            if (cap < plants[i]) return -1;
            if (rem < plants[i]) {
                rem = cap;
                step += i * 2;
            }
            rem -= plants[i];
            step++;
        }
        return step;
    }

    public static int minDominoRotations(int[] A, int[] B) {
        int n = A.length;

        int a = 0;
        int b = 0;
        int i = 0;
        int target = A[0];
        while (i < n && (A[i] == target || B[i] == target)) {
            if (A[i] != target) a++;
            if (B[i] != target) b++;
            i++;
        }
        if (i == n) return Math.min(a, b);
        a = 0;
        b = 0;
        i = 0;
        target = B[0];
        while (i < n && (A[i] == target || B[i] == target)) {
            if (A[i] != target) a++;
            if (B[i] != target) b++;
            i++;
        }
        if (i == n) return Math.min(a, b);
        return -1;
    }

    public static void main(String[] args) {

    }
}
