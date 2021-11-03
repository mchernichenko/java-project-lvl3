package hexlet.code.schemas;

import java.util.Objects;

public class StringSchema {
    public Check<String> required() {
        Check<String> isValid = Objects::nonNull;
        return isValid;
    }
    public Check<String> contains(String str) {
        Check<String> isValid = x -> x.contains(str);
        return isValid;
    }
    public Check<String> minLength(int minLength) {
        Check<String> isValid = x -> x.length() >= minLength;
        return isValid;
    }
}
