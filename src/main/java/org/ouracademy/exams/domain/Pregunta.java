package org.ouracademy.exams.domain;

import java.util.List;

public class Pregunta extends TextContent {
    Double score;
    TextContent respuesta;
    
    List<TextContent> alternativas() {
        return this.childs;
    }
}
