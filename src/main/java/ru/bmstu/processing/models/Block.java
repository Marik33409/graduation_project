package ru.bmstu.processing.models;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Block {
    private long id;
    private long fileId;
    private String description;
    private Map<String, List<Double>> paramsMap;
}
