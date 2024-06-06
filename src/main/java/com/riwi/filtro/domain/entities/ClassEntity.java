package com.riwi.filtro.domain.entities;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity(name = "class")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 255, nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable = true)
    private Boolean active;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "classId",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    private List<StudentEntity> student;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "classId",
        cascade = CascadeType.ALL,
        orphanRemoval = false
    )
    private List<LessonEntity> lesson;
}
