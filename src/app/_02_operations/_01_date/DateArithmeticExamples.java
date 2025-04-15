package app._02_operations._01_date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;


public class DateArithmeticExamples {
    public static void main(String[] args) {


    LocalDate today = LocalDate.now();
        System.out.println("Fecha actual: " + today);

    // Sumar días, meses, años
    LocalDate nextWeek = today.plusDays(7);
        System.out.println("Próxima semana: " + nextWeek);

    LocalDate nextMonth = today.plusMonths(1);
        System.out.println("Próximo mes: " + nextMonth);

    LocalDate nextYear = today.plusYears(1);
        System.out.println("Próximo año: " + nextYear);

    // Restar días, meses, años
    LocalDate lastWeek = today.minusDays(7);
        System.out.println("Semana pasada: " + lastWeek);

    LocalDate lastMonth = today.minusMonths(1);
        System.out.println("Mes pasado: " + lastMonth);

    LocalDate lastYear = today.minusYears(1);
        System.out.println("Año pasado: " + lastYear);

    // Operaciones encadenadas (los objetos de fecha son inmutables)
    LocalDate futureDate = today
            .plusYears(2)
            .plusMonths(3)
            .plusDays(10);
        System.out.println("Fecha futura con operaciones encadenadas: " + futureDate);

    // Modificar componentes específicos
    LocalDate withDifferentDay = today.withDayOfMonth(1);
        System.out.println("Primer día del mes actual: " + withDifferentDay);

    LocalDate withDifferentMonth = today.withMonth(12);
        System.out.println("Mismo día en diciembre: " + withDifferentMonth);

    LocalDate withDifferentYear = today.withYear(2030);
        System.out.println("Mismo día en 2030: " + withDifferentYear);

    // Uso de TemporalAdjusters para operaciones más complejas
    LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("Último día del mes actual: " + lastDayOfMonth);

    LocalDate nextFriday = today.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println("Próximo viernes: " + nextFriday);

    LocalDate firstDayOfNextMonth = today.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println("Primer día del próximo mes: " + firstDayOfNextMonth);

    LocalDate thirdFridayOfNextMonth = today
            .with(TemporalAdjusters.firstDayOfNextMonth())
            .with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY))
            .plusWeeks(2);
        System.out.println("Tercer viernes del próximo mes: " + thirdFridayOfNextMonth);
  }

}
