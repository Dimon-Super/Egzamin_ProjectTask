package edu.itstep.project.service;

import edu.itstep.project.dto.TeacherDTO;
import edu.itstep.project.model.Teacher;
import edu.itstep.project.exception.TeacherNotFoundException;
import edu.itstep.project.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository
                .findAll()
                .stream()
                .map(TeacherDTO::new)
                .toList();
    }

    public TeacherDTO getTeacherById(long id) {
        return teacherRepository
                .findById(id)
                .map(TeacherDTO::new)
                .orElseThrow(() -> new TeacherNotFoundException(id));
    }

    public void createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacherRepository.save(teacher);
    }

    public void updateTeacher(TeacherDTO teacherDTO, long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
        teacher.setFirstName(teacherDTO.getFirstName());
        teacher.setLastName(teacherDTO.getLastName());
        teacherRepository.save(teacher);
    }

    public void deleteTeacher(long id) {
        if (!teacherRepository.existsById(id)) {
            throw new TeacherNotFoundException(id);
        }
        teacherRepository.deleteById(id);
    }
}
