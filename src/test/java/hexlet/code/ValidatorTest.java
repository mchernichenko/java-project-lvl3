package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidatorTest {

    @Test
    void testRequired() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertThat(schema.required().isValid("")).isEqualTo(true);
        assertThat(schema.required().isValid(null)).isEqualTo(false);
    }

    @Test
    void testContains() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertThat(schema.contains("what").isValid("what does the fox say")).isEqualTo(true);
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isEqualTo(false);
    }

    @Test
    void testMinLength() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        boolean actual;
        final int val = 5;

        actual = schema.minLength(val).isValid("12345");
        assertThat(actual).isEqualTo(true);
        actual = schema.minLength(val).isValid("1234");
        assertThat(actual).isEqualTo(false);
    }

    @Test
    void testRequiredNumber() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        final int val = 10;

        assertThat(schema.required().isValid(null)).isEqualTo(false);
        assertThat(schema.required().isValid(val)).isEqualTo(true);
        assertThat(schema.required().isValid("5")).isEqualTo(false);
    }

    @Test
    void testPositive() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        final int val1 = -10;
        final int val2 = 10;

        assertThat(schema.positive().isValid(0)).isEqualTo(false);
        assertThat(schema.positive().isValid(val1)).isEqualTo(false);
        assertThat(schema.positive().isValid(val2)).isEqualTo(true);
    }

    @Test
    void testRange() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        final int val1 = -2;
        final int val2 = 3;

        assertThat(schema.range(-1, 2).isValid(-1)).isEqualTo(true);
        assertThat(schema.range(-1, 2).isValid(2)).isEqualTo(true);
        assertThat(schema.range(-1, 2).isValid(0)).isEqualTo(true);
        assertThat(schema.range(-1, 2).isValid(val1)).isEqualTo(false);
        assertThat(schema.range(-1, 2).isValid(val2)).isEqualTo(false);
    }

}
