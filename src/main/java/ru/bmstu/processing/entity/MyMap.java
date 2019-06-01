package ru.bmstu.processing.entity;

import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Data
@Entity(name = "Map")
@Table(name = "map", schema = "public", catalog = "db_avia")
public class MyMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

//    @Column(name = "block_id")
//    private Integer block_id;

    @Basic
    @Column(name = "key")
    private String key;

    @Type(type = "ru.bmstu.processing.utils.GenericArrayUserType")
    @Column(name = "value")
    private Double[] value;


//    @ElementCollection
//    @MapKeyColumn(name="key")
//    @Column(name="value")
//    private java.util.MyMap<String, List<Double>> myMap = new HashMap<>();
}
