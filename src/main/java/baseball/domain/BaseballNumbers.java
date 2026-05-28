package baseball.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class BaseballNumbers {
    public static final int REQUIRED_LENGTH = 3;

    private final String value;

    private BaseballNumbers(String value) {
        this.value = value;
    }

    public static BaseballNumbers from(String value) {
        validate(value);
        return new BaseballNumbers(value);
    }

    public int size() {
        return value.length();
    }

    public char digitAt(int index) {
        return value.charAt(index);
    }

    public boolean contains(char digit) {
        return value.indexOf(digit) >= 0;
    }

    private static void validate(String value) {
        if (value == null) {
            throw new IllegalArgumentException("numbers must not be null");
        }

        if (value.length() != REQUIRED_LENGTH) {
            throw new IllegalArgumentException("numbers must be " + REQUIRED_LENGTH + " digits");
        }

        Set<Character> digits = new HashSet<>();
        for (char digit : value.toCharArray()) {
            validateDigit(digit);
            validateUnique(digits, digit);
        }
    }

    private static void validateDigit(char digit) {
        if (digit < '0' || digit > '9') {
            throw new IllegalArgumentException("numbers must contain only digits");
        }
    }

    private static void validateUnique(Set<Character> digits, char digit) {
        if (!digits.add(digit)) {
            throw new IllegalArgumentException("numbers must not contain duplicate digits");
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof BaseballNumbers)) {
            return false;
        }

        BaseballNumbers that = (BaseballNumbers) object;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
