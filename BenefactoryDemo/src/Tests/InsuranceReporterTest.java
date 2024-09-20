package Tests;

import Scripts.InsuranceReporter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.sql.Date;

public class InsuranceReporterTest {

    @Test
    public void testReport_Before10th() {
        LocalDateTime testDate = LocalDateTime.of(2024, 9, 10, 12, 39);
        Date expectedDate = Date.valueOf("2024-09-10");
        assertEquals(expectedDate, InsuranceReporter.report(testDate));
    }

    @Test
    public void testReport_Between10thAnd20th() {
        LocalDateTime testDate = LocalDateTime.of(2025, 1, 15, 12, 0);
        Date expectedDate = Date.valueOf("2025-01-20");
        assertEquals(expectedDate, InsuranceReporter.report(testDate));
    }

    @Test
    public void testReport_After20th() {
        LocalDateTime testDate = LocalDateTime.of(2025, 1, 25, 12, 0);
        Date expectedDate = Date.valueOf("2025-01-31");
        assertEquals(expectedDate, InsuranceReporter.report(testDate));
    }

    @Test
    public void testReport_After18Hours() {
        LocalDateTime testDate = LocalDateTime.of(2025, 1, 15, 19, 0);
        Date expectedDate = Date.valueOf("2025-01-20");
        assertEquals(expectedDate, InsuranceReporter.report(testDate));
    }

    @Test
    public void testReport_WeekendAdjustment() {
        LocalDateTime testDate = LocalDateTime.of(2025, 1, 10, 12, 0);
        Date expectedDate = Date.valueOf("2024-12-31");
        assertEquals(expectedDate, InsuranceReporter.report(testDate));
    }
}
