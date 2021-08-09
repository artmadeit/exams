package org.ouracademy.exams.domain.structure;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
public class ExamPart {

    
    public enum Type {
        EXAM, SECTION, TEXT, QUESTION, ALTERNATIVE;

        // un examen => secciones
        // una seccion => textos | preguntas
        // una texto => preguntas

        // question tiene contenido
        // alternativa tiene contenido
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter
    String title;
    
    @Setter
    String content;

    @Enumerated(EnumType.STRING)
    Type type;

    @ManyToOne
    ExamPart parent;

    @OneToMany(mappedBy="parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamPart> childs = new ArrayList<>();

    @LastModifiedDate
    public LocalDateTime lastModifiedDate;

    public List<ExamPart> getChilds() {
        return new ArrayList<>(childs);
    }
    
    public void setParent(ExamPart newParent) {
        if(this.parent != null) {
            this.parent.childs.removeIf(obj -> obj.id.equals(this.id));
        }
            
        newParent.childs.add(this);
        this.parent = newParent;
    }


    public static ExamPart exam(String title, @Nullable String description) {
        return new ExamPart(Type.EXAM, title, description);
    }

    public static ExamPart section(String title, @Nullable String description, ExamPart parentExam) {
        if(!parentExam.type.equals(Type.EXAM))
            throw new IllegalArgumentException("parent has to be an exam");

        var result = new ExamPart(Type.SECTION, title, description);
        result.setParent(parentExam);
        return result;
    }

    public static ExamPart text(String title, @NotNull String content, ExamPart parent) {
        if(!parent.type.equals(Type.SECTION) && !parent.type.equals(Type.EXAM))
            throw new IllegalArgumentException("parent has to be a section or exam");
        
        var result = new ExamPart(Type.TEXT, title, content);
        result.setParent(parent);
        return result;
    }


    /**
     * @apiNote jpa only
     */
    ExamPart() {}

    ExamPart(Type type, String title, String content) {
        this.type = type;
        this.title = title;
        this.content = content;
    }
}