package org.ouracademy.exams.api;

import org.ouracademy.exams.domain.PostulantExam;
import org.ouracademy.exams.domain.PostulantExamRepository;
import org.ouracademy.exams.domain.event.ExamEvent;
import org.ouracademy.exams.domain.event.ExamEventRepository;
import org.ouracademy.exams.utils.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/exam-results")
public class ResultsController {
    
    ExamEventRepository examEvents;
    PostulantExamRepository postulantExamRepository;

    @GetMapping("{examEventId}")
    public Page<PostulantExam> search(@PathVariable Long examEventId, final Pageable pageable) {
        var examEvent = examEvents.findById(examEventId)
            .orElseThrow(() -> new NotFoundException(ExamEvent.class, examEventId));
        return postulantExamRepository.findByEvent(examEvent, pageable);
    }

            //  {{ `*${p.dni}` }}</td>
            //   *{{ p.codigo_postulante }}</td>
            // {{ `${p.apellido_paterno} ${p.apellido_materno}, ${p.nombre}` }}
            //   {{ p.codigo_upg }}</td>
            //   *{{ p.codigo_programa }}</td>
            //   {{ p.puntaje }}</td>
            //   {{ p.timeStart | time }}</td>
            //   {{ p.timeEnd | time }}</td>
}
