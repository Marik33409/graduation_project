package ru.bmstu.processing.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "block_id", nullable = false, insertable=false, updatable= false)
////    java.util.Map<String, List<Double>> paramsMap;
//    Set<Map> maps;

    @ElementCollection(fetch = FetchType.EAGER)
//    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
//            orphanRemoval = true, targetEntity = MyList.class)
    @CollectionTable(name = "map")
//    @JoinTable(name = "map", joinColumns = @JoinColumn(name = "block_id"))
    @MapKeyColumn(name="key")
    @Column(name="value")
    private java.util.Map<String, MyList> myMap = new HashMap<>();

    //    @MapKey(name = "block_id")
//    private java.util.Map<>

//    @Data
//    private static class MyList {
//        private List<Float> list = new ArrayList<>();
//    }

    @Data

    public static class MyList {

//        @ElementCollection
        private List<Float> list = new ArrayList<>();

    }

}
