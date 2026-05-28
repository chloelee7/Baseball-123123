import GuessResult.GuessResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void createGame() {
        assertNotNull(game);
    }

    private void assertIllegalArgument(String guessNumber) {
        try {
            game.guess(guessNumber);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    void throwIllegalArgumentExceptionInvalidInput() {
        assertIllegalArgument(null);
        assertIllegalArgument("12");
        assertIllegalArgument("1234");
        assertIllegalArgument("12s");
        assertIllegalArgument("121");
    }

    private void assertMatchedNumber(GuessResult result, boolean solved, int strikes, int balls) {
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.isSolved()).isEqualTo(solved);
        Assertions.assertThat(result.getStrikes()).isEqualTo(strikes);
        Assertions.assertThat(result.getBalls()).isEqualTo(balls);
    }

    private void generateQuestion(String question) {
        game.question = question;
    }

    @Test
    void returnSolvedResultIfMatchedNumber() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("123"), true, 3, 0);
    }

    @Test
    void returnSolvedResultIfUnmatchedNumber() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("456"), false, 0, 0);
    }

    @Test
    void returnSolvedResultIf2Strikes0Ball() {
        generateQuestion("123");
        assertMatchedNumber(game.guess("126"), false, 2, 0);
    }
}