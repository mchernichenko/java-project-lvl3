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

        // minLength – строка равна или длиннее указанного числа + required
        final int val5 = 5;
        assertTrue(schema.minLength(val5).isValid("12345"));
        assertFalse(schema.minLength(val5).isValid("1234"));

        // contains – строка содержит определённую подстроку + minLength + required
        assertThat(schema.contains("what").isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isEqualTo(false);
    }

    @Test
    void testNumberSchema() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        // критерий валидации для схемы не определён => вcегда true
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(1));

        // positive – положительное число или null
        final int val10 = 10;
        final int valMinis10 = -10;
        schema.positive();
        assertThat(schema.isValid(null)).isEqualTo(true);
        assertThat(schema.isValid(0)).isEqualTo(false);
        assertThat(schema.isValid(valMinis10)).isEqualTo(false);
        assertThat(schema.isValid(val10)).isEqualTo(true);

        // required – любое число включая ноль + required
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(val10));
        assertFalse(schema.isValid("5"));

        // range – диапазон в который должны попадать числа включая границы + required + positive
        final int val2 = 2;
        final int val3 = 3;
        schema.range(val2, val10);
        assertThat(schema.isValid(-1)).isEqualTo(false);
        assertThat(schema.isValid(val2)).isEqualTo(true);
        assertThat(schema.isValid(1)).isEqualTo(false);
        assertThat(schema.isValid(val3)).isEqualTo(true);
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
        schema.sizeof(2);

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
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
