import Scripts.InsuranceReporter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        BigDecimal bigDecimal = new BigDecimal("100000000");

        Scripts.IntegerToWordsConvertor convertor = new Scripts.IntegerToWordsConvertor();
        System.out.println(convertor.convert(bigDecimal));


        LocalDateTime time = LocalDateTime.of(2024, 8, 31, 17, 8);

        System.out.println(
        InsuranceReporter.report(time)
        );

    }
}