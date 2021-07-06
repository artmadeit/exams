package org.ouracademy.exams.domain;

import java.util.List;

public class PostulantQuestion {
    Question question;
    List<ExamPart> alternatives; // C, D, A, B
    ExamPart postulantAnswer; // A <= postulant edit this

    PostulantQuestion(Question question, List<ExamPart> alternatives) {
        this.question = question;
        this.alternatives = alternatives;
    }

    public boolean isCorrect() {
        return question.answer.equals(postulantAnswer);
    }

    public boolean isWrong() {
        return !isCorrect();
    }
}