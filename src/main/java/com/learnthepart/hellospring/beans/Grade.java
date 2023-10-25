package com.learnthepart.hellospring.beans;

import java.util.UUID;
import com.learnthepart.hellospring.annotations.ValidScore;
import com.learnthepart.hellospring.annotations.ValidUsername;

import jakarta.validation.constraints.NotBlank;

public class Grade {
    @NotBlank(message = "Name cannot be empty")
    @ValidUsername(message = "Username should contain at least 1 lowercase letter, 1 uppercase letter, 1 digit")
    private String name;

    @NotBlank(message = "Subject cannot be empty")
    private String subject;

    @ValidScore(message = "Invalid score")
    private Double score;

    private String id;

    @Override
    public String toString() {
        return "Grade [name=" + name + ", subject=" + subject + ", score=" + score + ", id=" + id + "]";
    }

    public Grade() {
        this.id = UUID.randomUUID().toString();
    }

    public Grade(String name, String subject, Double score) {
        this.name = name;
        this.subject = subject;
        this.score = score;
        this.id = UUID.randomUUID().toString();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getScore() {
        return this.score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
