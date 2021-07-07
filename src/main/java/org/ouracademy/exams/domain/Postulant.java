package org.ouracademy.exams.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.ouracademy.exams.event.ExamEvent;

@Entity
public class Postulant {
    @Id
    Long id;
    String dni;
    String name;
    String lastName;
    String motherLastName;
    String code;
    String programCode;
    String upgCode;

    public PostulantExam start(ExamEvent event, List<PostulantQuestion> questions) {
        return PostulantExam.builder()
            .questions(questions)
            .postulant(this)
            .event(event).build();
    }
}