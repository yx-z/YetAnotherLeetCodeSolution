package citadel;

import java.util.Arrays;
import java.util.Comparator;

public class Area {

    // coordinates is a 4 by 2 matrix, 4 pairs of (x, y) coordinate
    public double area(int[][] coordinates) {
        // sort by x
        Arrays.sort(coordinates, Comparator.comparing(i -> i[0]));

        return 0;
    }
}
