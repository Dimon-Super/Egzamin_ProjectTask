package edu.itstep.project.service;

import edu.itstep.project.dto.*;
import edu.itstep.project.exception.StudentNotFoundException;
import edu.itstep.project.exception.SubjectNotFoundException;
import edu.itstep.project.exception.TeacherNotFoundException;
import edu.itstep.project.model.Grade;
import edu.itstep.project.exception.GradeNotFoundException;
import edu.itstep.project.repository.GradeRepository;
import edu.itstep.project.repository.StudentRepository;
import edu.itstep.project.repository.SubjectRepository;
import edu.itstep.project.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

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

        grade.setStudent(studentRepository.findById(gradeInDTO.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(gradeInDTO.getStudentId())));

        grade.setSubject(subjectRepository.findById(gradeInDTO.getSubjectId())
                .orElseThrow(() -> new SubjectNotFoundException(gradeInDTO.getSubjectId())));

        grade.setTeacher(teacherRepository.findById(gradeInDTO.getTeacherId())
                .orElseThrow(() -> new TeacherNotFoundException(gradeInDTO.getTeacherId())));

        gradeRepository.save(grade);
    }

    public void updateGrade(GradeInDTO gradeInDTO, long id) {
        Grade grade = gradeRepository.findById(id).orElseThrow(() -> new GradeNotFoundException(id));
        grade.setGradeValue(gradeInDTO.getGradeValue());
        grade.setComment(gradeInDTO.getComment());
        grade.setDate(gradeInDTO.getDate());

        grade.setStudent(studentRepository.findById(gradeInDTO.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(gradeInDTO.getStudentId())));

        grade.setSubject(subjectRepository.findById(gradeInDTO.getSubjectId())
                .orElseThrow(() -> new SubjectNotFoundException(gradeInDTO.getSubjectId())));

        grade.setTeacher(teacherRepository.findById(gradeInDTO.getTeacherId())
                .orElseThrow(() -> new TeacherNotFoundException(gradeInDTO.getTeacherId())));

        gradeRepository.save(grade);
    }

    public void deleteGrade(long id) {
        if (!gradeRepository.existsById(id)) {
            throw new GradeNotFoundException(id);
        }
        gradeRepository.deleteById(id);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentDTO::new)
                .toList();
    }


    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(SubjectDTO::new)
                .toList();
    }


    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(TeacherDTO::new)
                .toList();
    }

    public List<GradeOutDTO> filterGrades(Long subjectId, LocalDate date) {
        return gradeRepository.findAll()
                .stream()
                .filter(grade -> (subjectId == null || grade.getSubject().getId().equals(subjectId)) &&
                        (date == null || grade.getDate().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                                .isEqual(date)))
                .map(GradeOutDTO::new)
                .collect(Collectors.toList());
    }
}
