import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Peak6 {

    public static int flight(int[] landingTimes,
                             int[] takeOffTimes,
                             int maxWaitTime,
                             int initialPlanes) {
        int maxGates = initialPlanes;
        int landIdx = 0;
        int takeOffIdx = 0;
        int currGates = initialPlanes;
        while (landIdx < landingTimes.length &&
                takeOffIdx < takeOffTimes.length) {
            int landWait = landingTimes[landIdx] +
                    (maxWaitTime / 60) * 100 +
                    (maxWaitTime % 60);
            if (landWait % 100 >= 60) landWait += 40;
            if (landWait > takeOffTimes[takeOffIdx]) {
                takeOffIdx++;
                currGates--;
            } else {
                landIdx++;
                currGates++;
                maxGates = Math.max(maxGates, currGates);
            }
        }
        return maxGates;
    }

    public static int kangroo(String[] words,
                              String[] wordsToSynonyms,
                              String[] wordsToAntonyms) {
        Map<String, String[]> synonyms = new HashMap<>();
        Arrays.stream(wordsToSynonyms).forEach(s -> {
            String[] split = s.split(":");
            String word = split[0];
            synonyms.put(word, split[1].split(","));
        });
        Map<String, String[]> antonyms = new HashMap<>();
        Arrays.stream(wordsToAntonyms).forEach(s -> {
            String[] split = s.split(":");
            String word = split[0];
            antonyms.put(word, split[1].split(","));
        });
        final int[] score = new int[]{0};
        Map<String, Integer> revSyn = new HashMap<>();
        Map<String, Integer> revAnt = new HashMap<>();
        Arrays.stream(words).forEach(word -> {
            if (synonyms.containsKey(word))
                for (String s : synonyms.get(word))
                    if (isJoey(word, s)) {
                        score[0]++;
                        revSyn.put(s, revSyn.getOrDefault(s, 0) + 1);
                    }
            if (antonyms.containsKey(word))
                for (String s : antonyms.get(word))
                    if (isJoey(word, s)) {
                        score[0]--;
                        revAnt.put(s, revAnt.getOrDefault(s, 0) + 1);
                    }
        });
        revSyn.forEach((k, l) -> score[0] += l * (l - 1) / 2);
        revAnt.forEach((k, l) -> score[0] += l * (l - 1) / 2);
        return score[0];
    }

    private static boolean isJoey(String large, String small) {
        int loLarge = 0;
        int hiLarge = large.length() - 1;
        int loSmall = 0;
        int hiSmall = small.length() - 1;
        while (loSmall <= hiSmall) {
            if (loLarge >= large.length()) return false;
            if (large.charAt(loLarge) == small.charAt(loSmall)) loSmall++;
            loLarge++;
            if (hiLarge < 0) return false;
            if (large.charAt(hiLarge) == small.charAt(hiSmall)) hiSmall--;
            hiLarge--;
        }
        return hiLarge != hiSmall;
    }

    public static void main(String[] args) {
    }
}
