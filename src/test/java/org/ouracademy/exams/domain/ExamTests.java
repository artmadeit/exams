package org.ouracademy.exams.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.ouracademy.exams.domain.ExamPart.Type;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ExamTests {
    @Test
    public void test_crea_un_examen_word() {
        assertNotNull(examen2());
    }

    public ExamPart examen2() {
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
        examenBase.titulo = "Examen 2";
        
        var capacidadesComunicativas = new ExamPart();
        examenBase.type = Type.SECTION;
        capacidadesComunicativas.titulo = "capacidades comunicativas";
        capacidadesComunicativas.padre = examenBase;

        var texto1 = new ExamPart();
        examenBase.type = Type.TEXT;
        texto1.titulo = "Texto 1";
        texto1.contenido = "El desarrollo...";
        texto1.padre = capacidadesComunicativas;

        var p1 = new Pregunta();
        p1.contenido = "El texto aborda, respecto ";
        p1.padre = texto1;

        var a1 = new TextContent();
        a1.contenido = "La importancia..";
        a1.padre = p1;
        var a2 = new TextContent();
        a2.contenido = "La accion..";
        a2.padre = p1;

        var texto2 = new ExamPart();
        examenBase.type = Type.TEXT;
        texto2.titulo = "Texto 2";
        texto2.contenido = "Los ingresos ...";
        texto2.padre = capacidadesComunicativas;

        var p6 = new Pregunta();
        p6.contenido = "Respecto al sistema financiero ";
        p6.padre = texto2;
        // ...

        var capacidadesLogico = new ExamPart();
        examenBase.type = Type.SECTION;
        capacidadesLogico.titulo = "capacidades lógico matematicas";
        capacidadesLogico.padre = examenBase;

        var p11 = new Pregunta();
        p11.contenido = "Un albañil";
        p11.padre = capacidadesLogico;
        return examenBase;
    }

    @Test
    public void test_iniciar_un_examen() {
        var postulante = new Postulante();
        var specification = new ExamSpecification();
        var examenAleatorio = new ExamPart();

        var postulantExam = examenAleatorio.start(postulante, specification);
        assertNotNull(postulantExam);
    }

    public void test_generar_examen_aleatorio() {
        var spec = new ExamRandomBuilder.Specification();
        spec.of(Type.SECTION, "CAPACIDADES COMUNICATIVAS")
            .has(() -> {
                var sectionSpec = new ExamRandomBuilder.Specification();
                sectionSpec.of(Type.TEXT).has(5, Pregunta.class);
                sectionSpec.of(Type.TEXT).has(5, Pregunta.class);
                return sectionSpec;
            });
        spec.of(Type.SECTION, "CAPACIDADES LOGICO MATEMATICAS").has(5, Pregunta.class);
        spec.of(Type.SECTION, "CAPACIDADES INVESTIGATIVAS").has(5, Pregunta.class);
        spec.of(Type.SECTION, "PENSAMIENTO CRITICO").has(5, Pregunta.class);
        
        var randomExam = new ExamRandomBuilder().from(
            List.of(examen1(), examen2()), spec
        );
        
        assertNotNull(randomExam);
        assertTrue(spec.meet(randomExam));
        
    }

    private ExamPart examen1() {
        return null;
    }

    
}
