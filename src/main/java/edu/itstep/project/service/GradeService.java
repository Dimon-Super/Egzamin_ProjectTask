package edu.itstep.project.service;

import edu.itstep.project.dto.GradeInDTO;
import edu.itstep.project.dto.GradeOutDTO;
import edu.itstep.project.model.Grade;
import edu.itstep.project.exception.GradeNotFoundException;
import edu.itstep.project.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {
    @Autowired
    private GradeRepository gradeRepository;

    public List<GradeOutDTO> getAllGrades() {
        return gradeRepository
                .findAll()
                .stream()
                .map(GradeOutDTO::new)
                .toList();
    }

    public GradeOutDTO getGradeById(long id) {
        return gradeRepository
                .findById(id)
                .map(GradeOutDTO::new)
                .orElseThrow(() -> new GradeNotFoundException(id));
    }

    public void createGrade(GradeInDTO gradeInDTO) {
        Grade grade = new Grade();
        grade.setGradeValue(gradeInDTO.getGradeValue());
        grade.setComment(gradeInDTO.getComment());
        grade.setDate(gradeInDTO.getDate());
        // Set the related entities (student, subject, teacher) here if needed
        gradeRepository.save(grade);
    }

    public void updateGrade(GradeInDTO gradeInDTO, long id) {
        Grade grade = gradeRepository.findById(id).orElseThrow(() -> new GradeNotFoundException(id));
        grade.setGradeValue(gradeInDTO.getGradeValue());
        grade.setComment(gradeInDTO.getComment());
        grade.setDate(gradeInDTO.getDate());
        gradeRepository.save(grade);
    }

    public void deleteGrade(long id) {
        if (!gradeRepository.existsById(id)) {
            throw new GradeNotFoundException(id);
        }
        gradeRepository.deleteById(id);
    }
}
