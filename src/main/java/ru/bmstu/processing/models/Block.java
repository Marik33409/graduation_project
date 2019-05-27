package ru.bmstu.processing.models;

import lombok.Data;

import java.util.List;

@Data
public class Block {
    private long id;
    private long fileId;
    private String description;
    private List<Double> timeArray;
    private List<Double> xrKArray;
    private List<Double> xrTArray;
    private List<Double> rddrArray;
    private List<Double> aShGArray;
    private List<Double> ShNVArray;
    private List<Double> HaArray;
    private List<Double> VArray;
    private List<Double> VprArray;
    private List<Double> VyArray;
    private List<Double> kurArray;
    private List<Double> gamArray;
    private List<Double> tngArray;
    private List<Double> psiArray;
    private List<Double> tetArray;
    private List<Double> alfArray;
    private List<Double> betArray;
    private List<Double> snosArray;
    private List<Double> wxArray;
    private List<Double> wyArray;
    private List<Double> wzArray;
    private List<Double> ax1Array;
    private List<Double> ay1Array;
    private List<Double> az1Array;
    private List<Double> PstArray;
    private List<Double> qArray;
    private List<Double> GpArray;
    private List<Double> TnvArray;
    private List<Double> TrvArray;
    private List<Double> figArray;
    private List<Double> lagArray;


//    TODO: дополнить массивами всех параметров. Попросить выпилить время(последнюю колонку с :)


    // (iSOK), Time_SPNM, xrK, xrT, rddr, aShG,
    // ShNV, Ha, V, Vpr, Vy,
    // kur, gam, tng, psi, tet,
    // alf, bet, snos, wx, wy,
    // wz, ax1, ay1, az1, Pst,
    // q, Gp, Tnv, Trv, fig,
    // lag, (SO), (HH:MM:SS:MS)
}
