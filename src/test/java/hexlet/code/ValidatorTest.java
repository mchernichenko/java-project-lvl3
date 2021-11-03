package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeAll;
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
        actual= schema.minLength(5).isValid("12345");
        assertThat(actual).isEqualTo(true);
        actual = schema.minLength(5).isValid("1234");
        assertThat(actual).isEqualTo(false);
    }
}
