package Scripts;

import java.math.BigDecimal;

public class IntegerToWordsConvertor {

    private static final String[] units = {
            "", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять",
            "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
            "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
    };

    private static final String[] tens = {
            "", "", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"
    };

    private static final String[] hundreds = {
            "", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"
    };

    private static final String[] thousands = {"тысяча", "тысячи", "тысяч"};

    public String convert(BigDecimal bigDecimal) {


        if (bigDecimal.compareTo(new BigDecimal("0")) < 0 || bigDecimal.compareTo(new BigDecimal("99999.99")) > 0)
            throw new IllegalArgumentException("Число не подходит. Допустимый диапазон от 0 до 99999.99");
        else {
            int rubles = bigDecimal.intValue();
            int kopecks = bigDecimal.remainder(BigDecimal.ONE).multiply(BigDecimal.valueOf(100)).intValue();
            String result = convertRubles(rubles) + " " + getRublesForm(rubles);

            if (kopecks == 0) {
                return result;
            } else {
                return result + " " + convertKopecksPart(kopecks) + " " + getKopecksForm(kopecks);
            }

        }
    }

    private static String convertRubles(int number) {
        if (number == 0) return "ноль";


        StringBuilder result = new StringBuilder();

        if (number >= 1000) {
            int thousands = number / 1000;
            result.append(convertThousands(thousands)).append(" ");
            number %= 1000;
        }

        result.append(convertUnderThousand(number));
        return result.toString().trim();
    }

    private static String convertThousands(int number) {
        if (number == 1) return "одна " + thousands[0];
        if (number == 2) return "две " + thousands[1];

        return convertUnderThousand(number) + " " + getThousandsForm(number);
    }

    private static String convertUnderThousand(int number) {
        if (number == 0) return "";
        StringBuilder result = new StringBuilder();

        result.append(hundreds[number / 100]).append(" ");
        int lastTwoDigits = number % 100;

        if (lastTwoDigits < 20) {
            result.append(units[lastTwoDigits]);
        } else {
            result.append(tens[lastTwoDigits / 10]).append(" ").append(units[lastTwoDigits % 10]);
        }

        return result.toString().trim();
    }

    private static String convertKopecksPart(int number) {
        if (number == 0) return getKopecksForm(number);
        if (number == 1) return "одна";
        if (number == 2) return "две";

        return convertUnderThousand(number);
    }

    private static String getRublesForm(int number) {
        int lastTwoDigits = number % 100;
        if (lastTwoDigits >= 11 && lastTwoDigits <= 19) return "рублей";
        return switch (number % 10) {
            case 1 -> "рубль";
            case 2, 3, 4 -> "рубля";
            default -> "рублей";
        };
    }

    private static String getThousandsForm(int number) {
        int lastTwoDigits = number % 100;
        if (lastTwoDigits >= 11 && lastTwoDigits <= 19) return thousands[2];
        return switch (number % 10) {
            case 1 -> thousands[0];
            case 4 -> thousands[1];
            default -> thousands[2];
        };
    }

    private static String getKopecksForm(int number) {
        if (number == 0) return "";
        int lastTwoDigits = number % 100;
        if (lastTwoDigits >= 11 && lastTwoDigits <= 19) return "копеек";
        return switch (number % 10) {
            case 1 -> "копейка";
            case 2, 3, 4 -> "копейки";
            default -> "копеек";
        };

    }
}
