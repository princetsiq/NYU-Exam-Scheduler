package dev.javaprojects.ExamScheduler.repository;

import dev.javaprojects.ExamScheduler.model.Exam;
import dev.javaprojects.ExamScheduler.model.ExamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamRepository extends JpaRepository<Exam, ExamId> {
    Optional<Exam> findByExamId(ExamId examId);
    void deleteByExamId(ExamId examId);
}