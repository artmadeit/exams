package org.ouracademy.exams.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

public class TextContent {
    @Id
    Long id;
    String contenido;
    
    TextContent padre;
    List<TextContent> childs = new ArrayList<>();
}