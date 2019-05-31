package ru.bmstu.processing;

import ru.bmstu.processing.parsing.ExperimentParser;

public class  Application {
    public static void main(String[] args) {
//        System.out.println("Hello world!");
        ExperimentParser experimentParser = new ExperimentParser();
        experimentParser.parse("/Users/ruabymg/IdeaProjects/graduation_project/src/main/resources/SOK_MSKBO_Exp_1.txt");
    }
}

//TODO: подключить базу, научиться сохранять в нее HashMap