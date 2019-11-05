package google;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// TODO
public class ConstrainedSort {

    static void sort(@NotNull int[] arr, int k, int m) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k,
                Comparator.comparingInt(i -> arr[i]));
        for (int i = 0; i < k; i++) minHeap.add(i);
        int swapped = 0;
        int sorted = 0;
        int i = k;
        while (!minHeap.isEmpty() && sorted < arr.length && swapped <= m) {
            int minIdx = minHeap.poll();
            if (minIdx != sorted) {
                swapped++;
                swap(arr, sorted, minIdx);
            }
            sorted++;
            minHeap.add(i);
            i++;
        }
    }

    static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 3, 4, 2, 1};
        sort(arr, 2, 3);
        System.out.println(Arrays.toString(arr));
    }
}

