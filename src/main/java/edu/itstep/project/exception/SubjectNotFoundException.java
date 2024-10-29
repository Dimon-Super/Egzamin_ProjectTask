package edu.itstep.project.exception;

public class SubjectNotFoundException extends RuntimeException {
    public SubjectNotFoundException(Long id) {
        super("Subject with id " + id + " not found");
    }
}
