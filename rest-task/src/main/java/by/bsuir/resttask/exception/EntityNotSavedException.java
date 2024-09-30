package by.bsuir.resttask.exception;

public class EntityNotSavedException extends RuntimeException{
    public EntityNotSavedException(String entityName, Long entityId) {
        super("Failed to save " + entityName + " (id: " + entityId + ")");
    }
}
