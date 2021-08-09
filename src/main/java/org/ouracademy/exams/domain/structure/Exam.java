package org.ouracademy.exams.domain.structure;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Exam extends ExamPart {
    
    @LastModifiedDate
    LocalDateTime lastModifiedDate;

    Exam(String title, String content) {
        super(Type.EXAM, title, content);
    }
}
