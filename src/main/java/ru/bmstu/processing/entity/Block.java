package ru.bmstu.processing.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Block")
@Table(name = "block", schema = "public", catalog = "db_avia")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic
    @Column(name = "experiment_id")
    private Integer experiment_id;

    @Basic
    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "block_id", nullable = false)
    java.util.Set<MyMap> paramsMyMap;
}
