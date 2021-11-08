package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {

    /**
     *
     * @return - текущая схема
     */
    public StringSchema string() {
        return new StringSchema();
    }
    /**
     *
     * @return - текущая схема
     */
    public NumberSchema number() {
        return new NumberSchema();
    }
    /**
     *
     * @return - текущая схема
     */
    public MapSchema map() {
        return new MapSchema();
    }
}
