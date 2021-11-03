package hexlet.code;

import hexlet.code.schemas.StringSchema;

public class App {
    public static void main(String[] args) {
        Validator v = new Validator();
        StringSchema schema = v.string();

        System.out.println(schema.required().isValid(""));
        System.out.println(schema.required().isValid(null));

        System.out.println(schema.contains("what").isValid("what does the fox say"));
        System.out.println(schema.contains("whatthe").isValid("what does the fox say"));

        System.out.println(schema.minLength(5).isValid("12345"));
        System.out.println(schema.minLength(5).isValid("1234"));
    }
}
