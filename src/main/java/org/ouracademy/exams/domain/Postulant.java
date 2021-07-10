package org.ouracademy.exams.domain;

import java.util.List;

import javax.persistence.Entity;

import org.ouracademy.exams.auth.UserAccount;
import org.ouracademy.exams.event.ExamEvent;

import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
public class Postulant extends UserAccount { 
    String firstName;
    String lastName;
    String motherLastName;
    String programCode;
    String upgCode;

    Postulant(){ }

    @Builder
    public Postulant(
        String code,
        String dni,
        String firstName,
        String lastName,
        String motherLastName,
        String programCode,
        String upgCode
    ) {
        super(code, dni);
        this.firstName = firstName;
        this.lastName = lastName;
        this.motherLastName = motherLastName;
        this.programCode = programCode;
        this.upgCode = upgCode;
    }

    public PostulantExam start(ExamEvent event, List<PostulantQuestion> questions) {
        if (!event.hasStarted())
            throw new ExamEvent.NotStartedException(event);

        if (event.hasEnded())
            throw new ExamEvent.EndedException(event);

        return PostulantExam.builder()
            .questions(questions)
            .postulant(this)
            .event(event).build();
    }

    public String getCode() {
        return this.getName();
    }

    public String getDni() {
        return this.getPassword();
    }
}