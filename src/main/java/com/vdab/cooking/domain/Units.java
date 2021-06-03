package com.vdab.cooking.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.EnumSet;

@Getter
public enum Units {
    ML("1", "Milliliters", "volume"),
    CL("2", "Centiliters", "volume"),
    DL("3", "Deciliters", "volume"),
    L("4", "Liters", "volume"),
    GR("5", "Grams", "weight"),
    KG("6", "Kilograms", "weight"),
    TBSP("7", "Tablespoon", "amount"),
    CSP("8", "Coffee spoon", "amount"),
    CUP("9", "Cup", "amount"),
    PINCH("10", "Pinch", "amount"),
    DROP("11", "Drop", "amount"),
    PIECE("12", "Piece", "amount");


    private final String id;
    private final String unitWrittenInFull;
    private final String measuringType;


    Units(String id, String unitWrittenInFull, String measuringType) {
        this.id = id;
        this.unitWrittenInFull = unitWrittenInFull;
        this.measuringType = measuringType;
    }


    public static EnumSet<Units> getMeasuringTypes() {
        return EnumSet.of(Units.CL, Units.GR , Units.PIECE);
    }
}
