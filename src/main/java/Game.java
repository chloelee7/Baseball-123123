import GuessResult.GuessResult;

public class Game {

    public String question;

    public GuessResult guess(String guessNumber) {
        assertIllegalArgument(guessNumber);
        int strikes = getStrike(guessNumber);

        if (strikes == 3) {
            return new GuessResult(true, 3, 0);
        }

        int balls = getBalls(guessNumber);

        return new GuessResult(false, strikes, balls);
    }

    private int getBalls(String guessNumber) {
        int balls = 0;

        for (int i = 0; i < 3; i++) {
            for ( int j = 0; j <3; j++){
                if (i == j){ continue; }
                if (question.charAt(i) == guessNumber.charAt(j)){
                    balls++;
                }
            }
        }
        return balls;
    }

    private int getStrike(String guessNumber) {
        int strike = 0;

        for (int i = 0; i < 3; i++) {
            if(question.charAt(i) == guessNumber.charAt(i)){
                strike++;
            }
        }
        return strike;
    }

    private void assertIllegalArgument(String guessNumber) {
        if (guessNumber == null) {
            throw new IllegalArgumentException();
        }

        if (guessNumber.length() != 3) {
            throw new IllegalArgumentException();
        }

        for (char number : guessNumber.toCharArray()) {
            if (number < '0' || number > '9') {
                throw new IllegalArgumentException();
            }
        }

        if (isDuplicatedNumber(guessNumber)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isDuplicatedNumber(String guessNumber) {
        return guessNumber.charAt(0) == guessNumber.charAt(1) ||
                guessNumber.charAt(0) == guessNumber.charAt(2) ||
                guessNumber.charAt(1) == guessNumber.charAt(2);
    }
}
