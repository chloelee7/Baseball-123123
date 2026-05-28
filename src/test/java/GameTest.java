import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class GameTest {

    @Test
    void createGame() {
        Game game = new Game();
        assertNotNull(game);
    }

    @Test
    void throwExceptionWhenInputIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Game game = new Game();
            game.guess(null);
        });
    }

}