package com.ecommerce.project.exceptions;
//custom exception class - > handles when resource is not found

public class ResourceNotFoundException extends RuntimeException {
    String resourceName ;
    String field ;
    String filedName;
    long fieldId ;

    public ResourceNotFoundException() {
    }
    public ResourceNotFoundException(String field, String resourceName, String filedName) {
        super(String.format("%s not found with %s : %s",resourceName , field ,filedName));
        this.field = field;
        this.resourceName = resourceName;
        this.filedName = filedName;
    }

    public ResourceNotFoundException(String field, String resourceName, long fieldId) {
        super(String.format("%s not found with %s : %d",resourceName , field ,fieldId));
        this.field = field;
        this.resourceName = resourceName;
        this.fieldId = fieldId;
    }
}
