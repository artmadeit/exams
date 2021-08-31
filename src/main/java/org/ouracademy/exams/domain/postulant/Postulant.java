package org.ouracademy.exams.domain.postulant;

import java.util.Optional;

import javax.persistence.Entity;

import org.ouracademy.exams.auth.UserAccountRole;
import org.ouracademy.exams.auth.UserAccount;
import org.ouracademy.exams.domain.PostulantExam;

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
    String code;

    /**
     * @apiNote jpa only
     */
    Postulant(){ }

    @Builder
    public Postulant(
        String code,
        String dni,
        String password,
        String firstName,
        String lastName,
        String motherLastName,
        String programCode,
        String upgCode
    ) {
        super(dni, password, UserAccountRole.POSTULANT);
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.motherLastName = motherLastName;
        this.programCode = programCode;
        this.upgCode = upgCode;
    }
    
    public String getDni() {
        return this.getName();
    }
    
    public boolean isTaker(Optional<PostulantExam> postulantExam) {
        return postulantExam
            .map(exam -> this.equals(exam.getPostulant()))
            .orElse(false);
    }
}