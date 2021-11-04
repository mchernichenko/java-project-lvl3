package hexlet.code.schemas;

@FunctionalInterface
public interface Check<T> {
    boolean isValid(T t);
}
