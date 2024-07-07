package dev.javaprojects.ExamScheduler.model;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Exam {
    @EmbeddedId
    private ExamId examId;

    @Column(nullable = false)
    private Long classNumber;

    @Column(nullable = false)
    private String courseTitle;

    @Column(nullable = false)
    private String professorName;

    @Column(nullable = false)
    private String examWeekday;

    @Column(nullable = false)
    private LocalDate examDate;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private String location;

    public Exam() {
    }

    public Exam(ExamId examId, Long classNumber, String courseTitle, String professorName,
                String examWeekday, LocalDate examDate, LocalTime startTime, LocalTime endTime, String location) {
        this.examId = examId;
        this.classNumber = classNumber;
        this.courseTitle = courseTitle;
        this.professorName = professorName;
        this.examWeekday = examWeekday;
        this.examDate = examDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public ExamId getExamId() {
        return examId;
    }

    public Long getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(Long classNumber) {
        this.classNumber = classNumber;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getExamWeekday() {
        return examWeekday;
    }

    public void setExamWeekday(String examWeekday) {
        this.examWeekday = examWeekday;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}