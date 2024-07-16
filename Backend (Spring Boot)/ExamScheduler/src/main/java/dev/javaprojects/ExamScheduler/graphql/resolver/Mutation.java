package dev.javaprojects.ExamScheduler.graphql.resolver;

import dev.javaprojects.ExamScheduler.model.*;
import dev.javaprojects.ExamScheduler.service.BookmarkService;
import dev.javaprojects.ExamScheduler.service.ExamService;
import dev.javaprojects.ExamScheduler.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.jetbrains.annotations.NotNull;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
public class Mutation implements GraphQLMutationResolver {
    private final UserService userService;
    private final ExamService examService;
    private final BookmarkService bookmarkService;

    public Mutation(UserService userService, ExamService examService, BookmarkService bookmarkService) {
        this.userService = userService;
        this.examService = examService;
        this.bookmarkService = bookmarkService;
    }

    @MutationMapping
    public UserResponse loginUser(@Argument @NotNull String email, @Argument @NotNull String password) {
        return userService.loginUser(email, password);
    }

    @MutationMapping
    public boolean logoutUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            SecurityContextHolder.getContext().setAuthentication(null);
            return true;
        }
        return false;
    }

    @MutationMapping
    public UserResponse createUser(@Argument @NotNull String firstName, @Argument @NotNull String lastName,
                                   @Argument String email, @Argument String password) {
        User user = new User(firstName, lastName, email, password);
        return userService.createUser(user);
    }

    @MutationMapping
    public Exam createExam(@Argument @NotNull ExamIdInput examIdInput, @Argument @NotNull Long classNumber,
                           @Argument @NotNull String courseTitle, @Argument @NotNull String professorName,
                           @Argument @NotNull String examWeekday, @Argument @NotNull String examDate,
                           @Argument @NotNull String startTime, @Argument @NotNull String endTime,
                           @Argument @NotNull String location) {
        Exam exam = new Exam(new ExamId(examIdInput.getCourseName(), examIdInput.getCourseSection()), classNumber,
                courseTitle, professorName, examWeekday, LocalDate.parse(examDate), LocalTime.parse(startTime),
                LocalTime.parse(endTime), location);
        return examService.createExam(exam);
    }

    @MutationMapping
    public Bookmark createBookmark(@Argument @NotNull Long userId, @Argument @NotNull ExamIdInput examIdInput) {
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Exam exam = examService.getExamById(new ExamId(examIdInput.getCourseName(), examIdInput.getCourseSection()))
                .orElseThrow(() -> new RuntimeException("Exam not found"));
        Bookmark bookmark = new Bookmark(user, exam);
        return bookmarkService.createBookmark(bookmark);
    }

    @MutationMapping
    public boolean updateUser(@Argument @NotNull Long userId, @Argument @NotNull String firstName,
                              @Argument @NotNull String lastName, @Argument @NotNull String email,
                              @Argument @NotNull String password) {
        return userService.updateUser(userId, firstName, lastName, email, password);
    }

    @MutationMapping
    public boolean updateExam(@Argument @NotNull String courseName, @Argument @NotNull String courseSection,
                              @Argument @NotNull Long classNumber, @Argument @NotNull String courseTitle,
                              @Argument @NotNull String professorName, @Argument @NotNull String examWeekday,
                              @Argument @NotNull LocalDate examDate, @Argument @NotNull LocalTime startTime,
                              @Argument @NotNull LocalTime endTime, @Argument @NotNull String location) {
        return examService.updateExam(courseName, courseSection, classNumber, courseTitle, professorName, examWeekday,
                examDate, startTime, endTime, location);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument @NotNull Long userId) {
        return userService.deleteUser(userId);
    }

    @MutationMapping
    public Boolean deleteExam(@Argument @NotNull ExamIdInput examIdInput) {
        ExamId examId = new ExamId(examIdInput.getCourseName(), examIdInput.getCourseSection());
        return examService.deleteExam(examId);
    }

    @MutationMapping
    public Boolean deleteBookmark(@Argument Long bookmarkId) {
        return bookmarkService.deleteBookmark(bookmarkId);
    }
}