package org.ouracademy.exams.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class ExamTests {
    @Test
    public void test_crea_un_examen_word() {
        // Fwd: examen
        // Examen para sabado 12
        // vie, 11 de sep 2020

        var examenBase = new ExamPart();
        examenBase.titulo = "Examen 2";
        
        var capacidadesComunicativas = new ExamPart();
        capacidadesComunicativas.titulo = "capacidades comunicativas";
        capacidadesComunicativas.padre = examenBase;

        var texto1 = new ExamPart();
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
        texto2.titulo = "Texto 2";
        texto2.contenido = "Los ingresos ...";
        texto2.padre = capacidadesComunicativas;

        var p6 = new Pregunta();
        p6.contenido = "Respecto al sistema financiero ";
        p6.padre = texto2;
        // ...

        var capacidadesLogico = new ExamPart();
        capacidadesLogico.titulo = "capacidades lógico matematicas";
        capacidadesLogico.padre = examenBase;

        var p11 = new Pregunta();
        p11.contenido = "Un albañil";
        p11.padre = capacidadesLogico;

        assertNotNull(examenBase);
    }

    @Test
    public void test_iniciar_un_examen() {
        var postulante = new Postulante();
        var specification = new ExamSpecification();
        var examenAleatorio = new ExamPart();

        var postulantExam = examenAleatorio.start(postulante, specification);
        assertNotNull(postulantExam);
    }

    
}
