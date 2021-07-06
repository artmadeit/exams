package org.ouracademy.exams.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Postulante {
    @Id
    Long id;
    String dni;
    String nombre;
    String apellidoPaterno;
    String apellidoMaterno;
    String codigoPostulante;
    String codigoPrograma;
    String codigoUpg;

    public PostulantExam start(ExamEvent event, List<PostulantQuestion> questions) {
        return PostulantExam.builder()
            .questions(questions)
            .postulant(this)
            .event(event).build();
    }
}