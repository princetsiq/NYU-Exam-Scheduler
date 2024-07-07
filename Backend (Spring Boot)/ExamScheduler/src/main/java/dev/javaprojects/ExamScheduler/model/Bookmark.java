package dev.javaprojects.ExamScheduler.model;

import jakarta.persistence.*;

@Entity
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookmarkId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Exam exam;

    public Bookmark() {}

    public Bookmark(User user, Exam exam) {
        this.user = user;
        this.exam = exam;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Exam getExam() {
        return exam;
    }
}
