package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(schema.isValid(null)).isEqualTo(true);

        // required – любое число включая ноль
        schema.required();
        final int val = 10;
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid(val)).isEqualTo(true);
        assertThat(schema.isValid("5")).isEqualTo(false);

        // positive – положительное число
        final int val11 = -10;
        final int val12 = 10;
        schema.positive();
        assertThat(schema.isValid(0)).isEqualTo(false);
        assertThat(schema.isValid(val11)).isEqualTo(false);
        assertThat(schema.isValid(val12)).isEqualTo(true);

        // range – диапазон в который должны попадать числа включая границы
        final int val21 = -2;
        final int val22 = 3;
        assertThat(schema.range(-1, 2).isValid(-1)).isEqualTo(true);
        assertThat(schema.range(-1, 2).isValid(2)).isEqualTo(true);
        assertThat(schema.range(-1, 2).isValid(0)).isEqualTo(true);
        assertThat(schema.range(-1, 2).isValid(val21)).isEqualTo(false);
        assertThat(schema.range(-1, 2).isValid(val22)).isEqualTo(false);
    }

    @Test
    void testMapSchema() {
        Validator v = new Validator();
        MapSchema schema = v.map();

        // критерий валидации для схемы не определён => вcегда true
        assertThat(schema.isValid(null)).isEqualTo(true);

        // required – требуется тип данных Map
        schema.required();
        assertThat(schema.isValid(null)).isEqualTo(false);
        assertThat(schema.isValid(new HashMap<String, String>())).isEqualTo(true);

        // sizeof – количество пар ключ-значений в объекте Map должно быть равно заданному
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertThat(schema.sizeof(1).isValid(data)).isEqualTo(true);
        assertThat(schema.sizeof(2).isValid(data)).isEqualTo(false);

        data.put("key2", "value2");
        assertThat(schema.sizeof(1).isValid(data)).isEqualTo(false);
        assertThat(schema.sizeof(2).isValid(data)).isEqualTo(true);

        // shape - позволяет описывать валидацию для значений объекта Map по ключам.
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required()); // 'name' должен быть непустой строкой
        schemas.put("age", v.number().positive()); // 'age' должен быть числом > 0
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        final int val1 = 100;
        human1.put("name", "Kolya");
        human1.put("age", val1);

        assertThat(schema.isValid(human1)).isEqualTo(true);

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(schema.isValid(human2)).isEqualTo(false);

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(schema.isValid(human3)).isEqualTo(false);

        Map<String, Object> human4 = new HashMap<>();
        final int val2 = -5;
        human4.put("name", "Valya");
        human4.put("age", val2);
        assertThat(schema.isValid(human4)).isEqualTo(false);
    }
}
