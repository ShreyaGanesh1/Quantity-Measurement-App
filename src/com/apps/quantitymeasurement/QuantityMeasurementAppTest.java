package com.apps.quantitymeasurement;

import org.junit.Test;
import static org.junit.Assert.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Length;
import com.apps.quantitymeasurement.QuantityMeasurementApp.Length.LengthUnit;

public class QuantityMeasurementAppTest {

    // -------- YARD TESTS --------

    @Test
    public void testYardToYard_SameValue() {
        assertTrue(new Length(1.0, LengthUnit.YARDS)
                .equals(new Length(1.0, LengthUnit.YARDS)));
    }

    @Test
    public void testYardToYard_DifferentValue() {
        assertFalse(new Length(1.0, LengthUnit.YARDS)
                .equals(new Length(2.0, LengthUnit.YARDS)));
    }

    @Test
    public void testYardToFeet_Equivalent() {
        assertTrue(new Length(1.0, LengthUnit.YARDS)
                .equals(new Length(3.0, LengthUnit.FEET)));
    }

    @Test
    public void testFeetToYard_Equivalent() {
        assertTrue(new Length(3.0, LengthUnit.FEET)
                .equals(new Length(1.0, LengthUnit.YARDS)));
    }

    @Test
    public void testYardToInches_Equivalent() {
        assertTrue(new Length(1.0, LengthUnit.YARDS)
                .equals(new Length(36.0, LengthUnit.INCHES)));
    }

    @Test
    public void testInchesToYard_Equivalent() {
        assertTrue(new Length(36.0, LengthUnit.INCHES)
                .equals(new Length(1.0, LengthUnit.YARDS)));
    }

    @Test
    public void testYardToFeet_NotEqual() {
        assertFalse(new Length(1.0, LengthUnit.YARDS)
                .equals(new Length(2.0, LengthUnit.FEET)));
    }

    // -------- CENTIMETER TESTS --------

    @Test
    public void testCmToCm_SameValue() {
        assertTrue(new Length(2.0, LengthUnit.CENTIMETERS)
                .equals(new Length(2.0, LengthUnit.CENTIMETERS)));
    }

    @Test
    public void testCmToInches_Equivalent() {
        assertTrue(new Length(1.0, LengthUnit.CENTIMETERS)
                .equals(new Length(0.393701, LengthUnit.INCHES)));
    }

    @Test
    public void testCmToFeet_NotEqual() {
        assertFalse(new Length(1.0, LengthUnit.CENTIMETERS)
                .equals(new Length(1.0, LengthUnit.FEET)));
    }

    // -------- GENERIC TESTS --------

    @Test
    public void testSameReference() {
        Length l = new Length(1.0, LengthUnit.YARDS);
        assertTrue(l.equals(l));
    }

    @Test
    public void testNullComparison() {
        assertFalse(new Length(1.0, LengthUnit.YARDS).equals(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUnit() {
        new Length(1.0, null);
    }

    @Test
    public void testTransitiveProperty() {
        Length a = new Length(1.0, LengthUnit.YARDS);
        Length b = new Length(3.0, LengthUnit.FEET);
        Length c = new Length(36.0, LengthUnit.INCHES);

        assertTrue(a.equals(b) && b.equals(c) && a.equals(c));
    }

    @Test
    public void testComplexScenario() {
        assertTrue(new Length(2.0, LengthUnit.YARDS)
                .equals(new Length(6.0, LengthUnit.FEET)));
    }
}