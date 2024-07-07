package dev.javaprojects.ExamScheduler.repository;

import dev.javaprojects.ExamScheduler.model.Bookmark;
import dev.javaprojects.ExamScheduler.model.Exam;
import dev.javaprojects.ExamScheduler.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    List<Bookmark> findByUser_UserId(Long userId);
    Optional<Bookmark> findByUserAndExam(User user, Exam exam);
}