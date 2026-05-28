package baseball;

import java.util.Objects;

public final class GuessResult {
    private final boolean solved;
    private final int strikes;
    private final int balls;

    private GuessResult(boolean solved, int strikes, int balls) {
        this.solved = solved;
        this.strikes = strikes;
        this.balls = balls;
    }

    public static GuessResult of(int strikes, int balls, int requiredStrikes) {
        if (strikes < 0 || balls < 0 || requiredStrikes < 0) {
            throw new IllegalArgumentException("score counts must not be negative");
        }

        return new GuessResult(strikes == requiredStrikes, strikes, balls);
    }

    public boolean isSolved() {
        return solved;
    }

    public int getStrikes() {
        return strikes;
    }

    public int getBalls() {
        return balls;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof GuessResult)) {
            return false;
        }

        GuessResult that = (GuessResult) object;
        return solved == that.solved && strikes == that.strikes && balls == that.balls;
    }

    @Override
    public int hashCode() {
        return Objects.hash(solved, strikes, balls);
    }

    @Override
    public String toString() {
        return "GuessResult{" +
                "solved=" + solved +
                ", strikes=" + strikes +
                ", balls=" + balls +
                '}';
    }
}
