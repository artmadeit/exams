package org.ouracademy.exams.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import com.google.common.collect.Lists;

import org.ouracademy.exams.domain.DateTimeRange;
import org.ouracademy.exams.domain.Inscription;
import org.ouracademy.exams.domain.InscriptionRepository;
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
    InscriptionRepository inscriptions;


    @Value
    public static class PostulantExamSummary {
        Long id;
        DateTimeRange actualRange;
        Double score;
        Boolean isApproved;

        
        public static PostulantExamSummary toDTO(PostulantExam exam) {
            if(exam == null)
                return null;
            return new PostulantExamSummary(exam.getId(), exam.getActualRange(), exam.getScore(), exam.isApproved());
        }
    }
        
    @Value
    public static class InscriptionResponse {
        Postulant postulant;
        PostulantExamSummary exam;
        
        public static InscriptionResponse toDTO(Inscription inscription) {
            var examSummary = PostulantExamSummary.toDTO(inscription.getPostulantExam());
            return new InscriptionResponse(inscription.getPostulant(), examSummary);
        }
    }

    @GetMapping("{examEventId}")
    public Page<InscriptionResponse> search(
        @PathVariable Long examEventId, final Pageable pageable,
        @RequestParam Optional<String> dni,
        @RequestParam Optional<String> programCode) {
        
        var spec = postulantExamsofExamEvent(examEventId, dni, programCode);

        return inscriptions.findAll(spec, pageable).map(InscriptionResponse::toDTO);
    }

    public static Specification<Inscription> postulantExamsofExamEvent(
        Long examEventId, Optional<String> optionalDni, Optional<String> optionalProgramCode) {
        return (root, query, cb) -> {
            // select i
            // from inscription i join i.postulant p
            // where i.event.id = :eventId
            // and p.dni = :dni and p.programCode = :programCode
            
            var i = root;
            
            var p = i.join("postulant");
            
            return cb.and(
                Lists.newArrayList(
                    cb.equal(i.get("event").get("id"), examEventId),
                    optionalDni.map(dni -> cb.equal(p.get("name"), dni)).orElse(null),
                    optionalProgramCode.map(programCode -> cb.equal(p.get("programCode"), programCode)).orElse(null)
                )
                .stream()
                .filter(Objects::nonNull)
                .toArray(Predicate[]::new)
            );
        };
    }
}
