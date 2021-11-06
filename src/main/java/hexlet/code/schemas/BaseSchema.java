package hexlet.code.schemas;

import java.util.function.Predicate;

public class BaseSchema {
     private Predicate<? super Object> simpleCheck; // простая проверка

    /**
     * Установить проверку.
     * @param check - проверка
     */
    public void setCheck(Predicate<? super Object> check) {
        this.simpleCheck = check;
    }

    /**
     * Валидиция по заданной схеме. Если схема не заданы, то валидации нет.
     * @param t - проверяемые данные
     * @param <T> - тип проверяемых данных
     * @return - результат проверки
     */
    public <T> boolean isValid(T t) {
        return simpleCheck == null || simpleCheck.test(t);
    }
}
