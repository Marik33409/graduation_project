package ru.bmstu.processing.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Data
@Entity(name = "Map")
@Table(name = "map", schema = "public", catalog = "db_avia")
public class Map {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "block_id")
    private Integer block_id;

    @Basic
    @Column(name = "key")
    private String key;

    @Basic
    @Column(name = "value")
    private Float value;

//    @ElementCollection
//    @MapKeyColumn(name="key")
//    @Column(name="value")
//    private java.util.Map<String, List<Double>> myMap = new HashMap<>();
}
