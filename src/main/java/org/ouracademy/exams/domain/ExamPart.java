package org.ouracademy.exams.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

public class ExamPart {

    
    public enum Type {
        EXAM, SECTION, TEXT, QUESTION;
    }

    @Id
    Long id;
    String content;
    Type type;

    ExamPart parent;
    private List<ExamPart> childs = new ArrayList<>();

    public List<ExamPart> getChilds() {
        return new ArrayList<>(childs);
    }
    
    public void setParent(ExamPart parent) {
        parent.childs.add(this);       
        this.parent = parent;
    }
}