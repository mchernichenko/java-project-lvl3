package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {

    /**
     * Проверка, что объект является числом.
     * @return - результат проверки
     */
    public NumberSchema required() {
        this.setCheck(x -> x instanceof Number);
        return this;
     }

    /**
     * Проверка, что число положительное.
     * @return - результат проверки
     */
    public NumberSchema positive() {
        this.setCheck(x -> x == null || (Integer) x > 0);
        return this;
    }

    /**
     * Проверка, что число лежит в диапазоне, включая границы.
     * @param minValue - минимальное значение
     * @param maxValue - маскимальное значание
     * @return - результат проверки
     */
    public NumberSchema range(int minValue, int maxValue) {
        this.setCheck(x -> (Integer) x >= minValue && (Integer) x <= maxValue);
        return this;
    }
}
