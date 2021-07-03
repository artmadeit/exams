package org.ouracademy.exams.domain;

import static org.ouracademy.exams.domain.ExamPart.Type;

import java.util.List;
import java.util.stream.IntStream;

public class ExamTestData {
    
    public static ExamPart examen(int numero) {
        return new ExamTestData().build(numero);
    }
    
    int numeroPreguntas = 0;

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

        var examenBase = new ExamPart();
        examenBase.type = Type.EXAM;
        examenBase.titulo = "Examen "+ numero;
        
        var section1 = new ExamPart();
        section1.type = Type.SECTION;
        section1.titulo = "capacidades comunicativas";
        section1.setParent(examenBase);
        agregarTextos(section1);
        
        var section2 = new ExamPart();
        section2.type = Type.SECTION;
        section2.titulo = "capacidades lógico matematicas";
        section2.setParent(examenBase);
        agregarPreguntas(5, section2);

        var section3 = new ExamPart();
        section3.type = Type.SECTION;
        section3.titulo = "capacidades investigativas";
        section3.setParent(examenBase);
        agregarPreguntas(5, section3);

        var section4 = new ExamPart();
        section4.type = Type.SECTION;
        section4.titulo = "pensamiento crítico";
        section4.setParent(examenBase);
        agregarPreguntas(5, section4);

        return examenBase;
    }

    private void agregarTextos(TextContent seccion) {
        var texto1 = new ExamPart();
        texto1.type = Type.TEXT;
        texto1.titulo = "Texto 1";
        texto1.contenido = "El desarrollo...";
        texto1.setParent(seccion);
        agregarPreguntas(5, texto1);

        var texto2 = new ExamPart();
        texto2.type = Type.TEXT;
        texto2.titulo = "Texto 2";
        texto2.contenido = "Los ingresos ...";
        texto2.setParent(seccion);
        agregarPreguntas(5, texto2);
    }

    private void agregarPreguntas(int numero, TextContent padre) {
        IntStream.rangeClosed(1, numero).forEach(i -> {
            numeroPreguntas ++;
            var p1 = new Pregunta();
            p1.contenido = "Pregunta " + numeroPreguntas;
            p1.setParent(padre);
            agregarAlternativas(p1);
        });
    }

    private void agregarAlternativas(Pregunta p1) {
        List.of("A", "B", "C", "D").forEach(i -> {
            var a1 = new TextContent();
            a1.contenido = "alternativa " + i;
            a1.setParent(p1);
        });
    }

    public static void prettyPrint(TextContent exam, String b) {
        prettyPrintR(exam, b);
        exam.childs.forEach(x -> {
            prettyPrint(x, b + "\t");
        });
    }

    private static void prettyPrintR(TextContent x, String b) {
        if(x instanceof ExamPart ex) {
            System.out.println(b + ex.titulo);
        }
        if(x.contenido != null)
            System.out.println(b + x.contenido);
    }
}
