package app._02_operations._06_recurringevents;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.List;


public class RecurringEventExamples {

    public static void main(String[] args) {
        // Método para calcular el tercer viernes de cada mes durante un año
        List<LocalDate> thirdFridays = calculateThirdFridayOfMonths(LocalDate.now(), 12);

        System.out.println("Tercer viernes de cada mes durante un año:");
        for (LocalDate date : thirdFridays) {
            System.out.println(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }

        // Calcular días laborables (excluyendo fines de semana)
        LocalDate startWork = LocalDate.of(2025, 3, 1);
        LocalDate endWork = LocalDate.of(2025, 3, 31);

        long workdays = countWorkdays(startWork, endWork);
        System.out.println("Días laborables en marzo 2025: " + workdays);

        // Calcular próximos días festivos (suponiendo algunos días festivos fijos)
        List<LocalDate> holidays2025 = getHolidays(2025);

        System.out.println("Días festivos 2025:");
        for (LocalDate holiday : holidays2025) {
            System.out.println(holiday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }

        // Encontrar la próxima fecha de pago (suponiendo que los pagos son el día 15 y último de cada mes)
        LocalDate nextPayday = getNextPayday(LocalDate.now());
        System.out.println("Próxima fecha de pago: " +
                nextPayday.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    /**
     * Calcula el tercer viernes de cada mes durante un número específico de meses
     * @param startDate Fecha de inicio
     * @param monthCount Número de meses a calcular
     * @return Lista de fechas
     */
    public static List<LocalDate> calculateThirdFridayOfMonths(LocalDate startDate, int monthCount) {
        List<LocalDate> thirdFridays = new ArrayList<>();

        for (int i = 0; i < monthCount; i++) {
            LocalDate firstDayOfMonth = startDate.plusMonths(i).withDayOfMonth(1);

            // Obtener el primer viernes del mes
            LocalDate firstFriday = firstDayOfMonth.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));

            // El tercer viernes es el primer viernes + 2 semanas
            LocalDate thirdFriday = firstFriday.plusWeeks(2);
            thirdFridays.add(thirdFriday);
        }

        return thirdFridays;
    }

    /**
     * Cuenta los días laborables (lunes a viernes) entre dos fechas
     * @param startDate Fecha de inicio
     * @param endDate Fecha de fin
     * @return Número de días laborables
     */
    public static long countWorkdays(LocalDate startDate, LocalDate endDate) {
        long days = 0;
        LocalDate date = startDate;

        while (!date.isAfter(endDate)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (!(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)) {
                days++;
            }
            date = date.plusDays(1);
        }

        return days;
    }

    /**
     * Devuelve una lista de días festivos para un año específico
     * Ejemplo simplificado con algunos días festivos comunes en España
     * @param year Año
     * @return Lista de fechas festivas
     */
    public static List<LocalDate> getHolidays(int year) {
        List<LocalDate> holidays = new ArrayList<>();

        // Días festivos fijos
        holidays.add(LocalDate.of(year, 1, 1));   // Año Nuevo
        holidays.add(LocalDate.of(year, 1, 6));   // Epifanía
        holidays.add(LocalDate.of(year, 5, 1));   // Día del Trabajo
        holidays.add(LocalDate.of(year, 8, 15));  // Asunción
        holidays.add(LocalDate.of(year, 10, 12)); // Fiesta Nacional
        holidays.add(LocalDate.of(year, 11, 1));  // Todos los Santos
        holidays.add(LocalDate.of(year, 12, 6));  // Día de la Constitución
        holidays.add(LocalDate.of(year, 12, 8));  // Inmaculada Concepción
        holidays.add(LocalDate.of(year, 12, 25)); // Navidad

        // Calcular Semana Santa (simplificación - normalmente requiere algoritmos específicos)
        // Este es un ejemplo aproximado para el cálculo del Viernes Santo
        // Un cálculo real requeriría el algoritmo de cómputo eclesiástico
        Month easterMonth = Month.APRIL; // Aproximación para 2025
        int easterDay = 18;              // Aproximación para 2025

        holidays.add(LocalDate.of(year, easterMonth, easterDay)); // Viernes Santo

        return holidays;
    }

    /**
     * Obtiene la próxima fecha de pago (asumiendo pagos el día 15 y último día del mes)
     * @param fromDate Fecha desde la cual calcular
     * @return Próxima fecha de pago
     */
    public static LocalDate getNextPayday(LocalDate fromDate) {
        int dayOfMonth = fromDate.getDayOfMonth();
        LocalDate lastDayOfMonth = fromDate.with(TemporalAdjusters.lastDayOfMonth());

        // Si estamos entre el día 1 y 14, el próximo pago es el 15
        if (dayOfMonth < 15) {
            return fromDate.withDayOfMonth(15);
        }
        // Si estamos entre el 15 y el último día exclusive, el próximo pago es fin de mes
        else if (dayOfMonth < lastDayOfMonth.getDayOfMonth()) {
            return lastDayOfMonth;
        }
        // Si estamos en el último día del mes, el próximo pago es el 15 del mes siguiente
        else {
            return fromDate.plusMonths(1).withDayOfMonth(15);
        }
    }

}
