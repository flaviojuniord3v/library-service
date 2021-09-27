package dev.flaviojunior.web.rest.errors;

public class BadRequestAlertException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String entityName;

    public BadRequestAlertException(String defaultMessage, String entityName) {
        super(defaultMessage);
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }


}
