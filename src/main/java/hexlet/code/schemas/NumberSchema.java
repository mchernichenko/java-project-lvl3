package hexlet.code.schemas;

public class NumberSchema {
    /**
     * Проверка, что объект является числом.
     * @return - результат проверки
     */
    public Check<? super Object> required() {
        Check<? super Object> isValid = x -> x instanceof Number;
        return isValid;
    }

    /**
     * Проверка, что число положительное.
     * @return - результат проверки
     */
    public Check<Number> positive() {
        Check<Number> isValid = x -> x.doubleValue() > 0;
        return isValid;
    }

    /**
     * Проверка, что число лежит в диапазоне, включая границы.
     * @param minValue - минимальное значение
     * @param maxValue - маскимальное значание
     * @return - результат проверки
     */
    public Check<Number> range(int minValue, int maxValue) {
        Check<Number> isValid = x -> x.doubleValue() >= minValue && x.doubleValue() <= maxValue;
        return isValid;
    }
}
