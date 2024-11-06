package edu.itstep.project.service;

import edu.itstep.project.dto.SubjectDTO;
import edu.itstep.project.model.Subject;
import edu.itstep.project.exception.SubjectNotFoundException;
import edu.itstep.project.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository
                .findAll()
                .stream()
                .map(SubjectDTO::new)
                .toList();
    }
}
