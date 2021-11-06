package hexlet.code.schemas;

public class StringSchema extends BaseSchema {

    /**
     * Проверка, что любая строка непустая.
     * @return - результат проверки
     */
    public StringSchema required() {
        this.setCheck(x -> x != null && !x.equals(""));
        return this;
    }

    /**
     * Проверка, что строка содержит определённую подстроку.
     * @param str - подстрока
     * @return - результат проверки
     */
    public StringSchema contains(String str) {
        this.setCheck(x -> ((String) x).contains(str));
        return this;
    }

    /**
     * Проверка, что строка равна или длиннее указанного числа.
     * @param minLength - минимальная длина строки
     * @return - результат проверки
     */
    public StringSchema minLength(int minLength) {
        this.setCheck(x -> ((String) x).length() >= minLength);
        return this;
    }
}
