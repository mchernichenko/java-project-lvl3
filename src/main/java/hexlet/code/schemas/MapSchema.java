package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    private Map<String, BaseSchema> checkMap;

    /**
     * Валидация по заданной схеме. Если схема не заданы, то валидации нет.
     * @return - результат проверки
     */
    @Override
    public <T> boolean isValid(T t) {
        if (checkMap != null)  { // определена сложная валидация
            for (Map.Entry<String, Object> key : ((Map<String, Object>) t).entrySet()) {
                // достаём по ключу схему валидации и проверяем по ней value
                if (!checkMap.get(key.getKey()).isValid(key.getValue())) {
                    return false; // если хоть одна валидация value не проходит, то мапа считается невалидной
                }
            }
            return true; // мапа валидная, если все value валидны
        }
        return super.isValid(t);
    }

    /**
     * Определение схем валидации: объект является Map.
     * @return - схема с определённой валидацией
     */
    public MapSchema required() {
        this.addCheck(x -> x instanceof Map);
        this.checkMap = null;
        return this;
    }

    /**
     * Определение схем валидации: количество пар key-value в мапе соответствует заданному значению.
     *
     * @param cnt - количество пар
     * @return - схема с определённой валидацией
     */
    public MapSchema sizeof(int cnt) {
        this.addCheck(x -> x == null || x instanceof Map && ((Map) x).size() == cnt);
        this.checkMap = null;
        return this;
    }

    /**
     * Определение вложенной валидации для мапы.
     *
     * @param schemas - каждому ключу определяем схему валидации
     * @return - схема с определённой валидацией
     */
    public MapSchema shape(Map<String, BaseSchema> schemas) {
        this.checkMap = schemas;
        return this;
    }
}
