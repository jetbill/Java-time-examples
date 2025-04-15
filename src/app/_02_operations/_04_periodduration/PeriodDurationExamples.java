package app._02_operations._04_periodduration;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class PeriodDurationExamples {

    public static void main(String[] args) {
        // Periods - para trabajar con fechas (años, meses, días)
        LocalDate startDate = LocalDate.of(2020, 5, 15);
        LocalDate endDate = LocalDate.of(2023, 8, 10);

        Period period = Period.between(startDate, endDate);
        System.out.println("Período entre fechas: " + period);  // Output: P3Y2M26D (3 años, 2 meses, 26 días)

        System.out.println("Años: " + period.getYears());       // Output: 3
        System.out.println("Meses: " + period.getMonths());     // Output: 2
        System.out.println("Días: " + period.getDays());        // Output: 26

        // Crear Period directamente
        Period threeMonths = Period.ofMonths(3);
        Period twoYearsSixMonths = Period.of(2, 6, 0);  // 2 años, 6 meses, 0 días

        // Aplicar Period a una fecha
        LocalDate futureDate = LocalDate.now().plus(twoYearsSixMonths);
        System.out.println("Fecha actual + 2 años y 6 meses: " + futureDate);

        // Duration - para trabajar con tiempo (horas, minutos, segundos, nanosegundos)
        LocalTime startTime = LocalTime.of(9, 0, 0);
        LocalTime endTime = LocalTime.of(17, 30, 0);

        Duration duration = Duration.between(startTime, endTime);
        System.out.println("Duración entre horas: " + duration);  // Output: PT8H30M

        System.out.println("Horas: " + duration.toHours());       // Output: 8
        System.out.println("Minutos: " + duration.toMinutes());   // Output: 510 (total de minutos)
        System.out.println("Segundos: " + duration.getSeconds()); // Output: 30600 (total de segundos)

        // Crear Duration directamente
        Duration tenMinutes = Duration.ofMinutes(10);
        Duration threeHours = Duration.ofHours(3);

        // Aplicar Duration a una hora
        LocalTime laterTime = LocalTime.now().plus(threeHours);
        System.out.println("Hora actual + 3 horas: " + laterTime);

        // ChronoUnit para cálculos precisos de unidades
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        System.out.println("Días entre fechas: " + daysBetween);  // Output: valor exacto en días

        long minutesBetween = ChronoUnit.MINUTES.between(startTime, endTime);
        System.out.println("Minutos entre horas: " + minutesBetween);  // Output: 510

        // Cálculos con LocalDateTime
        LocalDateTime startDateTime = LocalDateTime.of(2023, 1, 1, 9, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2023, 1, 5, 17, 30);

        long hoursBetween = ChronoUnit.HOURS.between(startDateTime, endDateTime);
        System.out.println("Horas entre fechas/horas: " + hoursBetween);

        // Ejemplo práctico: calcular la edad de una persona en años, meses y días
        LocalDate birthDate = LocalDate.of(1990, 5, 15);
        LocalDate currentDate = LocalDate.now();

        Period age = Period.between(birthDate, currentDate);
        System.out.println("Edad: " + age.getYears() + " años, " +
                age.getMonths() + " meses y " +
                age.getDays() + " días");
    }

}
