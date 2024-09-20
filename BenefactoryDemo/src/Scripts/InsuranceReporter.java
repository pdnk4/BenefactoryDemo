package Scripts;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class InsuranceReporter {

    private static final Set<LocalDate> holidays = new HashSet<>();

    static {
        holidays.add(LocalDate.of(2025, 1, 1));
        holidays.add(LocalDate.of(2025, 1, 2));
        holidays.add(LocalDate.of(2025, 1, 3));
        holidays.add(LocalDate.of(2025, 1, 4));
        holidays.add(LocalDate.of(2025, 1, 5));
        holidays.add(LocalDate.of(2025, 1, 6));
        holidays.add(LocalDate.of(2025, 1, 7));
        holidays.add(LocalDate.of(2025, 1, 8));
        holidays.add(LocalDate.of(2025, 1, 9));
        holidays.add(LocalDate.of(2025, 1, 10));
        holidays.add(LocalDate.of(2025, 5, 9));
        holidays.add(LocalDate.of(2025, 5, 1));
    }

    public static java.sql.Date report() {
        LocalDateTime now = LocalDateTime.now();
        return report(now);
    }

    public static java.sql.Date report(LocalDateTime time) {
        LocalDate currentDate = time.toLocalDate();

        // Увеличиваем день на 1, если время после 18:00
        if (time.getHour() >= 18) {
            currentDate = currentDate.plusDays(1);
        }

        // Определение ближайшей даты отправки (1, 10, 20 числа)
        int day = currentDate.getDayOfMonth();
        if (day <= 1) {
            currentDate = check(currentDate.withDayOfMonth(1));
        } else if (day <= 10) {
            currentDate = check(currentDate.withDayOfMonth(10));
        } else if (day <= 20) {
            currentDate = check(currentDate.withDayOfMonth(20));
        } else {
            // Если день больше 20-го, переходим на 1-е число следующего месяца
            currentDate = check(currentDate.plusMonths(1).withDayOfMonth(1));
        }

        return Date.valueOf(currentDate);
    }

    public static LocalDate check(LocalDate date) {
        // Проверка на выходные и корректировка
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
            date = date.minusDays(1);
        } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            date = date.minusDays(2);
        }

        // Проверка на праздники и корректировка
        while (holidays.contains(date)) {
            date = date.minusDays(1);
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                date = date.minusDays(1);
            } else if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                date = date.minusDays(2);
            }
        }

        return date;
    }
}
