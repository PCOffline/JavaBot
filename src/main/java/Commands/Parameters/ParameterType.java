package Commands.Parameters;

public enum ParameterType {
    free,
    regex,
    limited,
    constant;

    @Override
    public String toString() {
        return name();
    }
}
