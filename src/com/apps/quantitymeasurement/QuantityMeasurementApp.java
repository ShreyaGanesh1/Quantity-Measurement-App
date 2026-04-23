package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static class Length {

        private final double value;
        private final LengthUnit unit;

        public enum LengthUnit {
            FEET(12.0),          // 1 ft = 12 in
            INCHES(1.0),         // base unit
            YARDS(36.0),         // 1 yard = 36 in
            CENTIMETERS(0.393701); // 1 cm = 0.393701 in

            private final double conversionFactor;

            LengthUnit(double conversionFactor) {
                this.conversionFactor = conversionFactor;
            }

            public double getConversionFactor() {
                return conversionFactor;
            }
        }

        // Constructor
        public Length(double value, LengthUnit unit) {
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        // Convert everything to inches (base unit)
        private double toBaseUnit() {
            return this.value * this.unit.getConversionFactor();
        }

        private boolean compare(Length other) {
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;

            Length other = (Length) obj;
            return compare(other);
        }
    }

    // Main demo
    public static void main(String[] args) {

        Length l1 = new Length(1.0, Length.LengthUnit.YARDS);
        Length l2 = new Length(3.0, Length.LengthUnit.FEET);

        Length l3 = new Length(1.0, Length.LengthUnit.CENTIMETERS);
        Length l4 = new Length(0.393701, Length.LengthUnit.INCHES);

        System.out.println("1 yard == 3 feet? " + l1.equals(l2));
        System.out.println("1 cm == 0.393701 inch? " + l3.equals(l4));
    }
}