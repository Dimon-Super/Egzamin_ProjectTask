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

    public SubjectDTO getSubjectById(long id) {
        return subjectRepository
                .findById(id)
                .map(SubjectDTO::new)
                .orElseThrow(() -> new SubjectNotFoundException(id));
    }

    public void createSubject(SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        subject.setName(subjectDTO.getName());
        subjectRepository.save(subject);
    }

    public void updateSubject(SubjectDTO subjectDTO, long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new SubjectNotFoundException(id));
        subject.setName(subjectDTO.getName());
        subjectRepository.save(subject);
    }

    public void deleteSubject(long id) {
        if (!subjectRepository.existsById(id)) {
            throw new SubjectNotFoundException(id);
        }
        subjectRepository.deleteById(id);
    }
}
