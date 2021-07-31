package org.ouracademy.exams.domain.structure;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.lang.Nullable;

import lombok.Getter;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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
    
    String title;
    
    String content;

    @Enumerated(EnumType.STRING)
    Type type;

    @ManyToOne
    ExamPart parent;

    @OneToMany(mappedBy="parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamPart> childs = new ArrayList<>();

    public List<ExamPart> getChilds() {
        return new ArrayList<>(childs);
    }
    
    public void setParent(ExamPart parent) {
        parent.childs.add(this);       
        this.parent = parent;
    }


    public static ExamPart exam(String title, @Nullable String description) {
        return generic(Type.EXAM, title, description);
    }

    public static ExamPart section(String title, @Nullable String description, ExamPart exam) {
        var result = generic(Type.SECTION, title, description);
        result.setParent(exam);
        return result;
    }

    public static ExamPart text(String title, String content, ExamPart exam) {
        var result = generic(Type.TEXT, title, content);
        result.setParent(exam);
        return result;
    }

    private static ExamPart generic(Type type, String title, String description) {
        var examenBase = new ExamPart();
        examenBase.type = type;
        examenBase.title = title;
        examenBase.content = description;
        return examenBase;
    }
}