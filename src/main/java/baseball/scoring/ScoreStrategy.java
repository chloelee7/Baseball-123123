package baseball.scoring;

import baseball.GuessResult;
import baseball.domain.BaseballNumbers;

public interface ScoreStrategy {
    GuessResult score(BaseballNumbers answer, BaseballNumbers guess);
}
