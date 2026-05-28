package baseball;

import baseball.domain.BaseballNumbers;
import baseball.scoring.ScoreStrategy;
import baseball.scoring.StandardScoreStrategy;

import java.util.Objects;

public final class Game {
    private final BaseballNumbers answer;
    private final ScoreStrategy scoreStrategy;

    public Game(String answer) {
        this(answer, StandardScoreStrategy.INSTANCE);
    }

    public Game(String answer, ScoreStrategy scoreStrategy) {
        this.answer = BaseballNumbers.from(answer);
        this.scoreStrategy = Objects.requireNonNull(scoreStrategy, "scoreStrategy must not be null");
    }

    public GuessResult guess(String guessNumber) {
        return scoreStrategy.score(answer, BaseballNumbers.from(guessNumber));
    }
}
