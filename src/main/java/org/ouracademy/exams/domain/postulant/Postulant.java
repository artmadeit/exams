package org.ouracademy.exams.domain.postulant;

import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;

import org.ouracademy.exams.auth.UserAccount;
import org.ouracademy.exams.domain.PostulantExam;
import org.ouracademy.exams.domain.PostulantQuestion;
import org.ouracademy.exams.domain.event.ExamEvent;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Entity
@EqualsAndHashCode(callSuper = true)
public class Postulant extends UserAccount { 
    // TODO: codigo_postulante y dni unique

    String firstName;
    String lastName;
    String motherLastName;
    String programCode;
    String upgCode;

    /**
     * @apiNote jpa only
     */
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
        super(dni, code, Role.POSTULANT);
        this.firstName = firstName;
        this.lastName = lastName;
        this.motherLastName = motherLastName;
        this.programCode = programCode;
        this.upgCode = upgCode;
    }
    
    public String getDni() {
        return this.getName();
    }
    
    public String getCode() {
        return this.getPassword();
    }
    
    public boolean isTaker(Optional<PostulantExam> postulantExam) {
        return postulantExam
            .map(exam -> this.equals(exam.getPostulant()))
            .orElse(false);
    }
}