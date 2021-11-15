package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema {

    /**
     * Определение схем валидации: объект является Map.
     * @return - схема с определённой валидацией
     */
    public MapSchema required() {
        this.addCheck(x -> x instanceof Map);
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
        return this;
    }

    /**
     * Определение вложенной валидации для мапы.
     *
     * @param schemas - каждому ключу определяем схему валидации
     * @return - схема с определённой валидацией
     */
    public MapSchema shape(Map<String, BaseSchema> schemas) {
      //  this.checkMap = schemas;
        this.addCheck(x -> {
            for (Map.Entry<String, Object> key : ((Map<String, Object>) x).entrySet()) {
                // достаём по ключу схему валидации и проверяем по ней value
                if (!schemas.get(key.getKey()).isValid(key.getValue())) {
                    return false; // если хоть одна валидация value не проходит, то мапа считается невалидной
                }
            }
            return  true; // мапа валидная, если все value валидны
        });
        return this;
    }
}
