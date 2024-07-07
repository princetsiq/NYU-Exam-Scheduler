package dev.javaprojects.ExamScheduler.graphql.resolver;

import dev.javaprojects.ExamScheduler.model.ExamId;

import java.util.Objects;

public class ExamIdInput {
    private String courseName;
    private String courseSection;

    public ExamIdInput() {}

    public ExamIdInput(String courseName, String courseSection) {
        this.courseName = courseName;
        this.courseSection = courseSection;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseSection() {
        return courseSection;
    }

    public void setCourseSection(String courseSection) {
        this.courseSection = courseSection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExamIdInput examId = (ExamIdInput) o;
        return Objects.equals(courseName, examId.courseName) &&
                Objects.equals(courseSection, examId.courseSection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, courseSection);
    }
}