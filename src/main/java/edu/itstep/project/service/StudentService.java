package edu.itstep.project.service;

import edu.itstep.project.dto.StudentDTO;
import edu.itstep.project.model.Student;
import edu.itstep.project.exception.StudentNotFoundException;
import edu.itstep.project.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDTO> getAllStudents() {
        System.out.println("getAllStudents method called");
        List<StudentDTO> students = studentRepository
                .findAll()
                .stream()
                .map(StudentDTO::new)
                .toList();
        System.out.println("Students count: " + students.size());
        return students;
    }

    public StudentDTO getStudentById(long id) {
        return studentRepository
                .findById(id)
                .map(StudentDTO::new)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }
}
