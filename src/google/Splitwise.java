package google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Splitwise {

    static int min = Integer.MAX_VALUE;

    static int minTransaction(int[][] pays) {
        var account = new HashMap<Integer, Integer>();
        for (var transaction : pays) {
            var from = transaction[0];
            var to = transaction[1];
            var amount = transaction[2];
            account.put(from, account.getOrDefault(from, 0) + amount);
            account.put(to, account.getOrDefault(to, 0) - amount);
        }
        help(new ArrayList<>(account.values()), 0, 0);
        return min;
    }

    static void help(List<Integer> settles, int lo, int count) {
        var n = settles.size();
        while (lo < n && settles.get(lo) == 0) {
            lo++;
        }
        if (lo == n) {
            min = Math.min(min, count);
        } else {
            for (var i = lo + 1; i < n; i++) {
                if (settles.get(i) * settles.get(lo) < 0) {
                    settles.set(i, settles.get(i) + settles.get(lo));
                    help(settles, lo + 1, count + 1);
                    settles.set(i, settles.get(i) - settles.get(lo));
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(minTransaction(new int[][]{
                {0, 1, 10}, {1, 0, 1}, {1, 2, 5}, {2, 0, 5}}));
    }
}
