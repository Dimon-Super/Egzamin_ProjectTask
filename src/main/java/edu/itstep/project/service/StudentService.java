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
        return studentRepository
                .findAll()
                .stream()
                .map(StudentDTO::new)
                .toList();
    }

    public StudentDTO getStudentById(long id) {
        return studentRepository
                .findById(id)
                .map(StudentDTO::new)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    public void createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        studentRepository.save(student);
    }

    public void updateStudent(StudentDTO studentDTO, long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }
}
