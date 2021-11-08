package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    /**
     * Проверка, что объект является числом.
     * @return - результат проверки
     */
    public NumberSchema required() {
        this.addCheck(x -> x instanceof Number);
        return this;
     }

    /**
     * Проверка, что число положительное (> 0).
     * Если проверяемое значение не определено, то проверять нечего, и результат проверки true
     * @return - результат проверки
     */
    public NumberSchema positive() {
        this.addCheck(x -> x == null || (x instanceof Integer && (Integer) x > 0));
        return this;
    }

    /**
     * Проверка, что число лежит в диапазоне, включая границы.
     * Если проверяемое значение не определено, то проверять нечего, и результат проверки true
     * @param minValue - минимальное значение
     * @param maxValue - маскимальное значание
     * @return - результат проверки.
     */
    public NumberSchema range(int minValue, int maxValue) {
        this.addCheck(x -> x == null || (x instanceof Integer && (Integer) x >= minValue && (Integer) x <= maxValue));
        return this;
    }
}
