package ru.bmstu.processing.parsing;

import ru.bmstu.processing.models.Block;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ExperimentParser {
    public void parse(String filePath){
        List<Double> timeArray = new ArrayList<>(), xrKArray = new ArrayList<>(),
                xrTArray = new ArrayList<>(), rddrArray = new ArrayList<>(),
                aShGArray = new ArrayList<>(), ShNVArray = new ArrayList<>(),
                HaArray = new ArrayList<>(), VArray = new ArrayList<>(),
                VprArray = new ArrayList<>(), VyArray = new ArrayList<>(),
                kurArray = new ArrayList<>(), gamArray = new ArrayList<>(),
                tngVArray = new ArrayList<>(), psiArray = new ArrayList<>(),
                tetArray = new ArrayList<>(), alfArray = new ArrayList<>(),
                betArray = new ArrayList<>(), snosArray = new ArrayList<>(),
                wxArray = new ArrayList<>(), wyVArray = new ArrayList<>(),
                wz1Array = new ArrayList<>(), ax1VArray = new ArrayList<>(),
                ay1Array = new ArrayList<>(), az1Array = new ArrayList<>(),
                PstArray = new ArrayList<>(), qArray = new ArrayList<>(),
                GpArray = new ArrayList<>(), TnvArray = new ArrayList<>(),
                figArray = new ArrayList<>(), lagArray = new ArrayList<>();//DONE: Добавлены все основные параметры полета -31 шт

        // (iSOK), Time_SPNM, xrK, xrT, rddr, aShG,
        // ShNV, Ha, V, Vpr, Vy,
        // kur, gam, tng, psi, tet,
        // alf, bet, snos, wx, wy,
        // wz, ax1, ay1, az1, Pst,
        // q, Gp, Tnv, Trv, fig,
        // lag, (SO), (HH:MM:SS:MS)

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = reader.readLine();

            while (line != null) {
                if (line.equals(">>>>>>>>>>")) {   //закончили сохранение
                    //block = new block;
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
                            xrKArray.add(tickArray[2]);
                        } else {
                            timeArray.add(round(timeArray.get(timeArray.size() - 1) + 0.02, 2));
                            xrKArray.add(tickArray[2]);
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
            System.out.println(timeArray.get(i) + "      " + xrKArray.get(i));
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
