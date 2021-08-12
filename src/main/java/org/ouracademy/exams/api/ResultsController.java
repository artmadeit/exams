package org.ouracademy.exams.api;

import java.util.Optional;

import javax.persistence.criteria.JoinType;

import org.ouracademy.exams.domain.DateTimeRange;
import org.ouracademy.exams.domain.PostulantExam;
import org.ouracademy.exams.domain.PostulantExamRepository;
import org.ouracademy.exams.domain.event.ExamEventRepository;
import org.ouracademy.exams.domain.postulant.Postulant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Page<PostulantExamSummary> search(
        @PathVariable Long examEventId, final Pageable pageable,
        @RequestParam Optional<String> dni,
        @RequestParam Optional<String> programCode) {
        
        var spec = postulantExamsofExamEvent(examEventId, dni, programCode);

        return postulantExamRepository.findAll(spec, pageable).map(PostulantExamSummary::toDTO);
    }

    public static Specification<PostulantExam> postulantExamsofExamEvent(
        Long examEventId, Optional<String> optionalDni, Optional<String> optionalProgramCode) {
        return (root, query, cb) -> {
            // select e.*
            // from postulant_exam e right join postulant p
            // where e.event_id = :eventId
            // and p.dni = :dni and p.programCode = :programCode
            
            var exam = root;
            var p = exam.join("postulant", JoinType.RIGHT);
            
            return cb.and(
                cb.equal(exam.get("event").get("id"), examEventId),
                optionalDni.map(dni -> cb.equal(p.get("dni"), dni)).orElse(null),
                optionalProgramCode.map(programCode -> cb.equal(p.get("programCode"), programCode)).orElse(null)
            );
        };
    }
}
