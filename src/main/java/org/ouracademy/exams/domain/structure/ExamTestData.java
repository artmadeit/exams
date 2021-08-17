package org.ouracademy.exams.domain.structure;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Getter;

public class ExamTestData {
    
    public static ExamPart examen(int numero) {
        return new ExamTestData().build(numero);
    }
    
    @Getter int numeroPreguntas = 0;
    int numeroExamen;

    public ExamPart build(int numero) {
        // Fwd: examen
        // Examen para sabado 12
        // vie, 11 de sep 2020

        // Examen 2
        //     Capacidades Comunicativas
        //         Texto 1
        //             P1. El texto 
        //             P2. ...
        //             P3
        //             P4
        //             P5
        //         Texto 2
        //             P6. ....
        //             P7
        //             P8
        //             P9
        //             P10
        //     Capacidades Logico
        //         P11
        //         P12

        this.numeroExamen = numero;

        var examenBase = ExamPart.exam("Examen "+ numero, null);

        var section1 = ExamPart.section("CAPACIDADES COMUNICATIVAS", null);
        examenBase.addChild(section1);
        agregarTextos(section1);
        
        var section2 = ExamPart.section("CAPACIDADES LOGICO MATEMATICAS", null);
        examenBase.addChild(section2);
        agregarPreguntas(5, section2);

        var section3 = ExamPart.section("CAPACIDADES INVESTIGATIVAS", null);
        examenBase.addChild(section3);
        agregarPreguntas(5, section3);

        var section4 = ExamPart.section("PENSAMIENTO CRITICO", null);
        examenBase.addChild(section4);
        agregarPreguntas(5, section4);

        return examenBase;
    }

    private void agregarTextos(ExamPart seccion) {
        var texto1 = ExamPart.text("Texto 1, examen:" + numeroExamen, "El desarrollo...");
        seccion.addChild(texto1);
        agregarPreguntas(5, texto1);

        var texto2 = ExamPart.text("Texto 2, examen: "+ numeroExamen, "Los ingresos ...");
        seccion.addChild(texto2);
        agregarPreguntas(5, texto2);
    }

    private void agregarPreguntas(int numero, ExamPart padre) {
        IntStream.rangeClosed(1, numero).forEach(i -> {
            numeroPreguntas ++;
            var question = new Question("P" + numeroPreguntas + "-E" + numeroExamen, padre);
            agregarAlternativas(question);
            question.markAsAnswer(1);
        });
    }

    private void agregarAlternativas(Question p1) {
        List.of("A", "B", "C", "D").forEach(i -> {
            Question.alternative("alternativa " + i, p1);
        });
    }

    public static String prettyString(ExamPart exam, String identation, String res) {
        res += toString(exam, identation);
        for (var x : exam.getChilds()) {
            res += prettyString(x, identation + "\t", "");   
        }
        return res;
    }

    private static String toString(ExamPart x, String identation) {
        String result = "";

        if(x.title != null) {
            result += identation + x.title + "\n";
        }
        if(x.content != null)
            result += identation + x.content + "\n";

        return result;
    }
}
