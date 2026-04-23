package com.apps.quantitymeasurement;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    /* -------- WEIGHT TESTS -------- */

    @Test
    public void testEquality_KgToGram() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);

        assertTrue(a.equals(b));
    }

    @Test
    public void testEquality_KgToPound() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(2.20462, WeightUnit.POUND);

        assertTrue(a.equals(b));
    }

    @Test
    public void testConversion_KgToGram() {
        QuantityWeight q = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight result = q.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), 0.01);
    }

    @Test
    public void testConversion_PoundToKg() {
        QuantityWeight q = new QuantityWeight(2.20462, WeightUnit.POUND);

        QuantityWeight result = q.convertTo(WeightUnit.KILOGRAM);

        assertEquals(1.0, result.getValue(), 0.01);
    }

    @Test
    public void testAddition_KgPlusGram() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = a.add(b, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    public void testAddition_PoundPlusKg() {
        QuantityWeight a = new QuantityWeight(2.20462, WeightUnit.POUND);
        QuantityWeight b = new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        QuantityWeight result = a.add(b, WeightUnit.POUND);

        assertEquals(4.40924, result.getValue(), 0.01);
    }

    @Test
    public void testAddition_ExplicitTarget_Gram() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result = a.add(b, WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), 0.01);
    }

    @Test
    public void testAddition_WithZero() {
        QuantityWeight a = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(0.0, WeightUnit.GRAM);

        QuantityWeight result = a.add(b, WeightUnit.KILOGRAM);

        assertEquals(5.0, result.getValue(), 0.01);
    }

    @Test
    public void testAddition_Negative() {
        QuantityWeight a = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(-2.0, WeightUnit.KILOGRAM);

        QuantityWeight result = a.add(b, WeightUnit.KILOGRAM);

        assertEquals(3.0, result.getValue(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidValue() {
        new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM);
    }
}