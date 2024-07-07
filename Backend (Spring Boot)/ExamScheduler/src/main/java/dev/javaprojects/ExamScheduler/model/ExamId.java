package dev.javaprojects.ExamScheduler.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class ExamId {
    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String courseSection;

    public ExamId() {}

    public ExamId(String courseName, String courseSection) {
        this.courseName = courseName;
        this.courseSection = courseSection;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseSection() {
        return courseSection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamId examId = (ExamId) o;
        return Objects.equals(courseName, examId.courseName) && Objects.equals(courseSection, examId.courseSection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, courseSection);
    }
}