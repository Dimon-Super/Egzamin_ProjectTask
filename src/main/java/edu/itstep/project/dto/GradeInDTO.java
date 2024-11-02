package edu.itstep.project.dto;


import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeInDTO {
    @NotNull(message = "Student is required")
    private Long studentId;

    @NotNull(message = "Subject is required")
    private Long subjectId;

    @NotNull(message = "Teacher is required")
    private Long teacherId;

    @NotNull(message = "Grade date is required")
    private Date date;

    @Min(value = 0, message = "Grade must be at least 0")
    @Max(value = 100, message = "Grade must not exceed 100")

    private Integer gradeValue;
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public @NotNull(message = "Student is required") Long getStudentId() {
        return studentId;
    }

    public void setStudentId(@NotNull(message = "Student is required") Long studentId) {
        this.studentId = studentId;
    }

    public @NotNull(message = "Subject is required") Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(@NotNull(message = "Subject is required") Long subjectId) {
        this.subjectId = subjectId;
    }

    public @NotNull(message = "Teacher is required") Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(@NotNull(message = "Teacher is required") Long teacherId) {
        this.teacherId = teacherId;
    }

    public @NotNull(message = "Grade date is required") Date getDate() {
        return date;
    }

    public void setDate(@NotNull(message = "Grade date is required") Date date) {
        this.date = date;
    }

    public @Min(value = 0, message = "Grade must be at least 0") @Max(value = 100, message = "Grade must not exceed 100") Integer getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(@Min(value = 0, message = "Grade must be at least 0") @Max(value = 100, message = "Grade must not exceed 100") Integer gradeValue) {
        this.gradeValue = gradeValue;
    }
}
