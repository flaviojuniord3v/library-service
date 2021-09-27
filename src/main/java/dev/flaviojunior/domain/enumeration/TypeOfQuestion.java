package dev.flaviojunior.domain.enumeration;

/**
 * The TypeOfQuestion enumeration.
 */
public enum TypeOfQuestion {
    FREE_TEXT("Free Text"),
    MULTIPLE_CHOICE("Multiple Choice"),
    ONE_CHOICE("One Choice");

    private final String value;

    TypeOfQuestion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
