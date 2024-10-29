package edu.itstep.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeInDTO {
    private int gradeValue;
    private String comment;
    private Date date;
    private Long studentId;
    private Long subjectId;
    private Long teacherId;
}
