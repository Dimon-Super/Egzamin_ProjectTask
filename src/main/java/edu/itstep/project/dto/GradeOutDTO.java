package edu.itstep.project.dto;

import edu.itstep.project.model.Grade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeOutDTO {
    private Long id;
    private int gradeValue;
    private String comment;
    private LocalDate date;
    private Long studentId;
    private Long subjectId;
    private Long teacherId;
    private String studentName;
    private String subjectName;
    private String teacherName;

    public GradeOutDTO(Grade grade) {
        this.id = grade.getId();
        this.gradeValue = grade.getGradeValue();
        this.comment = grade.getComment();
        this.date = grade.getDate();
        this.studentId = grade.getStudent().getId();
        this.subjectId = grade.getSubject().getId();
        this.teacherId = grade.getTeacher().getId();
        this.studentName = grade.getStudent().getFirstName() + " " + grade.getStudent().getLastName();
        this.subjectName = grade.getSubject().getName();
        this.teacherName = grade.getTeacher().getFirstName() + " " + grade.getTeacher().getLastName();
    }

}
