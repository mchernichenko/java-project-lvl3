package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    /**
     * Проверка, что любая строка непустая.
     * @return - результат проверки
     */
    public StringSchema required() {
        this.addCheck(x -> x instanceof String && !((String) x).isEmpty());
        return this;
    }

    /**
     * Проверка, что строка содержит определённую подстроку.
     * Если проверяемое значение не определено, то проверять нечего, и результат проверки true
     * @param str - подстрока
     * @return - результат проверки
     */
    public StringSchema contains(String str) {
        this.addCheck(x -> x == null || (x instanceof String && ((String) x).contains(str)));
        return this;
    }

    /**
     * Проверка, что строка равна или длиннее указанного числа.
     * Если проверяемое значение не определено, то проверять нечего, и результат проверки true
     * @param minLength - минимальная длина строки
     * @return - результат проверки
     */
    public StringSchema minLength(int minLength) {
        this.addCheck(x -> x == null || (x instanceof String && ((String) x).length() >= minLength));
        return this;
    }
}
