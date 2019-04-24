package ru.bmstu.processing.parsing;

import lombok.val;
import ru.bmstu.processing.models.Block;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ExperimentParser {
    public void parse(String filePath){

        Map<String, List<Double>> paramsMap = new LinkedHashMap<>();
        List<Block> blocksArray = new ArrayList<>();
        double firstTime = 0;

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();

            while (line != null) {
                if (line.equals(">>>>>>>>>>")) {   //закончили сохранение
                    Block block = new Block();

                    // TODO: навести красоту, присобачить round()
                    final double firstTimeConst = firstTime;
                    List<Double> times = paramsMap.get("Time_SPNM");
                    times = times.stream().map(elem -> elem - firstTimeConst).collect(Collectors.toList());//round
                    //paramsMap.replace("Time_SPNM", times);

                    block.setParamsMap(new LinkedHashMap<>(paramsMap));

//                    for (val entrySet : paramsMap.entrySet()) {
//                        System.out.println(entrySet.getKey() + "  " + entrySet.getValue());
//                    }


                    paramsMap.clear();
                    blocksArray.add(block);
                } else {
                    try {

                        Stream<String> stringStream = Arrays.stream(line.trim().split("\\s+"));
                        stringStream = skipLastElements(stringStream, 1);
                        String[] columnArray = stringStream
                                .toArray(String[]::new);
                        if (columnArray[1].equals("Time_SPNM")){
                            for (int i = 1; i < columnArray.length-1; i++) {
                                paramsMap.put(columnArray[i], new ArrayList<>());
                            }
                        }


                        // TODO: оптимизировать
                        Stream<String> valuesStream = Arrays.stream(line.trim().split("\\s+"));
                        valuesStream = skipLastElements(valuesStream, 1);
                        Double[] tickArray = valuesStream
                                .map(Double::parseDouble)
                                .toArray(Double[]::new);

                        //TODO: доработать обнуление времени(приведение времени к нулю
                        if (tickArray[0].equals(1.0)) {
                            firstTime = tickArray[1];
//                            timeArray.add(0.0);
//                            xrKArray.add(tickArray[2]);
                        } else {
                            int i = 1;
                            for (val entrySet : paramsMap.entrySet()) {
                                entrySet.getValue().add(tickArray[i]);
                                i++;
                            }
//                            timeArray.add(round(timeArray.get(timeArray.size() - 1) + 0.02, 2));
//                            xrKArray.add(tickArray[2]);
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

//        for (Block block : blocksArray) {
//            System.out.println(block);
//        }
        for (val entrySet : blocksArray.get(2).getParamsMap().entrySet()) {
            System.out.println(entrySet.getKey() + "  " + entrySet.getValue());
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
}
