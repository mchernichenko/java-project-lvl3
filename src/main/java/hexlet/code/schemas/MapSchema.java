package hexlet.code.schemas;

import java.util.Map;

public class MapSchema {
    /**
     * Проверка, что объект является Map.
     * @return - результат проверки
     */
    public Check<? super Object> required() {
        Check<? super Object> isValid = x -> x instanceof Map;
        return isValid;
    }

    /**
     * Проверка, что количество пар key-value в мапе соответствует заданному значению.
     *
     * @param cnt - количество пар
     * @return - результат проверки
     */
    public Check<Map<String, String>> sizeof(int cnt) {
        Check<Map<String, String>> isValid = x -> x.size() == cnt;
        return isValid;
    }
}
