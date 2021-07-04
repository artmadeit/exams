package org.ouracademy.exams.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

public class ExamPart {

    
    public enum Type {
        EXAM, SECTION, TEXT;
    }

    @Id
    Long id;
    String contenido;
    Type type;

    ExamPart parent;
    List<ExamPart> childs = new ArrayList<>();


    
    public void setParent(ExamPart parent) {
        parent.childs.add(this);       
        this.parent = parent;
    }
}