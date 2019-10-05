import java.util.*;

public class Amazon {

    private static int time;
    private static List<Integer>[] graph;
    private static int[] low; // low[u] = lowest label u can reach
    private static int[] dis; // dis[u] = clock time when u is discovered

    public static Map<String, List<String>> favoriteGenre(
            Map<String, List<String>> userSongs, // <user, [songs]>
            Map<String, List<String>> genreSongs // <genre, [songs]>
    ) {
        if (userSongs == null || genreSongs == null) {
            return Collections.emptyMap();
        }

        // <song, genre>
        Map<String, String> songGenre = new HashMap<>();
        genreSongs.forEach((genre, songs) ->
                songs.forEach(song -> songGenre.put(song, genre))
        );

        // <user, [genres]>
        Map<String, List<String>> userGenres = new HashMap<>();
        userSongs.forEach((user, songs) -> {
            userGenres.put(user, new ArrayList<>());

            // <genre, count>
            Map<String, Integer> count = new HashMap<>();
            final int[] max = new int[]{0};
            songs.stream().filter(songGenre::containsKey).map(songGenre::get)
                    .forEach(genre -> {
                        count.put(genre, count.getOrDefault(genre, 0) + 1);
                        max[0] = Math.max(max[0], count.get(genre));
                    });

            count.entrySet().stream().filter(e -> e.getValue() == max[0])
                    .forEach(e -> userGenres.get(user).add(e.getKey()));
        });
        return userGenres;
    }

    public static List<List<Integer>> criticalConnections(
            int n, List<List<Integer>> connections
    ) {
        // build graph
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> connection : connections) {
            int u = connection.get(0);
            int v = connection.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }

        // dfs
        low = new int[n + 1];
        dis = new int[n + 1];
        time = 0;
        for (int i = 0; i <= n; i++) {
            if (dis[i] == 0) {
                dfs(i, i);
            }
        }

        // find cut edges
        List<List<Integer>> res = new ArrayList<>();
        for (List<Integer> connection : connections) {
            int u = connection.get(0);
            int v = connection.get(1);
            // v cannot reach lower than u => no back edge or cycle => cut edge
            if (dis[u] < low[v] || dis[v] < low[u]) {
                res.add(connection);
            }
        }
        return res;
    }

    private static void dfs(int u, int p) {
        time++;
        dis[u] = time;
        low[u] = time;
        for (int v : graph[u]) {
            if (v == p) {
                continue;
            }
            if (dis[v] == 0) {
                dfs(v, u);
                low[u] = Math.min(low[u], low[v]);
            } else {
                low[u] = Math.min(low[u], dis[v]);
            }
        }
    }

    public static void main(String[] args) {

    }
}
