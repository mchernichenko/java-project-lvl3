package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @Test
    void testStringSchema() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        // критерий валидации для схемы не определён => вcегда true
        assertThat(schema.isValid("")).isEqualTo(true);
        assertThat(schema.isValid(null)).isEqualTo(true);

        // required – любая непустая строка
        schema.required();
        assertThat(schema.isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.isValid("hexlet")).isEqualTo(true);
        assertThat(schema.isValid("")).isEqualTo(false);
        assertThat(schema.isValid(null)).isEqualTo(false);

        // contains – строка содержит определённую подстроку
        assertThat(schema.contains("what").isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isEqualTo(false);

        // minLength – строка равна или длиннее указанного числа
        boolean actual;
        final int val = 5;
        actual = schema.minLength(val).isValid("12345");
        assertThat(actual).isEqualTo(true);
        actual = schema.minLength(val).isValid("1234");
        assertThat(actual).isEqualTo(false);
    }

    @Test
    void testNumberSchema() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        // критерий валидации для схемы не определён => вcегда true
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(1));

        // required – любое число включая ноль
        schema.required();
        final int val = 10;
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid(val)).isEqualTo(true);
        assertThat(schema.isValid("5")).isEqualTo(false);

        // positive – положительное число или его вообще нет
        final int val11 = -10;
        final int val12 = 10;
        schema.positive();
        // отсутствие значения означает, что нечего проверять => true
        assertThat(schema.isValid(null)).isEqualTo(true);
        assertThat(schema.isValid(0)).isEqualTo(false);
        assertThat(schema.isValid(val11)).isEqualTo(false);
        assertThat(schema.isValid(val12)).isEqualTo(true);

        // range – диапазон в который должны попадать числа включая границы
        final int valMinis2 = -2;
        final int val3 = 3;
        assertThat(schema.range(-1, 2).isValid(-1)).isEqualTo(true);
        assertThat(schema.range(-1, 2).isValid(2)).isEqualTo(true);
        assertThat(schema.range(-1, 2).isValid(0)).isEqualTo(true);
        assertThat(schema.range(-1, 2).isValid(valMinis2)).isEqualTo(false);
        assertThat(schema.range(-1, 2).isValid(val3)).isEqualTo(false);
    }

    @Test
    void testMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        // критерий валидации для схемы не определён => вcегда true
        assertTrue(schema.isValid(null));

        // required – требуется тип данных Map
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<String, String>()));

        // sizeof – количество пар ключ-значений в объекте Map должно быть равно заданному
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.sizeof(1).isValid(data));
        assertFalse(schema.sizeof(2).isValid(data));

        data.put("key2", "value2");
        assertFalse(schema.sizeof(1).isValid(data));
        assertTrue(schema.sizeof(2).isValid(data));

        // shape - позволяет описывать валидацию для значений объекта Map по ключам.
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required()); // 'name' должен быть непустой строкой
        schemas.put("age", v.number().positive()); // 'age' должен быть числом > 0 или null
        schema.shape(schemas);

        final int val100 = 100;
        Map<String, Object> human1 = Map.of("name", "Kolya",
                                            "age", val100);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3));

        final int valMinus5 = -5;
        Map<String, Object> human4 = Map.of("name", "Valya",
                                            "age", valMinus5);
        assertFalse(schema.isValid(human4));
    }
}
