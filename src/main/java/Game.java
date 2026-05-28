import GuessResult.GuessResult;

public class Game {

    public String question;

    public GuessResult guess(String guessNumber) {
        assertIllegalArgument(guessNumber);
        int strike = getStrike(guessNumber);
        if (strike == 3) {
            return new GuessResult(true, 3, 0);
        }

        if(getStrike(guessNumber) != 0 ){
            return new GuessResult(false, getStrike(guessNumber), 0);
        }


        return new GuessResult(false, 0, 0);
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
