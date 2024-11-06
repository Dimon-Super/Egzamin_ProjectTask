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
}
