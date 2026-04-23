package com.apps.quantitymeasurement;

import org.junit.Test;
import static org.junit.Assert.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Length;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Length.LengthUnit;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testFeetToInches() {
        assertEquals(12.0,
                Length.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testInchesToFeet() {
        assertEquals(2.0,
                Length.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET),
                EPSILON);
    }

    @Test
    public void testYardsToInches() {
        assertEquals(36.0,
                Length.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testInchesToYards() {
        assertEquals(2.0,
                Length.convert(72.0, LengthUnit.INCHES, LengthUnit.YARDS),
                EPSILON);
    }

    @Test
    public void testCmToInches() {
        assertEquals(1.0,
                Length.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES),
                1e-3);
    }

    @Test
    public void testFeetToYards() {
        assertEquals(2.0,
                Length.convert(6.0, LengthUnit.FEET, LengthUnit.YARDS),
                EPSILON);
    }

    @Test
    public void testZeroValue() {
        assertEquals(0.0,
                Length.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testNegativeValue() {
        assertEquals(-12.0,
                Length.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPSILON);
    }

    @Test
    public void testSameUnit() {
        assertEquals(5.0,
                Length.convert(5.0, LengthUnit.FEET, LengthUnit.FEET),
                EPSILON);
    }

    @Test
    public void testRoundTrip() {
        double value = 5.0;

        double converted = Length.convert(value, LengthUnit.FEET, LengthUnit.INCHES);
        double back = Length.convert(converted, LengthUnit.INCHES, LengthUnit.FEET);

        assertEquals(value, back, EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUnit() {
        Length.convert(1.0, null, LengthUnit.FEET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidValue() {
        Length.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES);
    }
}