package org.ouracademy.exams.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

public class TextContent {
    @Id
    Long id;
    String contenido;
    
    TextContent parent;
    List<TextContent> childs = new ArrayList<>();

    
    public void setParent(TextContent parent) {
        parent.childs.add(this);       
        this.parent = parent;
    }
}