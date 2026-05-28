package baseball;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game("123");
    }

    @Test
    void createGame() {
        assertThat(game).isNotNull();
    }

    @Test
    void throwIllegalArgumentExceptionInvalidAnswer() {
        assertThatThrownBy(() -> new Game(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Game("12")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Game("1234")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Game("12s")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Game("121")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void throwIllegalArgumentExceptionInvalidInput() {
        assertIllegalArgumentWhenGuessing(null);
        assertIllegalArgumentWhenGuessing("12");
        assertIllegalArgumentWhenGuessing("1234");
        assertIllegalArgumentWhenGuessing("12s");
        assertIllegalArgumentWhenGuessing("121");
    }

    private void assertIllegalArgumentWhenGuessing(String guessNumber) {
        assertThatThrownBy(() -> game.guess(guessNumber)).isInstanceOf(IllegalArgumentException.class);
    }

    private void assertMatchedNumber(GuessResult result, boolean solved, int strikes, int balls) {
        assertThat(result).isNotNull();
        assertThat(result.isSolved()).isEqualTo(solved);
        assertThat(result.getStrikes()).isEqualTo(strikes);
        assertThat(result.getBalls()).isEqualTo(balls);
    }

    @Test
    void returnSolvedResultIfMatchedNumber() {
        assertMatchedNumber(game.guess("123"), true, 3, 0);
    }

    @Test
    void returnSolvedResultIfUnmatchedNumber() {
        assertMatchedNumber(game.guess("456"), false, 0, 0);
    }

    @Test
    void returnSolvedResultIf2Strikes0Ball() {
        assertMatchedNumber(game.guess("126"), false, 2, 0);
    }

    @Test
    void returnSolvedResultIf2Strikes1Ball() {
        assertMatchedNumber(game.guess("321"), false, 1, 2);
    }

    @Test
    void returnSolvedResultIf0Strike3Balls() {
        assertMatchedNumber(game.guess("312"), false, 0, 3);
    }

    @Test
    void returnSolvedResultIf1Strike1Ball() {
        assertMatchedNumber(game.guess("139"), false, 1, 1);
    }

    @Test
    void supportCustomScoreStrategy() {
        Game game = new Game("123", (answer, guess) -> GuessResult.of(1, 2, 3));

        assertMatchedNumber(game.guess("456"), false, 1, 2);
    }
}
