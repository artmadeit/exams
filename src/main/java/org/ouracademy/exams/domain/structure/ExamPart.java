package org.ouracademy.exams.domain.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

import org.springframework.lang.Nullable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class ExamPart {

    
    public enum Type {
        EXAM {
            @Override
            List<Type> allowedChilds() {
                return List.of(SECTION, TEXT, QUESTION);
            }
        }, 
        // section y text <= question container
        SECTION {
            @Override
            List<Type> allowedChilds() {
                return List.of(SECTION, TEXT, QUESTION);
            }
        }, 
        TEXT {
            @Override
            List<Type> allowedChilds() {
                return List.of(QUESTION);
            }
        },         
        QUESTION {
            @Override
            List<Type> allowedChilds() {
                return List.of(ALTERNATIVE);
            }
        }, 
        ALTERNATIVE; // <= measurable

        List<Type> allowedChilds() {
            return Collections.emptyList();
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Setter
    String title;
    
    @Setter
    // Not using @Lob because we need always put @Transactional
    // https://stackoverflow.com/questions/3164072/large-objects-may-not-be-used-in-auto-commit-mode
    @Column(columnDefinition="TEXT")
    String content;

    @Enumerated(EnumType.STRING)
    Type type;

    @ManyToOne
    ExamPart parent;

    @OneToMany(mappedBy="parent", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderColumn
    private List<ExamPart> childs = new ArrayList<>();

    public List<ExamPart> getChilds() {
        return new ArrayList<>(childs);
    }

    public static Exam exam(String title, @Nullable String description) {
        return new Exam(title, description);
    }

    /**
     * @apiNote jpa only
     */
    ExamPart() {}

    public ExamPart(Type type, String title, String content) {
        this.type = type;
        this.title = title;
        this.content = content;
    }

    
    public void addChild(ExamPart child) {
        if(!this.type.allowedChilds().contains(child.type)) {
            throw new IllegalArgumentException("child type invalid for type:" + type + ", child.type:" + child.type);
        }
        
        this.childs.add(child);
        child.parent = this;
    }
}