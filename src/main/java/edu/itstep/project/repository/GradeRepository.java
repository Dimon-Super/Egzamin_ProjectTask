package edu.itstep.project.repository;


import edu.itstep.project.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {

}
