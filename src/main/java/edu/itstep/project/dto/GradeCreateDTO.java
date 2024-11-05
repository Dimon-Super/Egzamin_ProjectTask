package edu.itstep.project.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeCreateDTO {
    @NotNull(message = "Student is required")
    private Long studentId;

    @NotNull(message = "Subject is required")
    private Long subjectId;

    @NotNull(message = "Teacher is required")
    private Long teacherId;

    @NotNull(message = "Grade date is required")
    private LocalDate date;

    @Min(value = 0, message = "Grade must be at least 0")
    @Max(value = 100, message = "Grade must not exceed 100")
    @NotNull(message = "Grade value is required")
    private Integer gradeValue;

    private String comment;
}
