package ru.bmstu.processing.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "Experiment")
@Table(name = "experiment", schema = "public", catalog = "db_avia")
public class Experiment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "file_name")
    private String file_name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "experiment_id", nullable = false)
    private List<Experiment> planTasksEntities;



}
