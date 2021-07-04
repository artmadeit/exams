package org.ouracademy.exams.domain;

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

    public PostulantExam start(ExamPartContainer exam, ExamSpecification specification) {
        return PostulantExam.builder()
            .examPart(exam)
            .postulante(this)
            .specification(specification).build();
    }
}