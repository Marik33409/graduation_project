package ru.bmstu.processing.parsing;

import ru.bmstu.processing.models.Block;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
//TODO
public class ExperimentParser {
    public void parse(String filePath){
        List<Double> timeArray = new ArrayList<>(), xrkArray = new ArrayList<>(); // добавить саммивы под другие переменные

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();

            while (line != null) {
                if (line.equals(">>>>>>>>>>")) {   //закончили сохранение
                    //block = new block
                    //block.setArray(timeArray)
                    //обнуляем timeArray...
                    break; //убрать и заменить на сохранение в базу
                } else {
                    try {
                        Stream<String> stringStream = Arrays.stream(line.trim().split("\\s+"));
                        stringStream = skipLastElements(stringStream, 1);
                        Double[] tickArray = stringStream
                                .map(Double::parseDouble)
                                .toArray(Double[]::new);

                        if (tickArray[0].equals(1.0)) {
                            timeArray.add(0.0);
                            xrkArray.add(tickArray[2]);
                        } else {
                            timeArray.add(round(timeArray.get(timeArray.size() - 1) + 0.02, 2));
                            xrkArray.add(tickArray[2]);
                        }

                    } catch (Exception e) {

                    }
                }
                line = reader.readLine();
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < timeArray.size(); i++) {
            System.out.println(timeArray.get(i) + "      " + xrkArray.get(i));
        }
    }




    static <T> Stream<T> skipLastElements(Stream<T> s, int count) {
        if(count<=0) {
            if(count==0) return s;
            throw new IllegalArgumentException(count+" < 0");
        }
        ArrayDeque<T> pending=new ArrayDeque<T>(count+1);
        Spliterator<T> src=s.spliterator();
        return StreamSupport.stream(new Spliterator<T>() {
            public boolean tryAdvance(Consumer<? super T> action) {
                while(pending.size()<=count && src.tryAdvance(pending::add));
                if(pending.size()>count) {
                    action.accept(pending.remove());
                    return true;
                }
                return false;
            }
            public Spliterator<T> trySplit() {
                return null;
            }
            public long estimateSize() {
                return src.estimateSize()-count;
            }
            public int characteristics() {
                return src.characteristics();
            }
        }, false);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static void main(String[] args) {
        skipLastElements(Stream.of("foo", "bar", "baz", "hello", "world"), 2)
                .forEach(System.out::println);
    }

//    public static void main(String[] args) {
//        String test = "adgsfgdf rteerre      bmnmbm   ui t    ggygymng";
//        for (String substring : test.split("\\s+")) {
//            System.out.println(substring);
//        }
//    }
}
