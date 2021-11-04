package hexlet.code.schemas;

import java.util.Objects;

public class StringSchema {
    /**
     * Проверка, что любая строка непустая.
     * @return - результат проверки
     */
    public Check<String> required() {
        Check<String> isValid = Objects::nonNull;
        return isValid;
    }

    /**
     * Проверка, что строка содержит определённую подстроку.
     * @param str - подстрока
     * @return - результат проверки
     */
    public Check<String> contains(String str) {
        Check<String> isValid = x -> x.contains(str);
        return isValid;
    }

    /**
     * Проверка, что строка равна или длиннее указанного числа.
     * @param minLength - минимальная длина строки
     * @return - результат проверки
     */
    public Check<String> minLength(int minLength) {
        Check<String> isValid = x -> x.length() >= minLength;
        return isValid;
    }
}
