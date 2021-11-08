package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {
    private List<Predicate<? super Object>> checkList = new ArrayList<>(); // список проверок

    /**
     * Добавить проверку в список проверок.
     *
     * @param check - проверка
     */
    public void addCheck(Predicate<? super Object> check) {
        this.checkList.add(check);
    }

    /**
     * Валидиция по списку проверок.
     *
     * @param t   - проверяемые данные.
     *            Если (t == null), то считается, что проверяемое значение не определено.
     *            Результат проверки в данном случае всегда true
     * @param <T> - тип проверяемых данных
     * @return - результат проверки.
     */
    public <T> boolean isValid(T t) {
        return this.checkList.stream().allMatch(x -> x.test(t));
    }
}
