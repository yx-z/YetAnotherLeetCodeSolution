package google;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HistoricalMap<K, V> {
    Map<K, TreeMap<Long, V>> data = new HashMap<>();

    void put(@NotNull K key, @NotNull V value) {
        long time = System.currentTimeMillis();
        if (!data.containsKey(key)) {
            data.put(key, new TreeMap<>());
        }
        data.get(key).put(time, value);
    }

    @Nullable
    V get(@NotNull K key, long time) {
        if (!data.containsKey(key)) {
            return null;
        }
        Map.Entry<Long, V> value = data.get(key).floorEntry(time);
        if (value == null) {
            return null;
        }
        return value.getValue();
    }
}
