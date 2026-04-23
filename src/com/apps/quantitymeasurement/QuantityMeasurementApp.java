package com.apps.quantitymeasurement;

// ✅ Standalone Enum (UC8 change)
enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    // convert to base (feet)
    public double convertToBaseUnit(double value) {
        return value * toFeetFactor;
    }

    // convert from base (feet)
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }
}

// ✅ QuantityLength now SIMPLE (delegates conversion)
class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    // convert method (UC5 style but using enum)
    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit null");

        double base = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(base);

        return new QuantityLength(result, targetUnit);
    }

    // UC7 style addition (explicit target)
    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(sumBase);

        return new QuantityLength(result, targetUnit);
    }

    // equality (UC1–UC4 behavior using base unit)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < 0.01;
    }
}

// ✅ MAIN
public class QuantityMeasurementApp {
    public static void main(String[] args) {

        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println("Convert: " + a.convertTo(LengthUnit.INCHES).getValue());
        System.out.println("Add (feet): " + a.add(b, LengthUnit.FEET).getValue());
        System.out.println("Add (yards): " + a.add(b, LengthUnit.YARDS).getValue());
        System.out.println("Equals: " + a.equals(b));
    }
}