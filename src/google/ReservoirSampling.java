package google;

import java.util.Arrays;
import java.util.Random;

public class ReservoirSampling {
    private int size = 0;
    private int sample = -1;
    private Random rand = new Random();

    public void accept(int i) {
        size++;
        if (rand.nextInt(size) == 0) {
            // P[in this branch] = 1/current size
            sample = i;
        }
    }
    /*
    proof: let current size be n. we have accepted samples s[1..n]. for all i:
    P[sample == s[i]] = P[choose at i-th accept()] * P[not choose at i+1..n]
                      = 1/i * i/(i+1) * (i+1)/(i+2) * ... * (n-1)/n
                      = 1/n
     */

    public int getSample() {
        return sample;
    }

    /**
     * @param source data array
     * @param k      # of samples
     * @return k samples from source
     */
    public int[] getSample(int[] source, int k) {
        int n = source.length;
        int[] samples = Arrays.copyOf(source, k);
        for (int i = k; i < n; i++) {
            int j = rand.nextInt(i);
            if (j < k) {
                samples[j] = source[i];
            }
        }
        return samples;
    }
    /*
    proof: need to prove that for all i: P[i] == P[source[i] in samples] == k/n
    case 1 (i in 0..k-1):
        P[i] = P[not choose i for elements in k..n-1]
             = k/(k+1) * (k+1)/(k+2) * ... * (n-1)/n
             = k/n
    case 2 (i in k..n-1):
        P[i] = P[j < k] * P[not choose j for elements in i+1..n-1]
             = k/i * i/(i+1) * (i+1)/(i+2) * ... * (n-1)/n
             = k/n
     */
}
