package dev.flaviojunior.domain.enumeration;

/**
 * The StatusOfExam enumeration.
 */
public enum StatusOfExam {
    INITIATED("Initiated"),
    IN_PROGRESS("In Progress"),
    FINISHED("Finished");

    private final String value;

    StatusOfExam(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
