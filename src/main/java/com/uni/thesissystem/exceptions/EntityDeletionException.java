package com.uni.thesissystem.exceptions;

public class EntityDeletionException extends RuntimeException {
    public EntityDeletionException(String entityName, Long entityId) {
        super("Cannot delete " + entityName + " with ID " + entityId + " as it is associated with other records.");
    }
}
