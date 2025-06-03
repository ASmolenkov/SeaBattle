public enum FieldType {
    PLAYER(true, "Игрок"),
    OPPONENT(false, "Противник");

    private final boolean value;
    private final String description;

    FieldType(boolean value, String description) {
        this.value = value;
        this.description = description;
    }

    public boolean getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
