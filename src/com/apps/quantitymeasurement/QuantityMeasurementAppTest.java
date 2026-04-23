package com.apps.quantitymeasurement;

import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testConvert_FeetToInches() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength result = q.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), 0.01);
    }

    @Test
    public void testConvert_InchesToFeet() {
        QuantityLength q = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = q.convertTo(LengthUnit.FEET);

        assertEquals(1.0, result.getValue(), 0.01);
    }

    @Test
    public void testEquality_CrossUnit() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        assertTrue(a.equals(b));
    }

    @Test
    public void testAddition_Refactored() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = a.add(b, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    public void testAddition_TargetUnit_Yards() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result = a.add(b, LengthUnit.YARDS);

        assertEquals(0.667, result.getValue(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUnit() {
        new QuantityLength(1.0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidValue() {
        new QuantityLength(Double.NaN, LengthUnit.FEET);
    }

    @Test
    public void testZeroAddition() {
        QuantityLength a = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(0.0, LengthUnit.INCHES);

        QuantityLength result = a.add(b, LengthUnit.FEET);

        assertEquals(5.0, result.getValue(), 0.01);
    }
}