package by.bsuir.resttask.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, Long entityId) {
        super(entityName + " (id: " + entityId + ") not found");
    }
}
