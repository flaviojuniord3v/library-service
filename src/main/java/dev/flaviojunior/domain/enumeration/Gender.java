package dev.flaviojunior.domain.enumeration;

/**
 * The Gender enumeration.
 */
public enum Gender {
    MASCULINE("Masculine"),
    FEMININE("Feminine"),
    OHTER("Other");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
