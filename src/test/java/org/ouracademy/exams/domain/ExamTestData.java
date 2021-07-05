package org.ouracademy.exams.domain;

import static org.ouracademy.exams.domain.ExamPartContainer.Type;

import java.util.List;
import java.util.stream.IntStream;

public class ExamTestData {
    
    public static ExamPartContainer examen(int numero) {
        return new ExamTestData().build(numero);
    }
    
    int numeroPreguntas = 0;
    int numeroExamen;

    public ExamPartContainer build(int numero) {
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
        var examenBase = new ExamPartContainer();
        examenBase.type = Type.EXAM;
        examenBase.title = "Examen "+ numero;
        
        var section1 = new ExamPartContainer();
        section1.type = Type.SECTION;
        section1.title = "CAPACIDADES COMUNICATIVAS";
        section1.setParent(examenBase);
        agregarTextos(section1);
        
        var section2 = new ExamPartContainer();
        section2.type = Type.SECTION;
        section2.title = "CAPACIDADES LOGICO MATEMATICAS";
        section2.setParent(examenBase);
        agregarPreguntas(5, section2);

        var section3 = new ExamPartContainer();
        section3.type = Type.SECTION;
        section3.title = "CAPACIDADES INVESTIGATIVAS";
        section3.setParent(examenBase);
        agregarPreguntas(5, section3);

        var section4 = new ExamPartContainer();
        section4.type = Type.SECTION;
        section4.title = "PENSAMIENTO CRITICO";
        section4.setParent(examenBase);
        agregarPreguntas(5, section4);

        return examenBase;
    }

    private void agregarTextos(ExamPart seccion) {
        var texto1 = new ExamPartContainer();
        texto1.type = Type.TEXT;
        texto1.title = "Texto 1";
        texto1.content = "El desarrollo...";
        texto1.setParent(seccion);
        agregarPreguntas(5, texto1);

        var texto2 = new ExamPartContainer();
        texto2.type = Type.TEXT;
        texto2.title = "Texto 2";
        texto2.content = "Los ingresos ...";
        texto2.setParent(seccion);
        agregarPreguntas(5, texto2);
    }

    private void agregarPreguntas(int numero, ExamPart padre) {
        IntStream.rangeClosed(1, numero).forEach(i -> {
            numeroPreguntas ++;
            var p1 = new Pregunta();
            p1.content = "Pregunta " + numeroPreguntas + ", examen:" + numeroExamen;
            p1.setParent(padre);
            agregarAlternativas(p1);
        });
    }

    private void agregarAlternativas(Pregunta p1) {
        List.of("A", "B", "C", "D").forEach(i -> {
            var a1 = new ExamPart();
            a1.content = "alternativa " + i;
            a1.setParent(p1);
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

        if(x instanceof ExamPartContainer ex) {
            result += identation + ex.title + "\n";
        }
        if(x.content != null)
            result += identation + x.content + "\n";

        return result;
    }
}
