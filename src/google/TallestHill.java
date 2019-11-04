package google;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.Queue;

public class TallestHill {

    static final int[][] DIRS = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    static int height(@NotNull int[][] map) {
        int maxHeight = 0;
        int rows = map.length;
        int cols = map[0].length;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                maxHeight = Math.max(maxHeight, bfs(map, r, c));
            }
        }
        return maxHeight;
    }

    static int bfs(int[][] map, int r, int c) {
        if (map[r][c] != 1) {
            return 0;
        }
        // map[r][c] == 1
        int curHeight = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.remove();
                map[cur[0]][cur[1]] = curHeight;
                for (int[] dir : DIRS) {
                    int[] update = add(cur, dir);
                    if (isValid(map, update)) {
                        queue.add(update);
                    }
                }
            }
            curHeight++;
        }
        return curHeight;
    }

    static int[] add(int[] coord1, int[] coord2) {
        return new int[]{coord1[0] + coord2[0], coord1[1] + coord2[1]};
    }

    static boolean isValid(int[][] map, int[] coord) {
        return coord[0] >= 0 && coord[0] < map.length &&
                coord[1] >= 0 && coord[1] < map[coord[0]].length &&
                map[coord[0]][coord[1]] == 1;
    }

    public static void main(String[] args) {
        System.out.println(height(new int[][]{
                {0, 0, 1},
                {0, 1, 1},
                {1, 0, 1}
        }));
    }
}
