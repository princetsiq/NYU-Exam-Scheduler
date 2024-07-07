package dev.javaprojects.ExamScheduler.graphql.resolver;

import dev.javaprojects.ExamScheduler.model.*;
import dev.javaprojects.ExamScheduler.service.BookmarkService;
import dev.javaprojects.ExamScheduler.service.ExamService;
import dev.javaprojects.ExamScheduler.service.UserService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.jetbrains.annotations.NotNull;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class Query implements GraphQLQueryResolver {
    private final UserService userService;
    private final ExamService examService;
    private final BookmarkService bookmarkService;

    Query(UserService userService, ExamService examService, BookmarkService bookmarkService) {
        this.userService = userService;
        this.examService = examService;
        this.bookmarkService = bookmarkService;
    }

    @QueryMapping
    public Optional<User> getUser(@Argument Long userId) {
        return userService.getUserById(userId);
    }

    @QueryMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public Optional<Exam> getExam(@Argument @NotNull ExamIdInput examIdInput) {
        ExamId examId = new ExamId(examIdInput.getCourseName(), examIdInput.getCourseSection());
        return examService.getExamById(examId);
    }

    @QueryMapping
    public List<Exam> getExams() {
        return examService.getAllExams();
    }

    @QueryMapping
    public List<Exam> searchExams(@Argument @NotNull String searchInput) {
        return examService.searchExams(searchInput);
    }

    @QueryMapping
    public List<Exam> getMultipleExams(@Argument @NotNull List<String> examNames) {
        return examService.getMultipleExams(examNames);
    }

    @QueryMapping
    public List<Bookmark> getBookmarksByUserId(@Argument @NotNull Long userId) {
        return bookmarkService.getBookmarksByUserId(userId);
    }

    @QueryMapping
    public List<Bookmark> searchBookmarks(@Argument @NotNull String searchInput) {
        return bookmarkService.searchBookmarks(searchInput);
    }
}