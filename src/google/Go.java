package google;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.function.BiFunction;

public class Go {
    static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static boolean isSurrounded(@NotNull char[][] board, int r, int c) {
        BiFunction<Integer, Integer, Boolean> isValid = (x, y) ->
                x >= 0 && x < board.length && y >= 0 && y < board[0].length;
        var visited = new boolean[board.length][board[0].length];
        var queue = new LinkedList<int[]>();
        queue.add(new int[]{r, c});
        while (!queue.isEmpty()) {
            var size = queue.size();
            for (var i = 0; i < size; i++) {
                var cur = queue.pop();
                var curR = cur[0];
                var curC = cur[1];
                visited[curR][curC] = true;
                for (int[] dir : DIRS) {
                    var nr = curR + dir[0];
                    var nc = curC + dir[1];
                    if (!isValid.apply(nr, nc) || board[nr][nc] == 'e') {
                        return false;
                    }
                    if (board[nr][nc] == 'b' && !visited[nr][nc]) {
                        queue.push(new int[]{nr, nc});
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isSurrounded(new char[][]{
                {'b', 'w', 'w', 'b'},
                {'e', 'b', 'b', 'w'},
                {'w', 'w', 'w', 'e'}
        }, 1, 2));
    }
}
