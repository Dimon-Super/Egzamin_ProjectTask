package edu.itstep.project.exception;


public class GradeNotFoundException extends RuntimeException {
    public GradeNotFoundException(Long id) {
        super("Grade with id " + id + " not found");
    }
}
