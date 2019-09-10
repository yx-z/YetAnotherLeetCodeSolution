package easy

class Sol1025 {

    // dp (the programmatic solution)
    // i still like this SLOW solution
    val memo = hashMapOf(1 to false)

    fun divisorGame(N: Int): Boolean {
        if (N !in memo) memo[N] =
            (1..N / 2).filter { N % it == 0 }.any { !divisorGame(N - it) }
        return memo[N]!!
    }

    // math w/ proof (the clever solution)
    // "first player will win if N % 2 == 0" by induction on N

    // base case: if N == 1, first player lose immediately

    // inductive hypothesis: exist N such that for all N in 1..N,
    // first player will win if N % 2 == 0, lose otherwise

    // inductive step: now consider N+1
    // if N+1 is even, the first player picks 1 and updates the number as
    // N+1 - 1 = N, which is odd. by hypothesis, the second player must lose

    // else N+1 is odd. any number, x, the first player picks must be odd.
    // otherwise N+1 as a multiple of even number implies N+1 is even.
    // so the first player updates the number as N+1 - x, which is even.
    // since N+1 - x < N, by hypothesis, the second player must win
    fun game(N: Int) = N % 2 == 0
}