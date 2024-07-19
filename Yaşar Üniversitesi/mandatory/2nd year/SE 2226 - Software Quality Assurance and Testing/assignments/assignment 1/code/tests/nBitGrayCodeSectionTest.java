package assignment1REAL;

import homework1.GrayCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class nBitGrayCodeSectionTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 31, 32})
    public void testGrayCodeConversion(int digitCount) {
        nBitGrayCodeSection gc = new nBitGrayCodeSection(digitCount);
        assertNotNull(gc.initialList);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 33} )
    public void testInvalidInput(int digitCount) {
        nBitGrayCodeSection gc = new nBitGrayCodeSection(digitCount);
        assertNull(gc.finalList);


    }
}