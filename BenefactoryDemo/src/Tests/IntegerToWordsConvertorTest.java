package Tests;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import Scripts.IntegerToWordsConvertor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntegerToWordsConvertorTest {

    private final IntegerToWordsConvertor convertor = new IntegerToWordsConvertor();

    @Test
    void testConvertWholeRubles() {
        assertEquals("один рубль", convertor.convert(new BigDecimal("1.00")));
        assertEquals("два рубля", convertor.convert(new BigDecimal("2.00")));
        assertEquals("пять рублей", convertor.convert(new BigDecimal("5.00")));
        assertEquals("одна тысяча рублей", convertor.convert(new BigDecimal("1000.00")));
        assertEquals("две тысячи пятьсот сорок один рубль", convertor.convert(new BigDecimal("2541.00")));
    }

    @Test
    void testConvertRublesAndKopecks() {
        assertEquals("один рубль одна копейка", convertor.convert(new BigDecimal("1.01")));
        assertEquals("два рубля две копейки", convertor.convert(new BigDecimal("2.02")));
        assertEquals("пять рублей пять копеек", convertor.convert(new BigDecimal("5.05")));
        assertEquals("десять рублей двадцать копеек", convertor.convert(new BigDecimal("10.20")));
        assertEquals("одна тысяча пятьдесят рублей сорок копеек", convertor.convert(new BigDecimal("1050.40")));
    }

    @Test
    void testConvertLargeNumbers() {
        assertEquals("девяносто девять тысяч девятьсот девяносто девять рублей девяносто девять копеек",
                convertor.convert(new BigDecimal("99999.99")));
    }

    @Test
    void testConvertZero() {
        assertEquals("ноль рублей", convertor.convert(new BigDecimal("0.00")));
    }

    @Test
    void testInvalidNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> convertor.convert(new BigDecimal("-1.00")));
    }

    @Test
    void testInvalidLargeNumber() {
        assertThrows(IllegalArgumentException.class, () -> convertor.convert(new BigDecimal("100000.00")));
    }
}

