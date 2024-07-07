package dev.javaprojects.ExamScheduler.service;

import dev.javaprojects.ExamScheduler.model.Bookmark;
import dev.javaprojects.ExamScheduler.model.Exam;
import dev.javaprojects.ExamScheduler.model.User;
import dev.javaprojects.ExamScheduler.repository.BookmarkRepository;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public Optional<Bookmark> getBookmarkByUserAndExam(User user, Exam exam) {
        return bookmarkRepository.findByUserAndExam(user, exam);
    }

    public Bookmark createBookmark(@NotNull Bookmark bookmark) {
        if (getBookmarkByUserAndExam(bookmark.getUser(), bookmark.getExam()).isPresent()) {
            throw new IllegalStateException("Bookmark already exists for this exam and user.");
        }
        return bookmarkRepository.save(bookmark);
    }

    public List<Bookmark> getBookmarksByUserId(Long userId) {
        return bookmarkRepository.findByUser_UserId(userId);    }

    public List<Bookmark> searchBookmarks(@NotNull String searchInput) {
        return bookmarkRepository.findAll().stream()
                .filter(bookmark ->
                        bookmark.getExam().getExamId().getCourseName().toLowerCase().contains(searchInput.toLowerCase()) ||
                                bookmark.getExam().getExamId().getCourseSection().toLowerCase().contains(searchInput.toLowerCase()) ||
                                bookmark.getExam().getCourseTitle().toLowerCase().contains(searchInput.toLowerCase()) ||
                                bookmark.getExam().getClassNumber().toString().contains(searchInput) ||
                                bookmark.getExam().getProfessorName().toLowerCase().contains(searchInput.toLowerCase()) ||
                                bookmark.getExam().getExamWeekday().toLowerCase().contains(searchInput.toLowerCase()) ||
                                bookmark.getExam().getExamDate().toString().contains(searchInput.toLowerCase()) ||
                                bookmark.getExam().getStartTime().toString().contains(searchInput.toLowerCase()) ||
                                bookmark.getExam().getEndTime().toString().contains(searchInput.toLowerCase()) ||
                                bookmark.getExam().getLocation().toLowerCase().contains(searchInput.toLowerCase())
                )
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean deleteBookmark(Long bookmarkId) {
        if (bookmarkRepository.existsById(bookmarkId)) {
            bookmarkRepository.deleteById(bookmarkId);
            return true;
        }
        return false;
    }
}