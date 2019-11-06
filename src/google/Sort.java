package google;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// TODO
public class Sort {

    static void constrainedSort(@NotNull int[] arr, int k, int m) {
        int n = arr.length;
        Deque<Integer> window = new ArrayDeque<>();
        for (int i = 0; i < k; i++) window.add(i);
        int numSwapped = 0;
        int i = k;
        while (numSwapped < m && i < n) {
            i++;
        }
    }

    static int[] mergeSort(@NotNull int[] arr) {
        int n = arr.length;
        if (n <= 1) return Arrays.copyOf(arr, n);
        int m = n / 2;
        int[] l = mergeSort(Arrays.copyOf(arr, m));
        int[] r = mergeSort(Arrays.copyOfRange(arr, m, n));
        int[] res = new int[n];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < l.length || j < r.length) {
            int nL = i < l.length ? l[i] : Integer.MAX_VALUE;
            int nR = j < r.length ? r[j] : Integer.MAX_VALUE;
            res[k] = Math.min(nL, nR);
            if (i < l.length && res[k] == l[i]) i++;
            if (j < r.length && res[k] == r[j]) j++;
            k++;
        }
        return res;
    }

    static void quickSort(@NotNull int[] arr, int l, int h) {
        if (l >= h) return;
        int i = partition(arr, l, h);
        quickSort(arr, l, i - 1);
        quickSort(arr, i + 1, h);
    }

    static int partition(int[] arr, int l, int h) {
        int i = l;
        for (int j = l; j < h; j++) {
            if (arr[j] < arr[h]) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, h);
        return i;
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 3, 4};
//        constrainedSort(arr, 2, 3);
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}

