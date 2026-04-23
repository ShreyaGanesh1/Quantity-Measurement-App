package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // Generic Length class (replaces Feet & Inches)
    public static class Length {

        private final double value;
        private final LengthUnit unit;

        // Enum for units
        public enum LengthUnit {
            FEET(12.0),     // 1 foot = 12 inches
            INCHES(1.0);    // base unit

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

        // Convert to base unit (inches)
        private double toBaseUnit() {
            return this.value * this.unit.getConversionFactor();
        }

        // Compare logic
        private boolean compare(Length other) {
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        // Override equals
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;

            Length other = (Length) obj;
            return compare(other);
        }
    }

    // Main method
    public static void main(String[] args) {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println("Are equal? " + l1.equals(l2)); // true
    }
}