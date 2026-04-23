package com.apps.quantitymeasurement;

/* ---------------- LENGTH (UNCHANGED FROM UC8) ---------------- */

enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * toFeetFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }
}

class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public QuantityLength convertTo(LengthUnit target) {
        double base = unit.convertToBaseUnit(value);
        double result = target.convertFromBaseUnit(base);
        return new QuantityLength(result, target);
    }

    public QuantityLength add(QuantityLength other, LengthUnit target) {
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        double sum = base1 + base2;
        return new QuantityLength(target.convertFromBaseUnit(sum), target);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < 0.01;
    }
}

/* ---------------- WEIGHT (UC9 NEW PART) ---------------- */

enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toKgFactor;

    WeightUnit(double toKgFactor) {
        this.toKgFactor = toKgFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * toKgFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toKgFactor;
    }
}

class QuantityWeight {
    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public QuantityWeight convertTo(WeightUnit target) {
        double base = unit.convertToBaseUnit(value);
        double result = target.convertFromBaseUnit(base);
        return new QuantityWeight(result, target);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit target) {
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sum = base1 + base2;

        return new QuantityWeight(target.convertFromBaseUnit(sum), target);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof QuantityWeight)) return false;

        QuantityWeight other = (QuantityWeight) obj;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < 0.01;
    }
}

/* ---------------- MAIN ---------------- */

public class QuantityMeasurementApp {
    public static void main(String[] args) {

        // Length
        QuantityLength l1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength l2 = new QuantityLength(12.0, LengthUnit.INCHES);
        System.out.println("Length Add: " + l1.add(l2, LengthUnit.FEET).getValue());

        // Weight
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        System.out.println("Weight Add: " + w1.add(w2, WeightUnit.KILOGRAM).getValue());

        System.out.println("Weight Equals: " + w1.equals(w2));
    }
}