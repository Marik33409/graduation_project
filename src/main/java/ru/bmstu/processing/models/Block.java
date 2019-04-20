package ru.bmstu.processing.models;

import lombok.Data;

import java.util.List;

@Data
public class Block {
    private long id;
    private long fileId;
    private String description;
    private List<Double> timeArray;
    private List<Double> xrkArray;
//    TODO: дополнить массивами всех параметров. Попросить выпилить время(последнюю колонку с :)

}
