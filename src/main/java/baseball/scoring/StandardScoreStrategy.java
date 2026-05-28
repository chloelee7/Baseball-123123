package baseball.scoring;

import baseball.GuessResult;
import baseball.domain.BaseballNumbers;

public final class StandardScoreStrategy implements ScoreStrategy {
    public static final StandardScoreStrategy INSTANCE = new StandardScoreStrategy();

    private StandardScoreStrategy() {
    }

    @Override
    public GuessResult score(BaseballNumbers answer, BaseballNumbers guess) {
        int strikes = 0;
        int balls = 0;

        for (int index = 0; index < answer.size(); index++) {
            char guessedDigit = guess.digitAt(index);
            if (answer.digitAt(index) == guessedDigit) {
                strikes++;
                continue;
            }

            if (answer.contains(guessedDigit)) {
                balls++;
            }
        }

        return GuessResult.of(strikes, balls, answer.size());
    }
}
