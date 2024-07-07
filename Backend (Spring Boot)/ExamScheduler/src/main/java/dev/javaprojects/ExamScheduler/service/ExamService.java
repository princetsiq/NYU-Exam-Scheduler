package dev.javaprojects.ExamScheduler.service;

import dev.javaprojects.ExamScheduler.model.Exam;
import dev.javaprojects.ExamScheduler.model.ExamId;
import dev.javaprojects.ExamScheduler.repository.ExamRepository;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamService {
    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    public Optional<Exam> getExamById(ExamId examId) {
        return examRepository.findById(examId);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public List<Exam> searchExams(@NotNull String keyword) {
        return examRepository.findAll().stream()
                .filter(exam ->
                        exam.getExamId().getCourseName().toLowerCase().contains(keyword.toLowerCase()) ||
                                exam.getExamId().getCourseSection().toLowerCase().contains(keyword.toLowerCase()) ||
                                exam.getCourseTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                                exam.getClassNumber().toString().contains(keyword) ||
                                exam.getProfessorName().toLowerCase().contains(keyword.toLowerCase()) ||
                                exam.getExamWeekday().toLowerCase().contains(keyword.toLowerCase()) ||
                                exam.getExamDate().toString().contains(keyword.toLowerCase()) ||
                                exam.getStartTime().toString().contains(keyword.toLowerCase()) ||
                                exam.getEndTime().toString().contains(keyword.toLowerCase()) ||
                                exam.getLocation().toLowerCase().contains(keyword.toLowerCase())
                )
                .collect(Collectors.toList());
    }

    public List<Exam> getMultipleExams(@NotNull List<String> examNames) {
        List<String> exams = examNames.stream()
                .map(String::toLowerCase)
                .toList();
        return examRepository.findAll().stream()
                .filter(exam ->
                        exams.contains(exam.getExamId().getCourseName().toLowerCase()) ||
                                exams.contains(exam.getCourseTitle().toLowerCase()))
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean updateExam(String courseName, String courseSection, Long classNumber, String courseTitle, String professorName, String examWeekday, LocalDate examDate, LocalTime startTime, LocalTime endTime, String location) {
        ExamId examId = new ExamId(courseName, courseSection);
        return examRepository.findById(examId).map(exam -> {
            if (classNumber != null) exam.setClassNumber(classNumber);
            if (courseTitle != null) exam.setCourseTitle(courseTitle);
            if (professorName != null) exam.setProfessorName(professorName);
            if (examWeekday != null) exam.setExamWeekday(examWeekday);
            if (examDate != null) exam.setExamDate(examDate);
            if (startTime != null) exam.setStartTime(startTime);
            if (endTime != null) exam.setEndTime(endTime);
            if (location != null) exam.setLocation(location);
            examRepository.save(exam);
            return true;
        }).orElse(false);
    }

    @Transactional
    public boolean deleteExam(ExamId examId) {
        return examRepository.findByExamId(examId)
                .map(_ -> {
                    examRepository.deleteByExamId(examId);
                    return true;
                })
                .orElse(false);
    }
}