package org.ouracademy.exams.api;

import org.ouracademy.exams.domain.DateTimeRange;
import org.ouracademy.exams.domain.PostulantExam;
import org.ouracademy.exams.domain.PostulantExamRepository;
import org.ouracademy.exams.domain.event.ExamEvent;
import org.ouracademy.exams.domain.event.ExamEventRepository;
import org.ouracademy.exams.domain.postulant.Postulant;
import org.ouracademy.exams.utils.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@RestController
@RequestMapping("/exam-results")
public class ResultsController {
    
    ExamEventRepository examEvents;
    PostulantExamRepository postulantExamRepository;


    @Value
    public static class PostulantExamSummary {
        Long id;
        Postulant postulant;
        DateTimeRange actualRange;
        Double score;
        Boolean isApproved;

        public static PostulantExamSummary toDTO(PostulantExam exam) {
            return new PostulantExamSummary(exam.getId(), exam.getPostulant(), exam.getActualRange(), exam.getScore(), exam.isApproved());
        }
    }

    @GetMapping("{examEventId}")
    public Page<PostulantExamSummary> search(@PathVariable Long examEventId, final Pageable pageable) {
        var examEvent = examEvents.findById(examEventId)
            .orElseThrow(() -> new NotFoundException(ExamEvent.class, examEventId));
        return postulantExamRepository.findByEvent(examEvent, pageable).map(PostulantExamSummary::toDTO);
    }
}
