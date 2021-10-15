package edu.epam.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringUtilsTest {

    @Test
    public void isPositiveNumberIntegerTestTrue(){
        String number = "5";
        StringUtils stringUtils = new StringUtils();
        assertTrue(stringUtils.isPositiveNumber(number));
    }

    @Test
    public void isPositiveNumberIntegerTestFalse(){
        String number = "-7";
        StringUtils stringUtils = new StringUtils();
        assertFalse(stringUtils.isPositiveNumber(number));
    }
}
