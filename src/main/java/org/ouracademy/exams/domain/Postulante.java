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

    public PostulantExam start(ExamSpecification specification, List<PostulantExam.PostulantQuestion> questions) {
        return PostulantExam.builder()
            .questions(questions)
            .postulante(this)
            .specification(specification).build();
    }
}